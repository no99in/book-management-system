package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entity.User;
import service.IUserService;
import service.UserServiceImpl;

public class UserManageFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField id;
	private JPanel bodyPanel;
	private IUserService userService = null;
	private JTextField nameTextField;
	private JTextField sexTextField;
	private JTextField ageTextField;
	private JTextField adressTextField;
	private JTextField departTextField;
	private JLabel idLabel;
	private JTextField idTextField;

	public UserManageFrame() {
		this.userService = new UserServiceImpl();
		JFrame frame = this;

		frame.setTitle("用户管理");
		frame.setSize(698, 546);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new AdminFrame().setVisible(true);
			}
		});
		getContentPane().setLayout(null);

		bodyPanel = new PicturePanel("/res/image/blue.jpg");
		bodyPanel.setBounds(0, 0, 698, 546);
		getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);

		table = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getValueAt(table.getSelectedRow(), 0) != null) {
					String uid = (String) table.getValueAt(table.getSelectedRow(), 1);
					userService.setUser(uid);
					idTextField.setText(uid);
					nameTextField.setText(userService.getUser().getUserName());
					sexTextField.setText(userService.getUser().isUserSex() ? "男" : "女");
					ageTextField.setText(userService.getUser().getUserAge() + "");
					adressTextField.setText(userService.getUser().getUserADress());
					departTextField.setText(userService.getUser().getUserDepartment());
				}
			}
		});

		JScrollPane BookScrollPane = new JScrollPane();
		BookScrollPane.setBounds(24, 13, 390, 496);
		bodyPanel.add(BookScrollPane);
		BookScrollPane.setViewportView(table);
		initTableModel(table);

		id = new JTextField();
		id.setEnabled(false);
		id.setOpaque(false);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setForeground(Color.WHITE);
		id.setFont(new Font("楷体", Font.BOLD, 20));
		id.setColumns(10);
		id.setBounds(716, 438, 56, 7);
		bodyPanel.add(id);

		JLabel nameLabel = new JLabel("读者名称:");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		nameLabel.setBounds(429, 239, 86, 29);
		bodyPanel.add(nameLabel);

		JLabel sexLabel = new JLabel("读者性别:");
		sexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sexLabel.setForeground(Color.WHITE);
		sexLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		sexLabel.setBounds(429, 281, 86, 29);
		bodyPanel.add(sexLabel);
				
						JLabel ageLabel = new JLabel("读者年龄:");
						ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
						ageLabel.setForeground(Color.WHITE);
						ageLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
						ageLabel.setBounds(429, 324, 86, 29);
						bodyPanel.add(ageLabel);
				
						JLabel adressLabel = new JLabel("读者地址:");
						adressLabel.setHorizontalAlignment(SwingConstants.CENTER);
						adressLabel.setForeground(Color.WHITE);
						adressLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
						adressLabel.setBounds(429, 367, 86, 29);
						bodyPanel.add(adressLabel);
				
						JLabel departLabel = new JLabel("读者部门:");
						departLabel.setHorizontalAlignment(SwingConstants.CENTER);
						departLabel.setForeground(Color.WHITE);
						departLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
						departLabel.setBounds(429, 410, 86, 29);
						bodyPanel.add(departLabel);
				
						idLabel = new JLabel("读者ID:");
						idLabel.setHorizontalAlignment(SwingConstants.CENTER);
						idLabel.setForeground(Color.WHITE);
						idLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
						idLabel.setBounds(428, 197, 86, 29);
						bodyPanel.add(idLabel);
		
				nameTextField = new JTextField();
				nameTextField.setText((String) null);
				nameTextField.setOpaque(false);
				nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
				nameTextField.setForeground(Color.WHITE);
				nameTextField.setFont(new Font("楷体", Font.BOLD, 20));
				nameTextField.setColumns(10);
				nameTextField.setBounds(529, 238, 128, 29);
				bodyPanel.add(nameTextField);

		sexTextField = new JTextField();
		sexTextField.setText("女");
		sexTextField.setOpaque(false);
		sexTextField.setHorizontalAlignment(SwingConstants.CENTER);
		sexTextField.setForeground(Color.WHITE);
		sexTextField.setFont(new Font("楷体", Font.BOLD, 20));
		sexTextField.setColumns(10);
		sexTextField.setBounds(529, 280, 128, 29);
		bodyPanel.add(sexTextField);

		ageTextField = new JTextField();
		ageTextField.setText("0");
		ageTextField.setOpaque(false);
		ageTextField.setHorizontalAlignment(SwingConstants.CENTER);
		ageTextField.setForeground(Color.WHITE);
		ageTextField.setFont(new Font("楷体", Font.BOLD, 20));
		ageTextField.setColumns(10);
		ageTextField.setBounds(529, 323, 128, 29);
		bodyPanel.add(ageTextField);

		adressTextField = new JTextField();
		adressTextField.setText((String) null);
		adressTextField.setOpaque(false);
		adressTextField.setHorizontalAlignment(SwingConstants.CENTER);
		adressTextField.setForeground(Color.WHITE);
		adressTextField.setFont(new Font("楷体", Font.BOLD, 20));
		adressTextField.setColumns(10);
		adressTextField.setBounds(529, 366, 128, 29);
		bodyPanel.add(adressTextField);

		departTextField = new JTextField();
		departTextField.setText((String) null);
		departTextField.setOpaque(false);
		departTextField.setHorizontalAlignment(SwingConstants.CENTER);
		departTextField.setForeground(Color.WHITE);
		departTextField.setFont(new Font("楷体", Font.BOLD, 20));
		departTextField.setColumns(10);
		departTextField.setBounds(529, 409, 128, 29);
		bodyPanel.add(departTextField);

		idTextField = new JTextField();
		idTextField.setText((String) null);
		idTextField.setOpaque(false);
		idTextField.setHorizontalAlignment(SwingConstants.CENTER);
		idTextField.setForeground(Color.WHITE);
		idTextField.setFont(new Font("楷体", Font.BOLD, 20));
		idTextField.setColumns(10);
		idTextField.setBounds(529, 196, 128, 29);
		bodyPanel.add(idTextField);
		
				JButton button = new JButton("更新信息");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						userService.setUser(idTextField.getText());
						userService.getUser().setUserADress(adressTextField.getText());
						Integer userAge = new Integer(Integer.parseInt(ageTextField.getText()));
						userService.getUser().setUserAge(userAge);
						userService.getUser().setUserDepartment(departTextField.getText());
						userService.getUser().setUserName(nameTextField.getText());
						boolean userSex = sexTextField.getText().trim().equals("男") ? true : false;
						userService.getUser().setUserSex(userSex);
						if (userService.userUpdateInformation())
							if (userService.shutDownUserService()) {
								dispose();
								showMessage("修改用户信息成功！");
								new UserManageFrame().setVisible(true);
								;
							}
					}
				});
				button.setBounds(429, 465, 113, 27);
				bodyPanel.add(button);

	}

	public void showMessage(String name) {
		JOptionPane.showMessageDialog(this, name);
	}

	public void initTableModel(JTable table) {
		Object[][] rowDate = null;
		Object[] columnNames = { "姓名", "编号", "部门", "剩余次数" };
		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		Set<User> userSet = userService.getAllUsers();
		Object[] BookRow;
		for (User user : userSet) {
			BookRow = new Object[] { user.getUserName(), user.getUserIDentification(), user.getUserDepartment(),
					user.getUserLimit() };
			model.insertRow(0, BookRow);
		}
		table.setModel(model);
	}
}