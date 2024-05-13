import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
class ViewDataDesign extends JFrame implements ItemListener{

    JPanel bottom;
    JLabel sb,ed;
    JTable table;
    JTableHeader header;
    JScrollPane scrollPane;
    JButton backtohome,ub,db,search,vab,changeprice;
    JComboBox<String> box;
    JTextField data;
    BookViewDataEventHandling obvdeh;

    Font f = new Font("Arial Rounded MT BOLD", Font.CENTER_BASELINE, 30);
    Font f2 = new Font("Times New Roman", Font.PLAIN, 17);
    Font f3 = new Font("Times New Roman", Font.BOLD, 25);
    Font headerFont = new Font("Arial Rounded MT BOLD", Font.BOLD, 15);
    Border matteBorder = BorderFactory.createMatteBorder(2, 7, 2, 2, Color.LIGHT_GRAY);

    ViewDataDesign(String s) {
        super(s);
        setLayout(null);
        
        obvdeh = new BookViewDataEventHandling(this);
        
        ImageIcon backGroundImage = new ImageIcon("src//book1.jpg");
		Image img = backGroundImage.getImage();
		Image temp_img = img.getScaledInstance(1600,900,Image.SCALE_SMOOTH);
		backGroundImage = new ImageIcon(temp_img);

		setContentPane(new JLabel(backGroundImage));
        
        sb = new JLabel("Search By : ");
        sb.setFont(f3);
        sb.setBounds(20, 30, 150, 25);
        sb.setForeground(Color.BLACK);
        add(sb);
        
        box = new JComboBox<String>();
        box.setBounds(150,30,200,25);
        box.setFont(f3);
        box.addItemListener(this);
        add(box);
        box.addItem("Book ID");
        box.addItem("Book Name");
        box.addItem("Author Names");
        box.addItem("Publication");
        
        ed = new JLabel("Enter Data : ");
        ed.setFont(f3);
        ed.setBounds(380, 30, 150, 25);
        ed.setForeground(Color.BLACK);
        add(ed);
        
        data = new JTextField();
        data.setBounds(520, 30, 500, 25);
        data.setFont(f3);
        add(data);
        
        search = new JButton("Search");
        search.setBounds(1040, 30, 100, 25);
        search.setFont(headerFont);
        search.setFocusPainted(false);
        search.addActionListener(obvdeh);
        add(search);
        
        vab = new JButton("View All Books");
        vab.setBounds(1150, 30, 192, 25);
        vab.setFont(headerFont);
        vab.setFocusPainted(false);
        vab.addActionListener(obvdeh);
        add(vab);

        bottom = new JPanel();
        bottom.setBounds(20, 90, 1326, 550);
        bottom.setBackground(new Color(0, 0, 0, 128));
        bottom.setLayout(null);
        bottom.setBorder(matteBorder);
        add(bottom);
        
        db = new JButton("Delete A Record");
        db.setBounds(20, 650, 200, 45);
        db.setFont(headerFont);
        db.setFocusPainted(false);
        db.addActionListener(obvdeh);
        add(db);
        
        ub = new JButton("Update A Record");
        ub.setBounds(350, 650, 200, 45);
        ub.setFont(headerFont);
        ub.setFocusPainted(false);
        ub.addActionListener(obvdeh);
        add(ub);
        
        changeprice = new JButton("Change Price (Apply GST)");
        changeprice.setBounds(700, 650, 250, 45);
        changeprice.setFont(headerFont);
        changeprice.setFocusPainted(false);
        changeprice.addActionListener(obvdeh);
        add(changeprice);

        table = new JTable();
        
        backtohome = new JButton("Back to Home Page");
        backtohome.setBounds(1146, 650, 200, 45);
        backtohome.setFont(headerFont);
        backtohome.addActionListener(obvdeh);
        backtohome.setFocusPainted(false);
        add(backtohome);
        

        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void fillTableFromDatabase() {
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{},
                new Object[]{"Book ID", "Book Name", "Author Names", "Publisher", "Date of Publication", "Price of Book", "Total Quantity", "Total Cost"}
        );

        List<BookBuisnessLogic> dataList = BookDatabaseDesign.show();

        for (BookBuisnessLogic book : dataList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(book.dop);

            Object[] rowData = {
                    book.bookid, book.bookname, book.authornames, book.publication,
                    formattedDate, book.price, book.quantity, book.totalcost
            };
            tableModel.addRow(rowData);
        }

        table = new JTable(tableModel);
        table.setFont(f2);
        table.setRowHeight(30);

        JScrollPane newScrollPane = new JScrollPane(table);
        newScrollPane.setBounds(20, 20, 1286, 510);

        header = table.getTableHeader();
        header.setFont(headerFont);
        header.setPreferredSize(new java.awt.Dimension(100, 50));
        header.setBackground(Color.PINK);

        bottom.removeAll();
        bottom.add(newScrollPane);
        bottom.revalidate();
    }



	@Override
	public void itemStateChanged(ItemEvent e) {
		
		
		
	}

}

public class BookViewData {

    public static void main(String[] args) {

        ViewDataDesign ovdd = new ViewDataDesign("Books Data Page");
        ovdd.setVisible(true);

    }

}
