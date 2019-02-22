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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entity.Book;
import service.IBookService;
import service.IRecordService;
import service.IUserService;


public class AllBookFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField searchTextField;
	private JTextArea bookDetailTextArea;
	private JTextField bookNameTextField;
	private JTextField bookAuthorTextField;
	private JTextField bookCategroyTextField;
	private JTextField bookIDentifcationTextField;
	private JPanel bodyPanel; 
	private JLabel bookPictrueLabel;
	private IBookService bookService = null;
	private IUserService userService = null;
	public AllBookFrame(IUserService userService, IBookService bookService, IRecordService recordService) {
		this.bookService = bookService;
		this.userService = userService;
		JFrame frame = this;
		frame.addWindowListener(new WindowAdapter(){
			   public void windowClosing(WindowEvent e) {
				   new UserFrame(userService, bookService, recordService);
			   }
		});
		frame.setTitle("借阅书籍");
		frame.setSize(698,546);
		frame.setResizable(false);
		frame.setLocation(300, 200);
		getContentPane().setLayout(null);
		
		bodyPanel = new PicturePanel("/res/image/blue.jpg");
		bodyPanel.setBounds(0, 0, 696, 542);
		getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);
		
		JLabel bookPictrueLebel = new JLabel("书籍展示");
		bookPictrueLebel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictrueLebel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookPictrueLebel.setBounds(439, 68, 76, 32);
		bodyPanel.add(bookPictrueLebel);
		
		
		bookPictrueLabel = new JLabel(new ImageIcon(getClass().getResource("/res/image/blue.jpg"))) ;
		bookPictrueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookPictrueLabel.setBounds(560, 37, 95, 109);
		bodyPanel.add(bookPictrueLabel);
		
		JLabel bookNameLabel = new JLabel("书籍名称");
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookNameLabel.setBounds(439, 302, 76, 32);
		bodyPanel.add(bookNameLabel);
		
		JLabel bookAuthorLabel = new JLabel("书籍作者");
		bookAuthorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookAuthorLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookAuthorLabel.setBounds(439, 347, 76, 32);
		bodyPanel.add(bookAuthorLabel);
		
		JLabel bookCategroyLabel = new JLabel("书籍分类");
		bookCategroyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookCategroyLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookCategroyLabel.setBounds(439, 392, 76, 32);
		bodyPanel.add(bookCategroyLabel);
		
		JLabel bookIDentifcationLabel = new JLabel("书籍编号");
		bookIDentifcationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDentifcationLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookIDentifcationLabel.setBounds(439, 437, 76, 32);
		bodyPanel.add(bookIDentifcationLabel);
		
		JLabel bookDetailLabel = new JLabel("书籍简介");
		bookDetailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookDetailLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bookDetailLabel.setBounds(439, 157, 76, 32);
		bodyPanel.add(bookDetailLabel);
		
		bookNameTextField = new JTextField();
		bookNameTextField.setEditable(false);
		bookNameTextField.setOpaque(false);
		bookNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameTextField.setForeground(Color.WHITE);
		bookNameTextField.setFont(new Font("楷体", Font.BOLD, 20));
		bookNameTextField.setColumns(10);
		bookNameTextField.setBounds(529, 302, 128, 29);
		bodyPanel.add(bookNameTextField);
		
		bookAuthorTextField = new JTextField();
		bookAuthorTextField.setEditable(false);
		bookAuthorTextField.setOpaque(false);
		bookAuthorTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bookAuthorTextField.setForeground(Color.WHITE);
		bookAuthorTextField.setFont(new Font("楷体", Font.BOLD, 20));
		bookAuthorTextField.setColumns(10);
		bookAuthorTextField.setBounds(529, 348, 128, 29);
		bodyPanel.add(bookAuthorTextField);
		
		bookCategroyTextField = new JTextField();
		bookCategroyTextField.setEnabled(false);
		bookCategroyTextField.setOpaque(false);
		bookCategroyTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bookCategroyTextField.setForeground(Color.WHITE);
		bookCategroyTextField.setFont(new Font("楷体", Font.BOLD, 20));
		bookCategroyTextField.setColumns(10);
		bookCategroyTextField.setBounds(529, 393, 128, 29);
		bodyPanel.add(bookCategroyTextField);
		
		bookIDentifcationTextField = new JTextField();
		bookIDentifcationTextField.setEnabled(false);
		bookIDentifcationTextField.setOpaque(false);
		bookIDentifcationTextField.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDentifcationTextField.setForeground(Color.WHITE);
		bookIDentifcationTextField.setFont(new Font("楷体", Font.BOLD, 20));
		bookIDentifcationTextField.setColumns(10);
		bookIDentifcationTextField.setBounds(529, 438, 128, 29);
		bodyPanel.add(bookIDentifcationTextField);
		
		JComboBox<String> classComboBox = new JComboBox<String>();
		classComboBox.setBounds(181, 30, 104, 32);
		bodyPanel.add(classComboBox);
		classComboBox.addItem("按名称");
		classComboBox.addItem("按作者");
		classComboBox.addItem("按分类");
		
		JButton searchButton = new JButton("查询",new ImageIcon(getClass().getResource("/res/image/search.png")));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String model = (String) classComboBox.getSelectedItem();
				if(model.equals("按名称")) {
					serchByNameTableModel(table,searchTextField.getText());
				}
				if(model.equals("按作者")) {
					serchByAuthorTableModel(table,searchTextField.getText());
				}
				if(model.equals("按分类")) {
					serchByCategoryTableModel(table,searchTextField.getText());
				}
			}
		});
		

		
		table = new JTable(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
            {
                       return false;
            }
            
		};
		
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) { 
		        if(table.getValueAt(table.getSelectedRow(),0)!=null){
		       	String isbn = (String)table.getValueAt(table.getSelectedRow(),3);
		       	bookService.setBook(isbn);
		       	Book book  = bookService.getBook();
		       	bookDetailTextArea.setText(book.getBookIntroduction());
		       	bookNameTextField.setText(book.getBookName());
		       	bookAuthorTextField.setText(book.getBookAuthor());
		      		bookIDentifcationTextField.setText(book.getBookIDentification());
		      	 	bookCategroyTextField.setText(book.getBookFirstCategory()+"->"+book.getBookSecondCategory());
		      	 	bookPictrueLabel.setIcon(new ImageIcon(getClass().getResource(book.getBookPicture()))); 
		        }
		    }
        });
		
		bookDetailTextArea = new JTextArea("请在左边的表格中选择一本图书！");
		bookDetailTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(bookDetailTextArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		bookDetailTextArea.setFont(new Font("宋体", Font.PLAIN, 18));
		bookDetailTextArea.setLineWrap(true);
		scroll.setBounds(560, 161, 95, 109);
		bodyPanel.add(scroll);
		bookDetailTextArea.setColumns(10);
		
		
		
		
		JScrollPane BookScrollPane = new JScrollPane();
		BookScrollPane.setBounds(24, 78, 390, 431);
		bodyPanel.add(BookScrollPane);
		BookScrollPane.setViewportView(table);
		initTableModel(table);
		searchButton.setBounds(299, 30, 113, 32);
		bodyPanel.add(searchButton);
		
		JButton button = new JButton("借阅");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!bookIDentifcationTextField.getText().equals("")) {
					bookService.setBook(bookIDentifcationTextField.getText());
					String res = userService.userBorrow(bookService.getBook());
					if(res.equals("Success")) {
						showMessage("借阅成功，请按于30天后归还！");
					}else if(res.equals("Fail-1")){
						showMessage("库存不足！");
					}else {
						showMessage("借阅超出限制！");
					}
				}else {
					showMessage("请先选择一本书籍！");
				}
			}
		});
		button.setFont(new Font("楷体", Font.BOLD, 18));
		button.setBounds(593, 482, 89, 27);
		bodyPanel.add(button);
		
		
		searchTextField = new JTextField();
		searchTextField.setBounds(24, 31, 143, 32);
		bodyPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		
	}

	protected void serchByCategoryTableModel(JTable table, String patter) {
		Pattern pattern = Pattern.compile(patter,Pattern.CASE_INSENSITIVE);
		Object[][] rowDate = null;
		Object[] columnNames = {"名称","作者","分类","isbn"};		
		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		Set<Book> bookSet = bookService.getBookList();
		Object[] BookRow;
		for(Book book:bookSet){
			Matcher matcher1 = pattern.matcher(book.getBookFirstCategory());
			Matcher matcher2 = pattern.matcher(book.getBookSecondCategory());
			if(matcher1.find() || matcher2.find()) {
				BookRow = new Object[]{book.getBookName(),book.getBookAuthor(),book.getBookSecondCategory(),book.getBookIDentification()};
				model.insertRow(0, BookRow);
			}
			
		}
		table.setModel(model);	
	}

	protected void serchByAuthorTableModel(JTable table, String patter) {
		Pattern pattern = Pattern.compile(patter,Pattern.CASE_INSENSITIVE);
		Object[][] rowDate = null;
		Object[] columnNames = {"名称","作者","分类","isbn"};		
		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		bookService.setBooks(userService.getBooks());
		Set<Book> bookSet = bookService.getBookList();
		Object[] BookRow;
		for(Book book:bookSet){
			Matcher matcher = pattern.matcher(book.getBookAuthor());
			if(matcher.find()) {
				BookRow = new Object[]{book.getBookName(),book.getBookAuthor(),book.getBookSecondCategory(),book.getBookIDentification()};
				model.insertRow(0, BookRow);
			}
		}
		table.setModel(model);	
		

		
	}

	public void showMessage(String name){
		JOptionPane.showMessageDialog(this, name);
	}
	
	
	public void initTableModel(JTable table){
		Object[][] rowDate = null;
		Object[] columnNames = {"名称","作者","分类","isbn"};		
		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		bookService.setBooks(userService.getBooks());
		Set<Book> bookSet = bookService.getBookList();
		Object[] BookRow;
		for(Book book:bookSet){
			BookRow = new Object[]{book.getBookName(),book.getBookAuthor(),book.getBookSecondCategory(),book.getBookIDentification()};
			model.insertRow(0, BookRow);
		}
		table.setModel(model);	
	}
	
	public void serchByNameTableModel(JTable table,String patter){
		Pattern pattern = Pattern.compile(patter,Pattern.CASE_INSENSITIVE);
		Object[][] rowDate = null;
		Object[] columnNames = {"名称","作者","分类","isbn"};		
		DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
		bookService.setBooks(userService.getBooks());
		Set<Book> bookSet = bookService.getBookList();
		Object[] BookRow;
		for(Book book:bookSet){
			Matcher matcher = pattern.matcher(book.getBookName());
			if(matcher.find()) {
				BookRow = new Object[]{book.getBookName(),book.getBookAuthor(),book.getBookSecondCategory(),book.getBookIDentification()};
				model.insertRow(0, BookRow);
			}
		}
		table.setModel(model);	
	}
}