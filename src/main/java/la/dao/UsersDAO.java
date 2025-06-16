package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import la.bean.UserBean;

public class UsersDAO {

	private String url = "jdbc:postgresql:team_dev_pisuta_shop";
	private String user = "student";
	private String pass = "himitu";

	public UsersDAO() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	//会員一覧表示
	public List<UserBean> findAll(Boolean status) throws DAOException {
		String sql = "select * from users";
		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();) {

			List<UserBean> list = new ArrayList<UserBean>();
			while (rs.next()) {
				int user_id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				String password = rs.getNString("password");

				UserBean bean = new UserBean(user_id, name, address, tel, email, birthday, password);
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	//IDで会員検索
	public UserBean findUserById(int id) throws DAOException {
		String sql = "select * from users where id=?";

		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, id);

			try (
					ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					int user_id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					LocalDate birthday = rs.getDate("birthday").toLocalDate();
					String password = rs.getNString("password");

					UserBean bean = new UserBean(user_id, name, address, tel, email, birthday, password);

					return bean;
				} else {
					return null;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

	}
}