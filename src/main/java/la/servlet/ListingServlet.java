package la.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import la.bean.CategoryBean;
import la.bean.ConditionBean;
import la.bean.ItemBean;
import la.bean.UserBean;
import la.dao.CategoriesDAO;
import la.dao.ConditionsDAO;
import la.dao.ItemsDAO;

/**
 * Servlet implementation class ListingServlet
 */
@WebServlet("/ListingServlet")
@MultipartConfig
public class ListingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			//文字エンコードセット
			request.setCharacterEncoding("UTF-8");
			//アクションパラメータ
			String action = request.getParameter("action");
			//DAOインスタンス
			CategoriesDAO categoriesDao = new CategoriesDAO();
			ConditionsDAO conditionsDao = new ConditionsDAO();
			ItemsDAO itemsDao = new ItemsDAO();

			// カテゴリーと状態の一覧の取得
			HttpSession sessionDao = request.getSession();
			List<CategoryBean> categories = categoriesDao.findAll();
			List<ConditionBean> conditions = conditionsDao.findAll();

			// セッションにカテゴリーと状態の一覧をセット
			sessionDao.setAttribute("categories", categories);
			sessionDao.setAttribute("conditions", conditions);

			//ログインユーザーの取得
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("user");

			//未ログインでアクセス時
			if (Objects.isNull(user)) {
				response.sendRedirect("/team_dev_pisuta_shop/ItemServlet");
				return;
			}

			//Actionパラメータ
			if (Objects.isNull(action) || action.isEmpty()) {

				gotoPage(request, response, "listing.jsp");

			} else if (action.equals("add")) {
				//入力情報の取得
				//出品商品名
				String name = request.getParameter("name");
				//指定カテゴリーID
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				//商品の状態ID
				int condId = Integer.parseInt(request.getParameter("conditionId"));
				//出品者コメント
				String comment = request.getParameter("comment");
				//価格
				String strPrice = request.getParameter("price");
				int price = 0;

				//入力チェック
				//商品名が無い場合
				if (name == null || name.length() == 0) {
					request.setAttribute("message", "商品名は必須です");
					gotoPage(request, response, "/listing.jsp");
					return;
				} else if (Objects.isNull(strPrice) || strPrice.length() == 0) {
					request.setAttribute("message", "価格は必須です");
					gotoPage(request, response, "/listing.jsp");
					return;
				} else if (name.length() > 100) {
					//商品名が100文字以上の場合
					request.setAttribute("name", "商品名は100文字以下にしてください");
					gotoPage(request, response, "/listing.jsp");
					return;
				} else {
					try {
						price = Integer.parseInt(strPrice);
						if (price < 1) {
							throw new Exception();
						}
					} catch (Exception e) {
						request.setAttribute("message", "正しい価格を入力してください");
						gotoPage(request, response, "/listing.jsp");
						return;
					}

					try {
						// 画像ファイルの受け取り
						Part part = request.getPart("product_image");

						// ファイル名を取得
						String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

						if (!fileName.matches(".*\\.png$")) {
							request.setAttribute("message", "画像形式は.pngである必要があります");
							gotoPage(request, response, "/listing.jsp");
							return;
						}

						// アップロードするフォルダ
						String path = getServletContext().getRealPath("/upload");

						// 書き込み
						try {
							part.write(Paths.get(path, fileName).toString());
						} catch (IOException e) {
							request.setAttribute("message", "ファイルの書き込みに失敗しました");
							gotoPage(request, response, "/listing.jsp");
							return;
						}

						// データベース登録
						try {
							itemsDao.addItem(name, categoryId, user.getId(), price, condId, comment, fileName);
						} catch (Exception e) {
							request.setAttribute("message", "データベース登録に失敗しました");
							gotoPage(request, response, "/listing.jsp");
							return;
						}

						// 登録した商品のidを検索
						int id = itemsDao.getIdbyItem(user.getId(), name);

						// ファイル名の変更
						File currentFile = new File(Paths.get(path, fileName).toString());
						File newFileName = new File(Paths.get(path, "pict" + id + ".png").toString());

						boolean success = currentFile.renameTo(newFileName);
						if (!success) {
							request.setAttribute("message", "画像アップロードに失敗しました");
							gotoPage(request, response, "/listing.jsp");
							return;
						}

						String file = newFileName.getName();
						// 変更後データベースの更新
						itemsDao.updateItemFileNameById(id, file);

						response.sendRedirect("/team_dev_pisuta_shop/UserServlet");
						return;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						request.setAttribute("message", "内部エラーが発生しました。");
						gotoPage(request, response, "/errInternal.jsp");
						return;
					}
				}

			} else if (action.equals("edit")) {
				int itemId = Integer.parseInt(request.getParameter("itemId"));

				ItemBean item = itemsDao.searchItemById(itemId);
				//商品情報をセット
				request.setAttribute("item", item);
				// 商品情報変更画面への遷移
				gotoPage(request, response, "/editItem.jsp");

			} else if (action.equals("update")) {
				ItemsDAO dao = new ItemsDAO();

				//商品情報の更新
				int id = Integer.parseInt(request.getParameter("itemId"));
				String name = request.getParameter("name");
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				String strPrice = request.getParameter("price");
				int price = 0;
				int condId = Integer.parseInt(request.getParameter("conditionId"));
				String comment = request.getParameter("comment");

				ItemBean item = dao.searchItemById(id);
				request.setAttribute("item", item);

				//入力チェック
				//商品名が無い場合
				if (name == null || name.length() == 0) {
					request.setAttribute("message", "商品名は必須です");
					gotoPage(request, response, "/editItem.jsp");
					return;
				} else if (Objects.isNull(strPrice) || strPrice.length() == 0) {
					request.setAttribute("message", "価格は必須です");
					gotoPage(request, response, "/editItem.jsp");
					return;
				} else if (name.length() > 100) {
					//商品名が100文字以上の場合
					request.setAttribute("message", "商品名は100文字以下にしてください");
					gotoPage(request, response, "/editItem.jsp");
					return;
				} else {
					try {
						price = Integer.parseInt(strPrice);
						if (price < 1) {
							throw new Exception();
						}
					} catch (Exception e) {
						request.setAttribute("message", "正しい価格を入力してください");
						gotoPage(request, response, "/editItem.jsp");
						return;
					}
					dao.updateItem(id, name, categoryId, price, condId, comment);

					int userId = user.getId();
					List<ItemBean> list = dao.findItemByUserId(userId);

					request.setAttribute("items", list);
					gotoPage(request, response, "/profile.jsp");
				}

				//商品の削除処理
			} else if (action.equals("delete")) {
				int itemId = Integer.parseInt(request.getParameter("itemId"));

				ItemsDAO dao = new ItemsDAO();
				dao.deleteItemById(itemId);

				int userId = user.getId();
				List<ItemBean> list = dao.findItemByUserId(userId);

				request.setAttribute("items", list);

				gotoPage(request, response, "/profile.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
