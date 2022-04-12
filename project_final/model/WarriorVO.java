package project_final.model;

public class WarriorVO {
	private String id;
	private String name;
	private int level = 1;
	private int exp = 0;
	private int damage = 40;
	private int hp = 300;
	private int mp = 150;
	private int money = 100;
	private int upgradehp = 0;
	private int upgrademp = 0;
	private int upgradedmg = 0;
	private String item = "Empty";

	public WarriorVO() {
	}

	public WarriorVO(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public void attack(BossVO boss) {
		boss.setHp(boss.getHp() - this.damage);
		this.hp -= boss.getDamage();
		this.setMoney(this.getMoney() + boss.getDamage() + (this.getLevel() * 10));
		int amountExp = (int) ((7 * this.getLevel()) * (1 + (0.1 * this.getExp())));
		int amountLevel = 100 + (this.getLevel() * 10);

		if (amountExp + this.getExp() >= amountLevel) {
			this.setExp(this.getExp() + amountExp);
			this.setLevel(this.getLevel() + (this.getExp() / amountLevel));
			this.setExp(this.getExp() % amountLevel);
			System.out.println("레벨업!! 현재 레벨 " + this.getLevel());
			this.setHp((int) ((300 * this.getLevel()) / 1.5));
			this.setMp((int) ((150 * this.getLevel()) / 1.2));
		} else {
			this.setExp(this.getExp() + amountExp);
		}

		int expState = (int) (((double) this.getExp() / amountLevel) * 100);
		System.out.println("경험치를 " + amountExp + " 만큼 획득 ( " + expState + "% )");
		System.out.println("\n" + this.getDamage() + " 만큼 골드가 지급됩니다.\n");
	}

	public void sklPower(BossVO boss) {
		if (this.getMp() > 39) {
			System.out.println("\n파워 스트라이크!!\n");
			this.setMp(this.getMp() - 40);
			boss.setHp(boss.getHp() - (this.getDamage() * 2));
			System.out.println("   " + (this.getDamage() * 2) + " Hit !!!!!!!!!\n");
		} else {
			System.out.println("마나가 부족합니다.");
		}
	}

	public void sklCanon(BossVO boss) {
		if (this.getMp() > 99) {
			System.out.println("\n버스트 캐넌!!\n");
			this.setMp(this.getMp() - 100);
			for (int i = 0; i < 3; i++) {
				boss.setHp(boss.getHp() - this.getDamage());
				System.out.println("  " + this.getDamage() + " Hit! \n");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			boss.setHp(boss.getHp() - (this.getDamage() * 2));
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Critical!! Hit!! " + (this.getDamage() * 2) + "!!\n");
		} else {
			System.out.println("마나가 부족합니다.");
		}
	}

	@Override
	public String toString() {
		return "[ 직업 : Warrior" + " | level: "+ level +" | damage: " + damage + " | Hp: " + hp + " | Mp: " + mp + " ]";
	}

	public String toListString() {
		return "\n[ 캐릭터 닉네임: " + name + " | Level: " + level + " | money: " + money + " ]";
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
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

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
