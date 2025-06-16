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

import la.bean.ItemBean;
import la.dao.ItemsDAO;

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
			ItemsDAO dao = new ItemsDAO();

			if (Objects.isNull(action) || action.isEmpty()) {
				//ItemBeanリスト:商品全検索
				List<ItemBean> list = dao.findAll(false);

				request.setAttribute("items", list);
				gotoPage(request, response, "/adminItem.jsp");
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
