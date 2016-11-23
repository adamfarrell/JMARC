import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.*; 
//import com.mysql.jdbc.Statement;

//import com.mysql.jdbc.PreparedStatement;

import java.sql.Driver; 


public class Controller {

	private static ArrayList<String> professors = new ArrayList<String>(); 
	private static ArrayList<String> courses = new ArrayList<String>();
	private static ArrayList<String> campuses = new ArrayList<String>();
	private static ArrayList<String> buildings = new ArrayList<String>();
	private static ArrayList<String> classrooms = new ArrayList<String>();
	private static ArrayList<String> schedules = new ArrayList<String>();
	private static ArrayList<String> sections = new ArrayList<String>();
	private static ArrayList<String> test = new ArrayList<String>();
	private static final  String connectionUrl = "jdbc:sqlserver://localhost\\SQL:4373;" +  "databaseName=SectionSchedule;user=James;password=Rpaint11";

	public static ArrayList<String> getProfessors()
	{
		 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM PROFESSOR";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  
  
  
         // Iterate through the data in the result set and display it.  
         while (rs.next()) 
         {  
            professors.add(rs.getString(1));
            professors.add(rs.getString(2));
            professors.add(rs.getString(3));
            professors.add(rs.getString(4));
            professors.add(rs.getString(5));
            professors.add(rs.getString(6));
            professors.add(rs.getString(7));
            
         }
            
         con.close();  
            
         } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return professors; 
	}
	
	
	public static ArrayList<String> getCourses(){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 //	System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM COURSE";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  
 
 
        // Iterate through the data in the result set and display it.  
        while (rs.next()) 
        {  
           courses.add(rs.getString(1));
           courses.add(rs.getString(2));
           courses.add(rs.getString(3));
           courses.add(rs.getString(4));
           courses.add(rs.getString(5));
           courses.add(rs.getString(6));
           courses.add(rs.getString(7));
           
        }
           
        con.close();  
           
        } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
	     
		return courses; 
	}
	
	public static ArrayList<String> getCampuses(){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM CAMPUS";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


       // Iterate through the data in the result set and display it.  
       while (rs.next()) 
       {  
          campuses.add(rs.getString(1));
          campuses.add(rs.getString(2));
          campuses.add(rs.getString(3));
          campuses.add(rs.getString(4));
          
       }
          
       con.close();  
          
       } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return campuses; 
	}
	
	
	public static ArrayList<String> getBuildings(){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM BUILDING";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


     // Iterate through the data in the result set and display it.  
     while (rs.next()) 
     {  
        buildings.add(rs.getString(1));
        buildings.add(rs.getString(2));
        buildings.add(rs.getString(3));
        buildings.add(rs.getString(4));
        buildings.add(rs.getString(5));
        
     }
        
     con.close();  
        
     } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return buildings; 
	}
	
	
	public static ArrayList<String> getClassrooms(){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM CLASSROOM";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


      // Iterate through the data in the result set and display it.  
      while (rs.next()) 
      {  
         classrooms.add(rs.getString(1));
         classrooms.add(rs.getString(2));
         classrooms.add(rs.getString(3));
         classrooms.add(rs.getString(4));
         classrooms.add(rs.getString(5));
         classrooms.add(rs.getString(6));
         
      }
         
      con.close();  
         
      } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return classrooms; 
	}
	
	
	public static ArrayList<String> getSchedules(){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM SCHEDULE";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


      // Iterate through the data in the result set and display it.  
      while (rs.next()) 
      {  
         schedules.add(rs.getString(1));
         schedules.add(rs.getString(2));
         schedules.add(rs.getString(3));
         
         
      }
         
      con.close();  
         
      } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return schedules; 
	}
	
	public static ArrayList<String> getSections(){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM SECTION";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


     // Iterate through the data in the result set and display it.  
     while (rs.next()) 
     {  
        sections.add(rs.getString(1));
        sections.add(rs.getString(2));
        sections.add(rs.getString(3));
        sections.add(rs.getString(4));
        sections.add(rs.getString(5));
        sections.add(rs.getString(6));
        sections.add(rs.getString(7));
        sections.add(rs.getString(8));
        sections.add(rs.getString(9));
        sections.add(rs.getString(10));
        sections.add(rs.getString(11));
        
        
     }
        
     con.close();  
        
     } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return sections; 
	}
	
	public static ArrayList<String> getSections(String professorID){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM SECTION WHERE professorID = " + professorID;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


    // Iterate through the data in the result set and display it.  
    while (rs.next()) 
    {  
       sections.add(rs.getString(1));
       sections.add(rs.getString(2));
       sections.add(rs.getString(3));
       sections.add(rs.getString(4));
       sections.add(rs.getString(5));
       sections.add(rs.getString(6));
       sections.add(rs.getString(7));
       sections.add(rs.getString(8));
       sections.add(rs.getString(9));
       sections.add(rs.getString(10));
       sections.add(rs.getString(11));
       
       
    }
       
    con.close();  
       
    } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return sections; 
	}
	
	public static ArrayList<String> getClassroomSections(String classroomID){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM SECTION WHERE classroomID = " + classroomID;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


   // Iterate through the data in the result set and display it.  
   while (rs.next()) 
   {  
      sections.add(rs.getString(1));
      sections.add(rs.getString(2));
      sections.add(rs.getString(3));
      sections.add(rs.getString(4));
      sections.add(rs.getString(5));
      sections.add(rs.getString(6));
      sections.add(rs.getString(7));
      sections.add(rs.getString(8));
      sections.add(rs.getString(9));
      sections.add(rs.getString(10));
      sections.add(rs.getString(11));
      
      
   }
      
   con.close();  
      
   } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return sections; 
	}
	
	public static ArrayList<String> getSectionsTime(String classroomID, String professorID){
		
		
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT startTime, endTime FROM SECTION WHERE classroomID = " + classroomID + "AND professorID = " + professorID;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


  // Iterate through the data in the result set and display it.  
  while (rs.next()) 
  {  
     sections.add(rs.getString(1));
     sections.add(rs.getString(2));
    
     
  }
     
  con.close();  
     
  } catch (Exception ex) 
	{ 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	 }
		
		return sections; 
	}
	
	public static void updateProfessor(String professorID, String updateColumn, String UPDATE)
	{
		 
		 Connection con = null;  
	     
	     try {
	    	 
	    	 
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "UPDATE PROFESSOR SET "+ updateColumn +" = "+ UPDATE + "WHERE professorID = " + professorID;
	    	 	PreparedStatement ps = con.prepareStatement(SQL);
	    	 	ps.executeUpdate();
	    	 	
  
	    	 	con.close();  
            
         } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		 
	}
	
	public static void deleteProfessor(String professorID)
	{
		
		 Connection con = null;  
	     
	     try {
	    	 
	    	 
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "DELETE PROFESSOR WHERE professorID = " + professorID;
	    	 	PreparedStatement ps = con.prepareStatement(SQL);
	    	 	ps.executeUpdate();
	    	 	
  
	    	 	con.close();  
            
         } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

         int j = 0; 
		 test = getProfessors(); 
		 System.out.println(" ");
		 System.out.println("________PROFESSORS________");
		 for(int i=1; i <= test.size(); i++)
		 {
			//System.out.print(", ");
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%7==0)
			{
				System.out.println(" ");
			}
			
		 }
		 //clear test ArrayList 
		 test.clear();
		 
		 test = getCourses(); 
		 System.out.println(" ");
		 System.out.println("________COURSES___________");
		 for(int i=1; i <= test.size(); i++)
		 {
			
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%7 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		 
		 test = getCampuses();
		 System.out.println(" ");
		 System.out.println("________CAMPUS________");
		 for(int i=1; i <= test.size(); i++)
		 {
			
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%4 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		 
		 test = getBuildings(); 
		 System.out.println(" ");
		 System.out.println("________BUILDING________");
		 for(int i=1; i <= test.size(); i++)
		 {
			
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%5 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		 
		 test = getClassrooms(); 
		 System.out.println(" ");
		 System.out.println("________CLASSROOM________");
		 for(int i=1; i <= test.size(); i++)
		 {
			
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%6 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		 
		 test = getSchedules(); 
		 System.out.println(" ");
		 System.out.println("________SCHEDULE________");
		 for(int i=1; i <= test.size(); i++)
		 {
			
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%3 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		 
		 
		 test = getSections(); 
		 System.out.println(" ");
		 System.out.println("________SECTION________");
		 for(int i=1; i <= test.size(); i++)
		 {
			
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%11 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		 
		 //getSection(professorID) get a Section with the professorID 
		 test = getSections("3"); 
		 System.out.println(" ");
		 System.out.println("________SECTION W/professorID________");
		 for(int i=1; i <= test.size(); i++)
		 {
			
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%11 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		
		 //getSection(professorID) get a Section with the classroomID 
		 test = getClassroomSections("1"); 
		 System.out.println(" ");
		 System.out.println("________SECTION W/classRoomID________");
		 for(int i=1; i <= test.size(); i++)
		 {
			
			System.out.print(test.get(i-1)); 
			System.out.print(", ");
			if(i%11 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		 
		 //getSection(professorID) get a Section with the classroomID 
		 test = getSectionsTime("1", "6"); 
		 System.out.println(" ");
		 System.out.println("________SECTION startTime & endTime W/classRoomID & professorID________");
		 for(int i=0; i < test.size(); i++)
		 {
			if(i ==0)
			{
				System.out.print("Start Time: " + test.get(i)); 
				System.out.print(", ");
			}
			else
			{
				System.out.print("End Time: " + test.get(i)); 
			}
		 }
		 test.clear();
		 
		 
		 // updateProfessor Table in status column for professorID 1 
		 updateProfessor("1", "Status", "'Ten'"); 
		
	}

}
