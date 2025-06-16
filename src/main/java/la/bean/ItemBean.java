package la.bean;

import java.io.Serializable;

public class ItemBean implements Serializable {
	private int id; // 商品ID
	private String name; // 商品名
	private int categoryId; // カテゴリーID
	private int sellerId; // 販売者ID
	private int price; // 価格
	private int condId; // 状態
	private boolean status; // 販売状況
	private String comment; // コメント

	// デフォルトコンストラクタ
	public ItemBean() {
	}

	// コンストラクタ
	public ItemBean(int id, String name, int categoryId, int sellerId, int price, int condId, boolean status,
			String comment) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.sellerId = sellerId;
		this.price = price;
		this.condId = condId;
		this.status = status;
		this.comment = comment;
	}

	// ゲッター
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public int getPrice() {
		return price;
	}

	public int getCondId() {
		return condId;
	}

	public boolean isStatus() {
		return status;
	}

	public String getComment() {
		return comment;
	}

	// セッター
	public void setStatus(boolean status) {
		this.status = status;
	}

}
