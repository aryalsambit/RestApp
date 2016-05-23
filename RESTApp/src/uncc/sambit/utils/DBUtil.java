package uncc.sambit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private final static String URL="jdbc:mysql://localhost:3306/emp_db";
	private final static String USER="root";
	private final static String PASSWORD="*********";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection connectToDB(){
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.print("SUccessful");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Failed");
		}
		
		return con;
	}
	
	public static void closeResources(Connection con, PreparedStatement ps, ResultSet rs){
		try {
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args){
		DBUtil.connectToDB();
	}
	
}
