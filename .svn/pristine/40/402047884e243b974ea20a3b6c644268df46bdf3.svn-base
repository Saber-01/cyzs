package ggg;
import java.sql.*;
public class main {
	
	public static void main(String[] args)

	 {

	  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

	  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Test";

	  String userName="sa";

	  String userPwd="123456";

	  try

	  {

	   Class.forName(driverName);

	   Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);

	    System.out.println("连接数据库成功");

	  }

	  catch(Exception e)

	  {

	   e.printStackTrace();

	   System.out.print("连接失败");

	  }    

	 }

}
