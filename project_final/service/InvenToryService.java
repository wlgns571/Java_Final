package project_final.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import project_final.dao.InvenToryDao;
import project_final.jdbc.ConnectionPool;
import project_final.model.InvenToryVO;
import project_final.model.ItemVO;

public class InvenToryService {
	private static InvenToryService instance = new InvenToryService();
	private InvenToryDao dao = InvenToryDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();
	
	private InvenToryService() {}
	
	public static InvenToryService getInstance() {
		if (instance == null) {
			instance = new InvenToryService();
		}
		
		return instance;
	}
	// 인벤토리 아이디 저장 (물약관련)
	public int saveId(String id, String iname, int idmg, int ihp, int imp, int icount) {
		Connection con = cp.getConnection();
		
		try {
			return dao.saveId(con, id, iname, idmg, ihp, imp, icount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return 0;
	}
	
	// 구매 후 인벤토리 저장
	public int saveWeapon(ItemVO item, String id, int cntno) {
		Connection con = cp.getConnection();
		
		try {
			return dao.saveWeapon(con, item, id, cntno);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return 0;
	}
	// 인벤토리 리스트 조회
		public ArrayList<InvenToryVO> invList(String id) {
			Connection con = cp.getConnection();
			
			try {
				return dao.invList(con, id);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					cp.releaseConnection(con);
				}
			}
			return new ArrayList<InvenToryVO>();
		}
	
	// 인벤토리 리스트 조회 (물약)
	public ArrayList<InvenToryVO> invDrugList(String id, String name) {
		Connection con = cp.getConnection();
		
		try {
			return dao.invDrugList(con, id, name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return new ArrayList<InvenToryVO>();
	}
	
	// 인벤토리 물약 업데이트
	public int saveDrug(InvenToryVO inven) {
		Connection con = cp.getConnection();
		
		try {
			return dao.saveDrug(con, inven);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return 0;
	}
	
	public void saveId(String id) {
		ItemService itemService = ItemService.getInstance();
		for (int i = 0; i < 3; i++) {
		saveId(id, itemService.itmList().get(i).getIname(), itemService.itmList().get(i).getIdmg(), 
				itemService.itmList().get(i).getIhp(), itemService.itmList().get(i).getImp(), 0);
		}
	}
}
