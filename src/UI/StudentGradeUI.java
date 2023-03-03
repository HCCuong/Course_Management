package UI;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BLL.CourseBLL;
import BLL.PersonBLL;
import BLL.StudentGradeBLL;
import DAL.StudentGradeDAL;
import DTO.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;

public class StudentGradeUI extends JPanel {

	private JPanel gradeGUI, p_button;
	private JTable studentGradeTable;
	private JTextField gradeTextField;
	private JTextField enrollmentIDTextField;
	private JComboBox studentNameComboBox, subjectComboBox;
	private JTextField search_TextField;
	private StudentGradeBLL studentGradeBLL;
	private DefaultTableModel model;
	private StudentGrade studentGrade;
	private String[] columnNames = { "EnrolmentID", "Course", "Student", "Grade", };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGradeUI frame = new StudentGradeUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public StudentGradeUI() throws SQLException {

		gradeGUI = new JPanel();

		studentGradeBLL = new StudentGradeBLL();

		final JPanel panel = new JPanel();
		panel.setBounds(94, 11, 754, 595);

		JPanel panel_2 = new JPanel();

		JLabel lblNewLabel_1 = new JLabel("StudentID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNewLabel_2 = new JLabel("CourseID");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNewLabel_3 = new JLabel("Grade");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);

		studentNameComboBox = new JComboBox();

		subjectComboBox = new JComboBox();
		getInfoStudent();
		getInfoCourse();
		gradeTextField = new JTextField();
		gradeTextField.setColumns(10);

		enrollmentIDTextField = new JTextField();
		enrollmentIDTextField.setEditable(false);
		enrollmentIDTextField.setColumns(10);

		search_TextField = new JTextField();
		search_TextField.setColumns(10);

		JPanel panel_3 = new JPanel();

		JScrollPane scrollPane = new JScrollPane();

		studentGradeTable = new JTable();
		initTable(1);
		studentGradeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tbModel = (DefaultTableModel) studentGradeTable.getModel();
				String enrollmentID = tbModel.getValueAt(studentGradeTable.getSelectedRow(), 0).toString();
				String courseID = tbModel.getValueAt(studentGradeTable.getSelectedRow(), 1).toString();
				String studentID = tbModel.getValueAt(studentGradeTable.getSelectedRow(), 2).toString();
				String grade = tbModel.getValueAt(studentGradeTable.getSelectedRow(), 3).toString();

				studentNameComboBox.setSelectedItem(studentID);
				subjectComboBox.setSelectedItem(courseID);
				enrollmentIDTextField.setText(enrollmentID);
				gradeTextField.setText(grade);

			}

		});

		scrollPane.setViewportView(studentGradeTable);
		GroupLayout gl_gradeGUI = new GroupLayout(gradeGUI);
		gl_gradeGUI.setHorizontalGroup(gl_gradeGUI.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gradeGUI.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 676, Short.MAX_VALUE).addContainerGap()));
		gl_gradeGUI.setVerticalGroup(gl_gradeGUI.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				gl_gradeGUI.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(20, Short.MAX_VALUE)));

		JPanel panel_1 = new JPanel();

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBorderPainted(false);
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(65,105,225));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStudentGrade();

			}
		});
		btnUpdate.setBounds(10, 11, 110, 35);
		panel_1.add(btnUpdate);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBorderPainted(false);
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(65,105,225));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addStudentGrade();
			}
		});
		btnAdd.setBounds(10, 65, 110, 35);
		panel_1.add(btnAdd);

		search_TextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String keyword = search_TextField.getText();
				p_button.removeAll();
				p_button.revalidate();
				p_button.repaint();
				addStudentGradeSearchPage(keyword);
				search(keyword, 1);
			}
		});

		JButton btnReset = new JButton("Reset");
		btnReset.setBorderPainted(false);
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setBackground(new Color(65,105,225));
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetUI();
			}

		});
		btnReset.setBounds(10, 203, 110, 35);
		panel_1.add(btnReset);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBorderPainted(false);
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(65,105,225));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteStudentGrade();

			}

		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(10, 157, 110, 35);
		panel_1.add(btnDelete);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(10).addComponent(btnUpdate,
								GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addGroup(gl_panel_1
								.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(11)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(11)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(11)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGap(24)));
		panel_1.setLayout(gl_panel_1);

		p_button = new JPanel();
//		addStudentGradePage();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(37))
				.addGroup(Alignment.LEADING,
						gl_panel.createSequentialGroup().addGap(1)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING,
						gl_panel.createSequentialGroup().addContainerGap()
								.addComponent(p_button, GroupLayout.PREFERRED_SIZE, 641, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(39, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel_1, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 191, Short.MAX_VALUE))
						.addGap(8).addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(p_button, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE).addGap(30)));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3.createSequentialGroup()
						.addContainerGap().addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(11)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);

		JLabel lblNewLabel_4 = new JLabel("Search");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNewLabel = new JLabel("Enrollment ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 86,
												Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(enrollmentIDTextField, GroupLayout.PREFERRED_SIZE, 217,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
												.addComponent(subjectComboBox, GroupLayout.PREFERRED_SIZE, 217,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(studentNameComboBox, GroupLayout.PREFERRED_SIZE, 217,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(gradeTextField, GroupLayout.PREFERRED_SIZE, 217,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(search_TextField, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
				.addGap(191)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(enrollmentIDTextField, GroupLayout.PREFERRED_SIZE, 28,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(subjectComboBox, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 28,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(studentNameComboBox, GroupLayout.PREFERRED_SIZE, 29,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24,
										GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(gradeTextField, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup().addGap(24).addComponent(lblNewLabel_4))
								.addGroup(gl_panel_2.createSequentialGroup().addGap(16).addComponent(search_TextField,
										GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		gradeGUI.setLayout(gl_gradeGUI);

		JLabel lblAssignmentManange = new JLabel("Grade Manage");
		lblAssignmentManange.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblAssignmentManange,
								GroupLayout.PREFERRED_SIZE, 562, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(gradeGUI,
								GroupLayout.PREFERRED_SIZE, 679, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(256, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblAssignmentManange, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(gradeGUI, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(48, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	
	public void getInfoStudent() {
		try {
			ArrayList<StudentGrade> student = studentGradeBLL.readStudent();
			studentNameComboBox.addItem("Student's Name");
			for (StudentGrade i : student) {
				String a = i.getName();
				studentNameComboBox.addItem(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getInfoCourse() {
		try {
			ArrayList<StudentGrade> courseID = studentGradeBLL.readCourse();
			subjectComboBox.addItem("Subject");
			for (StudentGrade j : courseID) {
				String a = j.getCourse();
				subjectComboBox.addItem(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void addStudentGradePage() {
//		int num = -1;
//		try {
//			num = studentGradeBLL.numOfStudentGradePage();
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		JButton[] btns = new JButton[num];
//		if (num > 1) {
//			for (int i = 0; i < btns.length; i++) {
//				String number = Integer.toString(i);
//				final int page = i + 1;
//				btns[i] = new JButton((i + 1) + " ");
//				p_button.add(btns[i]);
//				btns[i].addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						try {
//							initTable(page);
//						} catch (Exception e1) {
//							e1.printStackTrace();
//						}
//					}
//				});
//			}
//		}
//	}
	
	private DefaultTableModel convertStudentGrade(List list) {
		Object[][] data = new Object[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			studentGrade = (StudentGrade) list.get(i);
			data[i][0] = studentGrade.getEnrollmentID();
			data[i][1] = studentGrade.getCourse();
			data[i][2] = studentGrade.getName();
			data[i][3] = studentGrade.getGrade();
		}
		model = new DefaultTableModel(data, columnNames);
		return model;
	}

	public void initTable(int page) {
		try {
			List students = studentGradeBLL.initStudentList(page);
			DefaultTableModel model = convertStudentGrade(students);
			studentGradeTable.setModel(model);
			DefaultTableCellRenderer Align = new DefaultTableCellRenderer();
			Align.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < 4; i++) {
				studentGradeTable.getColumnModel().getColumn(i).setCellRenderer(Align);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void initTable() {
//	try {
//		List students = studentGradeBLL.initStudentList(page);
//		for (int i = 0; i < students.size(); i++) {
//
//			StudentGrade student_temp = (StudentGrade) students.get(i);
//			String enrollmentID = String.valueOf(student_temp.getEnrollmentID());
//			String courseID = String.valueOf(student_temp.getCourse());
//			String studentID = String.valueOf(student_temp.getName());
//			String grade = String.valueOf(student_temp.getGrade());
//
//			String tbData[] = { enrollmentID, courseID, studentID, grade };
//			DefaultTableModel tbModel = (DefaultTableModel) table.getModel();
//			tbModel.addRow(tbData);
//		}
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}

	public void updateStudentGrade() {
		String enrollmentID = enrollmentIDTextField.getText();
		String course = subjectComboBox.getSelectedItem().toString();
		String student = studentNameComboBox.getSelectedItem().toString();
		String grade = gradeTextField.getText();

		DefaultTableModel model = (DefaultTableModel) studentGradeTable.getModel();
		boolean found = false;
		for (int i = 0; i < model.getRowCount(); i++) {
			if (model.getValueAt(i, 1).equals(course) && model.getValueAt(i, 2).equals(student)) {
				found = true;
				try {
					int CourseID = studentGradeBLL.readCourseID(subjectComboBox.getSelectedItem().toString());
					int StudentID = studentGradeBLL.readStudentID(studentNameComboBox.getSelectedItem().toString());
					ArrayList studentInfo = new ArrayList();
					studentInfo.add(enrollmentIDTextField.getText());
					studentInfo.add(CourseID);
					studentInfo.add(StudentID);
					studentInfo.add(gradeTextField.getText());

					int status = new StudentGradeBLL().updateGrade(studentInfo);
					switch (status) {
					case -1:
						JOptionPane.showMessageDialog(gradeGUI, "Database error, cannot retrive data");
						break;
					case 0:
						JOptionPane.showMessageDialog(gradeGUI, "Update information failed");
						break;
					case 1:
						JOptionPane.showMessageDialog(gradeGUI, "Update information succeeded");
						break;
					default:
						JOptionPane.showMessageDialog(gradeGUI, "Unexpected error happened");
						break;
					}

					break;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (found) {
			model = (DefaultTableModel) studentGradeTable.getModel();
			model.setRowCount(0);
			initTable(1);
		} else {
			JOptionPane.showMessageDialog(gradeGUI, "Student not exist on course");
		}

	}

	public void resetUI() {
		DefaultTableModel tbModel = (DefaultTableModel) studentGradeTable.getModel();
		tbModel.setRowCount(0);
		initTable(1);
		search_TextField.setText("");
		enrollmentIDTextField.setText("");
		subjectComboBox.setSelectedIndex(0);
		studentNameComboBox.setSelectedIndex(0);
		gradeTextField.setText("");

	}

	public void search(String keyword, int page) {
		List students = null;
		try {
			students = studentGradeBLL.searchStudentGrade(keyword, page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel model = convertStudentGrade(students);
		studentGradeTable.setModel(model);
		DefaultTableCellRenderer Align = new DefaultTableCellRenderer();
		Align.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < 4; i++) {
			studentGradeTable.getColumnModel().getColumn(i).setCellRenderer(Align);
		}
	}

	public void addStudentGradeSearchPage(final String keyword) {
		int num = -1;
		try {
			num = studentGradeBLL.numOfSearchPage(keyword);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JButton[] btns = new JButton[num];
		if (num > 1) {
			for (int i = 0; i < btns.length; i++) {
				String number = Integer.toString(i);
				final int page = i + 1;
				btns[i] = new JButton((i + 1) + " ");
				p_button.add(btns[i]);
				btns[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							search(keyword, page);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}

	public void deleteStudentGrade() {
		model = (DefaultTableModel) studentGradeTable.getModel();
		String enrollmentID_1 = model.getValueAt(studentGradeTable.getSelectedRow(), 0).toString();
		int enrollmentID = Integer.parseInt(enrollmentIDTextField.getText());
		if (enrollmentID_1.equals(enrollmentIDTextField.getText())) {
			int status = new StudentGradeBLL().deleteStudentFromCourse(enrollmentID);
			switch (status) {
			case -1:
				JOptionPane.showMessageDialog(gradeGUI, "Database error, cannot retrive data");
				break;
			case 0:
				JOptionPane.showMessageDialog(gradeGUI, "Cannot delete this student");
				break;
			case 1:
				JOptionPane.showMessageDialog(gradeGUI, "Student is deleted");
				break;
			default:
				JOptionPane.showMessageDialog(gradeGUI, "Unexpected error happened");
				break;
			}
		}

		model = (DefaultTableModel) studentGradeTable.getModel();
		model.setRowCount(0);

		initTable(1);
	}

	public void addStudentGrade() {
		try {
			int targetStudent = studentGradeBLL.readStudentID(studentNameComboBox.getSelectedItem().toString());
			int targetCourse = studentGradeBLL.readCourseID(subjectComboBox.getSelectedItem().toString());
			int status = studentGradeBLL.registerNewCourse(targetCourse, targetStudent);

			switch (status) {
			case -1:
				JOptionPane.showMessageDialog(gradeGUI, "Database error, cannot retrive data");
				break;
			case 0:
				JOptionPane.showMessageDialog(gradeGUI, "Add student on this course failed");
				break;
			case 1:
				JOptionPane.showMessageDialog(gradeGUI, "Add student on this course succeeded");
				break;
			default:
				JOptionPane.showMessageDialog(gradeGUI, "Unexpected error happened");
				break;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		initTable(1);
	}
}