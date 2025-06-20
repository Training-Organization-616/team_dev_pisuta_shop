package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ConditionBean;

public class ConditionsDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:team_dev_pisuta_shop";
	private String user = "student";
	private String pass = "himitu";

	public ConditionsDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	public List<ConditionBean> findAll() throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM conditions ORDER BY id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			// 結果の取得および表示
			List<ConditionBean> list = new ArrayList<ConditionBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				ConditionBean bean = new ConditionBean(id, name);
				list.add(bean);
			}
			// 状態一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	//itemsテーブルのcond_idから、カテゴリ名を取得
	public String findNameById(int condId) throws DAOException {
		String sql = "select * from conditions where id=?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, condId);
			try (// SQLの実行
					ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					String name = rs.getString("name");
					return name;
				}
				return null;

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
