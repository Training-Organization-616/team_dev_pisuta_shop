package la.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.UserBean;
import la.dao.DAOException;
import la.dao.ItemsDAO;
import la.dao.UsersDAO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// パラメータの解析
			String action = request.getParameter("action");
			UsersDAO dao = new UsersDAO();

			if (action == null || action.length() == 0) {
				// 会員管理画面への遷移
				gotoPage(request, response, "/profile.jsp");

			} else if (action.equals("regist")) {
				// 会員登録画面への遷移
				gotoPage(request, response, "/registUser.jsp");

			} else if (action.equals("new")) {
				// 会員登録
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				LocalDate birthday = LocalDate.parse(request.getParameter("birthday"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String password = request.getParameter("password");
				String confirm = request.getParameter("confirm");

				// 入力チェック
				if (name == null || name.length() == 0 || address == null || address.length() == 0
						|| tel == null || tel.length() == 0 || email == null || email.length() == 0
						|| password == null || password.length() == 0 || confirm == null || confirm.length() == 0) {
					request.setAttribute("message", "全ての項目を入力してください");
					gotoPage(request, response, "/registUser.jsp");
				} else if (tel.length() != 11) {
					request.setAttribute("message", "電話番号が正しくありません");
					gotoPage(request, response, "/registUser.jsp");
				} else if (dao.isRegisteredByTel(tel, -1)) {
					request.setAttribute("message", "既に登録されている電話番号です");
					gotoPage(request, response, "/registUser.jsp");
				} else if (!(email.contains("@") && (email.contains(".com") || email.contains(".jp")))) {
					request.setAttribute("message", "メールアドレスが正しくありません");
					gotoPage(request, response, "/registUser.jsp");
				} else if (dao.isRegisteredByEmail(email, -1)) {
					request.setAttribute("message", "既に登録されているメールアドレスです");
					gotoPage(request, response, "/registUser.jsp");
				} else if (password.length() < 6 || password.length() > 16) {
					request.setAttribute("message", "パスワードは6文字以上16文字以下にしてください");
					gotoPage(request, response, "/registUser.jsp");
				} else if (!password.equals(confirm)) {
					request.setAttribute("message", "パスワードが一致していません");
					gotoPage(request, response, "/registUser.jsp");
				} else {
					dao.addUser(name, address, tel, email, birthday, password);
					request.setAttribute("name", name);
					request.setAttribute("address", address);
					request.setAttribute("tel", tel);
					request.setAttribute("email", email);
					request.setAttribute("birthday", birthday);

					gotoPage(request, response, "/completed.jsp");
				}

			} else if (action.equals("edit")) {
				// 会員情報変更画面への遷移
				gotoPage(request, response, "/editUser.jsp");

			} else if (action.equals("update")) {
				// 会員情報更新
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				LocalDate birthday = LocalDate.parse(request.getParameter("birthday"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String password = request.getParameter("password");
				String confirm = request.getParameter("confirm");

				HttpSession session = request.getSession(false);
				UserBean bean = (UserBean) session.getAttribute("user");
				int id = bean.getId();

				// 入力チェック
				if (name == null || name.length() == 0 || address == null || address.length() == 0
						|| tel == null || tel.length() == 0 || email == null || email.length() == 0
						|| password == null || password.length() == 0 || confirm == null || confirm.length() == 0) {
					request.setAttribute("message", "全ての項目を入力してください");
					gotoPage(request, response, "/editUser.jsp");
				} else if (tel.length() != 11) {
					request.setAttribute("message", "電話番号が正しくありません");
					gotoPage(request, response, "/registUser.jsp");
				} else if (dao.isRegisteredByTel(tel, id)) {
					request.setAttribute("message", "既に登録されている電話番号です");
					gotoPage(request, response, "/registUser.jsp");
				} else if (!(email.contains("@") && (email.contains(".com") || email.contains(".jp")))) {
					request.setAttribute("message", "メールアドレスが正しくありません");
					gotoPage(request, response, "/editUser.jsp");
				} else if (dao.isRegisteredByEmail(email, id)) {
					request.setAttribute("message", "既に登録されているメールアドレスです");
					gotoPage(request, response, "/editUser.jsp");
				} else if (password.length() < 6 || password.length() > 16) {
					request.setAttribute("message", "パスワードは6文字以上16文字以下にしてください");
					gotoPage(request, response, "/editUser.jsp");
				} else if (!password.equals(confirm)) {
					request.setAttribute("message", "パスワードが一致していません");
					gotoPage(request, response, "/editUser.jsp");
				} else {
					dao.updateUser(id, name, address, tel, email, birthday, password);

					UserBean user = dao.findUserById(id);
					request.setAttribute("user", user);
					gotoPage(request, response, "/profile.jsp");
				}

				//会員の退会処理
			} else if (action.equals("remove")) {
				// セッションから会員IDの取得
				HttpSession session = request.getSession(false);
				UserBean user = (UserBean) session.getAttribute("user");
				int id = user.getId();

				dao.deleteUser(id);

				ItemsDAO itemsDao = new ItemsDAO();
				itemsDao.deleteItemByUserId(id);

				// セッションの削除
				if (session != null) {
					session.removeAttribute("user");
				}

				response.sendRedirect("/team_dev_pisuta_shop/ItemServlet");
			}

		} catch (DAOException e) {
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
