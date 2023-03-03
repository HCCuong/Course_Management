package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.AssignmentUI;
import UI.CourseUI;
import UI.StudentGradeUI;
import UI.PersonUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.Icon;

class RoundedPanel extends JPanel {
	private Color backgroundColor;
	private int cornerRadius = 15;

	public RoundedPanel(LayoutManager layout, int radius) {
		super(layout);
		cornerRadius = radius;
	}

	public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
		super(layout);
		cornerRadius = radius;
		backgroundColor = bgColor;
	}

	public RoundedPanel(int radius) {
		super();
		cornerRadius = radius;
	}

	public RoundedPanel(int radius, Color bgColor) {
		super();
		cornerRadius = radius;
		backgroundColor = bgColor;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(cornerRadius, cornerRadius);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draws the rounded panel with borders.
		if (backgroundColor != null) {
			graphics.setColor(backgroundColor);
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
		graphics.setColor(getForeground());
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
	}
}

public class App extends JFrame {
	private JPanel panel3;
	private ImageIcon icon;
	private ImageIcon icon_home;
	private ImageIcon icon_course;
	private ImageIcon icon_assignment;
	private ImageIcon icon_grade;
	private ImageIcon icon_person;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1007, 552);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JPanel panel1 = new JPanel();
				panel1.setBackground(new Color(124, 185, 232));


		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(new Rectangle(0, 0, 720, 420));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(mainPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 545,
										Short.MAX_VALUE)
								.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
						.addGap(0)));

		JLabel lblNewLabel = new JLabel("School Management Software");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));

		icon = new ImageIcon("images/school.png");
		Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image, icon.getDescription());

		JLabel abc = new JLabel(icon);

		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_mainPanel.createSequentialGroup().addGap(197)
						.addGroup(gl_mainPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(abc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(940)));
		gl_mainPanel.setVerticalGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup().addGap(66)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(abc, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(126, Short.MAX_VALUE)));
                mainPanel.setBackground(new Color(239,246,252));
		mainPanel.setLayout(gl_mainPanel);

		panel3 = new JPanel();
                panel3.setBackground(new Color(30,144,255));
                

		JPanel panel4 = new JPanel();
		panel4.setBorder(UIManager.getBorder("CheckBox.border"));
                //course button
		panel4.setBackground(new Color(240,248,255));
		panel4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					mainPanel.removeAll();
					mainPanel.setLayout(new BorderLayout());
					mainPanel.add(new CourseUI());
					mainPanel.validate();
					mainPanel.repaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		JPanel panel7 = new JPanel();
		panel7.setBorder(UIManager.getBorder("CheckBox.border"));
		panel7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					mainPanel.removeAll();
					mainPanel.setLayout(new BorderLayout());
					mainPanel.add(new AssignmentUI());
					mainPanel.validate();
					mainPanel.repaint();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
                
                //Assigment
		panel7.setBackground(new Color(240,248,255));
                panel7.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel lblNewLabel1 = new JLabel("Assignment");
		lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 16));

		icon_assignment = new ImageIcon("images/asm.png");
		Image image_asm = icon_assignment.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_assignment = new ImageIcon(image_asm, icon_assignment.getDescription());
		JLabel lb_asm = new JLabel(icon_assignment);

		GroupLayout gl_panel7 = new GroupLayout(panel7);
		gl_panel7.setHorizontalGroup(gl_panel7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel7.createSequentialGroup().addGap(8)
						.addComponent(lb_asm, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel1, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(19)));
		gl_panel7.setVerticalGroup(gl_panel7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel7.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel7.createParallelGroup(Alignment.LEADING)
								.addComponent(lb_asm, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel1, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
						.addContainerGap()));
		panel7.setLayout(gl_panel7);
                
		JPanel panel6 = new JPanel();
		panel6.setBorder(UIManager.getBorder("CheckBox.border"));
		panel6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					mainPanel.removeAll();
					mainPanel.setLayout(new BorderLayout());
					mainPanel.add(new StudentGradeUI());
					mainPanel.validate();
					mainPanel.repaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel6.setBackground(new Color(240,248,255));
                panel6.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
                
		JLabel lblNewLabel2 = new JLabel("Grade");
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		icon_grade = new ImageIcon("images/grade.png");
		Image image_grade = icon_grade.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_grade = new ImageIcon(image_grade, icon_grade.getDescription());
		JLabel lb_grade = new JLabel(icon_grade);

		GroupLayout gl_panel6 = new GroupLayout(panel6);
		gl_panel6.setHorizontalGroup(gl_panel6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel6.createSequentialGroup().addGap(8)
						.addComponent(lb_grade, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel2, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(19)));
		gl_panel6.setVerticalGroup(gl_panel6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel6.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel6.createParallelGroup(Alignment.LEADING)
								.addComponent(lb_grade, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel2, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
						.addContainerGap()));
		panel6.setLayout(gl_panel6);

		JPanel panel5 = new JPanel();
		panel5.setBorder(UIManager.getBorder("CheckBox.border"));
		panel5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					mainPanel.removeAll();
					mainPanel.setLayout(new BorderLayout());
					mainPanel.add(new PersonUI());
					mainPanel.validate();
					mainPanel.repaint();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel5.setBackground(new Color(240,248,255));
                panel5.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel lblNewLabel3 = new JLabel("Student & teacher");
		lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));

		icon_person = new ImageIcon("images/person.png");
		Image image_person = icon_person.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_person = new ImageIcon(image_person, icon_person.getDescription());
		JLabel lb_person = new JLabel(icon_person);

		GroupLayout gl_panel5 = new GroupLayout(panel5);
		gl_panel5.setHorizontalGroup(gl_panel5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel5.createSequentialGroup().addGap(8)
						.addComponent(lb_person, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel3, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE).addGap(19)));
		gl_panel5.setVerticalGroup(gl_panel5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel5.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel5.createParallelGroup(Alignment.LEADING)
								.addComponent(lb_person, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel3, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
						.addContainerGap()));
		panel5.setLayout(gl_panel5);

		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1
				.setHorizontalGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel1.createSequentialGroup().addGap(10)
								.addComponent(panel4, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE).addGap(10))
						.addGroup(
								gl_panel1.createSequentialGroup().addContainerGap()
										.addComponent(panel7, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(12, Short.MAX_VALUE))
						.addGroup(
								gl_panel1.createSequentialGroup().addContainerGap()
										.addComponent(panel6, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(12, Short.MAX_VALUE))
						.addGroup(
								gl_panel1.createSequentialGroup().addContainerGap()
										.addComponent(panel5, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(12, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel1.createSequentialGroup().addContainerGap()
								.addComponent(panel3, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)));
		gl_panel1.setVerticalGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
						.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE).addGap(35)
						.addComponent(panel4, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel7, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel6, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel5, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(79, Short.MAX_VALUE)));

		JLabel lblNewLabel5 = new JLabel("Course");
		lblNewLabel5.setFont(new Font("Tahoma", Font.PLAIN, 16));
                
		icon_course = new ImageIcon("images/Course.png");
		Image image_course = icon_course.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon_course = new ImageIcon(image_course, icon_course.getDescription());
		JLabel lb_course = new JLabel(icon_course);

		GroupLayout gl_panel4 = new GroupLayout(panel4);
		gl_panel4.setHorizontalGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup().addGap(8)
						.addComponent(lb_course, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel5, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE).addGap(19)));
		gl_panel4.setVerticalGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel4.createParallelGroup(Alignment.LEADING)
								.addComponent(lb_course, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel5, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
						.addContainerGap()));
		panel4.setLayout(gl_panel4);
                panel4.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		JLabel lblNewLabel4 = new JLabel("MENU");
    		lblNewLabel4.setFont(new Font("Tahoma", Font.PLAIN, 25));
    
    		JLabel lb_home = new JLabel(icon_home);
    
    		GroupLayout gl_panel3 = new GroupLayout(panel3);
    		gl_panel3.setHorizontalGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel3.createSequentialGroup().addContainerGap()
    						.addComponent(lb_home, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addGap(13)
    						.addComponent(lblNewLabel4, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE).addContainerGap()));
    		gl_panel3.setVerticalGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
    				.addComponent(lblNewLabel4, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
    				.addGroup(gl_panel3.createSequentialGroup().addGap(10)
    						.addComponent(lb_home, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE).addContainerGap()));
    		panel3.setLayout(gl_panel3);
		panel1.setLayout(gl_panel1);
		contentPane.setLayout(gl_contentPane);
	}
}
