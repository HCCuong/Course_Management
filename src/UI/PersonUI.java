package UI;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BLL.PersonBLL;
import DTO.Person;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
//import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

public class PersonUI extends JPanel {
	private JTextField txtSearch;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTable table;
	private DefaultTableModel model;
	private String[] columnNames = { "PersonID", "FirstName", "LastName", "HireDate", "EnrollmanetDate" };
	private ButtonGroup btnGroup;
	private JRadioButton rdbtnHireDate, rdbtnEnrollmentDate;

	private PersonBLL psBLL;
	private JTextField txtPersonID;
	private JButton button;
	private JPanel personGUI;
	private JPanel panel2;
	private JPanel p_button;
	private JLabel lblNewLabel;
	private JPanel panel13;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonUI frame = new PersonUI();
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
	public PersonUI() throws SQLException {

		JPanel personGUI = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(personGUI, GroupLayout.PREFERRED_SIZE, 681, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(184, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addComponent(personGUI, GroupLayout.PREFERRED_SIZE, 539, Short.MAX_VALUE).addContainerGap()));
		setLayout(groupLayout);

		panel2 = new JPanel();
		panel13 = new JPanel();
		lblNewLabel = new JLabel("Person Management");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));

		scrollPane = new JScrollPane();

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getPerson();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lbFirstName = new JLabel("First Name");
		lbFirstName.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lbLastName = new JLabel("Last Name");
		lbLastName.setFont(new Font("Arial", Font.PLAIN, 12));

		txtSearch = new JTextField();

		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String searchStr = txtSearch.getText();
					search(searchStr, 1);
					p_button.removeAll();
					p_button.setLayout(new BorderLayout());
					addSearchPage(searchStr);
					p_button.validate();
					p_button.repaint();
					buttonAlign();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		txtSearch.setColumns(10);

		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);

		rdbtnHireDate = new JRadioButton("Hire Date");
		rdbtnHireDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadInstructor(1);
					p_button.removeAll();
					p_button.setLayout(new BorderLayout());
					addTnstructorPage();
					p_button.validate();
					p_button.repaint();
					buttonAlign();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rdbtnHireDate.setFont(new Font("Arial", Font.PLAIN, 12));

		rdbtnEnrollmentDate = new JRadioButton("Enrollment Date");
		rdbtnEnrollmentDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadStudents(1);
					p_button.removeAll();
					p_button.setLayout(new BorderLayout());
//					addStudentPage();
					p_button.validate();
					p_button.repaint();
					buttonAlign();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rdbtnEnrollmentDate.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));

		JLabel lbPersonID = new JLabel("PersonID");
		lbPersonID.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtPersonID = new JTextField();
		txtPersonID.setEditable(false);
		txtPersonID.setColumns(10);

		JPanel panel = new JPanel();
		GroupLayout gl_panel13 = new GroupLayout(panel13);
		gl_panel13.setHorizontalGroup(gl_panel13.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel13
				.createSequentialGroup().addGap(19)
				.addGroup(gl_panel13.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel13.createSequentialGroup()
								.addGroup(gl_panel13.createParallelGroup(Alignment.LEADING)
										.addComponent(lbLastName, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
										.addComponent(lbFirstName, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
										.addComponent(lbPersonID, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel13.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtPersonID).addComponent(txtFirstName, 166, 166, Short.MAX_VALUE)
										.addComponent(txtLastName))
								.addGap(45)
								.addGroup(gl_panel13.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnHireDate, GroupLayout.PREFERRED_SIZE, 166,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(rdbtnEnrollmentDate, GroupLayout.PREFERRED_SIZE, 166,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel13.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE).addGap(67)));
		gl_panel13.setVerticalGroup(gl_panel13.createParallelGroup(Alignment.LEADING).addGroup(gl_panel13
				.createSequentialGroup().addGap(33)
				.addGroup(gl_panel13.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel13.createSequentialGroup().addGroup(gl_panel13
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel13.createSequentialGroup()
										.addComponent(rdbtnHireDate, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(rdbtnEnrollmentDate, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel13.createSequentialGroup()
										.addGroup(gl_panel13.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lbPersonID, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(
														txtPersonID, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel13.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_panel13.createSequentialGroup().addGap(18).addComponent(
														txtFirstName, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel13.createSequentialGroup().addGap(17).addComponent(
														lbFirstName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)))
										.addGap(15)
										.addGroup(gl_panel13.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lbLastName, GroupLayout.PREFERRED_SIZE, 21,
														GroupLayout.PREFERRED_SIZE))))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel13.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(57, Short.MAX_VALUE)));

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatePerson();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 12));
		btnUpdate.setOpaque(true);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(65,105,225));

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHireDate.isSelected()) {
					try {
						addInstructor();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						addStudent();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnInsert.setForeground(new Color(255, 255, 255));
		btnInsert.setFont(new Font("Arial", Font.PLAIN, 12));
		btnInsert.setOpaque(true);
		btnInsert.setBorderPainted(false);
		btnInsert.setBackground(new Color(65,105,225));

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deletePerson();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setOpaque(true);
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(65,105,225));

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReset.setOpaque(true);
		btnReset.setBorderPainted(false);
		btnReset.setBackground(new Color(65,105,225));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnReset, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
						.addComponent(btnInsert, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(20, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		panel13.setLayout(gl_panel13);

		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnHireDate);
		btnGroup.add(rdbtnEnrollmentDate);
		GroupLayout gl_personGUI = new GroupLayout(personGUI);
		gl_personGUI.setHorizontalGroup(gl_personGUI.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_personGUI
						.createSequentialGroup().addComponent(panel2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(173, Short.MAX_VALUE)));
		gl_personGUI.setVerticalGroup(gl_personGUI.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_personGUI.createSequentialGroup().addGap(5)
						.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(43, Short.MAX_VALUE)));
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
						.addGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel13, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel2.setVerticalGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel13, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE).addGap(206)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(40, Short.MAX_VALUE)));
		panel2.setLayout(gl_panel2);
		personGUI.setLayout(gl_personGUI);
		p_button = new JPanel();
		loadPerson(1);
//		addPersonPage();
		buttonAlign();
	}

	private void loadPerson(int page) throws SQLException {
		psBLL = new PersonBLL();
		List list = psBLL.loadPerson(page);
		DefaultTableModel model = convertPerson(list);
		table.setModel(model);

		personCenterAlign();
	}

	private DefaultTableModel convertPerson(List list) {
		Object[][] data = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			Person p = (Person) list.get(i);
			data[i][0] = p.getPersonID();
			data[i][1] = p.getFirstName();
			data[i][2] = p.getLastName();
			data[i][3] = p.getHireDate();
			data[i][4] = p.getEnrollmentDate();
		}
		model = new DefaultTableModel(data, columnNames);
		return model;
	}

	private void loadStudents(int page) throws SQLException {
		List list = psBLL.loadStudents(page);

		String[] column = { "PersonID", "FirstName", "LastName", "EnrollmanetDate" };
		Object[][] data = new Object[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			Person p = (Person) list.get(i);
			data[i][0] = p.getPersonID();
			data[i][1] = p.getFirstName();
			data[i][2] = p.getLastName();
			data[i][3] = p.getEnrollmentDate();
		}
		model = new DefaultTableModel(data, column);
		table.setModel(model);

		personCenterAlign();
	}

	private void loadInstructor(int page) throws SQLException {
		List list = psBLL.loadInstructor(page);
		String[] column = { "PersonID", "FirstName", "LastName", "HireDate" };
		Object[][] data = new Object[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			Person p = (Person) list.get(i);
			data[i][0] = p.getPersonID();
			data[i][1] = p.getFirstName();
			data[i][2] = p.getLastName();
			data[i][3] = p.getHireDate();
		}
		model = new DefaultTableModel(data, column);
		table.setModel(model);

		personCenterAlign();
	}

	private void reset() {
		txtFirstName.setText("");
		txtLastName.setText("");
		txtPersonID.setText("");
		btnGroup.clearSelection();
		try {
			loadPerson(1);
			p_button.removeAll();
			p_button.setLayout(new BorderLayout());
//			addPersonPage();
			p_button.validate();
			p_button.repaint();
			buttonAlign();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getPerson() {
		int row = table.getSelectedRow();

		txtPersonID.setText(table.getValueAt(row, 0).toString());
		txtFirstName.setText(table.getValueAt(row, 1).toString());
		txtLastName.setText(table.getValueAt(row, 2).toString());
		if (table.getValueAt(row, 3) != null) {
			rdbtnHireDate.setSelected(true);
		} else {
			rdbtnEnrollmentDate.setSelected(true);
		}
	}

	private void addStudent() throws HeadlessException, SQLException {
		Person p = new Person();

		p.setFirstName(txtFirstName.getText());
		p.setLastName(txtLastName.getText());

		psBLL = new PersonBLL();
		if (psBLL.addStudent(p) > 0) {
			loadStudents(1);
			JOptionPane.showMessageDialog(null, "AddStudent successfully");
		} else {
			JOptionPane.showMessageDialog(null, "AddStudent failed !");
		}
	}

	private void addInstructor() throws HeadlessException, SQLException {
		Person p = new Person();

		p.setFirstName(txtFirstName.getText());
		p.setLastName(txtLastName.getText());

		psBLL = new PersonBLL();
		if (psBLL.addInstructor(p) > 0) {
			loadInstructor(1);
			JOptionPane.showMessageDialog(null, "AddInstructor successfully");
		} else {
			JOptionPane.showMessageDialog(null, "AddInstructor failed !!!");
		}
	}

	private void updatePerson() throws SQLException {
		Person p = new Person();
		p.setPersonID(Integer.parseInt(txtPersonID.getText()));
		p.setFirstName(txtFirstName.getText());
		p.setLastName(txtLastName.getText());

		psBLL = new PersonBLL();
		if (psBLL.updatePerson(p) > 0) {
			if (rdbtnHireDate.isSelected()) {
				loadInstructor(1);
			}
			if (rdbtnEnrollmentDate.isSelected()) {
				loadStudents(1);
			}
			JOptionPane.showMessageDialog(null, "Update successfully");
		} else {
			JOptionPane.showMessageDialog(null, "Update failed !!!");
		}
	}

	private void deletePerson() throws HeadlessException, SQLException {
		int check = Integer.parseInt(txtPersonID.getText());
		if (check <= 0) {
			JOptionPane.showMessageDialog(null, "Please select 1 person from the table to delete");
			return;
		}
		psBLL = new PersonBLL();
		Person p = new Person();
		p.setPersonID(Integer.parseInt(txtPersonID.getText()));
		int kt = JOptionPane.showConfirmDialog(null, "Do you want to delete ?");
		if (kt == JOptionPane.YES_OPTION) {
			if (psBLL.deletePerson(p.getPersonID()) > 0) {
				if (rdbtnHireDate.isSelected()) {
					loadInstructor(1);
				}
				if (rdbtnEnrollmentDate.isSelected()) {
					loadStudents(1);
				}
				JOptionPane.showMessageDialog(null, "Delete successfully", "Thông báo", 1);
			} else {
				JOptionPane.showMessageDialog(null, "Delete failed !!!", "Thông báo", 0);
			}
		} else {
			return;
		}
	}

//	public void addPersonPage() throws SQLException {
//		int num = psBLL.numOfPersonPage();
//		JButton[] btns = new JButton[num];
//		if (num > 1) {
//			for (int i = 0; i < btns.length; i++) {
//				String number = Integer.toString(i);
//				int page = i + 1;
//				btns[i] = new JButton((i + 1) + " ");
//				p_button.add(btns[i]);
//				btns[i].addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						try {
//							loadPerson(page);
//						} catch (Exception e1) {
//							e1.printStackTrace();
//						}
//					}
//				});
//			}
//		}
//	}

	public void addTnstructorPage() throws SQLException {
		int num = psBLL.numOfInstructorPage();
		JButton[] btns = new JButton[num];
		if (num > 1) {
			for (int i = 0; i < btns.length; i++) {
				String number = Integer.toString(i);
				int page = i + 1;
				btns[i] = new JButton((i + 1) + " ");
				p_button.add(btns[i]);
				btns[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							loadInstructor(page);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}

//	public void addStudentPage() throws SQLException {
//		int num = psBLL.numOfStudentPage();
//		JButton[] btns = new JButton[num];
//		if (num > 1) {
//			for (int i = 0; i < btns.length; i++) {
//				String number = Integer.toString(i);
//				int page = i + 1;
//				btns[i] = new JButton((i + 1) + " ");
//				p_button.add(btns[i]);
//				btns[i].addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						try {
//							loadStudents(page);
//						} catch (Exception e1) {
//							e1.printStackTrace();
//						}
//					}
//				});
//			}
//		}
//	}

	private void search(String key, int page) throws SQLException {
		psBLL = new PersonBLL();
		List list = psBLL.searchPerson(key, page);
		DefaultTableModel model = convertPerson(list);
		table.setModel(model);

		personCenterAlign();
	}

	public void addSearchPage(String Key) throws SQLException {
		int num = psBLL.numOfSearchPage(Key);
		JButton[] btns = new JButton[num];
		if (num > 1) {
			for (int i = 0; i < btns.length; i++) {
				String number = Integer.toString(i);
				int page = i + 1;
				btns[i] = new JButton((i + 1) + " ");
				p_button.add(btns[i]);
				btns[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							search(Key, page);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}

	public void personCenterAlign() {
		DefaultTableCellRenderer Align = new DefaultTableCellRenderer();
		Align.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < 3; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(Align);
		}
	}

	public void buttonAlign() {
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
						.addGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel13, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE)
								.addComponent(p_button, GroupLayout.PREFERRED_SIZE, 662, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(10, Short.MAX_VALUE)));
		gl_panel2.setVerticalGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel13, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(p_button, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE).addGap(34)));
		p_button.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel2.setLayout(gl_panel2);
	}   
}
