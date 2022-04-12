package project_final.model;

public class BossVO {
	private String name;
	private int damage;
	private int hp;
	private String randitem;
	
	public BossVO() {}
	
	public BossVO(String name, int damage, int hp) {
		this.name = name;
		this.damage = damage;
		this.hp = hp;
	}
	
	@Override
	public String toString() {
		return "[ 보스이름: " + name + " | damage: " + damage + " | Hp: " + hp + " ]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getRanditem() {
		return randitem;
	}

	public void setRanditem(String randitem) {
		this.randitem = randitem;
	}
	
}
