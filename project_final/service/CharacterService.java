package project_final.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import project_final.dao.CharacterDao;
import project_final.jdbc.ConnectionPool;
import project_final.model.WarriorVO;

public class CharacterService {
	private static CharacterService instance = new CharacterService();
	private CharacterDao dao = CharacterDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();

	private CharacterService() {
	}

	public static CharacterService getInstance() {
		if (instance == null) {
			instance = new CharacterService();
		}

		return instance;
	}

	// 전사 캐릭터 생성
	public int warChar(String name, String id) {
		Connection con = cp.getConnection();

		try {
			return dao.warChar(con, name, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return 0;
	}

	// 캐릭터 조회 (아이디별)
	public ArrayList<WarriorVO> chaList(String id) {
		Connection con = cp.getConnection();

		try {
			return dao.chaList(con, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return new ArrayList<WarriorVO>();
	}

	// 캐릭터 저장 (아이디별)
	public int saveChar(WarriorVO war) {
		Connection con = cp.getConnection();

		try {
			return dao.saveChar(con, war);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return 0;
	}
}
