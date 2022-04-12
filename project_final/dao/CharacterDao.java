package project_final.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project_final.model.WarriorVO;

public class CharacterDao {
	private static CharacterDao instance = new CharacterDao();

	private CharacterDao() {
	}

	public static CharacterDao getInstance() {
		if (instance == null) {
			instance = new CharacterDao();
		}

		return instance;
	}
	
	// 캐릭터 조회 (아이디 별)
	public ArrayList<WarriorVO> chaList(Connection conn, String id) throws SQLException {
		StringBuffer query = new StringBuffer();

		query.append("SELECT	  		  	      ");
		query.append("		war_id				  ");
		query.append("	  , war_name			  ");
		query.append("	  , war_exp				  ");
		query.append("	  , war_dmg				  ");
		query.append("	  , war_level  			  ");
		query.append("	  , war_mp  			  ");
		query.append("	  , war_hp  			  ");
		query.append("	  , war_money  			  ");
		query.append("FROM				    	  ");
		query.append("		warrior		    	  ");
		query.append("WHERE				    	  ");
		query.append("		war_id = ?	    	  ");
		query.append("ORDER BY	    	 		  ");
		query.append("		war_level	 desc	  ");

		PreparedStatement ps = conn.prepareStatement(query.toString());
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		
		ArrayList<WarriorVO> result = new ArrayList<WarriorVO>();

		while (rs.next()) {
			WarriorVO temp = new WarriorVO();

			temp.setId(rs.getString("war_id"));
			temp.setName(rs.getString("war_name"));
			temp.setExp(rs.getInt("war_exp"));
			temp.setLevel(rs.getInt("war_level"));
			temp.setDamage(rs.getInt("war_dmg"));
			temp.setHp(rs.getInt("war_hp"));
			temp.setMp(rs.getInt("war_mp"));
			temp.setMoney(rs.getInt("war_money"));

			result.add(temp);
		}

		if (ps != null) {ps.close();}
		if (rs != null) {rs.close();}

		return result;
	}

	// 아이디별 캐릭터 생성 (전사)
	public int warChar(Connection conn, String name, String id) throws SQLException {
		StringBuffer query = new StringBuffer();

		query.append("insert into 		        ");
		query.append("			warrior		    ");
		query.append("values(				    ");
		query.append("		     ?			    ");
		query.append("  	   , ?			    ");
		query.append("  	   , ?			   	");
		query.append("  	   , ?			   	");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append("		)			        ");

		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		WarriorVO temp = new WarriorVO(name, id);
		
		int idx = 1;
		ps.setString(idx++, temp.getId());
		ps.setString(idx++, temp.getName());
		ps.setInt(idx++, temp.getLevel());
		ps.setInt(idx++, temp.getExp());
		ps.setInt(idx++, temp.getDamage());
		ps.setInt(idx++, temp.getHp());
		ps.setInt(idx++, temp.getMp());
		ps.setInt(idx++, temp.getMoney());
		ps.setInt(idx++, temp.getUpgradehp());
		ps.setInt(idx++, temp.getUpgrademp());
		ps.setInt(idx++, temp.getUpgradedmg());
		ps.setString(idx++, temp.getItem());
		
		
		int cnt = ps.executeUpdate();
		
		if(ps!= null)ps.close();
		
		return cnt;
	}
	
	// 캐릭터 업데이트 (나가기 or 로그아웃시)
	public int saveChar(Connection conn, WarriorVO war) throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("UPDATE					");
		query.append("		warrior				");
		query.append("SET 						");
		query.append("    war_level = ?			");
		query.append("  , war_exp = ?			");
		query.append("  , war_dmg = ?			");
		query.append("  , war_hp = ?			");
		query.append("  , war_mp = ?			");
		query.append("  , war_money = ?			");
		query.append("  , war_uphp = ?			");
		query.append("  , war_upmp = ?			");
		query.append("  , war_updmg = ?			");
		query.append("  , war_item = ?			");
		query.append("WHERE 	1=1				");
		query.append("  AND war_id = ?			");
		query.append("  AND war_name = ?		");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		int idx = 1;
		
		ps.setInt(idx++, war.getLevel());
		ps.setInt(idx++, war.getExp());
		ps.setInt(idx++, war.getDamage());
		ps.setInt(idx++, war.getHp());
		ps.setInt(idx++, war.getMp());
		ps.setInt(idx++, war.getMoney());
		ps.setInt(idx++, war.getUpgradehp());
		ps.setInt(idx++, war.getUpgrademp());
		ps.setInt(idx++, war.getUpgradedmg());
		ps.setString(idx++, war.getItem());
		ps.setString(idx++, war.getId());
		ps.setString(idx++, war.getName());
		
		int cnt = ps.executeUpdate();
		
		if(ps != null) ps.close();
		
		return cnt;
	}
}
