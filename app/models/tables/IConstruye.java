package models.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class IConstruye {
	public Long idDoc;
	public String numOc;

	public IConstruye(Long idDoc, String numOc) {
		super();
		this.idDoc = idDoc;
		this.numOc = numOc;
	}
	
	public IConstruye() {
		super();
	}
	
	
	
	public static Map<String,Long> mapNumOcVsIdDoc (Connection con, String base) {
		Map<String,Long> map = new HashMap<String,Long>();
		try {
			PreparedStatement smt = con
							.prepareStatement("Select idDoc, numOc from `"+base+"`.`iConstruye`;");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(2), rs.getLong(1));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(map);
	}
	
	public static boolean create (Connection con, String base, Long idDoc, String numOc) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
							.prepareStatement("insert into `"+base+"`.`iConstruye` (idDoc,numOc) values (?,?);");
			smt.setLong(1, idDoc);
			smt.setString(2, numOc);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean delete (Connection con, String base, String numOc) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
							.prepareStatement("delete from `"+base+"`.`iConstruye` where numOc = ?;");
			smt.setString(1, numOc);
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	
	public static boolean insert (Connection con, String base, String insert) {
		boolean flag = false;
		try {
			PreparedStatement smt = con
							.prepareStatement("insert into `"+base+"`.`iConstruye` (idDoc,numOc) values "+ insert + ";");
			smt.executeUpdate();
			smt.close();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(flag);
	}
	

}
