package project_final.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project_final.model.BossVO;

public class BossDao {
	private static BossDao instance = new BossDao();
	
	private BossDao() {}
	
	public static BossDao getInstance() {
		if (instance == null) {
			instance = new BossDao();
		}
		return instance;
	}
	
	// 보스 조회
	public ArrayList<BossVO> bosList(Connection conn) throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT	  		  	      ");
		query.append("		bos_name			  ");
		query.append("	  , bos_dmg				  ");
		query.append("	  , bos_hp	  			  ");
		query.append("	  , bos_randitem		  ");
		query.append("FROM				    	  ");
		query.append("		boss		    	  ");

		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<BossVO> result = new ArrayList<BossVO>();
		
		while (rs.next()) {
			BossVO temp = new BossVO();
			
			temp.setName(rs.getString("bos_name"));
			temp.setDamage(rs.getInt("bos_dmg"));
			temp.setHp(rs.getInt("bos_hp"));
			temp.setRanditem(rs.getString("bos_randitem"));
			
			result.add(temp);
		}
		
		if (ps != null) {ps.close();}
		if (rs != null) {rs.close();}

		return result;
	}
}
