package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminFrame() {
		JFrame frame = this;
		frame.setTitle("管理界面");
		frame.setBounds(0, 0, 588, 419);
		frame.setLocation(300, 200);
		frame.setResizable(false);
		getContentPane().setLayout(null);

		JPanel BodyPanel = new PicturePanel("/res/image/AdminFrame.jpg");
		BodyPanel.setBounds(0, 0, 582, 384);
		getContentPane().add(BodyPanel);
		BodyPanel.setLayout(null);

		JButton bookManagementButton = new JButton(new ImageIcon(getClass().getResource("/res/image/MBook.jpg")));
		bookManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new BookManageFrame();
				dispose();
				frame.setVisible(true);
			}
		});
		bookManagementButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		bookManagementButton.setHorizontalTextPosition(SwingConstants.CENTER);
		bookManagementButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		bookManagementButton.setBackground(Color.WHITE);
		bookManagementButton.setBounds(55, 88, 120, 120);
		BodyPanel.add(bookManagementButton);

		JButton userManagementButton = new JButton(new ImageIcon(getClass().getResource("/res/image/MUpdate.jpg")));
		userManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserManageFrame().setVisible(true);
			}
		});
		userManagementButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		userManagementButton.setHorizontalTextPosition(SwingConstants.CENTER);
		userManagementButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		userManagementButton.setBackground(Color.WHITE);
		userManagementButton.setBounds(223, 88, 120, 120);
		BodyPanel.add(userManagementButton);

		JButton recordManagementButton = new JButton(new ImageIcon(getClass().getResource("/res/image/Mrecord.jpg")));
		recordManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AllOrderFrame().setVisible(true);
			}
		});
		recordManagementButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		recordManagementButton.setHorizontalTextPosition(SwingConstants.CENTER);
		recordManagementButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		recordManagementButton.setBackground(Color.WHITE);
		recordManagementButton.setBounds(394, 88, 120, 120);
		BodyPanel.add(recordManagementButton);

		this.setVisible(true);
	}
}