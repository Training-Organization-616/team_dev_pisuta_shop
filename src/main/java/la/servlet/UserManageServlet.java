package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.UserBean;
import la.dao.UsersDAO;

@WebServlet("/UserManageServlet")
public class UserManageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String action = request.getParameter("action");
			UsersDAO dao = new UsersDAO();

			if (action == null || action.length() == 0) {

				List<UserBean> users = dao.findAll();
				request.setAttribute("users", users);
				gotoPage(request, response, "/adminUser.jsp");

				//検索ボタン押下時（管理者画面）
			} else if (action.equals("search")) {
				int userId = Integer.parseInt(request.getParameter("userId"));
				UserBean user = dao.findUserById(userId);
				request.setAttribute("user", user);
				gotoPage(request, response, "/adminUserInfo.jsp");

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
