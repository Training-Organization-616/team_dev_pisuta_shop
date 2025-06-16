package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import la.bean.DealBean;

public class DealsDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:team_dev_pisuta_shop";
	private String user = "student";
	private String pass = "himitu";

	public DealsDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	//★取引を登録するaddDeal
	//
	public int addDeal(int itemId, int buyerId) throws DAOException {
		// SQL文の作成
		//
		String sql = "INSERT INTO deals(item_id,buyer_id) " + "VALUES(?, ?)";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// 購入商品情報の指定
			//
			st.setInt(1, itemId);
			st.setInt(2, buyerId);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

	}

	//★商品の販売状況を更新する
	//
	public int updateStatus(int itemId) throws DAOException {
		String sql = "UPDATE items SET status = false WHERE id = ?";
		//◎sql文内容
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			//
			st.setInt(1, itemId);
			//
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	//★商品番号から取引情報を取得する
	//
	public DealBean findDealByItemId(int itemId) throws DAOException {
		String sql = "SELECT * FROM deals WHERE item_id = ?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, itemId);
			try (// SQLの実行
					ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					int buyerId = rs.getInt(" buyer_id");
					DealBean bean = new DealBean(id, itemId, buyerId);
					return bean;
				} else {
					return null; // 主キーに該当するレコードなし
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
}
