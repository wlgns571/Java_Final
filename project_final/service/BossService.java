package project_final.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import project_final.dao.BossDao;
import project_final.jdbc.ConnectionPool;
import project_final.model.BossVO;
import project_final.model.WarriorVO;

public class BossService {
	private static BossService instance = new BossService();
	private BossDao dao = BossDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();

	private BossService() {
	}

	public static BossService getInstance() {
		if (instance == null) {
			instance = new BossService();
		}

		return instance;
	}

	// 보스조회
	public ArrayList<BossVO> bosList() {
		Connection con = cp.getConnection();

		try {
			return dao.bosList(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return new ArrayList<BossVO>();
	}

	// 보스 사망시
	public void bosDie(BossVO boss, WarriorVO war) {
		if (boss.getHp() <= 0) {
			System.out.println(boss.getName() + " 이(가) 사망하였습니다.");
			war.setMoney(war.getMoney() + (boss.getDamage() * 5));
			System.out.println("축하합니다!! 보스를 처치 하였습니다.\n");
			System.out.println("보스 처치 보상으로 " + (boss.getDamage() * 5) + " 골드를 얻었습니다.\n");
			if (war.getLevel() > 1) {
				war.setHp((int) ((300 * war.getLevel()) / 1.5));
				war.setMp((int) ((150 * war.getLevel()) / 1.2));
			} else {
				war.setHp(300);
				war.setMp(150);
			}
			System.out.println("처음 화면으로 돌아갑니다.\n");
		}
	}

	// 보스 주요 패턴
	public void bosPattern(BossVO boss, WarriorVO war, Scanner sc) {
		String[] strs = { "a", "b", "c", "d", "e", "f", "g", "h", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b",
				"b", "b", "b" };

		List<String> list = Arrays.asList(strs);

		String answer = "";

		int remainPerHP = (int) ((boss.getHp() / 300.0) * 100);
		if (remainPerHP <= 50) {
			System.out.println("\n보스의 체력이 " + remainPerHP + " % 이하로 떨어졌습니다.\n");
			System.out.println("\n 전부 짓이겨주마!!!!!!!!!!!!!!! \n");
			System.out.println(boss.getName() + " 의 행동의 변화 합니다\n");
			System.out.println("갑작스러운 발악이 시작됩니다.\n");

			// 아재패턴 실행
			for (int i = 0; i < 7; i++) {
				Collections.shuffle(list);
				System.out.print(list.get(i) + "  ");
				answer += list.get(i);
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println();
			
			String ran = "";
			
			try {
				ran = sc.nextLine();
			} catch (Exception e1) {
				System.out.println("문자만 입력해주세요.");
			}
			
			if (ran.equals(answer)) {
				System.out.println("공격을 방어하였습니다.\n");
			} else {
				System.out.println("즉사 패턴이 시작됩니다.\n");
				war.setHp(war.getHp() - (boss.getDamage() * 5));
			}
			// 분신패턴 실행
			while (true) {
				if (remainPerHP <= 10) {
					System.out.println("\n보스의 체력이 " + remainPerHP + " % 이하로 떨어졌습니다.\n");
					BossVO bsClone = new BossVO("Bell", 0, 40);
					ArrayList<BossVO> bsCloneList = new ArrayList<BossVO>();
					bsCloneList.add(boss);
					bsCloneList.add(bsClone);
					Collections.shuffle(bsCloneList);

					System.out.println(boss.getName() + " 의 몸이 흔들리기 시작합니다!!!!\n");
					System.out.println("\n이대로 죽을 수 없다!!!!!!!!!!!!\n");
					System.out.println(boss.getName() + " 의 몸이 두개로 나뉘었습니다.");
					System.out.println("진짜를 찾으세요!!! (1 ~ 2 입력)");
					System.out.print(">>> ");
					
					int no = 0;
					
					try {
						no = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("숫자만 입력해주세요.");
						continue;
					}

					if (no == 1) {
						if (bsCloneList.get(no - 1).getName().equals(boss.getName())) {
							System.out.println("\n크아아아악!!!!!!!!!!\n");
							boss.setHp(0);
							bosDie(boss, war);
							break;
						} else {
							System.out.println("\n넌 나를 죽일 수 없다!!!\n");
							war.setHp(war.getHp() - bsClone.getHp());
							System.out.println(war.getName() + " 이(가) 데미지를 " + bsClone.getHp() + " 만큼 입었습니다.\n");
							continue;
						}
					} else if (no == 2) {
						if (bsCloneList.get(no - 1).getName().equals(boss.getName())) {
							System.out.println("\n크아아아악!!!!!!!!!!\n");
							boss.setHp(0);
							bosDie(boss, war);
							break;
						} else {
							System.out.println("\n넌 나를 죽일 수 없다!!!\n");
							war.setHp(war.getHp() - bsClone.getHp());
							System.out.println(war.getName() + " 이(가) 데미지를 " + bsClone.getHp() + " 만큼 입었습니다.\n");
							continue;
						}
					}
				}
				break;
			}
		}
	}
}
