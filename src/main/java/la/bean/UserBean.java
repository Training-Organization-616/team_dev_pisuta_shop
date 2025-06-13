package la.bean;

<<<<<<< Updated upstream
import java.io.Serializable;

public class UserBean implements Serializable {
=======
import java.time.LocalDate;
>>>>>>> Stashed changes

public class UserBean {
	private int id;//会員ID
	private String name;//氏名
	private String address;//住所
	private String tel;//電話番号
	private String email;//メールアドレス
	private LocalDate birthday;//生年月日
	private String password;//パスワード

	public UserBean() {

	}

	public UserBean(int id, String name, String address, String tel, String email, LocalDate birthday,
			String password) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}