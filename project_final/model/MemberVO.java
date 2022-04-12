package project_final.model;

public class MemberVO {
	private String id;
	private String name;
	private String pw;
	private int hpcnt;
	private int mpcnt;
	private int recovervcnt;

	public MemberVO() {}

	public MemberVO(String id, String name, String pw) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "memberVO [id=" + id + ", name=" + name + ", pw=" + pw + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getHpcnt() {
		return hpcnt;
	}

	public void setHpcnt(int hpcnt) {
		this.hpcnt = hpcnt;
	}

	public int getMpcnt() {
		return mpcnt;
	}

	public void setMpcnt(int mpcnt) {
		this.mpcnt = mpcnt;
	}

	public int getRecovervcnt() {
		return recovervcnt;
	}

	public void setRecovervcnt(int recovervcnt) {
		this.recovervcnt = recovervcnt;
	}

}
