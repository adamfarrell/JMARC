import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
//import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Driver; 


public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Statement stmt = null;
//		ResultSet rs = null;
//		Connection conn = null;
		 String connectionUrl = "jdbc:sqlserver://localhost\\SQL:4373;" +  
		         "databaseName=SectionSchedule;user=James;password=Rpaint11";
		
		 Connection con = null;  
	      Statement stmt = null;  
	      ResultSet rs = null;
		try {
			
			  
			
		      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		         con = DriverManager.getConnection(connectionUrl);
		         System.out.println("Database connection established");  
		         
		         String SQL = "SELECT * FROM COURSE";  
		         stmt = con.createStatement();  
		         rs = stmt.executeQuery(SQL);  
		  
		         // Iterate through the data in the result set and display it.  
		         while (rs.next()) {  
		            System.out.println(rs.getString(4) + " " + rs.getString(6));  
		            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
		         } 
		         
//			Class.forName("com.mysql.jdbc.Driver");  
//			Connection con=DriverManager.getConnection(  
//			"jdbc:mysql://DESKTOP-SNO1RR1\\SQL:3306/SectionSchedule","James","Rpaint11");  
//			System.out.println("Database connection established");
//			//here sonoo is database name, root is username and password  
//			Statement stmt=con.createStatement();  
//			ResultSet rs=stmt.executeQuery("select * from Course");  
//			while(rs.next())  
//			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//			con.close();  
			
			
			
            // The newInstance() call is a work around for some
            // broken Java implementations
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			
//			String userName = "James";
//			String password = "Rpaint11"; 
//			String url = "jdbc:mysql://SQL/SectionSchedule";
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			
			
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SectionSchedule?" + "user=DESKTOP-SNO1RR1\\JamesPainter&password=");
		//	conn = DriverManager.getConnection("jdbc:mysql://localhost/SectionSchedule?");
			
			
			
//			MysqlDataSource dataSource = new MysqlDataSource();
//			dataSource.setUser("James");
//			dataSource.setPassword("Rpaint11");
//			dataSource.setServerName("DESKTOP-SNO1RR1\\SQL");
//			Connection conn = dataSource.getConnection();
			

			//Statement stmt = conn.createStatement();
		
			
//			conn = DriverManager.getConnection(url, userName, password);
//			System.out.println("Database connection established");			
			
			
//			MysqlDataSource dataSource = new MysqlDataSource();
//			dataSource.setUser("DESKTOP-SNO1RR1\\JamesPainter");
//			dataSource.setPassword("Rpaint14");
//			dataSource.setServerName("DESKTOP-SNO1RR1\\SQLEXPRESS\\SectionSchedule");
//			
//			Connection conn = dataSource.getConnection();
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT courseID FROM COURSE WHERE coursID = 2");
//			System.out.println(rs);
//			
//			rs.close();
//			stmt.close();
//			conn.close();
//			
			//rs = stmt.executeQuery("SELECT courseID FROM COURSE WHERE coursID = 2");
			
			
			
			
			
			
        } catch (Exception ex) { //SQLException
            // handle the error
        	 	System.err.println("Cannot connect to database server");
        	  	System.out.println("SQLException: " + ex.getMessage());
        	  	ex.printStackTrace(); 
        	  //  System.out.println("SQLState: " +  ex.getSQLState());
        	   // System.out.println("VendorError: " + ex.getErrorCode());
        }
		 finally {  
	         if (rs != null) try { rs.close(); } catch(Exception e) {}  
	         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	         if (con != null) try { con.close(); } catch(Exception e) {}  
	      }
		
		
	}

}
