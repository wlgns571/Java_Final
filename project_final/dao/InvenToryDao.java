package project_final.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project_final.model.InvenToryVO;
import project_final.model.ItemVO;

public class InvenToryDao {
	private static InvenToryDao instance = new InvenToryDao();
	
	private InvenToryDao() {}
	
	public static InvenToryDao getInstance() {
		if (instance == null) {
			instance = new InvenToryDao();
		}
		
		return instance;
	}
	
	// 인벤토리 무기 추가
	public int saveWeapon(Connection conn, ItemVO item, String id, int cntno) throws SQLException {
		StringBuffer query = new StringBuffer();

		query.append("insert into 		        ");
		query.append("			inven		    ");
		query.append("values(				    ");
		query.append("		     ?			    ");
		query.append("  	   , ?			    ");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append(" 		   , ?				");
		query.append("		)			        ");

		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		InvenToryVO temp = new InvenToryVO(id, cntno);
		
		int idx = 1;
		ps.setString(idx++, temp.getIid());
		ps.setString(idx++, item.getIname());
		ps.setInt(idx++, item.getIdmg());
		ps.setInt(idx++, item.getIhp());
		ps.setInt(idx++, item.getImp());
		ps.setInt(idx++, temp.getIcount());
		
		int cnt = ps.executeUpdate();
		
		if(ps!= null)ps.close();
		
		return cnt;
		
	}
	// 인벤토리 리스트 추가 (물약관련)
	public int saveId(Connection conn, String iid, String iname, int idmg, int ihp, int imp, int icount) throws SQLException {
		StringBuffer query = new StringBuffer();

		query.append("INSERT INTO				");
		query.append("		inven				");
		query.append("Values (					");
		query.append("    		 ?				");
		query.append("    	   , ?				");
		query.append("    	   , ?				");
		query.append("    	   , ?				");
		query.append("    	   , ?				");
		query.append("    	   , ?				");
		query.append("    	   		)			");

		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		InvenToryVO temp = new InvenToryVO(iid, iname, idmg, ihp, imp, icount);
		
		int idx = 1;
		ps.setString(idx++, temp.getIid());
		ps.setString(idx++, temp.getIname());
		ps.setInt(idx++, temp.getIdmg());
		ps.setInt(idx++, temp.getIhp());
		ps.setInt(idx++, temp.getImp());
		ps.setInt(idx++, temp.getIcount());
		
		int cnt = ps.executeUpdate();
		
		if(ps!= null)ps.close();
		
		return cnt;
		
	}
	// 인벤토리 조회
		public ArrayList<InvenToryVO> invList(Connection conn, String id) throws SQLException {
			StringBuffer query = new StringBuffer();

			query.append("SELECT	  		  	      ");
			query.append("		i_id				  ");
			query.append("	  ,	i_name				  ");
			query.append("	  , i_dmg				  ");
			query.append("	  , i_hp  				  ");
			query.append("	  , i_mp  				  ");
			query.append("	  , i_count  			  ");
			query.append("FROM				    	  ");
			query.append("		inven		    	  ");
			query.append("WHERE				    	  ");
			query.append("		i_id = ?	    	  ");
			query.append("ORDER BY	    			  ");
			query.append("		i_name   asc    	  ");
			

			PreparedStatement ps = conn.prepareStatement(query.toString());
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			
			ArrayList<InvenToryVO> result = new ArrayList<InvenToryVO>();
			
			while (rs.next()) {
				InvenToryVO temp = new InvenToryVO();
				
				temp.setIid(rs.getString("i_id"));
				temp.setIname(rs.getString("i_name"));
				temp.setIdmg(rs.getInt("i_dmg"));
				temp.setIhp(rs.getInt("i_hp"));
				temp.setImp(rs.getInt("i_mp"));
				temp.setIcount(rs.getInt("i_count"));
				
				result.add(temp);
			}
			
			if (ps != null) {ps.close();}
			if (rs != null) {rs.close();}

			return result;
		}
	
	// 인벤토리 조회(물약)
	public ArrayList<InvenToryVO> invDrugList(Connection conn, String id, String name) throws SQLException {
		StringBuffer query = new StringBuffer();

		query.append("SELECT	  		  	      ");
		query.append("		i_id				  ");
		query.append("	  ,	i_name				  ");
		query.append("	  , i_dmg				  ");
		query.append("	  , i_hp  				  ");
		query.append("	  , i_mp  				  ");
		query.append("	  , i_count  			  ");
		query.append("FROM				    	  ");
		query.append("		inven		    	  ");
		query.append("WHERE				    	  ");
		query.append("		i_id = ?	    	  ");
		query.append("AND				    	  ");
		query.append("		i_name = ?	    	  ");
		query.append("ORDER BY	 			   	  ");
		query.append("		i_name asc	    	  ");
		

		PreparedStatement ps = conn.prepareStatement(query.toString());
		ps.setString(1, id);
		ps.setString(2, name);

		ResultSet rs = ps.executeQuery();
		
		ArrayList<InvenToryVO> result = new ArrayList<InvenToryVO>();
		
		while (rs.next()) {
			InvenToryVO temp = new InvenToryVO();
			
			temp.setIid(rs.getString("i_id"));
			temp.setIname(rs.getString("i_name"));
			temp.setIdmg(rs.getInt("i_dmg"));
			temp.setIhp(rs.getInt("i_hp"));
			temp.setImp(rs.getInt("i_mp"));
			temp.setIcount(rs.getInt("i_count"));
			
			result.add(temp);
		}
		
		if (ps != null) {ps.close();}
		if (rs != null) {rs.close();}

		return result;
	}
	
	// 인벤토리 물약 업데이트 
	public int saveDrug(Connection con, InvenToryVO inven) throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("UPDATE					");
		query.append("		inven				");
		query.append("SET 						");
		query.append("    i_id = ?				");
		query.append("  , i_count = ?			");
		query.append("WHERE 	1=1				");
		query.append("  AND i_name = ?			");
		
		PreparedStatement ps = con.prepareStatement(query.toString());
		
		int idx = 1;
		
		ps.setString(idx++, inven.getIid());
		ps.setInt(idx++, inven.getIcount());
		ps.setString(idx++, inven.getIname());
		
		int cnt = ps.executeUpdate();
		
		if(ps != null) ps.close();
		
		return cnt;
	}
}
