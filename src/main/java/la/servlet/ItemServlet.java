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

import la.bean.DealBean;
import la.bean.ItemBean;
import la.bean.UserBean;
import la.dao.CategoriesDAO;
import la.dao.ConditionsDAO;
import la.dao.DAOException;
import la.dao.DealsDAO;
import la.dao.ItemsDAO;
import la.dao.UsersDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//★全商品の一覧を出力する
		//
		try {
			request.setCharacterEncoding("UTF-8");
			// パラメータの解析
			String action = request.getParameter("action");
			// モデルのDAOを生成
			ItemsDAO itemsdao = new ItemsDAO();
			DealsDAO dealsdao = new DealsDAO();
			UsersDAO userdao = new UsersDAO();
			CategoriesDAO categoriesdao = new CategoriesDAO();
			ConditionsDAO conditionsdao = new ConditionsDAO();

			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("user");
			if (action == null) {

				List<ItemBean> itemslist = itemsdao.findAll(true);
				request.setAttribute("items", itemslist);
				gotoPage(request, response, "/top.jsp");

			} else if (action.equals("confirm")) {
				// ★購入確認画面への遷移

				if (Objects.isNull(user)) {
					response.sendRedirect("/team_dev_pisuta_shop/LoginServlet");
					return;
				}
				int itemId = Integer.parseInt(request.getParameter("itemId"));
				ItemBean bean = itemsdao.searchItemById(itemId);

				request.setAttribute("item", bean);
				request.setAttribute("address", user.getAddress());

				gotoPage(request, response, "/confirm.jsp");

			} else if (action.equals("buy")) {
				// ★購入情報への遷移
				int itemId = Integer.parseInt(request.getParameter("itemId"));
				user = (UserBean) session.getAttribute("user");

				dealsdao.addDeal(itemId, user.getId());
				dealsdao.updateStatus(itemId);

				ItemBean itembean = itemsdao.searchItemById(itemId);
				DealBean dealbean = dealsdao.findDealByItemId(itemId);
				String name = userdao.findUserById(itembean.getSellerId()).getName();

				request.setAttribute("sellerName", name);
				request.setAttribute("item", itembean);
				request.setAttribute("deal", dealbean);

				gotoPage(request, response, "/receipt.jsp");

			} else if (action.equals("detail")) {
				// ★商品詳細への遷移
				int itemId = Integer.parseInt(request.getParameter("itemId"));
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				int condId = Integer.parseInt(request.getParameter("condId"));
				user = (UserBean) session.getAttribute("user");

				ItemBean itembean = itemsdao.searchItemById(itemId);
				String category = categoriesdao.findNameById(categoryId);
				String condition = conditionsdao.findNameById(condId);
				String name = userdao.findUserById(itembean.getSellerId()).getName();

				request.setAttribute("item", itembean);
				request.setAttribute("category", category);
				request.setAttribute("condition", condition);
				request.setAttribute("sellerName", name);

				gotoPage(request, response, "/detail.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * ページ遷移
	 */
	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
