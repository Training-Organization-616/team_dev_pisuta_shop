package la.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

			if (Objects.isNull(action) || action.isEmpty()) {

				gotoPage(request, response, "listing.jsp");

			} else if (action.equals("add")) {
				//入力情報の取得
				String name = request.getParameter("name");
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				int condId = Integer.parseInt(request.getParameter("conditionId"));
				String comment = request.getParameter("comment");
				int price = 0;

				if (name == null || name.length() == 0) {
					request.setAttribute("message", "商品名は必須です");
					gotoPage(request, response, "listing.jsp");

				} else if (name.length() >= 100) {
					request.setAttribute("message", "商品名は100文字以下にしてください");
					gotoPage(request, response, "listing.jsp");

				} else if (action.equals("edit")) {

					// 商品　情報変更画面への遷移
					gotoPage(request, response, "/editItem.jsp");

					//商品　情報の更新
					int id = Integer.parseInt(request.getParameter("id"));
					//	String name = request.getParameter("name");
					//	int categoryId = Integer.parseInt(request.getParameter("categoryId"));
					int sellerId = Integer.parseInt(request.getParameter("sellerId"));
					//	int price = Integer.parseInt(request.getParameter("price"));
					//	int condId = Integer.parseInt(request.getParameter("condId"));
					//	String comment = request.getParameter("comment");
					String fileName = request.getParameter("fileName");

					//入力チェック
					if (Objects.isNull(id) || id == 0 || name == null || name.length() == 0
							|| Objects.isNull(categoryId) || categoryId == 0 || Objects.isNull(sellerId)
							|| sellerId == 0
							|| Objects.isNull(price) || price == 0 || Objects.isNull(condId) || condId == 0
							|| comment == null || comment.length() == 0 || fileName == null || fileName.length() == 0) {
						request.setAttribute("message", "全ての項目を入力してください");
						gotoPage(request, response, "/editItem.jsp");
					} else if (name.length() > 100) {
						request.setAttribute("name", "商品名は100文字以下にしてください");
						gotoPage(request, response, "/editUser.jsp");
					} else {
						ItemsDAO dao = new ItemsDAO();
						//dao.updateItem(id, name, categoryId, sellerId, price, condId, comment, fileName);

						gotoPage(request, response, "/profile.jsp");
					} //商品の削除処理
				} else if (action.equals("delere")) {
					// セッションから商品IDの取得
					HttpSession session = request.getSession(false);
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

			} else {
				try {
					int price = Integer.parseInt(request.getParameter("price"));
					int id = Integer.parseInt(request.getParameter("id"));
					String name = request.getParameter("name");
					int categoryId = Integer.parseInt(request.getParameter("categoryId"));
					int sellerId = Integer.parseInt(request.getParameter("sellerId"));
					int price1 = Integer.parseInt(request.getParameter("price"));
					int condId = Integer.parseInt(request.getParameter("condId"));
					String comment = request.getParameter("comment");
					String fileName = request.getParameter("fileName");

					//ログインユーザーの取得
					HttpSession session = request.getSession();
					UserBean user = (UserBean) session.getAttribute("user");
					//出品処理
					itemsDao.addItem(name, categoryId, user.getId(), price1, condId, comment);

					gotoPage(request, response, "profile.jsp");

				} catch (Exception e) {
					request.setAttribute("message", "価格は必須です");
					gotoPage(request, response, "listing.jsp");
					return;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
		// TODO: handle exception
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
