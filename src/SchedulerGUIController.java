import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;


public class SchedulerGUIController extends JFrame implements ActionListener {
	private static final long serialVersionUID = 853851515017588154L;
	private JMenuBar menuBar;
	private JMenu resourcesMenu, resourcesAddMenu, scheduleMenu, helpMenu;
	private JMenuItem addClassroom, addCourse, addCampus, addSection, addSchedule, addProfessor, addBuilding;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private String programTitle = "Super Scheduler";
	private enum Resource {
		Campus, Building, Classroom, Professor, Course, Schedule, Section
	}
	
	Action addClassroomAction = new AbstractAction("Classroom") {
		private static final long serialVersionUID = 6458706058533655369L;
		public void actionPerformed(ActionEvent event) {			
			addResource(Resource.Classroom);
		}		
	};
	
	Action addCourseAction = new AbstractAction("Course") {
		private static final long serialVersionUID = 3636547395935894394L;
		public void actionPerformed(ActionEvent event) {			
			addResource(Resource.Course);
		}		
	};
	
	Action addCampusAction = new AbstractAction("Campus") {
		private static final long serialVersionUID = 6605556645961134611L;
		public void actionPerformed(ActionEvent event) {
			addResource(Resource.Campus);
		}		
	};
	
	Action addSectionAction = new AbstractAction("Section") {
		private static final long serialVersionUID = 3605563216652070519L;
		public void actionPerformed(ActionEvent event) {
			addResource(Resource.Section);
		}		
	};
	
	Action addScheduleAction = new AbstractAction("Schedule") {
		private static final long serialVersionUID = -8312594230318444700L;
		public void actionPerformed(ActionEvent event) {
			addResource(Resource.Schedule);
		}		
	};
	
	Action addProfessorAction = new AbstractAction("Professor") {
		private static final long serialVersionUID = 2398623046248392795L;
		public void actionPerformed(ActionEvent event) {			
			addResource(Resource.Professor);
		}		
	};
	
	Action addBuildingAction = new AbstractAction("Building") {
		private static final long serialVersionUID = 3449638019170368998L;
		public void actionPerformed(ActionEvent event) {			
			addResource(Resource.Building);
		}		
	};
	
	public SchedulerGUIController() {
		try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	        	UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event) {
				exit();
			}
		});
		setLayout(new BorderLayout());
		makeMenu();
		setTitle(programTitle);
//		setSize(1250, 1000);
//		setMinimumSize(new Dimension(1250, 1000));
//		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
	}
	
	private void makeMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Begin Resources Menu
		resourcesMenu = new JMenu("Resources");
		resourcesMenu.setMnemonic('R');
		menuBar.add(resourcesMenu);
		
		//Begin Assets Add sub menu
		resourcesAddMenu = new JMenu("Add");
		resourcesAddMenu.setMnemonic('A');
		resourcesMenu.add(resourcesAddMenu);
		
		addCourse = new JMenuItem(addCourseAction);
		addCourse.setMnemonic('C');
		addCourse.setToolTipText("Add a new Course Resource");
		resourcesAddMenu.add(addCourse);
		
		addProfessor = new JMenuItem(addProfessorAction);
		addProfessor.setMnemonic('P');
		addProfessor.setToolTipText("Add a new Professor Resource");
		resourcesAddMenu.add(addProfessor);
		
		addCampus = new JMenuItem(addCampusAction);
		addCampus.setMnemonic('a');
		addCampus.setToolTipText("Add a new Campus Resource");
		resourcesAddMenu.add(addCampus);
		
		addBuilding = new JMenuItem(addBuildingAction);
		addBuilding.setMnemonic('B');
		addBuilding.setToolTipText("Add a new Building Resource");
		resourcesAddMenu.add(addBuilding);
		
		addClassroom = new JMenuItem(addClassroomAction);
		addClassroom.setMnemonic('r');
		addClassroom.setToolTipText("Add a new Classroom Resource");
		resourcesAddMenu.add(addClassroom);
		
		addSchedule = new JMenuItem(addScheduleAction);
		addSchedule.setMnemonic('S');
		addSchedule.setToolTipText("Add a new Schedule Resource");
		resourcesAddMenu.add(addSchedule);
		
		addSection = new JMenuItem(addSectionAction);
		addSection.setMnemonic('e');
		addSection.setToolTipText("Add a new Section Resource");
		resourcesAddMenu.add(addSection);
		//End Assets Add sub menu
		//End Resources Menu
		
		//Begin Help Menu
		scheduleMenu = new JMenu("Schedule");
		scheduleMenu.setMnemonic('S');
		menuBar.add(scheduleMenu);
		//End Schedule Menu
		
		//Begin Schedule Menu
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		menuBar.add(helpMenu);
		//End Schedule Menu
	}
	
	private void exit(){
		if (JOptionPane.showConfirmDialog(this, "Do you really want to quit Super Scheduler?", "Quit Scheduler?", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
	
	private void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	private void addResource(Resource resource) {
		switch(resource) {
		case Campus:
			showMessage("Add Campus", "I would like to add a new Campus");
			break;
		case Building:
			showMessage("Add Building", "I would like to add a new Building");
			break;
		case Classroom:
			showMessage("Add Classroom", "I would like to add a new Classroom");
			break;
		case Professor:
			showMessage("Add Professor", "I would like to add a new Professor");
			break;
		case Course:
			showMessage("Add Course", "I would like to add a new Course");
			break;
		case Schedule:
			showMessage("Add Schedule", "I would like to add a new Schedule");
			break;
		case Section:
			showMessage("Add Section", "I would like to add a new Section");
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new SchedulerGUIController();
	}
}
