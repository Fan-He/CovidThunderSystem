import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



class CovidThunderSystem {
	public static void main(String[] args) throws ClassNotFoundException {
		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Goodluck0412";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");

		} catch (SQLException ex) {
			throw new Error("Error ", ex);
		} finally {
			try {
				if (con != null) {
					System.out.println(con.getClientInfo("xiaofan"));
					CovidThunderSystem.viewTable(con);
					CovidThunderSystem.homePage();
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public static void viewTable(Connection con) throws SQLException {
		String query = "select dID, dName, department from Doctor";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int dID = rs.getInt("dID");
				String dName = rs.getString("dName");
				String department = rs.getString("department");
				System.out.println(dID + ", " + dName + ", " + department);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}


	public static JFrame homePage() {
		CovidThunderSystem cot = new CovidThunderSystem();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("This is home page! ");
		frame.setSize(680, 400);
//		JTextArea text = new JTextArea("Welcome to the Covid Thunder System! \nPlease select the database you would like to visit! ");
		JPanel panel = new JPanel();
		JButton doctor = new JButton("Doctor");
		JButton patient = new JButton("Patient");
		JButton ward = new JButton("Ward");
		JButton department = new JButton("Department");
		JButton mr = new JButton("Medical Record");
		JButton ct = new JButton("Covid Test");
		JButton or = new JButton("Operation Rooms");
		JButton[] buttons = {doctor, patient, ward, department, mr, ct, or};
		ImagePanel imgpanel = cot.new ImagePanel("src/healthcare.jpg", buttons);
		doctor.addActionListener(new ButtonAction());
		department.addActionListener(new ButtonAction());
		patient.addActionListener(new ButtonAction());
		ward.addActionListener(new ButtonAction());
		mr.addActionListener(new ButtonAction());
		ct.addActionListener(new ButtonAction());
		or.addActionListener(new ButtonAction());
		//	  doctor.setBounds(50,50,90, 50);
		//	  patient.setBounds(50,50,90, 50);
		//	  ward.setBounds(50,50,90, 50);
		//	  department.setBounds(50,50,90, 50);
		//	  mr.setBounds(50,50,90, 50);
		//	  ct.setBounds(50,50,90, 50);
		//	  or.setBounds(50,50,90, 50);
//		panel.add(text);
		panel.add(doctor);
		panel.add(patient);
		panel.add(ward);
		panel.add(department);
		panel.add(mr);
		panel.add(ct);
		panel.add(or);
		frame.add(imgpanel);
		frame.add(panel);
		//	  frame.pack();
		frame.setVisible(true);
		return frame;
	}

	public static class ButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if(action.equals("Doctor")) {
				try {
					CovidThunderSystem.doctorPage();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(action.equals("Department")){
				try {
					CovidThunderSystem.departmentPage();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(action.equals("Patient")){
				try {
					CovidThunderSystem.patientPage();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(action.equals("Ward")){
				try {
					CovidThunderSystem.wardPage();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(action.equals("Medical Record")){
				try {
					CovidThunderSystem.medicalRecordPage();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(action.equals("Covid Test")){
				try {
					CovidThunderSystem.testPage();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(action.equals("Operation Rooms")){
				try {
					CovidThunderSystem.optRoomPage();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public static JFrame generatePages(String title, String[] columnNames, String[][] data) {
		JFrame frame = new JFrame(title);
		frame.setVisible(true);
		frame.setSize(800, 500);
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);
		return frame;
	}

	//  public static JFrame doctorPage1() throws ClassNotFoundException, SQLException {
	//	  JFrame doctorFrame = new JFrame();
	//	  doctorFrame.setVisible(true);
	//	  doctorFrame.setSize(300, 50);
	//	  JTextArea text = new JTextArea();
	//	  Connection con = null;
	//	  String url = "jdbc:mysql://localhost:3306/mydb";
	//	  String username = "root";
	//	  String password = "Goodluck0412";
	//
	//	  Class.forName("com.mysql.cj.jdbc.Driver");
	//	  con = DriverManager.getConnection(url, username, password);
	//
	//	  System.out.println("Connected!");
	//	  String query = "select dID, dName, department from Doctor";
	//	  try (Statement stmt = con.createStatement()) {
	//		  ResultSet rs = stmt.executeQuery(query);
	//		  while (rs.next()) {
	//			  int dID = rs.getInt("dID");
	//			  String dName = rs.getString("dName");
	//			  String department = rs.getString("department");
	//			  //	        System.out.println(dID + ", " + dName + ", " + department);
	//			  text.append("ID: " + dID + ", Name: " + dName + ", Department: " + department + "\n");
	//		  }
	//	  } catch (SQLException e) {
	//		  System.out.println(e);
	//	  }
	//	  JScrollPane scrollPane = new JScrollPane(text);
	//	  doctorFrame.add(scrollPane);
	//	  return doctorFrame;
	//  }

	public static JFrame doctorPage() throws ClassNotFoundException, SQLException {
		String[][] data = new String[500][3];
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Goodluck0412";
		int i = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		String[] columns = {"dID", "dName", "department"};
		System.out.println("Connected!");
		String query = "select * from Doctor";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String dID = rs.getString("dID");
				String dName = rs.getString("dName");
				String department = rs.getString("department");
				//	        System.out.println(dID + ", " + dName + ", " + department);
				// text.append("ID: " + dID + ", Name: " + dName + ", Department: " + department + "\n");
				String[] row = {dID, dName, department};
				data[i] = row;
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		String title = "Doctor information page";
		return generatePages(title, columns, data);
	}

	public static JFrame departmentPage() throws ClassNotFoundException, SQLException {
		String[][] data = new String[500][2];
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Goodluck0412";
		int i = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		String[] columns = {"department", "manager ID"};
		System.out.println("Connected!");
		String query = "select * from Department";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String managerID = rs.getString("managerID");
				String department = rs.getString("department");
				//	        System.out.println(dID + ", " + dName + ", " + department);
				// text.append("ID: " + dID + ", Name: " + dName + ", Department: " + department + "\n");
				String[] row = {department, managerID};
				data[i] = row;
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		String title = "Department information page";
		return generatePages(title, columns, data);
	}

	public static JFrame patientPage() throws ClassNotFoundException, SQLException {
		String[][] data = new String[500][5];
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Goodluck0412";
		int i = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		String[] columns = {"medical_number", "patient name", "doctor ID", "ward number", "Covid-test number"};
		System.out.println("Connected!");
		String query = "select * from Patient";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String mn = rs.getString("medical_Number");
				String pName = rs.getString("pName");
				String dID = rs.getString("Doctor_dID");
				String wardNumber = rs.getString("Ward_wardNumber");
				String testNumber = rs.getString("Covid_Test_testNumber");
				//	        System.out.println(dID + ", " + dName + ", " + department);
				// text.append("ID: " + dID + ", Name: " + dName + ", Department: " + department + "\n");
				String[] row = {mn, pName, dID, wardNumber, testNumber};
				data[i] = row;
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		String title = "Patient information page";
		return generatePages(title, columns, data);
	}

	public static JFrame wardPage() throws ClassNotFoundException, SQLException {
		String[][] data = new String[500][2];
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Goodluck0412";
		int i = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		String[] columns = {"ward number", "department"};
		System.out.println("Connected!");
		String query = "select * from Ward";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String department = rs.getString("Department_department");
				String wardNumber = rs.getString("wardNumber");
				//	        System.out.println(dID + ", " + dName + ", " + department);
				// text.append("ID: " + dID + ", Name: " + dName + ", Department: " + department + "\n");
				String[] row = {wardNumber, department};
				data[i] = row;
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		String title = "Ward information page";
		return generatePages(title, columns, data);
	}

	public static JFrame medicalRecordPage() throws ClassNotFoundException, SQLException {
		String[][] data = new String[500][3];
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Goodluck0412";
		int i = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		String[] columns = {"patient medical number", "disease", "allergen"};
		System.out.println("Connected!");
		String query = "select * from medical_record";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String number = rs.getString("Patient_medical_Number");
				String disease = rs.getString("disease");
				String allergen = rs.getString("allergen");
				String[] row = {number, disease, allergen};
				data[i] = row;
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		String title = "Medical record information page";
		return generatePages(title, columns, data);
	}

	public static JFrame testPage() throws ClassNotFoundException, SQLException {
		String[][] data = new String[500][5];
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Goodluck0412";
		int i = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		String[] columns = {"test number", "date", "result", "shot number", "patient medical number"};
		System.out.println("Connected!");
		String query = "select * from covid_test";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String number = rs.getString("testNumber");
				String date = rs.getString("date");
				String result = rs.getString("result");
				String shot = rs.getString("shotNum");
				String medicalNumber = rs.getString("Patient_medical_Number");
				String[] row = {number, date, result, shot, medicalNumber};
				data[i] = row;
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		String title = "Covid Test information page";
		return generatePages(title, columns, data);
	}

	public static JFrame optRoomPage() throws ClassNotFoundException, SQLException {
		String[][] data = new String[500][2];
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Goodluck0412";
		int i = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		String[] columns = {"operation room number", "department"};
		System.out.println("Connected!");
		String query = "select * from operating_room";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String number = rs.getString("optRoomNumber");
				String department = rs.getString("Department_department");
				String[] row = {number, department};
				data[i] = row;
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		String title = "Operation room information page";
		return generatePages(title, columns, data);
	}


	class ImagePanel extends JPanel {

		  private Image img;
		  private JButton[] buttons;
		  public ImagePanel(String img, JButton[] buttons) {
		    this(new ImageIcon(img).getImage());
		    this.buttons = buttons;
		  }
		  public ImagePanel(Image img) {
		    this.img = img;
		    Dimension size = new Dimension(1000, 500);
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		  }
		  @Override
		  public void paintComponent(Graphics g) {
			for(int i = 0; i < buttons.length; i++) {
				this.add(buttons[i]);
			}
		    g.drawImage(img, 0, 0, this);
		  }
	}
	



}