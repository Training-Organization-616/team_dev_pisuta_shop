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

			if (Objects.isNull(action) || action.isEmpty()) {

				List<CategoryBean> categories = categoriesDao.findAll();
				List<ConditionBean> conditions = conditionsDao.findAll();

				request.setAttribute("categories", categories);
				request.setAttribute("conditions", conditions);

				gotoPage(request, response, "listing.jsp");

			} else if (action.equals("add")) {
				//入力情報の取得
				String name = request.getParameter("name");
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				int condId = Integer.parseInt(request.getParameter("condId"));
				String comment = request.getParameter("comment");
				int price = 0;

				try {
					price = Integer.parseInt(request.getParameter("price"));

					if (name == null || name.length() == 0) {
						request.setAttribute("message", "商品名は必須です");
						gotoPage(request, response, "listing.jsp");

					} else if (name.length() >= 100) {
						request.setAttribute("message", "商品名は100文字以下にしてください");
						gotoPage(request, response, "listing.jsp");

					} else {
						//ログインユーザーの取得
						HttpSession session = request.getSession();
						UserBean user = (UserBean) session.getAttribute("user");
						//出品処理
						itemsDao.addItem(name, categoryId, user.getId(), price, condId, comment);

						gotoPage(request, response, "profile.jsp");
					}

				} catch (Exception e) {
					request.setAttribute("message", "価格は必須です");
					gotoPage(request, response, "listing.jsp");
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
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
