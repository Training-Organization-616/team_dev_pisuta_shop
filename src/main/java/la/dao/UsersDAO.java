package la.dao;

import java.sql.Connection;
import java.sql.Date;
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
	public List<UserBean> findAll() throws DAOException {
		String sql = "SELECT * FROM users WHERE status = true";
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
				String password = rs.getString("password");
				boolean status = rs.getBoolean("status");

				UserBean bean = new UserBean(user_id, name, address, tel, email, birthday, password, status);
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
		String sql = "SELECT * FROM users WHERE id = ? AND status = true";

		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					int user_id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					LocalDate birthday = rs.getDate("birthday").toLocalDate();
					String password = rs.getString("password");
					boolean status = rs.getBoolean("status");

					UserBean bean = new UserBean(user_id, name, address, tel, email, birthday, password, status);

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

	// メールアドレスとパスワードで会員検索
	public UserBean findUserByEmailAndPassword(String email, String password) throws DAOException {
		String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND status = true";

		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, email);
			st.setString(2, password);

			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					int user_id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					LocalDate birthday = rs.getDate("birthday").toLocalDate();
					boolean status = rs.getBoolean("status");

					UserBean bean = new UserBean(user_id, name, address, tel, email, birthday, password, status);

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

	// メールアドレスをもとに登録済みかどうか判定する
	public boolean isRegisteredUser(String email) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM users WHERE email = ? AND status = true";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, email);

			try (ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				if (rs.next()) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public int addUser(String name, String address, String tel, String email, LocalDate birthday, String password)
			throws DAOException {
		// SQL文の作成
		String sql = "INSERT INTO users(name, address, tel, email, birthday, password) VALUES(?, ?, ?, ?, ?, ?) ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, email);
			st.setDate(5, Date.valueOf(birthday));
			st.setString(6, password);

			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	// 会員情報の更新
	public int updateUser(int id, String name, String address, String tel, String email, LocalDate birthday,
			String password)
			throws DAOException {
		// SQL文の作成
		String sql = "UPDATE users SET name = ?, address = ?, tel = ?, email = ?, birthday = ?, password = ? WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, email);
			st.setDate(5, Date.valueOf(birthday));
			st.setString(6, password);
			st.setInt(7, id);

			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	// 退会処理
	public int deleteUser(int id) throws DAOException {
		// SQL文の作成
		String sql = "UPDATE users SET status = false WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, id);

			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}