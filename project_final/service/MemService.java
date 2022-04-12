package project_final.service;

import java.sql.Connection;
import java.sql.SQLException;

import project_final.dao.MemberDao;
import project_final.jdbc.ConnectionPool;
import project_final.model.MemberVO;

public class MemService {
	private static MemService instance = new MemService();
	private MemberDao dao = MemberDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();
	
	private MemService() {}
	
	public static MemService getInstance() {
		if (instance == null) {
			instance = new MemService();
		}
		
		return instance;
	}
	
	// 회원가입
	public int registMem(String name, String id, String pw) {
		Connection con = cp.getConnection();
		
		try {
			return dao.registMem(con, name, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return 0;
	}
	
	// 회원정보 리턴 (id값)
	public MemberVO getMem(String id) {
		Connection con = cp.getConnection();
		
		try {
			return dao.getMem(con, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				cp.releaseConnection(con);
			}
		}
		return new MemberVO();
	}
	
}
