package project_final.model;

public class ItemVO {
	private String iname;
	private int idmg;
	private int ihp;
	private int imp;
	private int price;

	public ItemVO() {}
	
	@Override
	public String toString() {
		return "[ 아이템 이름 : " + iname + " 상품 가격 : " + price + " ]";
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}
	
	public int getIdmg() {
		return idmg;
	}

	public void setIdmg(int idmg) {
		this.idmg = idmg;
	}

	public int getIhp() {
		return ihp;
	}

	public void setIhp(int ihp) {
		this.ihp = ihp;
	}

	public int getImp() {
		return imp;
	}

	public void setImp(int imp) {
		this.imp = imp;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
