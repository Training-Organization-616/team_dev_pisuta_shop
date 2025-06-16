package la.bean;

import java.io.Serializable;

public class DealBean implements Serializable {
	private int id; // 取引ID
	private int ItemId; // 商品ID
	private int buyerId; // 購入者ID

	// デフォルトコンストラクタ
	public DealBean() {

	}

	// コンストラクタ
	public DealBean(int id, int itemId, int buyerId) {
		this.id = id;
		ItemId = itemId;
		this.buyerId = buyerId;
	}

	// ゲッター
	public int getId() {
		return id;
	}

	public int getItemId() {
		return ItemId;
	}

	public int getBuyerId() {
		return buyerId;
	}

}
