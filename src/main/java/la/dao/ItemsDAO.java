package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
	 * @param status :購入済みのみフラグ true:購入済みのみ
	 * @return 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findAll(boolean soldout) throws DAOException {

		String sql = "SELECT * FROM items WHERE 1 = 1 ";

		if (!soldout) {
			sql += "AND status = true ";
		}
		sql += "ORDER BY id";

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
				boolean status = rs.getBoolean("status");
				String comment = rs.getString("comment");
				String fileName = rs.getString("file_name");
				LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

				list.add(new ItemBean(id, name, categoryId, sellerId, price, condId, status, comment, fileName,
						createdAt));
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public List<ItemBean> findItemWithoutUserId(int userId) throws DAOException {

		String sql = "SELECT * FROM items EXCEPT SELECT * FROM items WHERE seller_id = ? ORDER BY id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, userId);

			try (ResultSet rs = st.executeQuery();) {
				List<ItemBean> list = new ArrayList<ItemBean>();
				//検索結果
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int categoryId = rs.getInt("category_id");
					int sellerId = rs.getInt("seller_id");
					int price = rs.getInt("price");
					int condId = rs.getInt("cond_id");
					boolean status = rs.getBoolean("status");
					String comment = rs.getString("comment");
					String fileName = rs.getString("file_name");
					LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

					list.add(
							new ItemBean(id, name, categoryId, sellerId, price, condId, status, comment, fileName,
									createdAt));
				}

				return list;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public List<ItemBean> findItemByUserId(int userId) throws DAOException {

		String sql = "SELECT * FROM items WHERE seller_id = ? AND status = true ORDER BY id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, userId);

			try (ResultSet rs = st.executeQuery();) {
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
					String fileName = rs.getString("file_name");
					LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

					list.add(
							new ItemBean(id, name, categoryId, sellerId, price, condId, itemStatus, comment, fileName,
									createdAt));
				}

				return list;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public ItemBean searchItemById(int itemId) throws DAOException {

		String sql = "SELECT * FROM items WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, itemId);
			try (ResultSet rs = st.executeQuery();) {
				ItemBean bean;
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
					String fileName = rs.getString("file_name");
					LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

					bean = new ItemBean(id, name, categoryId, sellerId, price, condId, itemStatus, comment, fileName,
							createdAt);
					return bean;
				}
				return null;

			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public List<ItemBean> searchItemByRefinement(String keyword, int searchCategoryId, int minPrice, int maxPrice,
			int searchConditionId)
			throws DAOException {

		String sql = "SELECT * FROM items WHERE 1 = 1 ";

		if (keyword != null && keyword.length() != 0) {
			sql += "AND (name LIKE ? OR comment LIKE ?) ";
		}
		if (searchCategoryId != -1) {
			sql += "AND category_id = ? ";
		}
		if (minPrice != -1) {
			sql += "AND price >= ? ";
		}
		if (maxPrice != -1) {
			sql += "AND price <= ? ";
		}
		if (searchConditionId != -1) {
			sql += "AND cond_id = ? ";
		}

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			int i = 1;
			if (keyword != null && keyword.length() != 0) {
				st.setString(i, "%" + keyword + "%");
				i++;
				st.setString(i, "%" + keyword + "%");
				i++;
			}
			if (searchCategoryId != -1) {
				st.setInt(i, searchCategoryId);
				i++;
			}
			if (minPrice != -1) {
				st.setInt(i, minPrice);
				i++;
			}
			if (maxPrice != -1) {
				st.setInt(i, maxPrice);
				i++;
			}
			if (searchConditionId != -1) {
				st.setInt(i, searchConditionId);
				i++;
			}

			try (ResultSet rs = st.executeQuery();) {

				List<ItemBean> list = new ArrayList<ItemBean>();
				//検索結果
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int categoryId = rs.getInt("category_id");
					int sellerId = rs.getInt("seller_id");
					int price = rs.getInt("price");
					int condId = rs.getInt("cond_id");
					boolean status = rs.getBoolean("status");
					String comment = rs.getString("comment");
					String fileName = rs.getString("file_name");
					LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

					list.add(
							new ItemBean(id, name, categoryId, sellerId, price, condId, status, comment, fileName,
									createdAt));
				}
				return list;

			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public List<ItemBean> searchItemByRefinementWithoutUserId(String keyword, int searchCategoryId, int minPrice,
			int maxPrice,
			int searchConditionId, int userId)
			throws DAOException {

		String sql = "SELECT * FROM items WHERE 1 = 1 ";

		if (keyword != null && keyword.length() != 0) {
			sql += "AND (name LIKE ? OR comment LIKE ?) ";
		}
		if (searchCategoryId != -1) {
			sql += "AND category_id = ? ";
		}
		if (minPrice != -1) {
			sql += "AND price >= ? ";
		}
		if (maxPrice != -1) {
			sql += "AND price <= ? ";
		}
		if (searchConditionId != -1) {
			sql += "AND cond_id = ? ";
		}
		sql += "EXCEPT SELECT * FROM items WHERE seller_id = ? ORDER BY id ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			int i = 1;
			if (keyword != null && keyword.length() != 0) {
				st.setString(i, "%" + keyword + "%");
				i++;
				st.setString(i, "%" + keyword + "%");
				i++;
			}
			if (searchCategoryId != -1) {
				st.setInt(i, searchCategoryId);
				i++;
			}
			if (minPrice != -1) {
				st.setInt(i, minPrice);
				i++;
			}
			if (maxPrice != -1) {
				st.setInt(i, maxPrice);
				i++;
			}
			if (searchConditionId != -1) {
				st.setInt(i, searchConditionId);
				i++;
			}
			st.setInt(i, userId);

			try (ResultSet rs = st.executeQuery();) {
				List<ItemBean> list = new ArrayList<ItemBean>();
				//検索結果
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int categoryId = rs.getInt("category_id");
					int sellerId = rs.getInt("seller_id");
					int price = rs.getInt("price");
					int condId = rs.getInt("cond_id");
					boolean status = rs.getBoolean("status");
					String comment = rs.getString("comment");
					String fileName = rs.getString("file_name");
					LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

					list.add(
							new ItemBean(id, name, categoryId, sellerId, price, condId, status, comment, fileName,
									createdAt));
				}
				return list;

			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public int addItem(String name, int categoryId, int sellerId, int price, int condId, String comment,
			String fileName)
			throws DAOException {
		String sql = "INSERT INTO items(name,category_id,seller_id,price,cond_id,comment,file_name) VALUES(?,?,?,?,?,?,?)";

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
			st.setString(7, fileName);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}
	}

	public int getIdbyItem(int userId, String fileName) throws DAOException {
		String sql = "SELECT * FROM items WHERE seller_id = ? AND name = ? ORDER BY created_at LIMIT 1";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, userId);
			st.setString(2, fileName);

			try (ResultSet rs = st.executeQuery();) {
				while (rs.next()) {
					int id = rs.getInt("id");
					return id;
				}
				return -1;
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}
	}

	public int updateItemFileNameById(int itemId, String fileName) throws DAOException {
		String sql = "UPDATE items SET file_name = ? WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, fileName);
			st.setInt(2, itemId);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}

	}

	public int updateItem(int id, String name, int categoryId, int price, int condId, String comment)
			throws DAOException {
		String sql = "UPDATE items SET name = ?, category_id = ?, price = ?, cond_id = ?, comment = ? WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, name);
			st.setInt(2, categoryId);
			st.setInt(3, price);
			st.setInt(4, condId);
			st.setString(5, comment);
			st.setInt(6, id);

			int rows = st.executeUpdate();
			return rows;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}
	}

	public int deleteItemById(int itemId) throws DAOException {
		String sql = "DELETE FROM items WHERE id = ? ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, itemId);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}
	}

	public int deleteItemByUserId(int userId) throws DAOException {
		String sql = "DELETE FROM items WHERE seller_id = ? AND status = true";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, userId);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}
	}

	public List<ItemBean> searchItemByKeywordAndUserName(String keyword, String userName) throws DAOException {
		String sql = "SELECT * FROM items WHERE status = true";

		if (userName.length() != 0) {
			sql = "SELECT i.*,u.name FROM items i JOIN users u ON i.seller_id = u.id WHERE u.name LIKE ? AND i.status = true";
		}
		if (userName.length() != 0 && keyword.length() != 0) {
			sql += " AND i.name LIKE ? ";
		} else if (keyword.length() != 0) {
			sql += " AND name LIKE ? ";
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
				String fileName = rs.getString("file_name");
				LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

				list.add(new ItemBean(id, name, categoryId, sellerId, price, condId, itemStatus, comment, fileName,
						createdAt));
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの登録に失敗しました。");
		}

	}

}
