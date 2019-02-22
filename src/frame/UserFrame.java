package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import entity.Record;
import service.IBookService;
import service.IRecordService;
import service.IUserService;

public class UserFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserFrame(IUserService userService, IBookService bookService, IRecordService recordService) {
		JFrame frame = this;
		frame.setTitle("你好," + userService.getUser().getUserName());
		frame.setSize(758, 580);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);

		JPanel headerPanel = new PicturePanel("/res/image/UserFrameHeader.jpg");
		headerPanel.setBounds(0, 0, 752, 114);
		getContentPane().add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("图 书 管 理 系 统 ");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 50));
		titleLabel.setBounds(14, 13, 528, 88);
		headerPanel.add(titleLabel);

		JPanel LeftPanel = new PicturePanel("/res/image/UserFrameBody.jpg");
		LeftPanel.setBounds(0, 114, 558, 431);
		getContentPane().add(LeftPanel);
		LeftPanel.setLayout(null);

		JButton borrowBookButton = new JButton(new ImageIcon(getClass().getResource("/res/image/borrow.jpg")));
		borrowBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame frame = new AllBookFrame(userService, bookService, recordService);
				frame.setVisible(true);
			}
		});

		borrowBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		borrowBookButton.setBackground(Color.WHITE);
		borrowBookButton.setHorizontalTextPosition(SwingConstants.CENTER);
		borrowBookButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		borrowBookButton.setBounds(53, 52, 120, 120);
		LeftPanel.add(borrowBookButton);

		JButton giveBackBookButton = new JButton(new ImageIcon(getClass().getResource("/res/image/giveBack.jpg")));
		giveBackBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				JFrame frame = new GvieBackFrame(userService, bookService, recordService);
				frame.setVisible(true);
			}
		});
		giveBackBookButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		giveBackBookButton.setHorizontalTextPosition(SwingConstants.CENTER);
		giveBackBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		giveBackBookButton.setBackground(Color.WHITE);
		giveBackBookButton.setBounds(221, 52, 120, 120);
		LeftPanel.add(giveBackBookButton);

		JButton renewBookButton = new JButton(new ImageIcon(getClass().getResource("/res/image/cborrow.jpg")));
		renewBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				JFrame frame = new RenewFrame(userService, bookService, recordService);
				frame.setVisible(true);
			}
		});
		renewBookButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		renewBookButton.setHorizontalTextPosition(SwingConstants.CENTER);
		renewBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		renewBookButton.setBackground(Color.WHITE);
		renewBookButton.setBounds(381, 52, 120, 120);
		LeftPanel.add(renewBookButton);

		JButton updateInformationButton = new JButton(new ImageIcon(getClass().getResource("/res/image/update.jpg")));
		updateInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new UpdateInformation(userService, bookService, recordService);
				dispose();
				frame.setVisible(true);

			}
		});
		updateInformationButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		updateInformationButton.setHorizontalTextPosition(SwingConstants.CENTER);
		updateInformationButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		updateInformationButton.setBackground(Color.WHITE);
		updateInformationButton.setBounds(53, 231, 120, 120);
		LeftPanel.add(updateInformationButton);

		JButton showRecordButton = new JButton(new ImageIcon(getClass().getResource("/res/image/record.jpg")));
		showRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new OrderFrame(userService, bookService, recordService);
				dispose();
				frame.setVisible(true);
			}
		});
		showRecordButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		showRecordButton.setHorizontalTextPosition(SwingConstants.CENTER);
		showRecordButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		showRecordButton.setBackground(Color.WHITE);
		showRecordButton.setBounds(221, 231, 120, 120);
		LeftPanel.add(showRecordButton);

		JButton showCardButton = new JButton(new ImageIcon(getClass().getResource("/res/image/card.jpg")));
		showCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame frame = new RegisterFrame(userService);
				frame.setTitle(userService.getUser().getUserName() + "的电子读者证");
				frame.submit.setVisible(false);
				frame.userPasswordLabel.setVisible(false);
				frame.userPasswordTextField.setVisible(false);
				frame.userIDentificationTextField.setEditable(false);
				frame.userNameTextField.setEditable(false);
				frame.userIDentificationTextField.setText(userService.getUser().getUserIDentification());
				frame.userNameTextField.setText(userService.getUser().getUserName());
				frame.setVisible(true);

			}
		});
		showCardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		showCardButton.setHorizontalTextPosition(SwingConstants.CENTER);
		showCardButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		showCardButton.setBackground(Color.WHITE);
		showCardButton.setBounds(381, 231, 120, 120);
		LeftPanel.add(showCardButton);

		JPanel rightPanel = new PicturePanel("/res/image/UserFrameUser.jpg");
		rightPanel.setBounds(558, 114, 194, 431);
		getContentPane().add(rightPanel);
		rightPanel.setLayout(null);

		JLabel userNameLabel = new JLabel("姓名");
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		userNameLabel.setBounds(14, 36, 48, 18);
		rightPanel.add(userNameLabel);

		JLabel userNameShowLabel = new JLabel(userService.getUser().getUserName());
		userNameShowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameShowLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		userNameShowLabel.setBounds(62, 36, 82, 18);
		rightPanel.add(userNameShowLabel);

		JLabel userSexLabel = new JLabel("性别");
		userSexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userSexLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		userSexLabel.setBounds(14, 70, 48, 18);
		rightPanel.add(userSexLabel);

		JLabel userSexShowLabel = new JLabel(userService.getUser().isUserSex() ? "男" : "女");
		userSexShowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userSexShowLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		userSexShowLabel.setBounds(62, 70, 82, 18);
		rightPanel.add(userSexShowLabel);

		JLabel userIDLabel = new JLabel("账号");
		userIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userIDLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		userIDLabel.setBounds(14, 101, 48, 18);
		rightPanel.add(userIDLabel);

		JLabel userIDShowLabel = new JLabel(userService.getUser().getUserIDentification());
		userIDShowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userIDShowLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		userIDShowLabel.setBounds(62, 101, 82, 18);
		rightPanel.add(userIDShowLabel);

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		int timedBookNumber = 0;
		recordService.setUser(userService.getUser());
		for (Record record : recordService.userSeeAllRecords()) {
			if (record.getEndTime().compareTo(year + "-" + month + "-" + day) < 0) {
				timedBookNumber++;
			}
		}
		if (timedBookNumber != 0)
			showMessage("现在您有" + timedBookNumber + "本书籍到期没有归还,请在续借界面续借或者在归还界面归还！");

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (userService.shutDownUserService())
					showMessage("业务层返回数据成功");
				System.exit(0);
			}
		});

		frame.setVisible(true);

	}

	public void showMessage(String name) {
		JOptionPane.showMessageDialog(this, name);
	}

}