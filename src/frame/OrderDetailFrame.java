package frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OrderDetailFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JTextField author;
	private JTextField category;
	private JTextField id;
	private JPanel bodyPanel;
	private JLabel img;

	public OrderDetailFrame(String s1, String s2, String s3, String s4, String s5) {

		JFrame frame = this;

		frame.setTitle("记录详情");
		frame.setSize(268, 342);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);

		bodyPanel = new PicturePanel("/res/image/blue.jpg");
		bodyPanel.setBounds(0, 0, 262, 307);
		getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);

		img = new JLabel(new ImageIcon(getClass().getResource(s5)));
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(79, 13, 95, 109);
		bodyPanel.add(img);

		JLabel bookNameLabel = new JLabel("订单详情");
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookNameLabel.setBounds(14, 135, 76, 32);
		bodyPanel.add(bookNameLabel);

		JLabel authorLabel = new JLabel("读者名称");
		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		authorLabel.setBounds(14, 180, 76, 32);
		bodyPanel.add(authorLabel);

		JLabel categroyLabel = new JLabel("记录编号");
		categroyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categroyLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		categroyLabel.setBounds(14, 225, 76, 32);
		bodyPanel.add(categroyLabel);

		JLabel bookIDentifcationLabel = new JLabel("归还状态");
		bookIDentifcationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDentifcationLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookIDentifcationLabel.setBounds(14, 270, 76, 32);
		bodyPanel.add(bookIDentifcationLabel);

		name = new JTextField(s1);
		name.setEditable(false);
		name.setOpaque(false);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("楷体", Font.BOLD, 20));
		name.setColumns(10);
		name.setBounds(104, 135, 128, 29);
		bodyPanel.add(name);

		author = new JTextField(s2);
		author.setEditable(false);
		author.setOpaque(false);
		author.setHorizontalAlignment(SwingConstants.CENTER);
		author.setForeground(Color.WHITE);
		author.setFont(new Font("楷体", Font.BOLD, 20));
		author.setColumns(10);
		author.setBounds(104, 181, 128, 29);
		bodyPanel.add(author);

		category = new JTextField(s3);
		category.setOpaque(false);
		category.setHorizontalAlignment(SwingConstants.CENTER);
		category.setForeground(Color.WHITE);
		category.setFont(new Font("楷体", Font.BOLD, 20));
		category.setColumns(10);
		category.setBounds(104, 226, 128, 29);
		bodyPanel.add(category);

		id = new JTextField(s4);
		id.setBackground(Color.WHITE);
		id.setOpaque(false);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setForeground(Color.WHITE);
		id.setFont(new Font("楷体", Font.BOLD, 20));
		id.setColumns(10);
		id.setBounds(104, 271, 128, 29);
		bodyPanel.add(id);

	}

	public void showMessage(String name) {
		JOptionPane.showMessageDialog(this, name);
	}

}