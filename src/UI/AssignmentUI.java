package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BLL.AssignmentBLL;
import DAL.AssignmentDAL;
import DTO.Person;
import DTO.Assignment;
import DTO.Course;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import java.awt.event.KeyAdapter;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;

public class AssignmentUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel pn;
	private JButton add;
	private JButton delete;
	private JButton update;
	private JButton reset;

	private JScrollPane listAsm;
	private JTable asmTable;

	private JLabel lName;
	private JLabel lLecture;
	private JTextField tSearch;
	private JLabel lblNewLabel;

	private JComboBox cbLecture;
	private JComboBox cbName;

	private DefaultTableModel defaultTableModel;

	private int row;

	AssignmentBLL asmBLL;
	private JLabel lblAssignmentManange;
	private JPanel p_page;

	public AssignmentUI() throws SQLException {
		asmBLL = new AssignmentBLL();
		JPanel assignmentGUI = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(assignmentGUI, GroupLayout.PREFERRED_SIZE, 713, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(88, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(assignmentGUI, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(117, Short.MAX_VALUE)));
		setLayout(groupLayout);

		lName = new JLabel("Name:");

		lLecture = new JLabel("Lecture");

		add = new JButton("Add");
		add.setEnabled(true);
		add.setForeground(Color.WHITE);
		add.setEnabled(true);
		add.setBorderPainted(false);
		add.setBackground(new Color(65,105,225));

		update = new JButton("Update");
		update.setVisible(true);
		update.setForeground(Color.WHITE);
		update.setBorderPainted(false);
		update.setBackground(new Color(65,105,225));

		delete = new JButton("Delete");
		delete.setEnabled(true);
		delete.setForeground(Color.WHITE);
		delete.setBorderPainted(false);
		delete.setBackground(new Color(65,105,225));

		reset = new JButton("Reset");
		reset.setForeground(Color.WHITE);
		reset.setEnabled(true);
		reset.setBorderPainted(false);
		reset.setBackground(new Color(65,105,225));
		
		lblNewLabel = new JLabel("Search");

		cbLecture = new JComboBox();
		cbName = new JComboBox();

		tSearch = new JTextField();
		tSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				try {
					searchKeyAsm();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		asmTable = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		listAsm = new JScrollPane();
		listAsm.setForeground(Color.BLACK);
		listAsm.setViewportView(asmTable);

		pn = new JPanel();
		assignmentGUI.add(pn);

		groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGap(147)
						.addComponent(pn, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(pn, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(68, Short.MAX_VALUE)));

		p_page = new JPanel();

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addAsm();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteAsm();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clearInfo();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateAsm();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		lblAssignmentManange = new JLabel("Assignment Manange");
		lblAssignmentManange.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout gl_assignmentGUI = new GroupLayout(assignmentGUI);
		gl_assignmentGUI.setHorizontalGroup(gl_assignmentGUI.createParallelGroup(Alignment.LEADING)
				.addComponent(lblAssignmentManange, GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
				.addComponent(pn, GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE));
		gl_assignmentGUI.setVerticalGroup(gl_assignmentGUI.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_assignmentGUI.createSequentialGroup()
						.addComponent(lblAssignmentManange, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pn, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)));
		assignmentGUI.setLayout(gl_assignmentGUI);

		asmTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				Editlnfo(evt);
			}
		});

		listAsm(1);
		listPerson();
		listCourse();
//		addAsmPage();
		buttonAlign();
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private void listAsm(int page) throws SQLException {
		List list = asmBLL.loadAsm(page);
		DefaultTableModel model = convertAsm(list);
		asmTable.setModel(model);

		asmCenterAlign(3);
	}

	private DefaultTableModel convertAsm(List list) {
		String[] Column = new String[] { "FIRST NAME", "LASTNAME", "COURSE" };
		Object[][] asmInfo = new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			Assignment Asm = (Assignment) list.get(i);
			asmInfo[i][0] = Asm.getFirstname();
			asmInfo[i][1] = Asm.getLastname();
			asmInfo[i][2] = Asm.getCourse();
		}
		DefaultTableModel model = new DefaultTableModel(asmInfo, Column);
		return model;
	}

	public void listPerson() throws SQLException {
		List list = asmBLL.readPerson();
		getInfoPerson(list);
	}

	public void getInfoPerson(List<Person> ps) {
		int size = ps.size();
		cbLecture.addItem("Full Name");
		for (Person i : ps) {
			String a = i.getFirstName() + " " + i.getLastName();
			cbLecture.addItem(a);
		}
		cbLecture.setMaximumRowCount(6);
	}

	public void listCourse() throws SQLException {
		List list = asmBLL.readCourse();
		getInfoCourse(list);
	}

	public void getInfoCourse(List<Course> cs) {
		int size = cs.size();
		cbName.addItem("Title");
		for (Course i : cs) {
			String a = i.getTitle();
			cbName.addItem(a);
		}
		cbName.setMaximumRowCount(6);
	}

	public void clearInfo() throws SQLException {
		cbName.setSelectedIndex(0);
		cbLecture.setSelectedIndex(0);
		
		listAsm(1);
	}

	public int getrow() {
		return row;
	}

	public void setrow(int row) {
		this.row = row;
	}

	public void ActionFillInfo() {
		int row = asmTable.getSelectedRow();
		setrow(row);
		if (row >= 0) {
			String lecture = asmTable.getValueAt(row, 0).toString() + " " + asmTable.getValueAt(row, 1).toString();
			for (int i = 0; i < 100; i++) {
				if (lecture.equals(cbLecture.getItemAt(i))) {
					cbLecture.setSelectedIndex(i);
					break;
				}
			}

			String course = asmTable.getValueAt(row, 2).toString();
			for (int i = 0; i < 100; i++) {
				if (course.equals(cbName.getItemAt(i))) {
					cbName.setSelectedIndex(i);
					break;
				}
			}
		}
	}

	public void Editlnfo(MouseEvent evt) {
		int column = asmTable.getSelectedColumn();
		int row = asmTable.getSelectedRow();
		ActionFillInfo();
	}

	public void resetAsmInfo(ActionListener listener) {
		reset.addActionListener(listener);
	}

	public Assignment getAsm() {

		if (!validName() || !validLecture()) {
			return null;
		}
		try {
			Assignment asm = new Assignment();
			String s[] = cbLecture.getSelectedItem().toString().split("\\s", 2);
			asm.setFirstname(s[0]);
			asm.setLastname(s[1]);
			asm.setCourse(cbName.getSelectedItem().toString());

			return asm;
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
		return null;
	}

	private boolean validLecture() {
		if (cbLecture.getSelectedItem() == "Full Name") {
			showMessage("Lecture can't be Empty! ");
			return false;
		}
		return true;
	}

	private boolean validName() {
		if (cbName.getSelectedItem() == "Title") {
			showMessage("Name can't be Empty! ");
			return false;
		}
		return true;
	}

	private boolean changeName() {
		String row1 = asmTable.getValueAt(row, 0).toString() + " " + asmTable.getValueAt(row, 1).toString();
		if (row1.equals(cbLecture.getSelectedItem())) {
			return true;
		}
		return false;
	}

	public void addAsm() throws SQLException {
		Assignment asm = getAsm();
		if(asm != null) {
			asm.setPersonID(asmBLL.readPersonID(asm.getFirstname(), asm.getLastname()));
			asm.setCourseID(asmBLL.readCourseID(asm.getCourse()));
			if (asmBLL.addAsm(asm) > 0) {
				listAsm(1);
				showMessage("add successful teaching information");
			} else {
				showMessage("add teaching information failed");
			}
		}
	}
	
	public void updateAsm() throws SQLException{
		Assignment asm = getAsm();
		if(asm != null) {
			int row = getrow();
			asm.setPersonID(asmBLL.readPersonID(asm.getFirstname(), asm.getLastname()));
			asm.setCourseID(asmBLL.readCourseID(asm.getCourse()));
			String firstname = asmTable.getValueAt(row, 0).toString();
			String lastname = asmTable.getValueAt(row, 1).toString();
			int personID = asmBLL.readPersonID(firstname, lastname);
			String course = asmTable.getValueAt(row, 2).toString();
			int courseID = asmBLL.readCourseID(course);
			if (asmBLL.updateAsm(asm, personID, courseID) > 0) {
				listAsm(1);
				showMessage("update successful teaching information");
			}
			else {
				showMessage("update teaching information failed");
			}
		}
		
	}

	public void deleteAsm() throws SQLException {
		Assignment asm = getAsm();
		if(asm != null) {
			asm.setPersonID(asmBLL.readPersonID(asm.getFirstname(), asm.getLastname()));
			asm.setCourseID(asmBLL.readCourseID(asm.getCourse()));
			if (asmBLL.deleteAsm(asm.getPersonID(), asm.getCourseID()) > 0) {
				listAsm(1);
				showMessage("delete successful teaching information");
			} else {
				showMessage("delete teaching information failed");
			}
		}
	}

	public void searchKeyAsm() throws SQLException{
		String text = tSearch.getText();
		search(text, 1);
		p_page.removeAll();
		p_page.setLayout(new BorderLayout());
		addSearchPage(text);
		p_page.validate();
		p_page.repaint();
		buttonAlign();
	}

	public void search(String Key, int page) throws SQLException {
		List list = asmBLL.searchAsm(Key, page);
		DefaultTableModel model = convertAsm(list);
		asmTable.setModel(model);

		asmCenterAlign(3);
	}

	public void addSearchPage(String key) throws SQLException {
		int num = asmBLL.numOfSearchPage(key);
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
							search(key, page);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}

//	public void addAsmPage() throws SQLException {
//		int num = asmBLL.numOfAsmPage();
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
//							listAsm(page);
//						} catch (Exception e1) {
//							e1.printStackTrace();
//						}
//					}
//				});
//			}
//		}
//	}

	public void asmCenterAlign(int column) {
		DefaultTableCellRenderer Align = new DefaultTableCellRenderer();
		Align.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < 3; i++) {
			asmTable.getColumnModel().getColumn(i).setCellRenderer(Align);
		}
	}

	public void buttonAlign() {
		GroupLayout gl_pn = new GroupLayout(pn);
		gl_pn.setHorizontalGroup(gl_pn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pn.createSequentialGroup().addGap(30)
						.addGroup(gl_pn.createParallelGroup(Alignment.LEADING).addGroup(gl_pn.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tSearch, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
								.addGroup(gl_pn.createSequentialGroup()
										.addGroup(gl_pn.createParallelGroup(Alignment.LEADING)
												.addComponent(lLecture, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
												.addComponent(lName, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pn.createParallelGroup(Alignment.LEADING)
												.addComponent(cbName, GroupLayout.PREFERRED_SIZE, 136,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(cbLecture, GroupLayout.PREFERRED_SIZE, 134,
														GroupLayout.PREFERRED_SIZE))
										.addGap(248)
										.addGroup(gl_pn.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pn.createSequentialGroup().addGap(1).addComponent(add,
														GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_pn.createSequentialGroup().addGap(1).addComponent(update,
														GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
												.addComponent(delete, GroupLayout.PREFERRED_SIZE, 140,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(reset, GroupLayout.PREFERRED_SIZE, 140,
														GroupLayout.PREFERRED_SIZE))
										.addGap(110))))
				.addGroup(
						gl_pn.createSequentialGroup().addContainerGap()
								.addGroup(
										gl_pn.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pn.createSequentialGroup()
														.addComponent(listAsm, GroupLayout.DEFAULT_SIZE, 693,
																Short.MAX_VALUE)
														.addContainerGap())
												.addComponent(p_page, GroupLayout.DEFAULT_SIZE, 703,
														Short.MAX_VALUE))));
		gl_pn.setVerticalGroup(gl_pn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pn.createSequentialGroup().addGap(39)
						.addGroup(gl_pn.createParallelGroup(Alignment.LEADING).addGroup(gl_pn.createSequentialGroup()
								.addGroup(gl_pn.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(cbName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_pn
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(cbLecture, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lLecture, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_pn.createSequentialGroup()
										.addComponent(add, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(update, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(delete, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6).addComponent(reset, GroupLayout.PREFERRED_SIZE, 26,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(23)
						.addGroup(gl_pn.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(tSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(listAsm, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(p_page, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(58)));
		p_page.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pn.setLayout(gl_pn);
	}

}
