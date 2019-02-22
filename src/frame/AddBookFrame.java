package frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entity.Book;
import service.BookServiceImpl;
import service.IBookService;

public class AddBookFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField bookPictrueTextField;
	private JTextField bookDetailTextField;
	private JTextField bookNameTextField;
	private JTextField bookaAuthorTextField;
	private JTextField bookCategroyFirstTextField;
	private JTextField bookIDentifcationTextField;
	private JTextField categroySecondTextField;
	private JTextField bookINventoryTextField;

	public AddBookFrame() {

		IBookService bookService = new BookServiceImpl();

		JFrame frame = this;
		frame.setTitle("添加书籍");
		frame.setSize(382, 444);
		frame.setLocation(300, 200);
		frame.setResizable(false);
		getContentPane().setLayout(null);

		JPanel bodyPanel = new PicturePanel("/res/image/blue.jpg");
		bodyPanel.setBounds(0, 0, 378, 410);
		getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);

		JLabel bookPictrueLebel = new JLabel("书籍展示");
		bookPictrueLebel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictrueLebel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookPictrueLebel.setBounds(43, 13, 76, 32);
		bodyPanel.add(bookPictrueLebel);

		JLabel bookNameLabel = new JLabel("书籍名称");
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookNameLabel.setBounds(43, 107, 76, 32);
		bodyPanel.add(bookNameLabel);

		JLabel authorLabel = new JLabel("书籍作者");
		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		authorLabel.setBounds(43, 152, 76, 32);
		bodyPanel.add(authorLabel);

		JLabel categroyLabel = new JLabel("书籍分类");
		categroyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categroyLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		categroyLabel.setBounds(43, 197, 76, 32);
		bodyPanel.add(categroyLabel);

		JLabel bookIDentifcationLabel = new JLabel("书籍编号");
		bookIDentifcationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDentifcationLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookIDentifcationLabel.setBounds(43, 287, 76, 32);
		bodyPanel.add(bookIDentifcationLabel);

		JLabel bookDetailLabel = new JLabel("书籍简介");
		bookDetailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookDetailLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookDetailLabel.setBounds(43, 58, 76, 32);
		bodyPanel.add(bookDetailLabel);
		
				JLabel bookINventoryLabel = new JLabel("书籍库存");
				bookINventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
				bookINventoryLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
				bookINventoryLabel.setBounds(43, 242, 76, 32);
				bodyPanel.add(bookINventoryLabel);

		bookPictrueTextField = new JTextField();
		bookPictrueTextField.setBounds(141, 13, 184, 32);
		bodyPanel.add(bookPictrueTextField);
		bookPictrueTextField.setColumns(10);

		bookDetailTextField = new JTextField();
		bookDetailTextField.setColumns(10);
		bookDetailTextField.setBounds(141, 58, 184, 32);
		bodyPanel.add(bookDetailTextField);

		bookNameTextField = new JTextField();
		bookNameTextField.setColumns(10);
		bookNameTextField.setBounds(141, 107, 184, 32);
		bodyPanel.add(bookNameTextField);

		bookaAuthorTextField = new JTextField();
		bookaAuthorTextField.setColumns(10);
		bookaAuthorTextField.setBounds(141, 152, 184, 32);
		bodyPanel.add(bookaAuthorTextField);

		bookCategroyFirstTextField = new JTextField();
		bookCategroyFirstTextField.setColumns(10);
		bookCategroyFirstTextField.setBounds(141, 197, 86, 32);
		bodyPanel.add(bookCategroyFirstTextField);

		bookIDentifcationTextField = new JTextField();
		bookIDentifcationTextField.setColumns(10);
		bookIDentifcationTextField.setBounds(141, 287, 184, 32);
		bodyPanel.add(bookIDentifcationTextField);

		categroySecondTextField = new JTextField();
		categroySecondTextField.setColumns(10);
		categroySecondTextField.setBounds(239, 197, 86, 32);
		bodyPanel.add(categroySecondTextField);
		
				JButton submitButton = new JButton("添加");
				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String res = "";
						try {
							Integer in = Integer.valueOf(bookINventoryTextField.getText()).intValue();
							res = bookService.managerAdd(new Book(bookIDentifcationTextField.getText(),
									bookaAuthorTextField.getText(), bookNameTextField.getText(), in,
									"/res/book/" + bookPictrueTextField.getText() + ".jpg", bookCategroyFirstTextField.getText(),
									categroySecondTextField.getText(), bookDetailTextField.getText()));

						} catch (NumberFormatException e) {
							showMessage("数量输入不正确！请重新输入");
							dispose();
							new AddBookFrame().setVisible(true);
						}
						if (res.equals("Success")) {
							if (bookService.shutDownBookService()) {
								showMessage("添加书籍成功！");
								dispose();
								new AddBookFrame().setVisible(true);
							}
						} else {
							showMessage("添加书籍失败！");
						}

					}
				});
				
						bookINventoryTextField = new JTextField();
						bookINventoryTextField.setColumns(10);
						bookINventoryTextField.setBounds(141, 242, 184, 32);
						bodyPanel.add(bookINventoryTextField);
				submitButton.setFont(new Font("楷体", Font.BOLD, 18));
				submitButton.setBounds(221, 342, 104, 27);
				bodyPanel.add(submitButton);

		frame.setVisible(true);

	}

	public void showMessage(String name) {
		JOptionPane.showMessageDialog(this, name);
	}

}