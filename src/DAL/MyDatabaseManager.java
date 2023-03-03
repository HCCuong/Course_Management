package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDatabaseManager {
	private static Connection c;
	private static Statement s;
	private static PreparedStatement p;
	private static String port, dbName, dbUser, dbPassword;
	
	public MyDatabaseManager() {
		port = "127.0.0.1";
		dbUser = "root";
		dbName = "school2";
		dbPassword = "";
	}
	
	public static void connectDB() {
		String dbPath = "jdbc:mysql://" + port + "/" + dbName + 
						 "?useUnicode=yes&characterEncoding=UTF-8";
		try {
			c = (Connection) DriverManager.getConnection(dbPath, dbUser, dbPassword);
			s = c.createStatement();
			p = null;
		} 
		catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	public static Connection getConnection()
    {
        return c;
    }
	
	public ResultSet doReadQuery(String sql) {
		ResultSet rs = null;
		try {
			rs = s.executeQuery(sql);
		} 
		catch (SQLException ex) {
			Logger.getLogger(MyDatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return rs;
	}
	 
	public static void main(String[] args) {
			new MyDatabaseManager().connectDB();
		}
}	


