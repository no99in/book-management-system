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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entity.Record;
import service.IBookService;
import service.IRecordService;
import service.IUserService;

public class OrderFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel bodyPanel;
	private IUserService userService = null;
	private IBookService bookService = null;
	private IRecordService recordService = null;
	private JTextField textField;

	public OrderFrame(IUserService userService, IBookService bookService, IRecordService recordService) {
		this.userService = userService;
		this.bookService = bookService;
		this.recordService = recordService;

		JFrame frame = this;
		frame.setTitle("我的记录");
		frame.setSize(662, 318);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);

		bodyPanel = new PicturePanel("/res/image/blue.jpg");
		bodyPanel.setBounds(0, 0, 656, 283);
		getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);

		JButton borrowButton = new JButton("查看记录详情");
		borrowButton.setBounds(334, 7, 308, 27);
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField.getText().equals("")) {
					Record record = recordService.getRecord(textField.getText());
					bookService.setBook(record.getBookIDentification());
					new OrderDetailFrame(bookService.getBook().getBookName(), userService.getUser().getUserName(),
							textField.getText(), record.isNotice() ? "已归还" : "未归还",
							bookService.getBook().getBookPicture()).setVisible(true);
				}
			}
		});
		borrowButton.setFont(new Font("楷体", Font.BOLD, 18));
		bodyPanel.add(borrowButton);

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
					String rid = (String) table.getValueAt(table.getSelectedRow(), 2);
					textField.setText(rid);
					if (e.getClickCount() == 2) {
						rid = (String) table.getValueAt(table.getSelectedRow(), 2);
						Record record = recordService.getRecord(rid);
						bookService.setBook(record.getBookIDentification());
						new OrderDetailFrame(bookService.getBook().getBookName(), userService.getUser().getUserName(),
								textField.getText(), record.isNotice() ? "已归还" : "未归还",
								bookService.getBook().getBookPicture()).setVisible(true);
					}
				}
			}
		});

		JScrollPane BookScrollPane = new JScrollPane();
		BookScrollPane.setBounds(14, 47, 628, 229);
		bodyPanel.add(BookScrollPane);
		BookScrollPane.setViewportView(table);
		initTableModel(table);

		textField = new JTextField();
		textField.setOpaque(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("楷体", Font.BOLD, 20));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(14, 7, 306, 27);
		bodyPanel.add(textField);

		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new UserFrame(userService, bookService, recordService);
			}
		});

	}

	public void showMessage(String name) {
		JOptionPane.showMessageDialog(this, name);
	}

	public void initTableModel(JTable table) {
		Object[][] rowDate = null;
		Object[] columnNames = { "书籍名称", "归还日期", "记录号", "是否归还" };
		recordService.setUser(userService.getUser());

		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		recordService.setRecords(userService.getRecords());
		Set<Record> recordSet = recordService.userSeeAllRecordsEx();
		Object[] BookRow;
		for (Record record : recordSet) {
			bookService.setBook(record.getBookIDentification());
			BookRow = new Object[] { bookService.getBook().getBookName(), record.getEndTime(),
					record.getRecordIDentification(), record.isNotice() ? "已归还" : "未归还" };
			model.insertRow(0, BookRow);
		}
		table.setModel(model);
	}

}