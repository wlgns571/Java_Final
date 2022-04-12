package project_final.model;

public abstract class HolyKnightVO {
	private String name;
	private int level;
	private int damage;
	private int hp;
	private int mp;
	private int money;
	private int upgradehp;
	private int upgrademp;
	private int upgradedmg;

	public HolyKnightVO() {}

	public HolyKnightVO(String name, int level, int damage, int hp, int mp, int money, int upgradehp, int upgrademp,
			int upgradedmg) {
		super();
		this.name = name;
		this.level = level;
		this.damage = damage;
		this.hp = hp;
		this.mp = mp;
		this.money = money;
		this.upgradehp = upgradehp;
		this.upgrademp = upgrademp;
		this.upgradedmg = upgradedmg;
	}

	@Override
	public String toString() {
		return "CharacterVO [name=" + name + ", level=" + level + ", damage=" + damage + ", hp=" + hp + ", mp=" + mp
				+ ", money=" + money + ", upgradehp=" + upgradehp + ", upgrademp=" + upgrademp + ", upgradedmg="
				+ upgradedmg + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getUpgradehp() {
		return upgradehp;
	}

	public void setUpgradehp(int upgradehp) {
		this.upgradehp = upgradehp;
	}

	public int getUpgrademp() {
		return upgrademp;
	}

	public void setUpgrademp(int upgrademp) {
		this.upgrademp = upgrademp;
	}

	public int getUpgradedmg() {
		return upgradedmg;
	}

	public void setUpgradedmg(int upgradedmg) {
		this.upgradedmg = upgradedmg;
	}

}
