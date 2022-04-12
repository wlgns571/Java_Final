package project_final.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project_final.model.MemberVO;


public class MemberDao {
	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	// 회원가입
	public int registMem(Connection conn, String name, String id, String pw) throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("INSERT INTO				");
		query.append("			member			");
		query.append("VALUES (					");
		query.append("		  ?					");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		)					");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		MemberVO temp = new MemberVO(id, name, pw);
		
		int idx = 1;
		ps.setString(idx++, temp.getName());
		ps.setString(idx++, temp.getId());
		ps.setString(idx++, temp.getPw());
		
		int cnt = ps.executeUpdate();
		
		if(ps!=null)ps.close();
		
		return cnt;
	}
	
	// 로그인 및 로그인시 아이디에 대한 중복체크
	public MemberVO getMem(Connection conn, String id) throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT					");
		query.append("	    mem_name 			");
		query.append("	  ,	mem_id				");
		query.append("	  , mem_pw 				");
		query.append("FROM						");
		query.append("		member				");
		query.append("WHERE 1=1					");
		query.append("	AND	mem_id = ?			");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		
		MemberVO temp = new MemberVO();
		
		while(rs.next()) {
			temp.setName(rs.getString("mem_name"));
			temp.setId(rs.getString("mem_id"));
			temp.setPw(rs.getString("mem_pw"));
		}
		
		if(ps!=null)ps.close();
		if(rs!=null)rs.close();
		
		return temp;
	}
	
	// 회원정보 업데이트
//	public int saveMem(Connection conn, MemberVO mem) throws SQLException {
//		StringBuffer query = new StringBuffer();
//		
//		query.append("UPDATE					");
//		query.append("		members				");
//		query.append("SET 						");
//		query.append("    dev_level = ?			");
//		query.append("  , dev_exp = ?			");
//		query.append("  , dev_money = ?			");
//		query.append("  , dev_upgrade_exp = ?	");
//		query.append("  , dev_upgrade_money = ?	");
//		query.append("WHERE 	1=1				");
//		query.append("  AND dev_id = ?			");
//		
//		PreparedStatement ps = conn.prepareStatement(query.toString());
//		
//		int idx = 1;
//	}
}
