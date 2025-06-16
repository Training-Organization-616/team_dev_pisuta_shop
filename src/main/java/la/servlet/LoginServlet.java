package la.servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.UserBean;
import la.dao.UsersDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

			String action = request.getParameter("action");

			if (Objects.isNull(action) || action.isEmpty()) {
				gotoPage(request, response, "/login.jsp");
			} else if (action.equals("login")) {
				HttpSession session = request.getSession();
				UsersDAO dao = new UsersDAO();

				String email = request.getParameter("email");
				String password = request.getParameter("password");

				if (Objects.isNull(email) || Objects.isNull(password)) {
					request.setAttribute("message", "メールアドレス、パスワードを入力してください");
					gotoPage(request, response, "/login.jsp");
				} else if (email.isEmpty() || password.isEmpty()) {
					request.setAttribute("message", "メールアドレス、パスワードを入力してください");
					gotoPage(request, response, "/login.jsp");
				}

				UserBean user = dao.findUserByEmailAndPassword(email, password);
				if (Objects.isNull(user)) {
					request.setAttribute("message", "メールアドレスまたはパスワードが間違っています");
					gotoPage(request, response, "/login.jsp");
				} else {
					session.setAttribute("user", user);
					response.sendRedirect("/ItemServlet");
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
