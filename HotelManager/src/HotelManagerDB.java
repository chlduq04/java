import java.sql.*;

public class HotelManagerDB {
	private static Connection connection;
	public static Connection getConnection(){
		if(connection == null){
			try{
				// Oracle �� ����̹�
				//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				// MySQL �� ����̹�
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
			}
			catch(java.lang.ClassNotFoundException e){
				System.err.print(e.getMessage());
			}
			try{
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost", "system", "1234skrj");
			}
			catch(SQLException e){
				System.err.print(e.getMessage());
			}
		}
		return connection;
	}
}
