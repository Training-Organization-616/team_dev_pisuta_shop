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
					//商品名が１００文字以上の場合
					request.setAttribute("name", "商品名は100文字以下にしてください");
					gotoPage(request, response, "/listing.jsp");
					return;
				} else {
					price = Integer.parseInt(strPrice);
				}

				//画像ファイルの受け取り
				Part part = request.getPart("product_image");
				//ファイル名を取得
				String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				//アップロードするフォルダ
				String path = getServletContext().getRealPath("/upload/");

				//書き込み
				part.write(path + File.separator + fileName);

				//データベース登録
				itemsDao.addItem(name, categoryId, user.getId(), price, condId, comment, fileName);

				//登録した商品のidを検索
				int id = itemsDao.getIdbyItem(user.getId(), fileName);

				//ファイル名の変更
				//現在のファイルのパス
				File currentFile = new File(path + File.separator + fileName);
				//新しいファイル名
				File newFileName = new File(path + File.separator + "pict" + id + ".png");

				//ファイル名の変更
				boolean success = currentFile.renameTo(newFileName);

				if (!success) {
					request.setAttribute("message", "内部エラーが発生しました。");
					gotoPage(request, response, "/errInternal.jsp");
					return;
				}

				//変更後データベースの更新
				itemsDao.updateItemFileNameById(id, newFileName.getName());

				response.sendRedirect("/team_dev_pisuta_shop/UserServlet");
				return;

			} else if (action.equals("edit")) {

				// 商品情報変更画面への遷移
				gotoPage(request, response, "/editItem.jsp");

				//商品情報の更新
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				String strPrice = request.getParameter("price");
				int condId = Integer.parseInt(request.getParameter("condId"));
				String comment = request.getParameter("comment");

				//入力チェック
				if (name == null || name.length() == 0
						|| Objects.isNull(categoryId) || categoryId == 0
						|| Objects.isNull(strPrice) || Objects.isNull(condId) || condId == 0) {
					request.setAttribute("message", "全ての項目を入力してください");
					gotoPage(request, response, "/editItem.jsp");
				} else if (name.length() > 100) {
					request.setAttribute("name", "商品名は100文字以下にしてください");
					gotoPage(request, response, "/editUser.jsp");
				} else {
					int price = Integer.parseInt(strPrice);
					ItemsDAO dao = new ItemsDAO();
					dao.updateItem(id, name, categoryId, price, condId, comment);

					gotoPage(request, response, "/profile.jsp");
				} //商品の削除処理
			} else if (action.equals("delete")) {
				// セッションから商品IDの取得
				session = request.getSession(false);
				ItemBean item = (ItemBean) session.getAttribute("item");
				int id = item.getId();

				ItemsDAO dao = new ItemsDAO();
				dao.deleteItemById(id);

				ItemsDAO itemsDao1 = new ItemsDAO();
				itemsDao1.deleteItemById(id);

				// セッションの削除
				if (session != null) {
					session.removeAttribute("item");
				}
				response.sendRedirect("/team_dev_pisuta_shop/ItemServlet");
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
