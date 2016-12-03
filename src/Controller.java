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

	private static final String user = "DefaultUser";
	private static final String password = "Password123";
	private static final String databaseName = "SectionSchedule";
	
	private static final String connectionUrl = "jdbc:sqlserver://localhost\\SQL:4373;" + 
			"databaseName=" + databaseName + ";user=" + user + ";password=" + password;
	private static ArrayList<String> test = new ArrayList<String>();
//Method that returns a Professor by professorPK	
	private static Professor getSingleProfessor(String pk)
	{
		 Professor professorObj = null; 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM PROFESSOR WHERE professorID = " + pk;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  
 
 
        // Iterate through the data in the result set and display it.  
        while (rs.next()) 
        {  
          professorObj = new Professor(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5), rs.getString(6)); 
           
        }
           
        con.close();  
           
        } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return professorObj; 
		
	}
	
//Method that returns a COURSE by coursePK	
	private static Course getSingleCourse(String pk)
	{
		  
		 Course courseObj = null; 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM COURSE WHERE courseID = " + pk;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  
 
 
        // Iterate through the data in the result set and display it.  
        while (rs.next()) 
        {  
        	courseObj = new Course(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6));  
           
        }
           
        con.close();  
           
        } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return courseObj; 
		
	}
//Method that returns a classroom object by the classroomPK	
	private static Classroom getSingleClassroom(String pk)
	{
		  
		 Classroom classroomObj = null; 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM CLASSROOM WHERE classroomID = " + pk;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  
 
 
        // Iterate through the data in the result set and display it.  
        while (rs.next()) 
        {  
        	 /*Columns in CLASROOM TABLE
            classrooms.add(rs.getString(1)); -- classroomID
            classrooms.add(rs.getString(2)); -- classroomNO
            classrooms.add(rs.getString(3)); -- buildingID
            classrooms.add(rs.getString(4)); -- capacity
            classrooms.add(rs.getString(5)); --	NumOfComps
            classrooms.add(rs.getString(6)); -- hidden
            */
        	classroomObj = new Classroom(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5));  
           
        }
           
        con.close();  
           
        } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return classroomObj; 
		
	}
	
	//Method that returns a Building object by the buildingID	
		private static Building getSingleBuilding(String pk)
		{
			  
			 Building buildingObj = null; 
			 Connection con = null;  
		     Statement stmt = null;  
		     ResultSet rs = null;
		     try {
		    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		    	 	con = DriverManager.getConnection(connectionUrl);
		    	 	//System.out.println("Database connection established");  	 
		    	 	String SQL = "SELECT * FROM BUILDING WHERE buildingID = " + pk;  
		    	 	stmt = con.createStatement();  
		    	 	rs = stmt.executeQuery(SQL);  
	 
	 
	        // Iterate through the data in the result set and display it.  
	        while (rs.next()) 
	        {  
	        	/*Columns of BUILDING TABLE
	            buildings.add(rs.getString(1)); -- buildingID
	            buildings.add(rs.getString(2)); -- campusID
	            buildings.add(rs.getString(3)); -- title
	            buildings.add(rs.getString(4)); -- buildingCode
	            buildings.add(rs.getString(5)); -- hidden
	            */
	         	
	            
	            buildingObj = new Building(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),getBuildingClassrooms(rs.getString(1)));
	        }
	           
	        con.close();  
	           
	        } catch (Exception ex) 
		       { 
		            // handle the error
		        	 	System.err.println("Cannot connect to database server");
		        	  	System.out.println("SQLException: " + ex.getMessage());
		        	  	ex.printStackTrace(); 
		        
		        }
			
			return buildingObj; 
			
		}
	
//Method that Returns ArrayList<Classroom> by buildingPK	
	private static ArrayList<Classroom> getBuildingClassrooms(String buildingPK)
	{
		  
		 //Classroom classroomObj = null; 
		 ArrayList<Classroom> buildingClassrooms = new ArrayList<Classroom>(); 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM CLASSROOM WHERE buildingID = " + buildingPK;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  
 
 
        // Iterate through the data in the result set and display it.  
        while (rs.next()) 
        {  
        	 /*Columns in CLASROOM TABLE
            classrooms.add(rs.getString(1)); -- classroomID
            classrooms.add(rs.getString(2)); -- classroomNO
            classrooms.add(rs.getString(3)); -- buildingID
            classrooms.add(rs.getString(4)); -- capacity
            classrooms.add(rs.getString(5)); --	NumOfComps
            classrooms.add(rs.getString(6)); -- hidden
            */
        	buildingClassrooms.add(new Classroom(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)));
        	//classroomObj = new Classroom(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5));  
           
        }
           
        con.close();  
           
        } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return buildingClassrooms; 
		
	}
//Method Returning an ArrayList of Section by scheduleID
	public static ArrayList<Section> getScheduleSections(String scheduleID){
		 ArrayList<Section> section = new ArrayList<Section>();
		 Section sectionObj;
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM SECTION WHERE scheduleID = "+scheduleID;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  

   // Iterate through the data in the result set and display it.  
   while (rs.next()) 
   {
      /*	Columns in SECTION TABLE	
      sections.add(rs.getString(1)); -- sectionID
      sections.add(rs.getString(2)); -- classroomID
      sections.add(rs.getString(3)); -- professorID
      sections.add(rs.getString(4)); -- courseID
      sections.add(rs.getString(5)); -- scheduleID
      sections.add(rs.getString(6)); -- startTime
      sections.add(rs.getString(7)); -- endTime
      sections.add(rs.getString(8)); -- startDate
      sections.add(rs.getString(9)); -- endDate
      sections.add(rs.getString(10));-- NumOfSeats
      sections.add(rs.getString(11));-- DaysOfWeek
      */
      sectionObj = new Section(rs.getString(1),getSingleClassroom(rs.getString(2)),getSingleProfessor(rs.getString(3)), getSingleCourse(rs.getString(4)), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10), rs.getString(11)); 
      section.add(sectionObj);
      
   }
      
   con.close();  
      
   } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return section; 
	}
	// Method Returning Arraylist of Building with buildingID
		public static ArrayList<Building> getCampusBuildings(String campusID){
			
			 ArrayList<Building> building = new ArrayList<Building>();
			 Building buildingObj;
			 
			 
			 Connection con = null;  
		     Statement stmt = null;  
		     ResultSet rs = null;
		     try {
		    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		    	 	con = DriverManager.getConnection(connectionUrl);
		    	 	//System.out.println("Database connection established");  	 
		    	 	String SQL = "SELECT * FROM BUILDING WHERE hidden = 0 AND campusID ="+ campusID;  
		    	 	stmt = con.createStatement();  
		    	 	rs = stmt.executeQuery(SQL);  


	    // Iterate through the data in the result set and display it.  
	    while (rs.next()) 
	    {  
	    	/*Columns of BUILDING TABLE
	       buildings.add(rs.getString(1)); -- buildingID
	       buildings.add(rs.getString(2)); -- campusID
	       buildings.add(rs.getString(3)); -- title
	       buildings.add(rs.getString(4)); -- buildingCode
	       buildings.add(rs.getString(5)); -- hidden
	       */
	    	//if building is hidden
	       
	       buildingObj = new Building(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),getBuildingClassrooms(rs.getString(1))); 
	       building.add(buildingObj);
	    }
	       
	    con.close();  
	       
	    } catch (Exception ex) 
		       { 
		            // handle the error
		        	 	System.err.println("Cannot connect to database server");
		        	  	System.out.println("SQLException: " + ex.getMessage());
		        	  	ex.printStackTrace(); 
		        
		        }
			
			return building; 
		}
	
//Method Returning Professor Table	
	public static ArrayList<Professor> getProfessors()
	{
		 ArrayList<Professor> professor = new ArrayList<Professor>();
		 Professor professorObj; 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM PROFESSOR WHERE hidden = 0";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  
  
  
         // Iterate through the data in the result set and display it.  
         while (rs.next()) 
         {  
        	 /*Columns in PROFESSOR TABLE
            professors.add(rs.getString(1)); --professorID
            professors.add(rs.getString(2)); --firstName
            professors.add(rs.getString(3)); --lastName
            professors.add(rs.getString(4)); --status
            professors.add(rs.getString(5)); --RequiredCreditHours
            professors.add(rs.getString(6)); --releaseHours
            professors.add(rs.getString(7)); --hidden
            */
        	//if professor is hidden 
        	
        		professorObj = new Professor(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(5), rs.getString(6)); 
        		professor.add(professorObj);
        
         }
            
         con.close();  
            
         } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return professor; 
	}

//Method Returning COURSE Table	
	public static ArrayList<Course> getCourses()
	{
		 ArrayList<Course> course = new ArrayList<Course>();
		 Course courseObj; 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM COURSE WHERE hidden = 0";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  
  
  
         // Iterate through the data in the result set and display it.  
         while (rs.next()) 
         {  
        	/*Columns oF COURSE TABLE
            courses.add(rs.getString(1)); -- courseID
            courses.add(rs.getString(2)); -- title
            courses.add(rs.getString(3)); -- creditHours
            courses.add(rs.getString(4)); -- prefix
            courses.add(rs.getString(5)); -- courseNo
            courses.add(rs.getString(6)); -- description
            courses.add(rs.getString(7)); -- hidden
            */
        	//if course is hidden 
            
            courseObj = new Course(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(6)); 
            course.add(courseObj);
         }
            
         con.close();  
            
         } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return course; 
	}
//Method returning the Arraylist of CampusObj	
	public static ArrayList<Campus> getCampuses(){
		
		 ArrayList<Campus> campus = new ArrayList<Campus>();
		 ArrayList<Building> building = new ArrayList<Building>();
		 building = getBuildings();
		 Campus campusObj; 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM CAMPUS WHERE hidden = 0";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


      // Iterate through the data in the result set and display it.  
      while (rs.next()) 
      {  
    	  /*Columns of CAMPUS TABLE
         campuses.add(rs.getString(1)); -- campusID
         campuses.add(rs.getString(2)); -- title
         campuses.add(rs.getString(3)); -- address
         campuses.add(rs.getString(4)); -- hidden
         */
         
         campusObj = new Campus(rs.getString(1),rs.getString(2), getCampusBuildings(rs.getString(1))); 
         campus.add(campusObj);
         
      }
         
      con.close();  
         
      } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return campus; 
	}
	
// Method Returning Arraylist of Building
	public static ArrayList<Building> getBuildings(){
		
		 ArrayList<Building> building = new ArrayList<Building>();
		 Building buildingObj;
		 ArrayList<Classroom> classroom = new ArrayList<Classroom>(); 
		 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM BUILDING WHERE hidden = 0";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


    // Iterate through the data in the result set and display it.  
    while (rs.next()) 
    {  
    	/*Columns of BUILDING TABLE
       buildings.add(rs.getString(1)); -- buildingID
       buildings.add(rs.getString(2)); -- campusID
       buildings.add(rs.getString(3)); -- title
       buildings.add(rs.getString(4)); -- buildingCode
       buildings.add(rs.getString(5)); -- hidden
       */
    	
       
       buildingObj = new Building(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),getBuildingClassrooms(rs.getString(1))); 
       building.add(buildingObj);
    }
       
    con.close();  
       
    } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return building; 
	}
	
// Method returning Arraylist of ClassroomObj
	public static ArrayList<Classroom> getClassrooms(){
		
		 ArrayList<Classroom> classroom = new ArrayList<Classroom>();
		 //Classroom classroomObj; 
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT * FROM CLASSROOM WHERE hidden = 0";  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


     // Iterate through the data in the result set and display it.  
     while (rs.next()) 
     {  
    	 /*Columns in CLASROOM TABLE
        classrooms.add(rs.getString(1)); -- classroomID
        classrooms.add(rs.getString(2)); -- classroomNO
        classrooms.add(rs.getString(3)); -- buildingID
        classrooms.add(rs.getString(4)); -- capacity
        classrooms.add(rs.getString(5)); --	NumOfComps
        classrooms.add(rs.getString(6)); -- hidden
        */
     
        //classroomObj = new Classroom(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)); 
        classroom.add(new Classroom(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        
     }
        
     con.close();  
        
     } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return classroom; 
	}

//Method return ArrayList of scheduleObj	
	public static ArrayList<Schedule> getSchedules(){
		 
		 ArrayList<Schedule> schedule = new ArrayList<Schedule>();
		 Schedule scheduleObj;
		 ArrayList<Section> sections = new ArrayList<Section>(); 
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
    	/* Columns in SCHEDULE TABLE
        schedules.add(rs.getString(1)); -- scheduleID
        schedules.add(rs.getString(2)); -- semester
        schedules.add(rs.getString(3)); -- yearID
        */
    	 
        scheduleObj = new Schedule(rs.getString(1), rs.getString(2), rs.getString(3), getScheduleSections(rs.getString(1))); 
        schedule.add(scheduleObj);
        
     }
        
     con.close();  
        
     } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return schedule; 
	}
//
////Method return ArrayList of schedule Strings	
//	public static ArrayList<String> selectSchedules(String season, String year){
//			 
//			 ArrayList<String> schedule = new ArrayList<String>();
//			 //Schedule scheduleObj; 
//			 Connection con = null;  
//		     Statement stmt = null;  
//		     ResultSet rs = null;
//		     try {
//		    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
//		    	 	con = DriverManager.getConnection(connectionUrl);
//		    	 	//System.out.println("Database connection established");  	 
//		    	 	String SQL = "SELECT s.semester, s.yearID, classroomID, professorID, courseID, startTime, endTime, startDate, endDate, NumOfSeats, DaysOfWeek FROM SCHEDULE s JOIN SECTION se ON s.scheduleID = se.scheduleID WHERE semester ="+"'"+season+"'"+"AND yearID = "+year;  
//		    	 	stmt = con.createStatement();  
//		    	 	rs = stmt.executeQuery(SQL);  
//
//
//	     // Iterate through the data in the result set and display it.  
//	     while (rs.next()) 
//	     {  
//	    	/* Columns in SCHEDULE TABLE */
//	        schedule.add(rs.getString(1)); // semester
//	        schedule.add(rs.getString(2)); // yearID
//	        schedule.add(rs.getString(3)); // classrooomID
//	        schedule.add(rs.getString(4)); // professorID
//	        schedule.add(rs.getString(5)); // courseID
//	        schedule.add(rs.getString(6)); // startTime
//	        schedule.add(rs.getString(7)); // endTime
//	        schedule.add(rs.getString(8)); // startDate
//	        schedule.add(rs.getString(9)); // endDate
//	        schedule.add(rs.getString(10));// NumOfSeats
//	        schedule.add(rs.getString(11));// DaysOfWeek
//	       
//	        
//	        //scheduleObj = new Schedule(rs.getString(1)); 
//	        //schedule.add(scheduleObj);
//	        
//	     }
//	        
//	     con.close();  
//	        
//	     } catch (Exception ex) 
//		       { 
//		            // handle the error
//		        	 	System.err.println("Cannot connect to database server");
//		        	  	System.out.println("SQLException: " + ex.getMessage());
//		        	  	ex.printStackTrace(); 
//		        
//		        }
//			
//			return schedule; 
//		}
	
//Method Returning ArrayList of SECTION
	public static ArrayList<Section> getSections(){
		 ArrayList<Section> section = new ArrayList<Section>();
		 Section sectionObj;
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
       /*	Columns in SECTION TABLE	
       sections.add(rs.getString(1)); -- sectionID
       sections.add(rs.getString(2)); -- classroomID
       sections.add(rs.getString(3)); -- professorID
       sections.add(rs.getString(4)); -- courseID
       sections.add(rs.getString(5)); -- scheduleID
       sections.add(rs.getString(6)); -- startTime
       sections.add(rs.getString(7)); -- endTime
       sections.add(rs.getString(8)); -- startDate
       sections.add(rs.getString(9)); -- endDate
       sections.add(rs.getString(10));-- NumOfSeats
       sections.add(rs.getString(11));-- DaysOfWeek
       */
       sectionObj = new Section(rs.getString(1),getSingleClassroom(rs.getString(2)),getSingleProfessor(rs.getString(3)), getSingleCourse(rs.getString(4)), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10), rs.getString(11)); 
       section.add(sectionObj);
       
    }
       
    con.close();  
       
    } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return section; 
	}
	
	
	public static ArrayList<String> getProfessorSections(String professorID){
		
		 ArrayList<String> sections = new ArrayList<String>();
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
    	sections.add(rs.getString(1)); // sectionID
        sections.add(rs.getString(2)); // classroomID
        sections.add(rs.getString(3)); // professorID
        sections.add(rs.getString(4)); // courseID
        sections.add(rs.getString(5)); // scheduleID
        sections.add(rs.getString(6)); // startTime
        sections.add(rs.getString(7)); // endTime
        sections.add(rs.getString(8)); // startDate
        sections.add(rs.getString(9)); // endDate
        sections.add(rs.getString(10));// NumOfSeats
        sections.add(rs.getString(11));// DaysOfWeek
       
       
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
		
		 ArrayList<String> sections = new ArrayList<String>();
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
	   sections.add(rs.getString(1)); // sectionID
       sections.add(rs.getString(2)); // classroomID
       sections.add(rs.getString(3)); // professorID
       sections.add(rs.getString(4)); // courseID
       sections.add(rs.getString(5)); // scheduleID
       sections.add(rs.getString(6)); // startTime
       sections.add(rs.getString(7)); // endTime
       sections.add(rs.getString(8)); // startDate
       sections.add(rs.getString(9)); // endDate
       sections.add(rs.getString(10));// NumOfSeats
       sections.add(rs.getString(11));// DaysOfWeek
      
      
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
	
	public static ArrayList<String> getSectionsTimeProfessorID(String professorID){
		
		 ArrayList<String> sections = new ArrayList<String>();
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT startTime, endTime FROM SECTION WHERE professorID = " + professorID;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


  // Iterate through the data in the result set and display it.  
  while (rs.next()) 
  {  
     sections.add(rs.getString(1)); //startTime
     sections.add(rs.getString(2)); //endTime
    
     
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
	
	//
	public static ArrayList<String> getSectionsTimeClassroomID(String classroomID){
		
		 ArrayList<String> sections = new ArrayList<String>();
		 Connection con = null;  
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "SELECT startTime, endTime FROM SECTION WHERE classroomID = " + classroomID ;  
	    	 	stmt = con.createStatement();  
	    	 	rs = stmt.executeQuery(SQL);  


 // Iterate through the data in the result set and display it.  
 while (rs.next()) 
 {  
    sections.add(rs.getString(1)); //startTime
    sections.add(rs.getString(2)); //endTime
   
    
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
	
// ---------------------------------------------------------INSERT, MODIFY, DELETE PROFESSOR ---------------------------------------
	public static void insertProfessor(String firstName, String lastName, String status, String requiredHours, String releaseHours)
	{
		 
		 Connection con = null;  
	     
	     try {
	    	 
	    	 
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "INSERT PROFESSOR VALUES ("+"'"+firstName+"'" +","+"'"+ lastName+"'"+","+"'"+status+"'"+","+requiredHours+","+releaseHours+","+"0"+")";
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
	    	 	String SQL = "UPDATE PROFESSOR SET hidden = 1 WHERE professorID = " + professorID;
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
	
// ---------------------------------------------------------INSERT, MODIFY, DELETE COURSE ---------------------------------------
	
	public static void insertCourse(String title, String creditHours, String prefix, String courseNo, String description)
	{
		 
		 Connection con = null;  
	     
	     try {
	    	 
	    	 
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "INSERT COURSE VALUES ("+"'"+title+"'" +","+creditHours+","+"'"+prefix+"'"+","+courseNo+","+"'"+description+"'"+","+"0"+")";
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
	
	public static void updateCourse(String courseID, String updateColumn, String UPDATE)
	{
		 
		 Connection con = null;  
	     
	     try {
	    	 
	    	 
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "UPDATE COURSE SET "+ updateColumn +" = "+ UPDATE + "WHERE courseID = " + courseID;
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
	
	public static void deleteCourse(String courseID)
	{
		
		 Connection con = null;  
	     
	     try {
	    	 
	    	 
	    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	 	con = DriverManager.getConnection(connectionUrl);
	    	 	//System.out.println("Database connection established");  	 
	    	 	String SQL = "UPDATE COURSE SET hidden = 1 WHERE courseID = " + courseID;
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
	
// ---------------------------------------------------------INSERT, MODIFY, DELETE CAMPUS ---------------------------------------
	
		public static void insertCampus(String title, String address)
		{
			 
			 Connection con = null;  
		     
		     try {
		    	 
		    	 
		    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		    	 	con = DriverManager.getConnection(connectionUrl);
		    	 	//System.out.println("Database connection established");  	 
		    	 	String SQL = "INSERT CAMPUS VALUES ("+"'"+title+"'" +","+"'"+address+"'"+","+"0"+")";
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
		
		public static void updateCampus(String campusID, String updateColumn, String UPDATE)
		{
			 
			 Connection con = null;  
		     
		     try {
		    	 
		    	 
		    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		    	 	con = DriverManager.getConnection(connectionUrl);
		    	 	//System.out.println("Database connection established");  	 
		    	 	String SQL = "UPDATE CAMPUS SET "+ updateColumn +" = "+ UPDATE + "WHERE campusID = " + campusID;
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
		
		public static void deleteCampus(String campusID)
		{
			
			 Connection con = null;  
		     
		     try {
		    	 
		    	 
		    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		    	 	con = DriverManager.getConnection(connectionUrl);
		    	 	//System.out.println("Database connection established");  	 
		    	 	String SQL = "UPDATE CAMPUS SET hidden = 1 WHERE campusID = " + campusID;
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
	
// ---------------------------------------------------------INSERT, MODIFY, DELETE BUILDING ---------------------------------------
		
			public static void insertBuilding(String campusID, String title, String buildingCode)
			{
				 
				 Connection con = null;  
			     
			     try {
			    	 
			    	 
			    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			    	 	con = DriverManager.getConnection(connectionUrl);
			    	 	//System.out.println("Database connection established");  	 
			    	 	String SQL = "INSERT BUILDING VALUES ("+campusID+","+"'"+title+"'" +","+"'"+buildingCode+"'"+","+"0"+")";
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
			
			public static void updateBuilding(String buildingID, String updateColumn, String UPDATE)
			{
				 
				 Connection con = null;  
			     
			     try {
			    	 
			    	 
			    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			    	 	con = DriverManager.getConnection(connectionUrl);
			    	 	//System.out.println("Database connection established");  	 
			    	 	String SQL = "UPDATE BUILDING SET "+ updateColumn +" = "+ UPDATE + "WHERE buildingID = " + buildingID;
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
			
			public static void deleteBuilding(String buildingID)
			{
				
				 Connection con = null;  
			     
			     try {
			    	 
			    	 
			    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			    	 	con = DriverManager.getConnection(connectionUrl);
			    	 	//System.out.println("Database connection established");  	 
			    	 	String SQL = "UPDATE BUILDING SET hidden = 1 WHERE buildingID = " + buildingID;
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
	
// ---------------------------------------------------------INSERT, MODIFY, DELETE CLASSROOM ---------------------------------------
			
				public static void insertClassroom(String classroomID, String classroomNo, String buildingID, String capacity,String numOfComps)
				{
					 
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "INSERT CLASSROOM VALUES ("+classroomID+","+classroomNo+","+buildingID+","+","+capacity+","+","+numOfComps+","+"0"+")";
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
				
				public static void updateClassroom(String classroomID, String updateColumn, String UPDATE)
				{
					 
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "UPDATE CLASSROOM SET "+ updateColumn +" = "+ UPDATE + "WHERE classroomID = " + classroomID;
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
				
				public static void deleteClassroom(String classroomID)
				{
					
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "UPDATE CLASSROOM SET hidden = 1 WHERE classroomID = " + classroomID;
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
	
	
// ---------------------------------------------------------INSERT, MODIFY, DELETE SCHEDULE ---------------------------------------
				
				public static void insertSchedule(String scheduleID, String semester, String yearID)
				{
					 
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "INSERT SCHEDULE VALUES ("+scheduleID+","+"'"+semester+"'"+","+yearID+")";
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
				
				public static void updateSchedule(String scheduleID, String updateColumn, String UPDATE)
				{
					 
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "UPDATE SCHEDULE SET "+ updateColumn +" = "+ UPDATE + "WHERE scheduleID = " + scheduleID;
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
				
				public static void deleteSchedule(String scheduleID)
				{
					
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "DELETE SCHEDULE WHERE scheduleID = " + scheduleID;
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
				
// ---------------------------------------------------------INSERT, MODIFY, DELETE SECTION ---------------------------------------
				
				public static void insertSection(String sectionID, String classroomID, String professorID, String courseID, String scheduleID, String startTime, String endTime, String startDate, String endDate,  String numOfSeats, String daysOfWeek)
				{
					 
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "INSERT SECTION VALUES ("+sectionID+","+","+classroomID+","+professorID+","+courseID+","+scheduleID+","+"'"+startTime+"'"+","+"'"+endTime+"'"+","+"'"+startDate+"'"+","+"'"+endDate+"'"+","+numOfSeats+","+daysOfWeek+")";
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
				
				public static void updateSection(String sectionID, String updateColumn, String UPDATE)
				{
					 
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "UPDATE SECTION SET "+ updateColumn +" = "+ UPDATE + "WHERE sectionID = " + sectionID;
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
				
				public static void deleteSection(String sectionID)
				{
					
					 Connection con = null;  
				     
				     try {
				    	 
				    	 
				    	 	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				    	 	con = DriverManager.getConnection(connectionUrl);
				    	 	//System.out.println("Database connection established");  	 
				    	 	String SQL = "DELETE SECTION WHERE sectionID = " + sectionID;
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

		 
		 // updateProfessor Table in status column for professorID 1 
		 updateProfessor("1", "Status", "'Ten'"); 
		 
		 
		 //get all rows in the professor table and print it 
		 ArrayList<Professor> testProf = new ArrayList<Professor>();
		 System.out.println(" ");
		 System.out.println("________Get primarykey, firstname and lastname with professor Object from an Arraylist of Professor Objects________");
		 testProf = getProfessors(); 
		 for(int i =0; i < testProf.size(); i++)
		   System.out.println(testProf.get(i).GetPrimaryKey() + " " +testProf.get(i).GetFirstName() + " " + testProf.get(i).GetLastName() + " " + testProf.get(i).GetFillerHours()+ " " + testProf.get(i).GetCreditHours());
 
		//get all rows in the COURSE table and print it
		 ArrayList<Course> testCourses = new ArrayList<Course>();
		 System.out.println(" ");
		 System.out.println("________Get primarykey, COURSE NAME, COURSE Prefix, COURSE No. and COURSE Description with COURSE Object from an Arraylist of Course Objects________");
		 testCourses = getCourses(); 
		 for(int i =0; i < testCourses.size(); i++)
		   System.out.println(testCourses.get(i).GetPrimaryKey() + " " +testCourses.get(i).GetCourseName() + " " + testCourses.get(i).GetCourseTag() + " " + testCourses.get(i).GetCourseNumber()+ " " + testCourses.get(i).GetCourseDescription());
		 
		
		//get all rows in the CAMPUS table and print it
		 ArrayList<Campus> testCampuses = new ArrayList<Campus>();
		 System.out.println(" ");
		 System.out.println("________Get primarykey, CAMPUS NAME, CAMPUS Object from an Arraylist of Campus Objects________");
		 testCampuses = getCampuses(); 
		 for(int i =0; i < testCampuses.size(); i++)
		   System.out.println(testCampuses.get(i).GetPrimaryKey() + " " +testCampuses.get(i).GetName());
		 
		 //print all buildings for each campus
		 System.out.println("________Campus Bulidings for "+ testCampuses.get(0).GetName()+"___________");
		 for(int i =0; i < testCampuses.get(0).GetBuildings().size(); i++)
			   System.out.println(testCampuses.get(0).GetBuildings().get(i).GetBuildingTag() + " " +testCampuses.get(0).GetBuildings().get(i).GetCampusID());
		 
		 System.out.println("________Campus Bulidings for "+ testCampuses.get(1).GetName()+"___________");
		 for(int i =0; i < testCampuses.get(1).GetBuildings().size(); i++)
			   System.out.println(testCampuses.get(1).GetBuildings().get(i).GetBuildingTag() + " " +testCampuses.get(1).GetBuildings().get(i).GetCampusID());
		 
		 System.out.println("________Campus Bulidings for "+ testCampuses.get(2).GetName()+"___________");
		 for(int i =0; i < testCampuses.get(2).GetBuildings().size(); i++)
			   System.out.println(testCampuses.get(2).GetBuildings().get(i).GetBuildingTag() + " " +testCampuses.get(2).GetBuildings().get(i).GetCampusID());
		 
		 
		 //get all rows in the BUILDING table and print it
		 ArrayList<Building> testBuildings = new ArrayList<Building>();
		 System.out.println(" ");
		 System.out.println("________Get primarykey, Building title, BUILDING Object from an Arraylist of Building Objects________");
		 testBuildings = getBuildings(); 
		 for(int i =0; i < testBuildings.size(); i++)
		   System.out.println(testBuildings.get(i).GetPrimaryKey() + " " +testBuildings.get(i).GetBuildingTag()+
				   				" "+testBuildings.get(i).GetBuildingTitle());
		 
		 //print all classrooms for each building
		 System.out.println("___________________Building " + testBuildings.get(0).GetBuildingTitle() + " Classroom Numbers____________________");
		 for(int i =0; i < testBuildings.get(0).GetClassrooms().size(); i++)
			   System.out.println(testBuildings.get(0).GetClassrooms().get(i).GetRoomNumber());
		 
		 System.out.println("___________________Building " + testBuildings.get(1).GetBuildingTitle() + " Classroom Numbers____________________");
		 for(int i =0; i < testBuildings.get(1).GetClassrooms().size(); i++)
			   System.out.println(testBuildings.get(1).GetClassrooms().get(i).GetRoomNumber());
		 
		 System.out.println("___________________Building " + testBuildings.get(2).GetBuildingTitle() + " Classroom Numbers____________________");
		 for(int i =0; i < testBuildings.get(2).GetClassrooms().size(); i++)
			   System.out.println(testBuildings.get(2).GetClassrooms().get(i).GetRoomNumber());
		 
		 System.out.println("___________________Building " + testBuildings.get(3).GetBuildingTitle() + " Classroom Numbers____________________");
		 for(int i =0; i < testBuildings.get(3).GetClassrooms().size(); i++)
			   System.out.println(testBuildings.get(3).GetClassrooms().get(i).GetRoomNumber());
		 
		 System.out.println("___________________Building " + testBuildings.get(4).GetBuildingTitle() + " Classroom Numbers____________________");
		 for(int i =0; i < testBuildings.get(4).GetClassrooms().size(); i++)
			   System.out.println(testBuildings.get(4).GetClassrooms().get(i).GetRoomNumber());
		 
		 System.out.println("___________________Building " + testBuildings.get(5).GetBuildingTitle() + " Classroom Numbers____________________");
		 for(int i =0; i < testBuildings.get(5).GetClassrooms().size(); i++)
			   System.out.println(testBuildings.get(5).GetClassrooms().get(i).GetRoomNumber());
		 
		 System.out.println("___________________Building " + testBuildings.get(6).GetBuildingTitle() + " Classroom Numbers____________________");
		 for(int i =0; i < testBuildings.get(6).GetClassrooms().size(); i++)
			   System.out.println(testBuildings.get(6).GetClassrooms().get(i).GetRoomNumber());
		 
		 
		//get all rows in the CLASSROOM table and print it
		 ArrayList<Classroom> testClassrooms = new ArrayList<Classroom>();
		 System.out.println(" ");
		 System.out.println("________Get primarykey, classroomNo, CLASSROOM Object from an Arraylist of Classroom Objects________");
		 testClassrooms = getClassrooms(); 
		 for(int i =0; i < testClassrooms.size(); i++)
		   System.out.println(testClassrooms.get(i).GetPrimaryKey() + " " +testClassrooms.get(i).GetRoomNumber());
		 
		 
		//get all rows in the SCHEDULE table and print it
		 ArrayList<Schedule> testSchedules = new ArrayList<Schedule>();
		 System.out.println(" ");
		 System.out.println("________Get primarykey, SCHEDULE Object from an Arraylist of Schedule Objects________");
		 testSchedules = getSchedules(); 
		 for(int i =0; i < testSchedules.size(); i++)
		   System.out.println(testSchedules.get(i).GetPrimaryKey()+" "+testSchedules.get(i).getSemester()+" "+testSchedules.get(i).getYear());
		 
		 System.out.println("________________Schedule: "+ testSchedules.get(15).getSemester()+" "+testSchedules.get(15).getYear()+"_____________________________");
		 for(int i =0; i < testSchedules.get(15).getSections().size(); i++)
			   System.out.println(  getSingleBuilding(testSchedules.get(15).getSections().get(i).getClassroom().GetBuildingID()).GetBuildingTag()+
					   				", "+testSchedules.get(15).getSections().get(i).getClassroom().GetRoomNumber()+
					   				", "+testSchedules.get(15).getSections().get(i).getProfessor().GetFirstName()+
					   				" "+testSchedules.get(15).getSections().get(i).getProfessor().GetLastName()+
					   				", "+testSchedules.get(15).getSections().get(i).getCourse().GetCourseTag()+
					   				" "+testSchedules.get(15).getSections().get(i).getCourse().GetCourseNumber()+
					   				" "+testSchedules.get(15).getSections().get(i).getCourse().GetCourseName()+
					   				", "+testSchedules.get(15).getSections().get(i).getStartTime()+
					   				", "+testSchedules.get(15).getSections().get(i).getEndTime()+
					   				", "+testSchedules.get(15).getSections().get(i).getStartDate()+
					   				", "+testSchedules.get(15).getSections().get(i).getEndDate()+
					   				", "+testSchedules.get(15).getSections().get(i).getNumSeats()+
					   				", "+testSchedules.get(15).getSections().get(i).getDaysOfWeek()
					   				);
		 
		 
		 
		//get all rows in the SECTION table and print it
		 ArrayList<Section> testSections = new ArrayList<Section>();
		 System.out.println(" ");
		 System.out.println("________Get primarykey, ClassroomID, SECTION Object from an Arraylist of Section Objects________");
		 testSections = getSections(); 
		 for(int i =0; i < testSections.size(); i++)
		   System.out.println(testSections.get(i).GetPrimaryKey()+ ", " +testSections.get(i).getClassroom().GetRoomNumber()+ ", " + testSections.get(i).getProfessor().GetFirstName() +" " + testSections.get(i).getProfessor().GetLastName() + ", " +testSections.get(i).getCourse()+ ", " + testSections.get(i).getStartTime()+ ", " + testSections.get(i).getEndTime()+ ", " +testSections.get(i).getStartDate()+ ", " +testSections.get(i).getEndDate()+ ", "+testSections.get(i).getNumSeats()+ ", "  + testSections.get(i).getDaysOfWeek());
		 
		 
		 //get a single professor that from primary key 
		 System.out.println(" ");
		 System.out.println("____________________Return a Single Professor_____________________");
		 Professor professor; 
		 professor = getSingleProfessor("1");
		 System.out.println(professor.GetPrimaryKey() + " " + professor.GetFirstName()+ " " + professor.GetLastName());
		 
		//get a single COURSE that from primary key 
		 System.out.println(" ");
		 System.out.println("____________________Return a Single Course_____________________");
		 Course course; 
		 course = getSingleCourse("1");
		 System.out.println(course.GetPrimaryKey() + " " + course.GetCourseTag()+ " " + course.GetCourseNumber());
		
		//get a single Classroom that from primary key 
		 System.out.println(" ");
		 System.out.println("____________________Return a Single Classroom_____________________");
		 Classroom classroom; 
		 classroom = getSingleClassroom("1");
		 System.out.println(classroom.GetPrimaryKey() + " " + classroom.GetRoomNumber());
		 
		//getSection(professorID) get a Section with the professorID 
		 test = getProfessorSections("3"); 
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
		 
		 //getTimeSections(classroomID, professorID) get SECTION startTime & endTime W/classRoomID & professorID 
		 test = getSectionsTimeProfessorID("10"); 
		 System.out.println(" ");
		 System.out.println("________SECTION startTime & endTime W/ professorID________");
		 for(int i=0; i < test.size(); i++)
		 {
			
			 if(i%2 == 0)
			 {
				System.out.print("Start Time: " + test.get(i)); 
				System.out.print(", ");
			 }
			 else
				System.out.print("End Time: " + test.get(i)+" "); 
		
		 }
		 test.clear();
		 
		 
		 System.out.println(" ");
		 test = getSectionsTimeClassroomID("1"); 
		 System.out.println(" ");
		 System.out.println("________SECTION startTime & endTime classRoomID________");
		 for(int i=0; i < test.size(); i++)
		 {
			
			 if(i%2 == 0)
			 {
				System.out.print("Start Time: " + test.get(i)); 
				System.out.print(", ");
			 }
			 else
				System.out.print("End Time: " + test.get(i)+" "); 
		
		 }
		 test.clear();
		 
		 
		//System.out.println("____________________This is an Insert into the professor table_____________________");
		
		 //insertProfessor("Riley","Painter","Ten","12", "7");
		 
		//Test Delete Professor by setting hidden equal to one then pull professor table
		 
		 System.out.println(" ");
		 deleteProfessor("1");
		 ArrayList<Professor> testDeleteProf = new ArrayList<Professor>();
		 System.out.println(" ");
		 System.out.println("________Get primarykey, firstname and lastname with professor Object from an Arraylist of Professor Objects________");
		 testDeleteProf = getProfessors(); 
		 for(int i =0; i < testDeleteProf.size(); i++)
		   System.out.println(testDeleteProf.get(i).GetPrimaryKey() + " " +testDeleteProf.get(i).GetFirstName() + " " + testDeleteProf.get(i).GetLastName());
		 
		 
		 /*
		 // Test creating whole schedule 
		 ArrayList<String> Spri2017Sched = new ArrayList<String>(); 
		 Spri2017Sched = selectSchedules("Spring", "2017");
		 System.out.println(" ");
		 System.out.println("________Complete Schedule Based on season and year________");
		 for(int i=1; i <= Spri2017Sched.size(); i++)
		 {
			
			System.out.print(Spri2017Sched.get(i-1)); 
			System.out.print(", ");
			if(i%11 == 0) 
			{
				System.out.println(" ");
			}
		 }
		 test.clear();
		 
		 //Test Schedule
		 ArrayList<Schedule> schedule = new ArrayList<Schedule>(); 
		 
		 */
		
		
	}

}
