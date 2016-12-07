import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
	private static final String databasePort = "4373";
	private static final String connectionUrl = "jdbc:sqlserver://localhost\\SQL:" + databasePort + ";"
			+ "databaseName=" + databaseName + ";user=" + user + ";password=" + password;
	private static Connection con = null;

	// Method that returns a Professor by professorPK
	private static Professor getSingleProfessor(String pk) {
		Professor professorObj = null;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM PROFESSOR WHERE professorID = " + pk;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				professorObj = new Professor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return professorObj;

	}

	// Method that returns a COURSE by coursePK
	private static Course getSingleCourse(String pk) {

		Course courseObj = null;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM COURSE WHERE courseID = " + pk;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				courseObj = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return courseObj;

	}

	// Method that returns a classroom object by the classroomPK
	private static Classroom getSingleClassroom(String pk) {

		Classroom classroomObj = null;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM CLASSROOM WHERE classroomID = " + pk;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns in CLASROOM TABLE classrooms.add(rs.getString(1)); --
				 * classroomID classrooms.add(rs.getString(2)); -- classroomNO
				 * classrooms.add(rs.getString(3)); -- buildingID
				 * classrooms.add(rs.getString(4)); -- capacity
				 * classrooms.add(rs.getString(5)); -- NumOfComps
				 * classrooms.add(rs.getString(6)); -- hidden
				 */
				String classroomId = rs.getString(1);
				String classroomNo = rs.getString(2);
				String buildingId = rs.getString(3);
				String capacity = rs.getString(4);
				String numOfComps = rs.getString(5);

				Building temp = getSingleBuilding(buildingId);
				// classroomObj = new Classroom(rs.getString(1),
				// rs.getString(2), temp.getBuildingCode(), rs.getString(4),
				// rs.getString(5));
				classroomObj = new Classroom(classroomId, classroomNo, buildingId, temp.getBuildingCode(), capacity,
						numOfComps);

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return classroomObj;

	}

	public static Campus getSingleCampus(String pk) {
		Campus campus = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String SQL = "SELECT * FROM CAMPUS WHERE campusID = " + pk;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				String campusId = rs.getString(1), title = rs.getString(2), address = rs.getString(3);

				campus = new Campus(campusId, title, address, getCampusBuildings(campusId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return campus;
	}

	// Method that returns a Building object by the buildingID
	public static Building getSingleBuilding(String pk) {

		Building buildingObj = null;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM BUILDING WHERE buildingID = " + pk;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns of BUILDING TABLE buildings.add(rs.getString(1)); --
				 * buildingID buildings.add(rs.getString(2)); -- campusID
				 * buildings.add(rs.getString(3)); -- title
				 * buildings.add(rs.getString(4)); -- buildingCode
				 * buildings.add(rs.getString(5)); -- hidden
				 */

				buildingObj = new Building(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						getBuildingClassrooms(rs.getString(1)));
			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return buildingObj;

	}

	private static String getSingleBuildingCode(String pk) {
		String buildingCode = "";

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM BUILDING WHERE buildingID = " + pk;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns of BUILDING TABLE buildings.add(rs.getString(1)); --
				 * buildingID buildings.add(rs.getString(2)); -- campusID
				 * buildings.add(rs.getString(3)); -- title
				 * buildings.add(rs.getString(4)); -- buildingCode
				 * buildings.add(rs.getString(5)); -- hidden
				 */

				buildingCode = rs.getString(4);
			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return buildingCode;
	}

	// Method that Returns ArrayList<Classroom> by buildingPK
	private static ArrayList<Classroom> getBuildingClassrooms(String buildingPK) {

		// Classroom classroomObj = null;
		ArrayList<Classroom> buildingClassrooms = new ArrayList<Classroom>();

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM CLASSROOM WHERE buildingID = " + buildingPK;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns in CLASSROOM TABLE classrooms.add(rs.getString(1));
				 * -- classroomID classrooms.add(rs.getString(2)); --
				 * classroomNO classrooms.add(rs.getString(3)); -- buildingID
				 * classrooms.add(rs.getString(4)); -- capacity
				 * classrooms.add(rs.getString(5)); -- NumOfComps
				 * classrooms.add(rs.getString(6)); -- hidden
				 */

				String classroomId = rs.getString(1);
				String classroomNo = rs.getString(2);
				String buildingId = rs.getString(3);
				String capacity = rs.getString(4);
				String numOfComps = rs.getString(5);

				buildingClassrooms.add(new Classroom(classroomId, classroomNo, buildingId,
						getSingleBuildingCode(buildingId), capacity, numOfComps));

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return buildingClassrooms;

	}

	// Method Returning an ArrayList of Section by scheduleID
	public static ArrayList<Section> getScheduleSections(String scheduleID) {
		ArrayList<Section> section = new ArrayList<Section>();
		Section sectionObj;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM SECTION WHERE scheduleID = " + scheduleID;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns in SECTION TABLE sections.add(rs.getString(1)); --
				 * sectionID sections.add(rs.getString(2)); -- classroomID
				 * sections.add(rs.getString(3)); -- professorID
				 * sections.add(rs.getString(4)); -- courseID
				 * sections.add(rs.getString(5)); -- scheduleID
				 * sections.add(rs.getString(6)); -- startTime
				 * sections.add(rs.getString(7)); -- endTime
				 * sections.add(rs.getString(8)); -- startDate
				 * sections.add(rs.getString(9)); -- endDate
				 * sections.add(rs.getString(10));-- NumOfSeats
				 * sections.add(rs.getString(11));-- DaysOfWeek
				 */
				sectionObj = new Section(rs.getString(1), getSingleClassroom(rs.getString(2)),
						getSingleProfessor(rs.getString(3)), getSingleCourse(rs.getString(4)), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
				section.add(sectionObj);

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return section;
	}

	// Method Returning Arraylist of Building with buildingID
	public static ArrayList<Building> getCampusBuildings(String campusID) {

		ArrayList<Building> building = new ArrayList<Building>();
		Building buildingObj;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM BUILDING WHERE hidden = 0 AND campusID =" + campusID;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns of BUILDING TABLE buildings.add(rs.getString(1)); --
				 * buildingID buildings.add(rs.getString(2)); -- campusID
				 * buildings.add(rs.getString(3)); -- title
				 * buildings.add(rs.getString(4)); -- buildingCode
				 * buildings.add(rs.getString(5)); -- hidden
				 */
				// if building is hidden

				// buildingObj = new Building(rs.getString(1),
				// getSingleCampus(rs.getString(2)), rs.getString(3),
				// rs.getString(4),
				// getBuildingClassrooms(rs.getString(1)));
				buildingObj = new Building(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						getBuildingClassrooms(rs.getString(1)));
				building.add(buildingObj);
			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return building;
	}

	// Method Returning Professor Table
	public static ArrayList<Professor> getProfessors() {
		ArrayList<Professor> professor = new ArrayList<Professor>();
		Professor professorObj;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM PROFESSOR WHERE hidden = 0";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns in PROFESSOR TABLE professors.add(rs.getString(1));
				 * --professorID professors.add(rs.getString(2)); --firstName
				 * professors.add(rs.getString(3)); --lastName
				 * professors.add(rs.getString(4)); --status
				 * professors.add(rs.getString(5)); --RequiredCreditHours
				 * professors.add(rs.getString(6)); --releaseHours
				 * professors.add(rs.getString(7)); --hidden
				 */
				// if professor is hidden

				professorObj = new Professor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				professor.add(professorObj);

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return professor;
	}

	// Method Returning COURSE Table
	public static ArrayList<Course> getCourses() {
		ArrayList<Course> course = new ArrayList<Course>();
		Course courseObj;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM COURSE WHERE hidden = 0";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns oF COURSE TABLE courses.add(rs.getString(1)); --
				 * courseID courses.add(rs.getString(2)); -- title
				 * courses.add(rs.getString(3)); -- creditHours
				 * courses.add(rs.getString(4)); -- prefix
				 * courses.add(rs.getString(5)); -- courseNo
				 * courses.add(rs.getString(6)); -- description
				 * courses.add(rs.getString(7)); -- hidden
				 */
				// if course is hidden

				courseObj = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				course.add(courseObj);
			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return course;
	}

	// Method returning the Arraylist of CampusObj
	public static ArrayList<Campus> getCampuses() {

		ArrayList<Campus> campus = new ArrayList<Campus>();
		ArrayList<Building> building = new ArrayList<Building>();
		building = getBuildings();
		Campus campusObj;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM CAMPUS WHERE hidden = 0";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns of CAMPUS TABLE campuses.add(rs.getString(1)); --
				 * campusID campuses.add(rs.getString(2)); -- title
				 * campuses.add(rs.getString(3)); -- address
				 * campuses.add(rs.getString(4)); -- hidden
				 */

				campusObj = new Campus(rs.getString(1), rs.getString(2), rs.getString(3),
						getCampusBuildings(rs.getString(1)));
				campus.add(campusObj);

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return campus;
	}

	// Method Returning Arraylist of Building
	public static ArrayList<Building> getBuildings() {

		ArrayList<Building> building = new ArrayList<Building>();
		Building buildingObj;
		ArrayList<Classroom> classroom = new ArrayList<Classroom>();

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM BUILDING WHERE hidden = 0";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns of BUILDING TABLE buildings.add(rs.getString(1)); --
				 * buildingID buildings.add(rs.getString(2)); -- campusID
				 * buildings.add(rs.getString(3)); -- title
				 * buildings.add(rs.getString(4)); -- buildingCode
				 * buildings.add(rs.getString(5)); -- hidden
				 */

				buildingObj = new Building(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						getBuildingClassrooms(rs.getString(1)));
				building.add(buildingObj);
			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return building;
	}

	// Method returning Arraylist of ClassroomObj
	public static ArrayList<Classroom> getClassrooms() {

		ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
		// Classroom classroomObj;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM CLASSROOM WHERE hidden = 0";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns in CLASROOM TABLE classrooms.add(rs.getString(1)); --
				 * classroomID classrooms.add(rs.getString(2)); -- classroomNO
				 * classrooms.add(rs.getString(3)); -- buildingID
				 * classrooms.add(rs.getString(4)); -- capacity
				 * classrooms.add(rs.getString(5)); -- NumOfComps
				 * classrooms.add(rs.getString(6)); -- hidden
				 */
				String classroomId = rs.getString(1);
				String classroomNo = rs.getString(2);
				String buildingId = rs.getString(3);
				String capacity = rs.getString(4);
				String numOfComps = rs.getString(5);

				Building temp = getSingleBuilding(buildingId);
				classrooms.add(new Classroom(classroomId, classroomNo, buildingId, temp.getBuildingCode(), capacity,
						numOfComps));

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return classrooms;
	}

	// Method return ArrayList of scheduleObj
	public static ArrayList<Schedule> getSchedules() {

		ArrayList<Schedule> schedule = new ArrayList<Schedule>();
		Schedule scheduleObj;
		ArrayList<Section> sections = new ArrayList<Section>();

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM SCHEDULE";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns in SCHEDULE TABLE schedules.add(rs.getString(1)); --
				 * scheduleID schedules.add(rs.getString(2)); -- semester
				 * schedules.add(rs.getString(3)); -- yearID
				 */

				scheduleObj = new Schedule(rs.getString(1), rs.getString(2), rs.getString(3),
						getScheduleSections(rs.getString(1)));
				schedule.add(scheduleObj);

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return schedule;
	}

	// Method Returning ArrayList of SECTION
	public static ArrayList<Section> getSections() {
		ArrayList<Section> section = new ArrayList<Section>();
		Section sectionObj;

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM SECTION";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns in SECTION TABLE sections.add(rs.getString(1)); --
				 * sectionID sections.add(rs.getString(2)); -- classroomID
				 * sections.add(rs.getString(3)); -- professorID
				 * sections.add(rs.getString(4)); -- courseID
				 * sections.add(rs.getString(5)); -- scheduleID
				 * sections.add(rs.getString(6)); -- startTime
				 * sections.add(rs.getString(7)); -- endTime
				 * sections.add(rs.getString(8)); -- startDate
				 * sections.add(rs.getString(9)); -- endDate
				 * sections.add(rs.getString(10));-- NumOfSeats
				 * sections.add(rs.getString(11));-- DaysOfWeek
				 */
				sectionObj = new Section(rs.getString(1), getSingleClassroom(rs.getString(2)),
						getSingleProfessor(rs.getString(3)), getSingleCourse(rs.getString(4)), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
				section.add(sectionObj);

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return section;
	}

	public static ArrayList<String> getProfessorSections(String professorID) {

		ArrayList<String> sections = new ArrayList<String>();

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM SECTION WHERE professorID = " + professorID;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
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

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return sections;
	}

	public static ArrayList<String> getClassroomSections(String classroomID) {

		ArrayList<String> sections = new ArrayList<String>();

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT * FROM SECTION WHERE classroomID = " + classroomID;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
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

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return sections;
	}

	public static ArrayList<String> getSectionsTimeProfessorID(String professorID) {

		ArrayList<String> sections = new ArrayList<String>();

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT startTime, endTime FROM SECTION WHERE professorID = " + professorID;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				sections.add(rs.getString(1)); // startTime
				sections.add(rs.getString(2)); // endTime

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return sections;
	}

	//
	public static ArrayList<String> getSectionsTimeClassroomID(String classroomID) {

		ArrayList<String> sections = new ArrayList<String>();

		Statement stmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("Database connection established");
			String SQL = "SELECT startTime, endTime FROM SECTION WHERE classroomID = " + classroomID;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				sections.add(rs.getString(1)); // startTime
				sections.add(rs.getString(2)); // endTime

			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return sections;
	}

	// ---------------------------------------------------------INSERT, MODIFY,
	// DELETE PROFESSOR ---------------------------------------
	public static void insertProfessor(String firstName, String lastName, String status, String requiredHours,
			String releaseHours) {

		try {

			// System.out.println("Database connection established");
			String SQL = "INSERT PROFESSOR VALUES (" + "'" + firstName + "'" + "," + "'" + lastName + "'" + "," + "'"
					+ status + "'" + "," + requiredHours + "," + releaseHours + "," + "0" + ")";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateProfessor(String professorID, String updateColumn, String UPDATE) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE PROFESSOR SET " + updateColumn + " = '" + UPDATE + "' WHERE professorID = "
					+ professorID;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateProfessor(Professor professor) {
		updateProfessor(professor.getPrimaryKey(), "firstName", professor.getFirstName());
		updateProfessor(professor.getPrimaryKey(), "lastName", professor.getLastName());
		updateProfessor(professor.getPrimaryKey(), "status", professor.getStatus());
		updateProfessor(professor.getPrimaryKey(), "RequiredCreditHours", professor.getCreditHours() + "");
		updateProfessor(professor.getPrimaryKey(), "releaseHours", professor.getReleaseHours() + "");
	}

	public static void deleteProfessor(Professor professor) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE PROFESSOR SET hidden = 1 WHERE professorID = " + professor.getPrimaryKey();
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	// ---------------------------------------------------------INSERT, MODIFY,
	// DELETE COURSE ---------------------------------------

	public static void insertCourse(String title, String creditHours, String prefix, String courseNo,
			String description) {

		try {

			// System.out.println("Database connection established");
			String SQL = "INSERT COURSE VALUES (" + "'" + title + "'" + "," + creditHours + "," + "'" + prefix + "'"
					+ "," + courseNo + "," + "'" + description + "'" + "," + "0" + ")";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			ex.printStackTrace();
		}

	}

	public static void updateCourse(String courseID, String updateColumn, String UPDATE) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE COURSE SET " + updateColumn + " = '" + UPDATE + "' WHERE courseID = " + courseID;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			ex.printStackTrace();
		}

	}

	// updates all fields for a course
	public static void updateCourse(Course course) {
		updateCourse(course.getPrimaryKey(), "title", course.getTitle());
		updateCourse(course.getPrimaryKey(), "creditHours", course.getCreditHours());
		updateCourse(course.getPrimaryKey(), "prefix", course.getPrefix());
		updateCourse(course.getPrimaryKey(), "courseNo", course.getCourseNumber());
		updateCourse(course.getPrimaryKey(), "description", course.getDescription());
	}

	public static void deleteCourse(Course course) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE COURSE SET hidden = 1 WHERE courseID = " + course.getPrimaryKey();
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	// ---------------------------------------------------------INSERT, MODIFY,
	// DELETE CAMPUS ---------------------------------------

	public static void insertCampus(String title, String address) {

		try {

			// System.out.println("Database connection established");
			String SQL = "INSERT CAMPUS VALUES (" + "'" + title + "'" + "," + "'" + address + "'" + "," + "0" + ")";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateCampus(String campusID, String updateColumn, String UPDATE) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE CAMPUS SET " + updateColumn + " = '" + UPDATE + "' WHERE campusID = " + campusID;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateCampus(Campus campus) {
		updateCampus(campus.getPrimaryKey(), "title", campus.getName());
		updateCampus(campus.getPrimaryKey(), "address", campus.getAddress());
	}

	public static void deleteCampus(Campus campus) {
		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE CAMPUS SET hidden = 1 WHERE campusID = " + campus.getPrimaryKey();
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

			// also hide all Buildings associated with this campus
			for (Building building : campus.getBuildings())
				deleteBuilding(building);

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	// ---------------------------------------------------------INSERT, MODIFY,
	// DELETE BUILDING ---------------------------------------

	public static void insertBuilding(String campusID, String title, String buildingCode) {

		try {

			// System.out.println("Database connection established");
			String SQL = "INSERT BUILDING VALUES (" + campusID + "," + "'" + title + "'" + "," + "'" + buildingCode
					+ "'" + "," + "0" + ")";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateBuilding(String buildingID, String updateColumn, String UPDATE) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE BUILDING SET " + updateColumn + " = '" + UPDATE + "' WHERE buildingID = " + buildingID;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateBuilding(Building building) {
		updateBuilding(building.getPrimaryKey(), "campusID", building.getCampusID());
		updateBuilding(building.getPrimaryKey(), "title", building.getTitle());
		updateBuilding(building.getPrimaryKey(), "buildingCode", building.getBuildingCode());
	}

	public static void deleteBuilding(Building building) {
		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE BUILDING SET hidden = 1 WHERE buildingID = " + building.getPrimaryKey();
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

			// also hide all classrooms in the building
			for (Classroom classroom : building.getClassrooms())
				deleteClassroom(classroom);
		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	// ---------------------------------------------------------INSERT, MODIFY,
	// DELETE CLASSROOM ---------------------------------------

	public static void insertClassroom(String classroomNo, String buildingID, int capacity, int numOfComps) {

		try {

			// System.out.println("Database connection established");
			String SQL = "INSERT CLASSROOM VALUES ('" + classroomNo + "'," + buildingID + "," + capacity + ","
					+ numOfComps + "," + "0" + ")";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateClassroom(String classroomID, String updateColumn, String UPDATE) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE CLASSROOM SET " + updateColumn + " = '" + UPDATE + "' WHERE classroomID = "
					+ classroomID;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateClassroom(Classroom classroom) {
		updateClassroom(classroom.getPrimaryKey(), "classroomNo", classroom.getRoomNumber());
		updateClassroom(classroom.getPrimaryKey(), "buildingID", classroom.getBuildingID());
		updateClassroom(classroom.getPrimaryKey(), "capacity", classroom.getCapacity());
		updateClassroom(classroom.getPrimaryKey(), "NumOfComps", classroom.getNumOfComps());
	}

	public static void deleteClassroom(Classroom classroom) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE CLASSROOM SET hidden = 1 WHERE classroomID = " + classroom.getPrimaryKey();
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	// ---------------------------------------------------------INSERT, MODIFY,
	// DELETE SCHEDULE ---------------------------------------

	public static void insertSchedule(String semester, String yearID) {

		try {

			// System.out.println("Database connection established");
			String SQL = "INSERT SCHEDULE VALUES (" + "'" + semester + "'" + "," + yearID + ")";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateSchedule(String scheduleID, String updateColumn, String UPDATE) {

		try {

			// System.out.println("Database connection established");
			String SQL = "UPDATE SCHEDULE SET " + updateColumn + " = '" + UPDATE + "' WHERE scheduleID = " + scheduleID;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateSchedule(Schedule schedule) {
		updateSchedule(schedule.getPrimaryKey(), "semester", schedule.getSemester());
		updateSchedule(schedule.getPrimaryKey(), "yearID", schedule.getYear());
	}

	public static void deleteSchedule(Schedule schedule) {

		try {

			// System.out.println("Database connection established");
			String SQL = "DELETE SCHEDULE WHERE scheduleID = " + schedule.getPrimaryKey();
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	// ---------------------------------------------------------INSERT, MODIFY,
	// DELETE SECTION ---------------------------------------

	public static void insertSection(Classroom classroom, Professor professor, Course course, String scheduleID,
			String startTime, String endTime, String startDate, String endDate, String numOfSeats, String daysOfWeek) {

		try {

			// System.out.println("Database connection established");
			String SQL = "INSERT SECTION VALUES (" + classroom.getPrimaryKey() + "," + professor.getPrimaryKey() + ","
					+ course.getPrimaryKey() + "," + scheduleID + "," + "'" + startTime + "'" + "," + "'" + endTime
					+ "'" + "," + "'" + startDate + "'" + "," + "'" + endDate + "'" + "," + numOfSeats + ",'"
					+ daysOfWeek + "')";
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	public static void updateSection(String sectionID, String updateColumn, String UPDATE) {

		try {
			// System.out.println("Database connection established");
			String SQL = "UPDATE SECTION SET " + updateColumn + " = '" + UPDATE + "' WHERE sectionID = " + sectionID;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	public static void updateSection(Section section) {
		String pk = section.getPrimaryKey();
		updateSection(pk, "classroomID", section.getClassroom().getPrimaryKey());
		updateSection(pk, "professorID", section.getProfessor().getPrimaryKey());
		updateSection(pk, "courseID", section.getCourse().getPrimaryKey());
		updateSection(pk, "startTime", section.getStartTime());
		updateSection(pk, "endTime", section.getEndTime());
		updateSection(pk, "NumOfSeats", section.getNumSeats());
		updateSection(pk, "DaysOfWeek", section.getDaysOfWeek());
	}

	public static void deleteSection(Section section) {

		try {

			// System.out.println("Database connection established");
			String SQL = "DELETE SECTION WHERE sectionID = " + section.getPrimaryKey();
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.executeUpdate();

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

	private static void openDatabaseConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void closeDatabaseConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Controller.openDatabaseConnection();
		MainGUI gui = new MainGUI(args);
		Controller.closeDatabaseConnection();
	}

	// **********************************Print
	// Methods*************************************************//
	private static char DEFAULT_SEPARATOR = ',';

	public static void writeLine(Writer w, List<String> values) throws IOException {

		writeLine(w, values, DEFAULT_SEPARATOR, ' ');

	}

	public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
		writeLine(w, values, separators, ' ');
	}

	private static String followCVSformat(String value) {

		String result = value;
		if (result.contains("\"")) {
			result = result.replace("\"", "\"\"");
		}
		return result;

	}

	public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

		boolean first = true;

		// default customQuote is empty

		if (separators == ' ') {
			separators = DEFAULT_SEPARATOR;
		}

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (!first) {
				sb.append(separators);
			}
			if (customQuote == ' ') {
				sb.append(followCVSformat(value));
			} else {
				sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
			}

			first = false;
		}
		sb.append("\n");
		w.append(sb.toString());

	}

	// ********************************************************************************************//
	// private static LinkOption linkOption = null;
	// Method that prints schedule
	public static String exportSchedule(String schedulepk) throws IOException {

		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		Schedule schedule;
		schedule = getSingleSchedule(schedulepk);
		File file = new File(schedule.getSemester() + schedule.getYear());
		String printFile = file.getAbsolutePath() + ".csv";
		FileWriter writer = new FileWriter(printFile);

		String Column1 = "BuildingCode";
		String Column2 = "Room Number";
		String Column3 = "Prof First Name";
		String Column4 = "Prof Last Name";
		String Column5 = "Prefix";
		String Column6 = "Course Number";
		String Column7 = "Course Name";
		String Column8 = "Start Time";
		String Column9 = "End Time";
		String Column10 = "Start Date";
		String Column11 = "End Date";
		String Column12 = "Number of Seats";
		String Column13 = "Days Of Week";

		try {

			writeLine(writer, Arrays.asList(schedule.getSemester(), schedule.getYear()));
			writeLine(writer, Arrays.asList(Column1, Column2, Column3, Column4, Column5, Column6, Column7, Column8,
					Column9, Column10, Column11, Column12, Column13));
			for (int i = 0; i < schedule.getSections().size(); i++) {

				writeLine(writer, Arrays.asList(
						getSingleBuilding(schedule.getSections().get(i).getClassroom().getBuildingID())
								.getBuildingCode(),
						schedule.getSections().get(i).getClassroom().getRoomNumber(),
						schedule.getSections().get(i).getProfessor().getFirstName(),
						schedule.getSections().get(i).getProfessor().getLastName(),
						schedule.getSections().get(i).getCourse().getCourseNumber(),
						schedule.getSections().get(i).getCourse().getCourseNumber(),
						schedule.getSections().get(i).getCourse().getTitle(),
						schedule.getSections().get(i).getStartTime(), schedule.getSections().get(i).getEndTime(),
						schedule.getSections().get(i).getStartDate(), schedule.getSections().get(i).getEndDate(),
						schedule.getSections().get(i).getNumSeats(), schedule.getSections().get(i).getDaysOfWeek()));
			}

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("CSV Did not Export");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		writer.flush();
		writer.close();
		
		return printFile;
	}

	private static Schedule getSingleSchedule(String pk) {

		Schedule scheduleObj = null;
		ArrayList<Section> sections = new ArrayList<Section>();

		// Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			String SQL = "SELECT * FROM SCHEDULE WHERE scheduleID = " + pk;
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				/*
				 * Columns in SCHEDULE TABLE schedules.add(rs.getString(1)); --
				 * scheduleID schedules.add(rs.getString(2)); -- semester
				 * schedules.add(rs.getString(3)); -- yearID
				 */

				sections = getSingleSection(rs.getString(1));
				scheduleObj = new Schedule(rs.getString(1), rs.getString(2), rs.getString(3), sections);
			}

			//

		} catch (Exception ex) {
			// handle the error
			System.err.println("Cannot connect to database server");
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();

		}

		return scheduleObj;

	}
	
	public static ArrayList<Section> getSingleSection(String scheduleID){
		 ArrayList<Section> section = new ArrayList<Section>();
		 Section sectionObj;
	     Statement stmt = null;  
	     ResultSet rs = null;
	     try {
	    		String SQL = "SELECT * FROM SECTION WHERE scheduleID ="+scheduleID;  
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
			       
     
      
   } catch (Exception ex) 
	       { 
	            // handle the error
	        	 	System.err.println("Cannot connect to database server");
	        	  	System.out.println("SQLException: " + ex.getMessage());
	        	  	ex.printStackTrace(); 
	        
	        }
		
		return section; 
	}

}
