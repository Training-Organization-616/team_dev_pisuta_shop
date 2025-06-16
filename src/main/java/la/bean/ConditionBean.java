package la.bean;

import java.io.Serializable;

public class ConditionBean implements Serializable {
	private int id; // 状態ID
	private String name; // 状態名

	// デフォルトコンストラクタ
	public ConditionBean() {

	}

	// コンストラクタ
	public ConditionBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

	// ゲッター
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
