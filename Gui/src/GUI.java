import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GUI {
 public static void main(String args[]) throws ClassNotFoundException, SQLException{
  Statement stmt = null;
  ResultSet rs = null;
  ResultSet input = null;
  Class.forName("oracle.jdbc.driver.OracleDriver");
  Connection myCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost","system","1234skrj");
  stmt = myCon.createStatement();
  rs = stmt.executeQuery("select stats_mode(customer) from DB where room_number = 102 group by room_number");
  //input = stmt.executeQuery("desc pc");
  System.out.println("success");
  
  while(rs.next()){
   System.out.println(rs.getString(1));
   System.out.println("");
  }
  
  System.out.println("JDBC가 정상적으로 작동합니다.");
  
  rs.close();
  stmt.close();
  myCon.close();
 }
}
