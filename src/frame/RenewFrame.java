package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entity.Book;
import entity.Record;
import service.IBookService;
import service.IRecordService;
import service.IUserService;

public class RenewFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField name;
	private JTextField author;
	private JTextField category;
	private JTextField id;
	private JPanel bodyPanel;
	private IUserService userService = null;
	private IRecordService recordService = null;
	private JTextField textField;

	public RenewFrame(IUserService userService, IBookService bookService, IRecordService recordService) {
		this.userService = userService;
		this.recordService = recordService;

		JFrame frame = this;
		frame.setTitle("续借书籍");
		frame.setSize(662, 350);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);

		bodyPanel = new PicturePanel("/res/image/blue.jpg");
		bodyPanel.setBounds(0, 0, 656, 318);
		getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);

		JComboBox<String> classComboBox = new JComboBox<String>();
		classComboBox.setBounds(431, 257, 104, 27);
		bodyPanel.add(classComboBox);
		classComboBox.addItem("全部");
		classComboBox.addItem("未过期");
		classComboBox.addItem("已过期");
		classComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String model = (String) classComboBox.getSelectedItem();
				if (model.equals("已过期")) {
					timedTableModel(table);
				}
				if (model.equals("未过期")) {
					timeTableModel(table);
				}
				if (model.equals("全部")) {
					initTableModel(table);
				}
			}
		});

		JButton borrowButton = new JButton("续借");
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField.getText().equals("")) {
					userService.setRecord(recordService.getRecord(textField.getText()));
					Book book = bookService.getBook();
					String res = userService.userRenew(book);

					if (res.equals("Success")) {
						showMessage("成功续借书籍！");
						dispose();
						new RenewFrame(userService, bookService, recordService);
					} else if (res.equals("Fail-2")) {
						showMessage("图书已经到期，请先归还图书！");
					} else {
						showMessage("请于到期十天前续借！");
					}
				} else {
					showMessage("请先选中一本书在进行操作！");
				}
			}
		});
		borrowButton.setFont(new Font("楷体", Font.BOLD, 18));
		borrowButton.setBounds(549, 255, 104, 27);
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
					String isbn = (String) table.getValueAt(table.getSelectedRow(), 3);
					String rid = (String) table.getValueAt(table.getSelectedRow(), 2);
					bookService.setBook(isbn);
					Record record = recordService.getRecord(rid);
					Book book = bookService.getBook();
					name.setText(book.getBookName());
					author.setText(book.getBookAuthor());
					textField.setText(record.getRecordIDentification());
					id.setText(book.getBookIDentification());
					category.setText(book.getBookFirstCategory() + "->" + book.getBookSecondCategory());
				}
			}
		});

		JScrollPane BookScrollPane = new JScrollPane();
		BookScrollPane.setBounds(27, 23, 390, 263);
		bodyPanel.add(BookScrollPane);
		BookScrollPane.setViewportView(table);
		initTableModel(table);

		JLabel bookNameLabel = new JLabel("书籍名称");
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookNameLabel.setBounds(428, 75, 76, 32);
		bodyPanel.add(bookNameLabel);

		JLabel authorLabel = new JLabel("借阅时间");
		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		authorLabel.setBounds(428, 120, 76, 32);
		bodyPanel.add(authorLabel);

		JLabel categroyLabel = new JLabel("应还时间");
		categroyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categroyLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		categroyLabel.setBounds(428, 165, 76, 32);
		bodyPanel.add(categroyLabel);

		JLabel bookIDentifcationLabel = new JLabel("书籍编号");
		bookIDentifcationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDentifcationLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookIDentifcationLabel.setBounds(428, 210, 76, 32);
		bodyPanel.add(bookIDentifcationLabel);

		name = new JTextField();
		name.setOpaque(false);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("楷体", Font.BOLD, 20));
		name.setColumns(10);
		name.setBounds(518, 75, 128, 29);
		bodyPanel.add(name);

		author = new JTextField();
		author.setOpaque(false);
		author.setHorizontalAlignment(SwingConstants.CENTER);
		author.setForeground(Color.WHITE);
		author.setFont(new Font("楷体", Font.BOLD, 20));
		author.setColumns(10);
		author.setBounds(518, 121, 128, 29);
		bodyPanel.add(author);

		category = new JTextField();
		category.setOpaque(false);
		category.setHorizontalAlignment(SwingConstants.CENTER);
		category.setForeground(Color.WHITE);
		category.setFont(new Font("楷体", Font.BOLD, 20));
		category.setColumns(10);
		category.setBounds(518, 166, 128, 29);
		bodyPanel.add(category);

		id = new JTextField();
		id.setOpaque(false);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setForeground(Color.WHITE);
		id.setFont(new Font("楷体", Font.BOLD, 20));
		id.setColumns(10);
		id.setBounds(518, 211, 128, 29);
		bodyPanel.add(id);

		JLabel label = new JLabel("订单编号");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label.setBounds(428, 22, 76, 32);
		bodyPanel.add(label);

		textField = new JTextField();
		textField.setText("");
		textField.setOpaque(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("楷体", Font.BOLD, 20));
		textField.setColumns(10);
		textField.setBounds(518, 23, 128, 29);
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
		Object[] columnNames = { "借书日期", "应还日期", "记录号", "书籍号" };
		recordService.setUser(userService.getUser());

		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		recordService.setRecords(userService.getRecords());
		Set<Record> recordSet = recordService.userSeeAllRecords();
		Object[] BookRow;
		for (Record record : recordSet) {
			BookRow = new Object[] { record.getStartTime(), record.getEndTime(), record.getRecordIDentification(),
					record.getBookIDentification() };
			model.insertRow(0, BookRow);
		}
		table.setModel(model);
	}

	public void timedTableModel(JTable table) {
		Object[][] rowDate = null;
		Object[] columnNames = { "借书日期", "应还日期", "记录号", "书籍号" };
		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		recordService.setUser(userService.getUser());
		recordService.setRecords(userService.getRecords());
		Set<Record> recordSet = recordService.userSeeAllRecords();
		Object[] BookRow;
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		for (Record record : recordSet) {
			if (record.getEndTime().compareTo(year + "-" + month + "-" + day) < 0) {
				BookRow = new Object[] { record.getStartTime(), record.getEndTime(), record.getRecordIDentification(),
						record.getBookIDentification() };
				model.insertRow(0, BookRow);
			}
		}
		table.setModel(model);
	}

	public void timeTableModel(JTable table) {
		Object[][] rowDate = null;
		Object[] columnNames = { "借书日期", "应还日期", "记录号", "书籍号" };
		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		recordService.setUser(userService.getUser());
		Set<Record> recordSet = recordService.userSeeAllRecords();
		Object[] BookRow;
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		for (Record record : recordSet) {
			if (record.getEndTime().compareTo(year + "-" + month + "-" + day) > 0) {
				BookRow = new Object[] { record.getStartTime(), record.getEndTime(), record.getRecordIDentification(),
						record.getBookIDentification() };
				model.insertRow(0, BookRow);
			}
		}

		table.setModel(model);
	}
}