package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entity.User;
import service.IUserService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField userNameTextField;
	public JTextField userIDentificationTextField;
	public JTextField userPasswordTextField;
	public JLabel userNameLabel;
	public JLabel userIDentificationLabel;
	public JLabel userPasswordLabel;
	public JButton submit;

	public RegisterFrame(IUserService userService) {
		JFrame frame = this;
		frame.setTitle("注册 - 图书管理系统");
		frame.setSize(586, 368);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);

		JPanel panel = new PicturePanel("/res/image/RegisterFrame.jpg");
		panel.setBounds(0, 0, 580, 333);
		getContentPane().add(panel);
		panel.setLayout(null);

		userNameLabel = new JLabel("姓名：");
		userNameLabel.setFont(new Font("楷体", Font.BOLD, 20));
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setBounds(26, 208, 63, 31);
		panel.add(userNameLabel);

		userNameTextField = new JTextField();
		userNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userNameTextField.setFont(new Font("楷体", Font.BOLD, 20));
		userNameTextField.setForeground(Color.WHITE);
		userNameTextField.setBounds(91, 208, 86, 29);
		userNameTextField.setOpaque(false);
		panel.add(userNameTextField);
		userNameTextField.setColumns(10);

		userIDentificationLabel = new JLabel("读者证号：");
		userIDentificationLabel.setForeground(Color.WHITE);
		userIDentificationLabel.setFont(new Font("楷体", Font.BOLD, 20));
		userIDentificationLabel.setBounds(26, 35, 105, 31);
		panel.add(userIDentificationLabel);

		userIDentificationTextField = new JTextField();
		userIDentificationTextField.setOpaque(false);
		userIDentificationTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userIDentificationTextField.setForeground(Color.WHITE);
		userIDentificationTextField.setFont(new Font("楷体", Font.BOLD, 20));
		userIDentificationTextField.setColumns(10);
		userIDentificationTextField.setBounds(134, 35, 128, 29);
		panel.add(userIDentificationTextField);

		userPasswordLabel = new JLabel("读者密码：");
		userPasswordLabel.setForeground(Color.WHITE);
		userPasswordLabel.setFont(new Font("楷体", Font.BOLD, 20));
		userPasswordLabel.setBounds(26, 79, 105, 31);
		panel.add(userPasswordLabel);

		userPasswordTextField = new JTextField();
		userPasswordTextField.setOpaque(false);
		userPasswordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userPasswordTextField.setForeground(Color.WHITE);
		userPasswordTextField.setFont(new Font("楷体", Font.BOLD, 20));
		userPasswordTextField.setColumns(10);
		userPasswordTextField.setBounds(134, 79, 128, 29);
		panel.add(userPasswordTextField);

		submit = new JButton("提交注册");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = userService.touristRegister(new User(userIDentificationTextField.getText(),
						userPasswordTextField.getText(), userNameTextField.getText()));
				if (res.equals("Success")) {
					showMessage("恭喜你！\n注册成功:" + userService.getUser().getUserName());
					showMessage("注册后，请先完善您的信息！");
				} else
					showMessage("Fail");
				dispose();
			}
		});

		submit.setFont(new Font("等线 Light", Font.BOLD, 16));
		submit.setBounds(26, 252, 113, 27);
		panel.add(submit);

	}

	public void showMessage(String name) {
		JOptionPane.showMessageDialog(this, name);
	}

}
