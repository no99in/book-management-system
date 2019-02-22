package frame;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import service.IBookService;
import service.IRecordService;
import service.IUserService;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class UpdateInformation extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputname;
	private JTextField inputsex;
	private JTextField inputage;
	private JTextField inputadress;
	private JTextField inputdepart;

	public UpdateInformation(IUserService userService, IBookService bookService, IRecordService recordService) {
		getContentPane().setBackground(Color.ORANGE);
		JFrame frame = this;
		frame.setTitle("修改信息");
		frame.setSize(362, 373);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);

		PicturePanel picturePanel = new PicturePanel("/res/image/RegisterFrame.jpg");
		picturePanel.setLayout(null);
		picturePanel.setBounds(0, 0, 356, 338);
		getContentPane().add(picturePanel);

		inputname = new JTextField();
		inputname.setText(userService.getUser().getUserName());
		inputname.setOpaque(false);
		inputname.setHorizontalAlignment(SwingConstants.CENTER);
		inputname.setForeground(Color.WHITE);
		inputname.setFont(new Font("楷体", Font.BOLD, 20));
		inputname.setColumns(10);
		inputname.setBounds(126, 12, 128, 29);
		picturePanel.add(inputname);

		JLabel name = new JLabel("读者名称:");
		name.setForeground(Color.WHITE);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("微软雅黑", Font.BOLD, 18));
		name.setBounds(26, 13, 86, 29);
		picturePanel.add(name);

		JLabel sex = new JLabel("读者性别:");
		sex.setHorizontalAlignment(SwingConstants.CENTER);
		sex.setForeground(Color.WHITE);
		sex.setFont(new Font("微软雅黑", Font.BOLD, 18));
		sex.setBounds(26, 55, 86, 29);
		picturePanel.add(sex);

		inputsex = new JTextField();
		inputsex.setOpaque(false);
		inputsex.setText(userService.getUser().isUserSex() ? "男" : "女");
		inputsex.setHorizontalAlignment(SwingConstants.CENTER);
		inputsex.setForeground(Color.WHITE);
		inputsex.setFont(new Font("楷体", Font.BOLD, 20));
		inputsex.setColumns(10);
		inputsex.setBounds(126, 54, 128, 29);
		picturePanel.add(inputsex);

		JLabel age = new JLabel("读者年龄:");
		age.setHorizontalAlignment(SwingConstants.CENTER);
		age.setForeground(Color.WHITE);
		age.setFont(new Font("微软雅黑", Font.BOLD, 18));
		age.setBounds(26, 98, 86, 29);
		picturePanel.add(age);

		inputage = new JTextField();
		inputage.setOpaque(false);
		String userAge = new String(userService.getUser().getUserAge() + "");
		inputage.setText(userAge);
		inputage.setHorizontalAlignment(SwingConstants.CENTER);
		inputage.setForeground(Color.WHITE);
		inputage.setFont(new Font("楷体", Font.BOLD, 20));
		inputage.setColumns(10);
		inputage.setBounds(126, 97, 128, 29);
		picturePanel.add(inputage);

		JLabel adress = new JLabel("读者地址:");
		adress.setHorizontalAlignment(SwingConstants.CENTER);
		adress.setForeground(Color.WHITE);
		adress.setFont(new Font("微软雅黑", Font.BOLD, 18));
		adress.setBounds(26, 141, 86, 29);
		picturePanel.add(adress);

		inputadress = new JTextField();
		inputadress.setOpaque(false);
		inputadress.setText(userService.getUser().getUserADress());
		inputadress.setHorizontalAlignment(SwingConstants.CENTER);
		inputadress.setForeground(Color.WHITE);
		inputadress.setFont(new Font("楷体", Font.BOLD, 20));
		inputadress.setColumns(10);
		inputadress.setBounds(126, 140, 128, 29);
		picturePanel.add(inputadress);

		inputdepart = new JTextField();
		inputdepart.setOpaque(false);
		inputdepart.setText(userService.getUser().getUserDepartment());
		inputdepart.setHorizontalAlignment(SwingConstants.CENTER);
		inputdepart.setForeground(Color.WHITE);
		inputdepart.setFont(new Font("楷体", Font.BOLD, 20));
		inputdepart.setColumns(10);
		inputdepart.setBounds(126, 183, 128, 29);
		picturePanel.add(inputdepart);

		JLabel depart = new JLabel("读者部门:");
		depart.setHorizontalAlignment(SwingConstants.CENTER);
		depart.setForeground(Color.WHITE);
		depart.setFont(new Font("微软雅黑", Font.BOLD, 18));
		depart.setBounds(26, 184, 86, 29);
		picturePanel.add(depart);

		JButton btnNewButton = new JButton("更新信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				userService.getUser().setUserADress(inputadress.getText());
				Integer userAge = new Integer(Integer.parseInt(inputage.getText()));
				userService.getUser().setUserAge(userAge);
				userService.getUser().setUserDepartment(inputdepart.getText());
				userService.getUser().setUserName(inputname.getText());
				boolean userSex = inputsex.getText().trim().equals("男") ? true : false;
				userService.getUser().setUserSex(userSex);
				userService.userUpdateInformation();
				new UserFrame(userService, bookService, recordService);
			}
		});
		btnNewButton.setBounds(26, 239, 113, 27);
		picturePanel.add(btnNewButton);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new UserFrame(userService, bookService, recordService);
			}
		});

	}
}