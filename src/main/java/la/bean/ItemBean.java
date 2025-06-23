package la.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ItemBean implements Serializable {
	private int id; // 商品ID
	private String name; // 商品名
	private int categoryId; // カテゴリーID
	private int sellerId; // 販売者ID
	private int price; // 価格
	private int condId; // 状態
	private boolean status; // 販売状況
	private String comment; // コメント
	private String fileName; //画像ファイル名
	private LocalDateTime createdAt; // 出品日時

	// デフォルトコンストラクタ
	public ItemBean() {
	}

	// コンストラクタ
	public ItemBean(int id, String name, int categoryId, int sellerId, int price, int condId, boolean status,
			String comment, String fileName, LocalDateTime createdAt) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.sellerId = sellerId;
		this.price = price;
		this.condId = condId;
		this.status = status;
		this.comment = comment;
		this.fileName = fileName;
		this.createdAt = createdAt;
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

	public String getFileName() {
		return fileName;
	}

	public String getCreatedAt() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String c = createdAt.format(dtf);
		return c;
	}

	// セッター
	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
