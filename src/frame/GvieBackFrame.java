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

public class GvieBackFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField bookNameTextField;
	private JTextField bookAuthorTextField;
	private JTextField bookCategroyTextField;
	private JTextField bookIDentifcationTextField;
	private JPanel bodyPanel;
	private IUserService userService = null;
	private IRecordService recordService = null;
	private JTextField recordIDentifcationtextField;

	public GvieBackFrame(IUserService userService, IBookService bookService, IRecordService recordService) {
		this.userService = userService;
		this.recordService = recordService;

		JFrame frame = this;
		frame.setTitle("归还书籍");
		frame.setSize(662, 318);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);

		bodyPanel = new PicturePanel("/res/image/blue.jpg");
		bodyPanel.setBounds(0, 0, 662, 318);
		getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);

		JLabel bookNameLabel = new JLabel("书籍名称");
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookNameLabel.setBounds(428, 75, 76, 32);
		bodyPanel.add(bookNameLabel);

		JLabel bookAuthorLabel = new JLabel("借阅时间");
		bookAuthorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookAuthorLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookAuthorLabel.setBounds(428, 120, 76, 32);
		bodyPanel.add(bookAuthorLabel);

		JLabel bookCategroyLabel = new JLabel("应还时间");
		bookCategroyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookCategroyLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookCategroyLabel.setBounds(428, 165, 76, 32);
		bodyPanel.add(bookCategroyLabel);

		JLabel bookIDentifcationLabel = new JLabel("书籍编号");
		bookIDentifcationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDentifcationLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookIDentifcationLabel.setBounds(428, 210, 76, 32);
		bodyPanel.add(bookIDentifcationLabel);
		
				JLabel recordIDentifcationLabel = new JLabel("订单编号");
				recordIDentifcationLabel.setHorizontalAlignment(SwingConstants.CENTER);
				recordIDentifcationLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
				recordIDentifcationLabel.setBounds(428, 22, 76, 32);
				bodyPanel.add(recordIDentifcationLabel);

		bookNameTextField = new JTextField();
		bookNameTextField.setOpaque(false);
		bookNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameTextField.setForeground(Color.WHITE);
		bookNameTextField.setFont(new Font("楷体", Font.BOLD, 20));
		bookNameTextField.setColumns(10);
		bookNameTextField.setBounds(518, 75, 128, 29);
		bodyPanel.add(bookNameTextField);

		bookAuthorTextField = new JTextField();
		bookAuthorTextField.setOpaque(false);
		bookAuthorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bookAuthorTextField.setForeground(Color.WHITE);
		bookAuthorTextField.setFont(new Font("楷体", Font.BOLD, 20));
		bookAuthorTextField.setColumns(10);
		bookAuthorTextField.setBounds(518, 121, 128, 29);
		bodyPanel.add(bookAuthorTextField);

		bookCategroyTextField = new JTextField();
		bookCategroyTextField.setOpaque(false);
		bookCategroyTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bookCategroyTextField.setForeground(Color.WHITE);
		bookCategroyTextField.setFont(new Font("楷体", Font.BOLD, 20));
		bookCategroyTextField.setColumns(10);
		bookCategroyTextField.setBounds(518, 166, 128, 29);
		bodyPanel.add(bookCategroyTextField);

		bookIDentifcationTextField = new JTextField();
		bookIDentifcationTextField.setOpaque(false);
		bookIDentifcationTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDentifcationTextField.setForeground(Color.WHITE);
		bookIDentifcationTextField.setFont(new Font("楷体", Font.BOLD, 20));
		bookIDentifcationTextField.setColumns(10);
		bookIDentifcationTextField.setBounds(518, 211, 128, 29);
		bodyPanel.add(bookIDentifcationTextField);

		recordIDentifcationtextField = new JTextField();
		recordIDentifcationtextField.setText("");
		recordIDentifcationtextField.setOpaque(false);
		recordIDentifcationtextField.setHorizontalAlignment(SwingConstants.CENTER);
		recordIDentifcationtextField.setForeground(Color.WHITE);
		recordIDentifcationtextField.setFont(new Font("楷体", Font.BOLD, 20));
		recordIDentifcationtextField.setColumns(10);
		recordIDentifcationtextField.setBounds(518, 23, 128, 29);
		bodyPanel.add(recordIDentifcationtextField);
		
				JButton borrowButton = new JButton("归还");
				borrowButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!recordIDentifcationtextField.getText().equals("")) {
							userService.setRecord(recordService.getRecord(recordIDentifcationtextField.getText()));
							bookService.setBook(bookIDentifcationTextField.getText());
							Book book = bookService.getBook();
							String res = userService.userGiveBack(book);
							if (res.equals("Success")) {
								showMessage("成功归还书籍！");
								dispose();
								new GvieBackFrame(userService, bookService, recordService);
							} else {
								showMessage("归还书籍失败！");
							}
						} else {
							showMessage("请先选中一本书在进行操作！");
						}
					}
				});
				
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
											bookNameTextField.setText(book.getBookName());
											bookAuthorTextField.setText(book.getBookAuthor());
											recordIDentifcationtextField.setText(record.getRecordIDentification());
											bookIDentifcationTextField.setText(book.getBookIDentification());
											bookCategroyTextField.setText(book.getBookFirstCategory() + "->" + book.getBookSecondCategory());
										}
									}
								});
										
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
								
										JScrollPane BookScrollPane = new JScrollPane();
										BookScrollPane.setBounds(27, 23, 390, 263);
										bodyPanel.add(BookScrollPane);
										BookScrollPane.setViewportView(table);
										initTableModel(table);
				borrowButton.setFont(new Font("楷体", Font.BOLD, 18));
				borrowButton.setBounds(549, 255, 104, 27);
				bodyPanel.add(borrowButton);

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