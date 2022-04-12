package project_final;

import java.util.ArrayList;
import java.util.Scanner;

import project_final.model.BossVO;
import project_final.model.InvenToryVO;
import project_final.model.ItemVO;
import project_final.model.MemberVO;
import project_final.model.WarriorVO;
import project_final.service.BossService;
import project_final.service.CharacterService;
import project_final.service.InvenToryService;
import project_final.service.ItemService;
import project_final.service.MemService;

public class MonsterRaid {
	public static void main(String[] args) {
		MemService service = MemService.getInstance();
		CharacterService charService = CharacterService.getInstance();
		BossService bosService = BossService.getInstance();
		ItemService itemSerivce = ItemService.getInstance();
		InvenToryService invenService = InvenToryService.getInstance();
		ArrayList<InvenToryVO> inv = new ArrayList<InvenToryVO>();
		InvenToryVO inven = new InvenToryVO();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("행동을 선택해주세요.");
			System.out.println("1. 회원가입 | 2. 로그인 | 3. 종료");
			System.out.print(">>> ");

			int select = 0;
			try {
				select = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}

			if (select == 1) {
				// 회원가입
				System.out.println("이름을 입력해주세요. ");
				System.out.print(">>> ");
				String name = sc.nextLine();
				System.out.println("아이디를 입력해주세요. ");
				System.out.print(">>> ");
				String id = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요. ");
				System.out.print(">>> ");
				String pw = sc.nextLine();

				MemberVO mem = service.getMem(id);

				if (mem.getId() != null) {
					System.out.println("중복된 아이디 입니다.");
				} else {
					int resultCnt = service.registMem(name, id, pw);

					if (resultCnt > 0) {
						System.out.println("회원가입 완료");
					} else {
						System.out.println("오류가 발생했습니다.");
					}
				}
			} else if (select == 2) {
				// 로그인
				System.out.println("아이디를 입력해주세요.");
				System.out.print(">>> ");
				String id = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요.");
				System.out.print(">>> ");
				String pw = sc.nextLine();

				MemberVO mem = service.getMem(id);

				if (mem.getId() == null) {
					System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
					continue;
				} else if (mem.getPw().equals(pw)) {
					// 로그인 이후 프로세스
					while (true) {
						System.out.println(
								"!!!;;::~~~~~~~:;;;;;::~~~------,,,....                                                 .... ..... . \n"
										+ ";!*===********!!!;;;;;:::::::~~~~~~-~~~~-!*!!!;;!;!!!!!!!!!*!!!!!!;- ,..........  ...,,,..          \n"
										+ ";*====*********!!!!;;;;;::::::~~~~~~-~~-~***!;:;;!!!!!!***!!*!!!!!!;~,,........ ..,,,,--,.          \n"
										+ ";*=====********!!!;;;;;;:::::::~~~~~-~~,!*=***!!!!!!!!!**!!****!!!!!;--,,........,,,----,,      .   \n"
										+ "!*====********!!!;;;;;;;:::::::~~~~~~-,:===****!!!;;!!!***********!!!;,,,!,......,,,----,,    ....  \n"
										+ "**===********!!!!;;;;;;:::::~~:~~:~~~-,*=====***!!:;;!!;*!!**!*!!*!!!!~,,*... ..,,--~~~-,.   ...... \n"
										+ "****=****!!!!!!!;;;;:::::!-.,,,-~~~~-.;======***!:!;;:;;!!!!!!!**!!!!!;,,*;.  ..,,--~~-,.    ...... \n"
										+ "**==****!!!!!!!!;;;;;;;;:!~,   ,-~~~--*=======**!!;;:~::!!!!******!!**!~,*;  ..,--~~~~-,.       ....\n"
										+ "*====****!!!!!!!;!!;;;::::;:.   ---~.!=======$=**;;:--~;;!!!***********;-*!~ .,--~~~~~~,...      ..,\n"
										+ "***=***!!!!!!!!-.         :~:.   ,-,:*=========*!;;;:,~:;!!!!!!********!-**! ,--~~~~~~-,,,..      ..\n"
										+ "**==***!!!!!!:.            :!;    .,!=====$=$===!!!;::~;;;:!!*!*****===*~**!---~~~~:::-,,,,..     ..\n"
										+ "**==**!!!!!!~      ,--~~~~~~!*;~-.,:*===*=$=====***;:~:;;;::!****======*~***!-~~~::::~~--,..       .\n"
										+ "**==**!!!!;-.   .:=$$$$$=*=======,-!======$$$===***!;:;;;!;-;***========:=***~~~:::::~~~~-,.      ..\n"
										+ "**=*!~~:;;-,. .,=$$$============~-;=======$$====!**!!;;;;;!:-:;========*:==**:::::::::::~~,..  ...,.\n"
										+ "**=*~,...--,..-$$$=$$$$$$$$$===;-~*=======$$===*!!!;.;;;;;!;-~:;!======*:==**!:::::::;::~~,.    .,,.\n"
										+ "***;~,.....,,:$$=$$$$$=$$==$$$*--;========$$!=*:!-;!  ;;;;!!:~::;!=**==*:==*==::;::;;;;;:~, .    ...\n"
										+ "**!$=~-,.....*==$$$$$==$$$$===-~~=========$$=:;-.;;!~  ~!!!!*;::;!!**==!;==**=;;;:~:;;;;;~-.     ...\n"
										+ "**=$$==;~-,,..$$$$$$$$$$$=$==::~!====$$===$=*!~,. :!!  ..!**;::;;!!**==;:;==*=!;;;;:;;;;::~-.. .,,.,\n"
										+ "**$$$$==*;-,,:$$$$$$$$$$$==$;!;:=$===$====$=!;;,   ;!, ..;!**:!;;!!**==:;:=***!;;;;~:!;;;;;:-. ..,.,\n"
										+ "**$$====$=*~~$$$$$$$$$$$==$;**;*$====$==$===!-~    .:; .,-::=*=;;!!**=! :==***!;;;;*,;;;;;;:~-, ...,\n"
										+ "*=$$::;*$$==*$$$$$$$=$==$$;=$=;=$$===$=$$===*,   .   ~..,-~~;=*;;!***=; -!=*==*;;;;=,:;!!!;;;~.-....\n"
										+ "*$$!:~~:!=$$#$$$$#$$=!$$=*=$$**$$=*==$==$=*=*:.   .   ..,-~~:;*;;;!**=:  ~=*===;;;;=*~;;!!;;::-,..,.\n"
										+ "*#$:::::*$$$$$$$$#$==:*=*$$$$*$$$=*=$$==$=***;~    .   .,--~::*;:;!!***~.;=====;;;!=*,:;!!;;;;:~,,,,\n"
										+ "=$*::::;$$;$$$$$$$$$=:-=$$$$==$$$=*==$==$=*!*!:.    .  .,-~~::;!~:;;;=***======;;!*=*!:;!!!!;;;;---,\n"
										+ "$$!;;:;=$=~$$$$$$$$$*:-$$$$$=$$$$=*=$$$=$$=*;!;,.    . .,~~~~~~:-,,~:****======:;;*==*:;!*!!!!;;:~::\n"
										+ "$*!;;;!$=;!$$$$$$$$**;~$$$$$=$$$$=*=$$==$$=*;;!~-,   .-.-~~:~-,   ,!!=**======* ~;*===!!*****!!!;:;;\n"
										+ "$;!!!;=$*:*$$$$$$$=$=;~$$$$=$$$$$=*$$$==$$$=!:--:---. :~,~~:~.   .**===========  :*====!********!;;!\n"
										+ "*!!!!*$*;:$$$$=$$##$*;~$$$$$$$$$$=*=$$$==$$=!~~-..:-, -.-~~~,   ,**=$==========   !==$=!*********!!!\n"
										+ "!*!*!==!;;$$$==$$#$$=::=$$$$$$$$$===$$$==$$==!~~,.     ,-~::~---!=*=$====$$$$==.  -====**********!!!\n"
										+ "-~:;!=*;;!====!$$#$==::*$$$$#$$$$===$$$==$$$=*;;-,....,,~!*;;!;;**=$$=====$$$$=;  ,====**********!!!\n"
										+ "-~~:!=!;:*==!*$####=*~:!$$$###$$$===$$$=*$$$$==:;-,,--~!!!=;;*!=*=$$=======$$$$=; .$=*=***********!!\n"
										+ "~~--*!;;:*!!=#####$=!::;$$$##$$$$=*=$$$===$$$$==*---~!*!*!$!!!*==$#$$$===$==$$$$=: =$***************\n"
										+ "!:~!*::::*!=######==!::;$$$##$$$$==*$$$=*=$$$$==:~~~:*****$=!!==$$#$$$$===$$=$$$$=~$$$==****===*****\n"
										+ "=~!*~---*;*$$$$$#$==::::$$###$$$$==*=$$=*=$$$$=*;~~~:!*===$*!*$$$$#$$$$$=!$$$$$$$==$$$$=***=====****\n"
										+ "=;=;~.-;:*=#$=$##==*::::$$$##$$$$$=*=$$=**$$$$$==;~:::;==*=!!$##$$#$$$$$$*$$$$$$$==$$$$$*****==**=**\n"
										+ "*=*;;:,::=*=**##$==;;::~#$$##$$$$====$$=**=$$$$==*:;;;;$$**!*$##=##$$$$$$$$=$$$$$$=$$$$$*===========\n"
										+ "$=**;:-~*==*!*$$***--~~~$$$$$$$$$==*=$$=**=$$$$*=*~::;$$=*!!*$##=$$$$$$$$$$*=$$$$$==$$$$;***==**===*\n"
										+ "$***;-::*=*!*=$=*=!,-,-;$###$$$$$====$$$=*=$$$*=!;::;!$$$*!!==##=##=$$$$$$$==$$$$$$$$$$$.!*=========\n"
										+ "===*~~:;!!;!*$$=**-,,.-!$###$$$$$=!*=$$$===$$$$=!:::!!$$$*!!=$##=##$=$$$$$$===$$$$$$$$$$,:==========\n"
										+ "===!~:;**;~;*=*=*!...,,=$$$$$$$$$=:**=$$====$===*:~:!*$$$!!!=###=$$===$$$$$====$$$$$$$$$$=*****=====\n"
										+ "==***;!**!~;**==*~  ..,##$$$$$$$$=;===$$====$$=!*;:~!=$$$!!!$###=#$====$$$$$*==$$$$$$$$$$$!;;;!*====\n"
										+ "**===!*=**;:!=**!-~, .,#$#$$$$$$$=**==$$====$$$=!*::;=$$$!!!=$##$$$=$===$$$$*==$$=$$$$$$$$!;;;;;*===\n"
										+ "*=$$===$$==;===*:;;:~,~$$#$$$$$$$=*~==$$$$$$=$$$**!;;*#$$*!;=$##$$$=====$$$$**====$$$$$$$$*;;;;;*===\n"
										+ "=$$$$==$$$=====*!!!;;:*####$$$$$$=*~==$$$=$$=$$***!;;*#$#$!;*$###$$$====*#$$=*====$$$$$$$$==!!!;;!*=\n"
										+ "$$$$$====$=====!!!!!!:$####$$$$$$=*:==$$$=$$$=$$=*!!!!#$##*!;$###$$$====*$$$$**===$$$$$$$$=$$=!*!!!*\n"
										+ "$$$$$$$=======;!*!!!!!$####$$$$$$=*:=$$$$=$$$=$$$=**!!#$##=!;=###$=#=$===*#$$!*===$$$$$$$$$$$$=!**!*\n"
										+ "$$$$$$$$$====*;;!*!!!**$####$$$$==*:*$$$$$$$$$$$$$$*!!$####!!=###$=#=====*$#$;!*=$$$#$$$$$==$$$*!=**\n"
										+ "$$$$$$$$$===*;;;;!!*!===####$$$$==!~;$$$$$$$$$$$$$$=!!!####*!=####=$*=====*##=~*=$$$$$$##$===$=*!*!*\n"
										+ "$$$$$$$!$===;!!!;;!!!$=!$###$$$===!-~$$$$$$$$$$=$$$$**!$###=!*####*==!$===*##$~;=$$$$$$#$$**=!!=!**!\n");

						while (true) {
							System.out.println(
									"                   ______   _____  _______ _______  ______ _______ _____ ______ ");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(
									"                   |_____] |     | |______ |______ |_____/ |_____|   |   |     \\");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(
									"                   |_____] |_____| ______| ______| |    \\_ |     | __|__ |_____/\n");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							break;
						}
						System.out.println("행동을 입력해주세요.");
						System.out.println("1. 캐릭터 생성 | 2. 캐릭터 선택 | 3. 로그아웃");
						System.out.print(">>> ");

						select = 0;
						try {
							select = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("숫자만 입력해주세요.");
							continue;
						}

						if (select == 1) {
							while (true) {
								// 캐릭터 생성
								System.out.println("직업을 선택해주세요.");
								System.out.println("1. Warrior | 2. Sorceress | 3. HolyKnight | 4. Thief | 5. 나가기");
								System.out.print(">>> ");

								select = 0;
								try {
									select = Integer.parseInt(sc.nextLine());
								} catch (NumberFormatException e) {
									System.out.println("숫자만 입력해주세요.");
									continue;
								}

								if (select == 1) {
									// 전사
									System.out.println("Warrior를 선택하셨습니다.");
									System.out.println("해당 캐릭터의 기본 능력치\n");

									WarriorVO temp = new WarriorVO();
									System.out.println(temp.toString() + "\n");

									System.out.println("해당 캐릭터의 별명을 지정해주세요.");
									System.out.print(">>> ");
									String name = sc.nextLine();

									int result = charService.warChar(name, id);

									if (result > 0) {
										System.out.println("캐릭터가 생성되었습니다.");
										invenService.saveId(id);
										break;
									} else {
										System.out.println("생성 실패");
									}
								} else if (select == 2) {
									// TODO 마법사

								} else if (select == 3) {
									// TODO 성기사

								} else if (select == 4) {
									// TODO 도적

								} else if (select == 5) {
									// 나가기
									System.out.println("처음 화면으로 돌아갑니다.");
									break;
								} else {
									System.out.println("잘못 입력하셨습니다.");
								}
							}
						} else if (select == 2) {
							while (true) {
								// 생성 캐릭터 조회

								ArrayList<WarriorVO> chaList = charService.chaList(id);

								for (int i = 0; i < chaList.size(); i++) {
									System.out.println(chaList.get(i).toListString());
								}

								System.out.println();
								System.out.println("전투에 참여할 캐릭터의 번호를 입력해주세요. (나가기 : 5번)");
								System.out.print(">>> ");

								int no1 = 0;
								try {
									no1 = Integer.parseInt(sc.nextLine());
								} catch (NumberFormatException e) {
									System.out.println("숫자만 입력해주세요.");
									continue;
								}
								if (no1 == 5) {
									break;
								} else {

									// TODO 캐릭터 선택 접속후 프로세스
									while (true) {
										if (chaList.get(no1 - 1) != null) {
											WarriorVO user = chaList.get(no1 - 1);
											int hp = user.getLevel() * 300;
											int mp = user.getLevel() * 150;
											System.out.println("\n" + user.getName() + " 으(로) 접속하였습니다.\n");
											System.out.println(
													"===============================================================================");
											System.out.println("현재 캐릭터 스텟: " + user.toString());
											System.out.println(
													"===============================================================================\n");
											System.out.println("1. 전투시작 | 2. 상점 | 3. 캐릭터 변경 | 4. 로그아웃 ");
											System.out.print(">>> ");

											select = 0;
											try {
												select = Integer.parseInt(sc.nextLine());
											} catch (NumberFormatException e) {
												System.out.println("숫자만 입력해주세요.");
												continue;
											}
											if (select == 1) {
												System.out.println("\n보스레이드에 오신걸 환영합니다.\n");
												// TODO 보스 이름 적어넣기
												System.out.println("공략할 보스를 선택해주세요.");
												ArrayList<BossVO> bosList = bosService.bosList();

												System.out.println(
														"\n==================== 보스 선택 ========================");
												for (int i = 0; i < bosList.size(); i++) {
													System.out.println(bosList.get(i).toString());
												}
												System.out.println(
														"=======================================================\n");
												System.out.print(">>> ");

												select = 0;
												try {
													select = Integer.parseInt(sc.nextLine());
												} catch (NumberFormatException e) {
													System.out.println("숫자만 입력해주세요.");
													continue;
												}
												System.out.println(
														"\n" + bosList.get(select - 1).getName() + " 레이드를 시작합니다.\n");

												// TODO 레이드 시작 프로세스
												loopOutBattle: outerLoop: while (true) {
													System.out.println(
															"현재 보스 체력: " + bosList.get(select - 1).getHp() + "\n");
													System.out.println("현재 " + user.getName() + " 체력: " + user.getHp());
													System.out.println(
															"현재 " + user.getName() + " 마나: " + user.getMp() + "\n");
													System.out.println("행동을 선택해주세요.");
													System.out.println("1. 일반공격 | 2. 스킬공격 | 3. 소지품 | 4. 도망가기(패널티 부여)");
													System.out.print(">>> ");

													int stance = 0;
													try {
														stance = Integer.parseInt(sc.nextLine());
													} catch (NumberFormatException e) {
														System.out.println("숫자만 입력해주세요.");
														continue;
													}
													// TODO 전투시작 프로세스
													while (true) {
														if (stance == 1) {
															// 일반공격
															user.attack(bosList.get(select - 1));
															bosService.bosPattern(bosList.get(select - 1), user, sc);
															if (user.getHp() <= 0) {
																System.out.println(
																		"\n" + user.getName() + " 이(가) 사망하였습니다.\n");
																System.out.println(user.getMoney() + " 만큼 골드를 벌었습니다.");
																// 사망시 페널티 구간
																user.setHp(150);
																System.out.println("Hp에 패널티를 입습니다: 현재 hp 150");
																user.setMp(80);
																System.out.println("Mp에 패널티를 입습니다: 현재 hp 80");
																user.setMoney(user.getMoney() - (70 * user.getLevel()));
																System.out.println("현재 소지금: " + user.getMoney());
																System.out.println("초기화면으로 돌아갑니다.\n");
																break outerLoop;
															} else if (bosList.get(select - 1).getHp() <= 0) {
																break outerLoop;
															}
															break;
														} else if (stance == 2) {
															// TODO 스킬공격
															System.out.println("스킬을 선택해주세요.");
															System.out.println(
																	"1. 파워스트라이크 (MP-40) | 2. 버스트캐넌 (MP-100) | 3. 돌아가기");
															System.out.print(">>> ");

															stance = 0;
															try {
																stance = Integer.parseInt(sc.nextLine());
															} catch (NumberFormatException e) {
																System.out.println("숫자만 입력해주세요.");
																continue;
															}

															if (stance == 1) {
																// 파워스트라이크
																user.sklPower(bosList.get(select - 1));
																break;
															} else if (stance == 2) {
																// 버스트 캐넌
																user.sklCanon(bosList.get(select - 1));
																break;
															} else if (stance == 3) {
																// 나가기
																break;
															}
														} else if (stance == 3) {
															// 소지품
															for (int i = 0; i < 3; i++) {
																inv.addAll(invenService.invDrugList(id,
																		itemSerivce.itmList().get(i).getIname()));
															}
															System.out.println();
															System.out.println(
																	"\n================================== 인벤토리 ===============================");
															for (int i = 0; i < 3; i++) {
																System.out.println(inv.get(i));
															}
															System.out.println(
																	"===========================================================================");
															System.out
																	.println("\n소지품 번호를 선택해주세요(1 ~ 3번) | 그 외 번호는 나가기 ");
															System.out.print(">>> ");

															int no = 0;
															try {
																no = Integer.parseInt(sc.nextLine());
															} catch (NumberFormatException e) {
																System.out.println("숫자만 입력해주세요.");
																continue;
															}

															if (no == 1) {
																// HP물약 사용
																if (inv.get(no - 1).getIcount() == 0) {
																	System.out.println("더이상 물약을 사용할 수 없습니다.");
																	break;
																} else if (user.getHp() >= hp) {
																	System.out.println("체력이 가득차 사용할 수 없습니다.");
																	break;
																}
																System.out.println("HP물약을 사용하셨습니다.");
																itemSerivce.drug(user, inv.get(no - 1));
																inv.get(no - 1)
																		.setIcount(inv.get(no - 1).getIcount() - 1);
																System.out.println(
																		"물약 남은 횟수: " + inv.get(no - 1).getIcount());

															} else if (no == 2) {
																// TODO MP물약
																if (inv.get(no - 1).getIcount() == 0) {
																	System.out.println("더이상 물약을 사용할 수 없습니다.");
																	break;
																} else if (user.getMp() >= mp) {
																	System.out.println("마나가 가득차 사용할 수 없습니다.");
																	break;
																}
																System.out.println("MP물약을 사용하셨습니다.");
																itemSerivce.drug(user, inv.get(no - 1));
																inv.get(no - 1)
																		.setIcount(inv.get(no - 1).getIcount() - 1);
																System.out.println(
																		"물약 남은 횟수: " + inv.get(no - 1).getIcount());

															} else if (no == 3) {
																// TODO 부활의 깃털

															} else if (no > 3) {
																break;
															} else {
																System.out.println("잘못 입력하셨습니다.");
																continue;
															}

														} else if (stance == 4) {
															// TODO 도망가기(패널티 메소드구현)
															System.out.println(
																	"\n  " + user.getMoney() + " 만큼 골드를 벌었습니다.\n");
															user.setHp(150);
															System.out.println("Hp에 패널티를 입습니다: 현재 hp 150");
															user.setMp(80);
															System.out.println("Mp에 패널티를 입습니다: 현재 hp 80\n");
															user.setMoney(user.getMoney() - (30 * user.getLevel()));
															System.out.println("현재 소지금: " + user.getMoney());
															System.out.println("\n초기화면으로 돌아갑니다.\n");
															break loopOutBattle;
														}
													}
												}
											} else if (select == 2) {
												// TODO 상점
												ArrayList<ItemVO> itmList = itemSerivce.itmList();
												System.out.println(
														"\n================================== 인벤토리 ===============================");
												for (int i = 0; i < invenService.invList(id).size(); i++) {
													System.out.println(invenService.invList(id).get(i));
												}
												System.out.println(
														"===========================================================================\n");

												System.out.println("\n=================== 상점 ==================");
												for (int i = 0; i < itmList.size(); i++) {
													System.out.println(itmList.get(i).toString());
												}
												System.out.println("===========================================");
												loopOutItem: while (true) {
													System.out.println("현재 소지금: " + user.getMoney() + " 골드 입니다.\n");

													System.out.println("상품은 위에서부터 1번입니다.");
													System.out.println("상품번호를 선택해주세요. | 나가기(5번)");
													System.out.print(">>> ");

													int no = 0;
													try {
														no = Integer.parseInt(sc.nextLine());
													} catch (NumberFormatException e) {
														System.out.println("숫자만 입력해주세요.");
														continue;
													}
													while (true) {
														if (no == 1) {
															ItemVO temp2 = itmList.get(no - 1);
															System.out.println("수량을 입력해주세요.");
															System.out.print(">>> ");
															no = 0;
															try {
																no = Integer.parseInt(sc.nextLine());
															} catch (NumberFormatException e) {
																System.out.println("숫자만 입력해주세요.");
																continue;
															}
															itemSerivce.buyItem(temp2, user, inven, inv, id, no);
															invenService.saveDrug(inven);
															break;

														} else if (no == 2) {
															ItemVO temp2 = itmList.get(no - 1);
															System.out.println("수량을 입력해주세요.");
															System.out.print(">>> ");
															no = 0;
															try {
																no = Integer.parseInt(sc.nextLine());
															} catch (NumberFormatException e) {
																System.out.println("숫자만 입력해주세요.");
																continue;
															}
															itemSerivce.buyItem(temp2, user, inven, inv, id, no);
															invenService.saveDrug(inven);
															break;
														} else if (no == 3) {
															ItemVO temp2 = itmList.get(no - 1);
															System.out.println("수량을 입력해주세요.");
															System.out.print(">>> ");
															no = 0;
															try {
																no = Integer.parseInt(sc.nextLine());
															} catch (NumberFormatException e) {
																System.out.println("숫자만 입력해주세요.");
																continue;
															}
															itemSerivce.buyItem(temp2, user, inven, inv, id, no);
															invenService.saveDrug(inven);
															break;
														} else if (no == 4) {
															ItemVO temp2 = itmList.get(no - 1);
															itemSerivce.buyWeapon(temp2, user, inven, inv, id);
															invenService.saveWeapon(temp2, id, 1);
															break;
														} else if (no == 5) {
															// 나가기
															break loopOutItem;
														}
													}
												}
											} else if (select == 3) {
												// TODO 캐릭터 변경 (DB저장)

											} else if (select == 4) {
												// 로그아웃
												System.out.println("로그아웃 합니다.");
												charService.saveChar(user);
												break;
											} else {
												System.out.println("잘못 입력하셨습니다.");
											}
										}
									}
								}
							}
						} else if (select == 3) {
							// 로그아웃
							System.out.println("로그아웃 합니다.");
							break;
						} else {
							System.out.println("잘못 입력하셨습니다.");
						}
					}
				}
			} else if (select == 3) {
				// 종료
				System.out.println("종료합니다.");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}
