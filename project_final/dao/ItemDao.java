package project_final.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project_final.model.ItemVO;

public class ItemDao {
	private static ItemDao instance = new ItemDao();
	
	private ItemDao() {}
	
	public static ItemDao getInstance() {
		if (instance == null) {
			instance = new ItemDao();
		}
		
		return instance;
	}
	
	// 아이템 조회
	public ArrayList<ItemVO> itmList(Connection conn) throws SQLException {
		StringBuffer query = new StringBuffer();

		query.append("SELECT	  		  	      ");
		query.append("		i_name				  ");
		query.append("	  , i_dmg				  ");
		query.append("	  , i_hp  				  ");
		query.append("	  , i_mp  				  ");
		query.append("	  , i_price  			  ");
		query.append("FROM				    	  ");
		query.append("		item		    	  ");
		query.append("ORDER BY	    	 		  ");
		query.append("		i_price	 	asc		  ");

		PreparedStatement ps = conn.prepareStatement(query.toString());

		ResultSet rs = ps.executeQuery();
		
		ArrayList<ItemVO> result = new ArrayList<ItemVO>();
		
		while (rs.next()) {
			ItemVO temp = new ItemVO();
			
			temp.setIname(rs.getString("i_name"));
			temp.setIdmg(rs.getInt("i_dmg"));
			temp.setIhp(rs.getInt("i_hp"));
			temp.setImp(rs.getInt("i_mp"));
			temp.setPrice(rs.getInt("i_price"));
			
			result.add(temp);
		}
		
		if (ps != null) {ps.close();}
		if (rs != null) {rs.close();}

		return result;
	}
}
