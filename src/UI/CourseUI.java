package UI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BLL.CourseBLL;
import DTO.Course;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class CourseUI extends JPanel {
	private JTextField tf_sreach;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JScrollPane ScrollPaneCourse;
	private int row;

	private JButton btn_addCourse;
	private JButton btn_reset;
	private JButton btn_updateCourse;
	private JButton btn_deleteCourse;

	private DefaultTableModel defaultTableModel;

	private JTable tableCourse;
	private JTextField tf_URL;
	private JTextField tf_DepartmentID;
	private JTextField tf_CourseID;
	private JTextField tf_Title;
	private JTextField tf_Credits;
	private JTextField tf_Location;
	private JTextField tf_Days;
	private JTextField tf_Time;

	private JRadioButton r_online;
	private JRadioButton r_onsite;

	private JPanel p_page;
	private JPanel courseGUI;
	private JLabel lblCourseManagement;
	CourseBLL csBLL;

	public JRadioButton getR_online() {
		return r_online;
	}

	public void setR_online(JRadioButton r_online) {
		this.r_online = r_online;
	}

	public JRadioButton getR_onsite() {
		return r_onsite;
	}

	public void setR_onsite(JRadioButton r_onsite) {
		this.r_onsite = r_onsite;
	}

	public CourseUI() throws SQLException {
		csBLL = new CourseBLL();
		courseGUI = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(courseGUI,
				GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(courseGUI, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE)));

		lblCourseManagement = new JLabel("Course Management");
		lblCourseManagement.setFont(new Font("Arial", Font.BOLD, 14));

		p_page = new JPanel();
		setLayout(groupLayout);

		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(0, 30, 720, 430));
		courseGUI.add(panel);

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144)));

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		r_online = new JRadioButton("Online");
		r_online.setSelected(true);
		buttonGroup.add(r_online);
		panel_3.add(r_online);

		r_onsite = new JRadioButton("Onsite");
		buttonGroup.add(r_onsite);
		panel_3.add(r_onsite);

		JPanel panel_5 = new JPanel();

		JLayeredPane layeredPane = new JLayeredPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 523,
														Short.MAX_VALUE)
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																.addComponent(panel_5, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 235,
																		GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(layeredPane)))
										.addGap(18).addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 159,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
				.addGap(12)));

		JPanel OnlinePanel = new JPanel();
		OnlinePanel.setBounds(0, 0, 272, 56);
		layeredPane.add(OnlinePanel);

		tf_URL = new JTextField();
		tf_URL.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("URL");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_OnlinePanel = new GroupLayout(OnlinePanel);
		gl_OnlinePanel.setHorizontalGroup(gl_OnlinePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_OnlinePanel.createSequentialGroup()
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tf_URL, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE).addContainerGap()));
		gl_OnlinePanel.setVerticalGroup(gl_OnlinePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_OnlinePanel.createSequentialGroup()
						.addGroup(gl_OnlinePanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(tf_URL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 16,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(37, Short.MAX_VALUE)));
		OnlinePanel.setLayout(gl_OnlinePanel);

		OnlinePanel.setVisible(true);

		OnlinePanel.setVisible(true);

		JPanel OnsitePanel = new JPanel();
		OnsitePanel.setBounds(0, 0, 227, 111);
		layeredPane.add(OnsitePanel);

		tf_Location = new JTextField();
		tf_Location.setColumns(10);

		tf_Days = new JTextField();
		tf_Days.setColumns(10);

		tf_Time = new JTextField();
		tf_Time.setColumns(10);

		JLabel lb_Time = new JLabel("Time");
		lb_Time.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_Time.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lblMKhaHc_1_2_1 = new JLabel("Days");
		lblMKhaHc_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMKhaHc_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lblMKhaHc_3_1 = new JLabel("Location");
		lblMKhaHc_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMKhaHc_3_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GroupLayout gl_OnsitePanel = new GroupLayout(OnsitePanel);
		gl_OnsitePanel.setHorizontalGroup(gl_OnsitePanel.createParallelGroup(Alignment.LEADING).addGroup(gl_OnsitePanel
				.createSequentialGroup()
				.addGroup(gl_OnsitePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lb_Time, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMKhaHc_1_2_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMKhaHc_3_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_OnsitePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(tf_Time, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
						.addComponent(tf_Days, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
						.addComponent(tf_Location, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
				.addContainerGap()));
		gl_OnsitePanel.setVerticalGroup(gl_OnsitePanel.createParallelGroup(Alignment.LEADING).addGroup(gl_OnsitePanel
				.createSequentialGroup()
				.addGroup(gl_OnsitePanel.createParallelGroup(Alignment.LEADING).addGroup(gl_OnsitePanel
						.createSequentialGroup()
						.addComponent(lblMKhaHc_3_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblMKhaHc_1_2_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lb_Time, GroupLayout.PREFERRED_SIZE,
								19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_OnsitePanel.createSequentialGroup()
								.addComponent(tf_Location, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tf_Days, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(tf_Time,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(42, Short.MAX_VALUE)));
		OnsitePanel.setLayout(gl_OnsitePanel);

		tf_DepartmentID = new JTextField();
		tf_DepartmentID.setColumns(10);

		JLabel lblMKhaHc_1_1_1 = new JLabel("Department");
		lblMKhaHc_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMKhaHc_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lblMKhaHc_2_1 = new JLabel("Credit(s)");
		lblMKhaHc_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMKhaHc_2_1.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lblMKhaHc_1_2 = new JLabel("Course title");
		lblMKhaHc_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMKhaHc_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lblMKhaHc_3 = new JLabel("Course ID");
		lblMKhaHc_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMKhaHc_3.setFont(new Font("Tahoma", Font.PLAIN, 10));

		tf_CourseID = new JTextField();
		tf_CourseID.setColumns(10);

		tf_Title = new JTextField();
		tf_Title.setColumns(10);

		tf_Credits = new JTextField();
		tf_Credits.setColumns(10);

		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_5
				.createSequentialGroup()
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_5.createSequentialGroup()
						.addComponent(lblMKhaHc_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE).addGap(4)
						.addComponent(tf_CourseID, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
								.addComponent(lblMKhaHc_1_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(tf_Title, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
								.addComponent(lblMKhaHc_2_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(tf_Credits, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
								.addComponent(lblMKhaHc_1_1_1, GroupLayout.PREFERRED_SIZE, 90,
										GroupLayout.PREFERRED_SIZE)
								.addGap(4).addComponent(tf_DepartmentID, GroupLayout.PREFERRED_SIZE, 119,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(22, Short.MAX_VALUE)));
		gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMKhaHc_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_CourseID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMKhaHc_1_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_Title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMKhaHc_2_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_Credits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMKhaHc_1_1_1, GroupLayout.PREFERRED_SIZE, 19,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tf_DepartmentID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(44, Short.MAX_VALUE)));
		panel_5.setLayout(gl_panel_5);

		tableCourse = new JTable();
		scrollPane.setViewportView(tableCourse);

		btn_addCourse = new JButton("Add");
		btn_addCourse.setBorderPainted(false);
		btn_addCourse.setForeground(new Color(255, 255, 255));
		btn_addCourse.setBackground(new Color(65,105,225));

		btn_reset = new JButton("Clear");
		btn_reset.setBorderPainted(false);
		btn_reset.setForeground(new Color(255, 255, 255));
		btn_reset.setBackground(new Color(65,105,225));

		btn_updateCourse = new JButton("Update");
		btn_updateCourse.setBorderPainted(false);
		btn_updateCourse.setForeground(new Color(255, 255, 255));
		btn_updateCourse.setBackground(new Color(65,105,225));

		btn_deleteCourse = new JButton("Delete");
		btn_deleteCourse.setBorderPainted(false);
		btn_deleteCourse.setForeground(new Color(255, 255, 255));
		btn_deleteCourse.setBackground(new Color(65,105,225));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(20)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btn_deleteCourse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_updateCourse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_addCourse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_reset, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 99,
										Short.MAX_VALUE))
						.addGap(105)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn_reset, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btn_addCourse, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btn_updateCourse, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btn_deleteCourse, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addGap(158)));
		panel_2.setLayout(gl_panel_2);

		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		tf_sreach = new JTextField();
		tf_sreach.setColumns(10);
		OnsitePanel.setVisible(false);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(tf_sreach,
												GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(389, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						tf_sreach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(129, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);

		btn_addCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addCourse();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btn_updateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateCourse();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btn_deleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteCourse();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btn_reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					clearAllField();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tableCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				Editlnfo(evt);
			}
		});

		r_online.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OnlinePanel.setVisible(true);
				OnsitePanel.setVisible(false);
				try {
					listOnlineCourse(1);
					p_page.removeAll();
					p_page.setLayout(new BorderLayout());
					addOnlineCoursePage();
					p_page.validate();
					p_page.repaint();
					buttonAlign();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		tf_sreach.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
				try {
					searchKeyCourse();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		r_onsite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OnlinePanel.setVisible(false);
				OnsitePanel.setVisible(true);
				try {
					listOnsiteCourse(1);
					p_page.removeAll();
					p_page.setLayout(new BorderLayout());
//					addOnsiteCoursePage();
					p_page.validate();
					p_page.repaint();
					buttonAlign();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		listOnlineCourse(1);
		addOnlineCoursePage();
		buttonAlign();
	}

	private void listOnlineCourse(int page) throws SQLException {
		List list = csBLL.getOnlineCourseTable(page);
		DefaultTableModel model = convertOnlineCourse(list);
		tableCourse.setModel(model);

		courseCenterAlign(5);
	}

	private DefaultTableModel convertOnlineCourse(List list) {
		String[] onlineCourseTableHeader = new String[] { "Course ID", "Title", "Credits", "Department", "URL" };
		Object[][] courseData = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			Course course = (Course) list.get(i);
			courseData[i][0] = course.getCourseID();
			courseData[i][1] = course.getTitle();
			courseData[i][2] = course.getCredits();
			courseData[i][3] = course.getName();
			courseData[i][4] = course.getUrl();
		}
		DefaultTableModel model = new DefaultTableModel(courseData, onlineCourseTableHeader);
		return model;
	}

	private void listOnsiteCourse(int page) throws SQLException {
		List list = csBLL.getOnsiteCourseTable(page);
		DefaultTableModel model = convertOnsiteCourse(list);
		tableCourse.setModel(model);

		courseCenterAlign(7);
	}

	private DefaultTableModel convertOnsiteCourse(List list) {
		String[] onsiteCourseTableHeader = new String[] { "Course ID", "Title", "Credits", "Department", "Location",
				"Days", "Time" };
		Object[][] courseData = new Object[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			Course course = (Course) list.get(i);
			courseData[i][0] = course.getCourseID();
			courseData[i][1] = course.getTitle();
			courseData[i][2] = course.getCredits();
			courseData[i][3] = course.getName();
			courseData[i][4] = course.getLocation();
			courseData[i][5] = course.getDays();
			courseData[i][6] = course.getTime();
		}
		DefaultTableModel model = new DefaultTableModel(courseData, onsiteCourseTableHeader);
		return model;
	}

	public void clearAllField() throws SQLException {
		tf_CourseID.setText("");
		tf_Title.setText("");
		tf_Credits.setText("");
		tf_DepartmentID.setText("");
		tf_URL.setText("");
		tf_Location.setText("");
		tf_Days.setText("");
		tf_Time.setText("");
		tf_sreach.setText("");

		if (r_online.isSelected()) {
			listOnlineCourse(1);
		} else {
			listOnsiteCourse(1);
		}
	}

	public int getrow() {
		return row;
	}

	public void setrow(int row) {
		this.row = row;
	}

	public void getSelectedField() {
		int row = tableCourse.getSelectedRow();
		if (row >= 0) {
			tf_CourseID.setText(tableCourse.getModel().getValueAt(row, 0).toString());
			tf_Title.setText(tableCourse.getModel().getValueAt(row, 1).toString());
			tf_Credits.setText(tableCourse.getModel().getValueAt(row, 2).toString());
			tf_DepartmentID.setText(tableCourse.getModel().getValueAt(row, 3).toString());

			if (r_online.isSelected()) {
				tf_URL.setText(tableCourse.getModel().getValueAt(row, 4).toString());
			} else {
				tf_Location.setText(tableCourse.getModel().getValueAt(row, 4).toString());
				tf_Days.setText(tableCourse.getModel().getValueAt(row, 5).toString());
				tf_Time.setText(tableCourse.getModel().getValueAt(row, 6).toString());
			}
		}
	}

	public void Editlnfo(MouseEvent evt) {
		int column = tableCourse.getSelectedColumn();
		int row = tableCourse.getSelectedRow();
		getSelectedField();
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public Course getOnlineCourse() {

		try {
			Course course = new Course();
			course.setCourseID(Integer.parseInt(tf_CourseID.getText().trim()));
			course.setTitle(tf_Title.getText().trim());
			course.setCredits(Integer.parseInt(tf_Credits.getText().trim()));
			course.setName(tf_DepartmentID.getText().trim());
			course.setUrl(tf_URL.getText().trim());

			return course;
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
		return null;
	}

	public Course getOnsiteCourse() {

		try {
			Course course = new Course();
			course.setCourseID(Integer.parseInt(tf_CourseID.getText().trim()));
			course.setTitle(tf_Title.getText().trim());
			course.setCredits(Integer.parseInt(tf_Credits.getText().trim()));
			course.setName(tf_DepartmentID.getText().trim());
			course.setLocation(tf_Location.getText().trim());
			course.setDays(tf_Days.getText().trim());
			course.setTime(Time.valueOf(tf_Time.getText().trim()));

			return course;
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
		return null;
	}

//	private boolean validCourseID() {
//		String ID = tf_CourseID.getText();
//		if (ID == null || "".equals(ID.trim())) {
//			tf_CourseID.requestFocus();
//			showMessage("Course ID is missing");
//			return false;
//		}
//		return true;
//	}
//
//	private boolean validTitle() {
//		String title = tf_courseTitle.getText();
//		if (title == null || "".equals(title.trim())) {
//			tf_courseTitle.requestFocus();
//			showMessage("Title is missing");
//			return false;
//		}
//		return true;
//	}

	public void addCourse() throws SQLException {
		if (r_online.isSelected()) {
			Course cs = getOnlineCourse();
			cs.setDepartmentID(csBLL.getDepartmentID(cs.getName()));
			if (csBLL.addOnlineCourse(cs) > 0) {
				listOnlineCourse(1);
				showMessage("add online course successfully");
			} else {
				showMessage("add online course failed");
			}
		} else {
			Course cs = getOnsiteCourse();
			cs.setDepartmentID(csBLL.getDepartmentID(cs.getName()));
			if (csBLL.addOnsiteCourse(cs) > 0) {
				listOnsiteCourse(1);
				showMessage("add onsite course successfully");
			} else {
				showMessage("add onsite course failed");
			}
		}
	}

	public void updateCourse() throws SQLException {
		if (r_online.isSelected()) {
			Course cs = getOnlineCourse();
			cs.setDepartmentID(csBLL.getDepartmentID(cs.getName()));
			if (csBLL.updateOnlineCourse(cs, cs.getCourseID()) > 0) {
				listOnlineCourse(1);
				showMessage("update online course successfully");
			} else {
				showMessage("update online course failed");
			}
		} else {
			Course cs = getOnsiteCourse();
			cs.setDepartmentID(csBLL.getDepartmentID(cs.getName()));
			if (csBLL.updateOnsiteCourse(cs, cs.getCourseID()) > 0) {
				listOnsiteCourse(1);
				showMessage("update onsite course successfully");
			} else {
				showMessage("update onsite course failed");
			}
		}
	}

	public void deleteCourse() throws SQLException {
		if (r_online.isSelected()) {
			Course cs = getOnlineCourse();
			if (csBLL.deleteOnlineCourse(cs.getCourseID()) > 0) {
				listOnlineCourse(1);
				showMessage("delete online course successfully");
			} else {
				showMessage("delete online course failed");
			}
		} else {
			Course cs = getOnsiteCourse();
			if (csBLL.deleteOnsiteCourse(cs.getCourseID()) > 0) {
				listOnsiteCourse(1);
				showMessage("delete onsite course successfully");
			} else {
				showMessage("delete onsite course failed");
			}
		}
	}

	public void searchKeyCourse() throws SQLException{
		String text = tf_sreach.getText();
		searchCourse(text, 1);
		p_page.removeAll();
		p_page.setLayout(new BorderLayout());
		addSearchPage(text);
		p_page.validate();
		p_page.repaint();
		buttonAlign(); 
	}

	public void searchCourse(String key, int page) throws SQLException {
		if (r_online.isSelected()) {
			List list = csBLL.searchOnlineCourse(key, page);
			DefaultTableModel model = (DefaultTableModel) convertOnlineCourse(list);
			tableCourse.setModel(model);

			courseCenterAlign(5);
		} else {
			List list = csBLL.searchOnsiteCourse(key, 1);
			DefaultTableModel model = convertOnsiteCourse(list);
			tableCourse.setModel(model);

			courseCenterAlign(7);
		}
	}

	public void addOnlineCoursePage() throws SQLException {
		int num = csBLL.numOfPageOnlineCourse();
		JButton[] btns = new JButton[num];
		if (num > 1) {
			for (int i = 0; i < btns.length; i++) {
				String number = Integer.toString(i);
				int page = i + 1;
				btns[i] = new JButton((i + 1) + " ");
				p_page.add(btns[i]);
				btns[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							listOnlineCourse(page);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}

//	public void addOnsiteCoursePage() throws SQLException {
//		int num = csBLL.numOfPageOnsiteCourse();
//		JButton[] btns = new JButton[num];
//		if (num > 1) {
//			for (int i = 0; i < btns.length; i++) {
//				String number = Integer.toString(i);
//				int page = i + 1;
//				btns[i] = new JButton((i + 1) + " ");
//				p_page.add(btns[i]);
//				btns[i].addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						try {
//							listOnsiteCourse(page);
//						} catch (Exception e1) {
//							e1.printStackTrace();
//						}
//					}
//				});
//			}
//		}
//	}

	public void addSearchPage(String Key) throws SQLException {
		int num = 0;
		if (r_online.isSelected()) {
			num = csBLL.numOfSearchOnlineCourse(Key);
		} else {
			num = csBLL.numOfSearchOnsiteCourse(Key);
		}
		JButton[] btns = new JButton[num];
		if (num > 1) {
			for (int i = 0; i < btns.length; i++) {
				String number = Integer.toString(i);
				int page = i + 1;
				btns[i] = new JButton((i + 1) + " ");
				p_page.add(btns[i]);
				btns[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							searchCourse(Key, page);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}

	public void courseCenterAlign(int column) {
		DefaultTableCellRenderer Align = new DefaultTableCellRenderer();
		Align.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < column; i++) {
			tableCourse.getColumnModel().getColumn(i).setCellRenderer(Align);
		}
	}

	public void buttonAlign() {
		GroupLayout gl_courseGUI = new GroupLayout(courseGUI);
		gl_courseGUI.setHorizontalGroup(gl_courseGUI.createParallelGroup(Alignment.LEADING).addGroup(gl_courseGUI
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_courseGUI.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCourseManagement, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE)
						.addComponent(p_page, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
				.addContainerGap()));
		gl_courseGUI.setVerticalGroup(gl_courseGUI.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_courseGUI.createSequentialGroup()
						.addComponent(lblCourseManagement, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 431, Short.MAX_VALUE)
						.addComponent(p_page, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(18)));
		p_page.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		courseGUI.setLayout(gl_courseGUI);
	}
}
