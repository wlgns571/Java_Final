package project_final.model;

public class InvenToryVO {
	private String iid;
	private String iname;
	private int idmg;
	private int ihp;
	private int imp;
	private int icount;

	public InvenToryVO() {}
	
	public InvenToryVO(String iid, int icount) {
		this.iid = iid;
		this.icount = icount;
	}
	
	public InvenToryVO(String iid, String iname, int idmg, int ihp, int imp, int icount) {
		this.iid = iid;
		this.iname = iname;
		this.idmg = idmg;
		this.ihp = ihp;
		this.imp = imp;
		this.icount = icount;
	}

	@Override
	public String toString() {
		return "[ 아이템 이름: " + iname + " | 체력회복량: " + ihp + " | 마나회복량: " + imp
				+ " | 총 수량: " + icount + " ]";
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
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

	public int getIcount() {
		return icount;
	}

	public void setIcount(int icount) {
		this.icount = icount;
	}

}
