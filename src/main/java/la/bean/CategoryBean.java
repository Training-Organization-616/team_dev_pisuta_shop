package la.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable {
	private int id;//カテゴリID
	private String name;//カテゴリ名

	public CategoryBean() {

	}

	public CategoryBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
