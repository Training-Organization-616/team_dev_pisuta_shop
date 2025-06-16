package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class ItemsDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:team_dev_pisuta_shop";
	private String user = "student";
	private String pass = "himitu";

	public ItemsDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	/**
	 * 商品全検索
	 * @param status :購入済みのみフラグ
	 * @return 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findAll(boolean status) throws DAOException {

		String sql = "SELECT * FROM items WHERE 1=1";

		if (!status) {
			sql += " status = false";
		}

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();) {
			List<ItemBean> list = new ArrayList<ItemBean>();
			//検索結果
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int categoryId = rs.getInt("category_id");
				int sellerId = rs.getInt("seller_id");
				int price = rs.getInt("price");
				int condId = rs.getInt("cond_id");
				boolean itemStatus = rs.getBoolean("status");
				String comment = rs.getString("comment");

				list.add(new ItemBean(id, name, categoryId, sellerId, price, condId, itemStatus, comment));
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public int addItem(String name, int categoryId, int sellerId, int price, int condId, String comment)
			throws DAOException {
		String sql = "INSERT INTO items(name,category_id,seller_id,price,cond_id,comment) VALUES(?,?,?,?,?,?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, name);
			st.setInt(2, categoryId);
			st.setInt(3, sellerId);
			st.setInt(4, price);
			st.setInt(5, condId);
			st.setString(6, comment);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}
	}

	public int deleteItem(int id) throws DAOException {
		String sql = "DELETE FROM items WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}
	}

	public List<ItemBean> serchItem(String keyword, String userName) throws DAOException {
		String sql = "SELECT * FROM items WHERE 1=1";

		if (userName.length() != 0) {
			sql = "SELECT i.*,u.name FROM items i JOIN users u ON i.seller_id = u.id WHERE u.name LIKE ?";
		}
		if (userName.length() != 0 && keyword.length() != 0) {
			sql += " AND i.name LIKE ?";
		} else if (keyword.length() != 0) {
			sql += " AND name LIKE ?";
		}

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			if (userName.length() != 0) {
				st.setString(1, "%" + userName + "%");
			}
			if (userName.length() != 0 && keyword.length() != 0) {
				st.setString(2, "%" + keyword + "%");
			} else if (keyword.length() != 0) {
				st.setString(1, "%" + keyword + "%");
			}

			ResultSet rs = st.executeQuery();
			List<ItemBean> list = new ArrayList<ItemBean>();
			//検索結果
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int categoryId = rs.getInt("category_id");
				int sellerId = rs.getInt("seller_id");
				int price = rs.getInt("price");
				int condId = rs.getInt("cond_id");
				boolean itemStatus = rs.getBoolean("status");
				String comment = rs.getString("comment");

				list.add(new ItemBean(id, name, categoryId, sellerId, price, condId, itemStatus, comment));
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}

	}

}
