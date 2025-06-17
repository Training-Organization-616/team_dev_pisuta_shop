package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.bean.UserBean;
import la.dao.ItemsDAO;
import la.dao.UsersDAO;

/**
 * Servlet implementation class ItemManageServlet
 */
@WebServlet("/ItemManageServlet")
public class ItemManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemManageServlet() {
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
			//文字コードセット
			request.setCharacterEncoding("UTF-8");
			//actionパラメータ取得
			String action = request.getParameter("action");
			//ItemDAOインスタンス
			ItemsDAO itemsDao = new ItemsDAO();
			UsersDAO usersDao = new UsersDAO();

			if (Objects.isNull(action) || action.isEmpty()) {
				//ItemBeanリスト:商品全検索
				List<ItemBean> list = itemsDao.findAll(true);
				List<UserBean> userList = usersDao.findAll();

				request.setAttribute("items", list);
				request.setAttribute("users", userList);
				gotoPage(request, response, "/adminItem.jsp");

			} else if (action.equals("delete")) {
				int id = Integer.parseInt(request.getParameter("itemId"));
				itemsDao.deleteItem(id);

				response.sendRedirect("/team_dev_pisuta_shop/ItemManageServlet");

			} else if (action.equals("search")) {
				String itemName = request.getParameter("keyword");
				String userName = request.getParameter("userName");

				if (Objects.isNull(itemName)) {
					itemName = "";
				}
				if (Objects.isNull(userName)) {
					userName = "";
				}

				List<ItemBean> list = itemsDao.searchItem(itemName, userName);

				request.setAttribute("items", list);

				gotoPage(request, response, "/adminItem.jsp");

			} else if (action.equals("data")) {
				List<ItemBean> list = itemsDao.findAll(false);

				String j = List2Json(list);
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.print(j);
				out.flush();
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

	private String List2Json(List<ItemBean> list) {
		int lastIndex = list.size() - 1;
		String json = "[";
		int index = 0;
		for (ItemBean item : list) {
			json += "{";
			json += "\"id\":" + item.getId() + ",";
			json += "\"categoryId\":" + item.getCategoryId() + ",";
			json += "\"sellerId\":" + item.getSellerId() + ",";
			json += "\"name\":\"" + item.getName() + "\",";
			json += "\"price\":" + item.getPrice();

			if (index == lastIndex) {
				json += "}";
			} else {
				json += "},";
			}
			index++;
		}
		json += "]";

		return json;
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
