import java.sql.*;
public class HelloWorld { 
   public static void main(String[] args) { 
	   Class.forName("com.mysql.jdbc.Driver"); 
	   Connection conn = 
	   DriverManager.getConnection(
	   "jdbc:mysql://hostname:port/dbname",
	   "username", "password"); 
	   conn.close();
   }
}
