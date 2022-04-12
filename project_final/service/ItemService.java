package project_final.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import project_final.dao.ItemDao;
import project_final.jdbc.ConnectionPool;
import project_final.model.InvenToryVO;
import project_final.model.ItemVO;
import project_final.model.WarriorVO;

public class ItemService {
	private static ItemService instance = new ItemService();
	private ItemDao dao = ItemDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();

	private ItemService() {
	}

	public static ItemService getInstance() {
		if (instance == null) {
			instance = new ItemService();
		}

		return instance;
	}

	// 아이템 목록 조회
	public ArrayList<ItemVO> itmList() {
		Connection con = cp.getConnection();

		try {
			return dao.itmList(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return new ArrayList<ItemVO>();
	}

	// 아이템 효과 (물약)
	public void drug(WarriorVO war, InvenToryVO inv) {
		war.setHp(war.getHp() + itmList().get(0).getIhp());
		System.out.println(itmList().get(0).getIhp() + " 만큼 체력이 회복 되었습니다.");
		System.out.println("현재 체력: " + war.getHp());
	}

	// 아이템 구매 후 수량 설정
	public void buyItem(ItemVO item, WarriorVO war, InvenToryVO inven, ArrayList<InvenToryVO> inv, String id, int no) {

		// 구매시 소지금이 있을 경우
		if (war.getMoney() != 0) {
			// 인벤토리에 상품 정보 담기
			inven.setIid(id);
			inven.setIname(item.getIname());
			inven.setIhp(item.getIhp());
			inven.setImp(item.getImp());
			inven.setIdmg(item.getIdmg());
			while (true) {
				if (war.getMoney() >= (item.getPrice() * no)) {
					// 인벤토리에 상품 수량 넣기
					inven.setIcount(no);
					// 구매시 소지금 마이너스
					war.setMoney(war.getMoney() - (item.getPrice() * no));
					System.out.println("구매 가격: " + (item.getPrice() * no) + "골드 입니다.\n");
					System.out.println("구매 후 소지금: " + war.getMoney() + " 골드 입니다.\n");
					// 소지품에 담기
					inv.add(inven);
					break;
				} else {
					System.out.println("골드가 부족합니다.\n");
					break;
				}
			}
		} else {
			System.out.println("골드가 부족합니다.\n");
		}

	}

	public void buyWeapon(ItemVO item, WarriorVO war, InvenToryVO inven, ArrayList<InvenToryVO> inv, String id) {
		// 구매시 소지금이 있을 경우
		if (war.getMoney() != 0) {
			while (true) {
				if (war.getMoney() >= (item.getPrice())) {
					if (inven.getIcount() == 0) {
						// 인벤토리에 상품 정보 담기
						inven.setIid(id);
						inven.setIname(item.getIname());
						inven.setIhp(item.getIhp());
						inven.setImp(item.getImp());
						inven.setIdmg(item.getIdmg());
						// 인벤토리에 상품 수량 넣기
						inven.setIcount(1);
						// 구매시 소지금 마이너스
						war.setMoney(war.getMoney() - item.getPrice());
						System.out.println("구매 가격: " + item.getPrice() + "골드 입니다.\n");
						System.out.println("구매 후 소지금: " + war.getMoney() + " 골드 입니다.\n");
						// 소지품에 담기
						inv.add(inven);
						war.setDamage(war.getDamage() + item.getIdmg());
						break;
					} else if (inven.getIcount() > 0) {
						System.out.println("한개 이상 상품을 구매할수 없습니다.");
						break;
					}
				} else {
					System.out.println("골드가 부족합니다.\n");
					break;
				}
			}
		} else {
			System.out.println("골드가 부족합니다.\n");
		}
	}
}
