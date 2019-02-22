package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entity.User;
import service.BookServiceImpl;
import service.IBookService;
import service.IRecordService;
import service.IUserService;
import service.RecordServiceImpl;
import service.UserServiceImpl;

public class LoginFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userIDentificationTextField;
	private JPasswordField userPasswordField;
	IUserService userService = new UserServiceImpl();

	public LoginFrame() {
		Icon loginIcon = new ImageIcon(getClass().getResource("/res/image/login.png"));
		Icon registerIcon = new ImageIcon(getClass().getResource("/res/image/register.png"));
		JFrame frame = this;
		frame.setTitle("登录 - 图书管理系统");
		frame.setSize(582, 376);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);

		JPanel panel_1 = new PicturePanel("/res/image/LoginFrame.jpg");
		panel_1.setBackground(new Color(238, 232, 170));
		panel_1.setBounds(0, 0, 576, 341);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel bookManageTitleLabel = new JLabel("图 书 管 理 系 统 V1.0");
		bookManageTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookManageTitleLabel.setBounds(225, 13, 325, 72);
		bookManageTitleLabel.setFont(new Font("微软雅黑", Font.BOLD | Font.ITALIC, 30));
		panel_1.add(bookManageTitleLabel);

		userIDentificationTextField = new JTextField();
		userIDentificationTextField.setBounds(359, 131, 191, 24);
		panel_1.add(userIDentificationTextField);
		userIDentificationTextField.setColumns(10);

		userPasswordField = new JPasswordField();
		userPasswordField.setBounds(359, 181, 191, 24);
		panel_1.add(userPasswordField);

		JLabel userIDentificationTitleLabel = new JLabel("读者证号：");
		userIDentificationTitleLabel.setFont(new Font("宋体", Font.BOLD, 15));
		userIDentificationTitleLabel.setBounds(271, 131, 86, 24);
		panel_1.add(userIDentificationTitleLabel);

		JLabel userPasswordTitleLabel = new JLabel("读者密码：");
		userPasswordTitleLabel.setFont(new Font("宋体", Font.BOLD, 15));
		userPasswordTitleLabel.setBounds(271, 181, 86, 24);
		panel_1.add(userPasswordTitleLabel);

		JButton loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(userPasswordField.getPassword());
				userService.setUser(new User(userIDentificationTextField.getText().trim(), password.trim()));
				String message = userService.touristLogin();
				if (message.equals("Success")) {
					showMessage("登陆成功\n" + "欢迎你，" + userService.getUser().getUserName());
					dispose();
					IBookService bookService = new BookServiceImpl();
					IRecordService recordService = new RecordServiceImpl();
					recordService.setRecords();
					new UserFrame(userService, bookService, recordService);
				} else if (password.equals("123456") && userIDentificationTextField.getText().equals("admin")) {
					dispose();
					showMessage("欢迎你，管理员");
					new AdminFrame();
				} else
					showMessage(message);

			}
		});
		loginButton.setFont(new Font("楷体", Font.BOLD, 17));
		loginButton.setBounds(437, 258, 113, 39);
		loginButton.setIcon(loginIcon);
		panel_1.add(loginButton);

		JButton registerButton = new JButton("注册");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new RegisterFrame(userService);
				frame.setVisible(true);
			}
		});
		registerButton.setFont(new Font("楷体", Font.BOLD, 17));
		registerButton.setBounds(271, 258, 113, 39);
		registerButton.setIcon(registerIcon);
		panel_1.add(registerButton);

		frame.setVisible(true);
	}

	public void showMessage(String name) {
		JOptionPane.showMessageDialog(this, name);
	}

}
