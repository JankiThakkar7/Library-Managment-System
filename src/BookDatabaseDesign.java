import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class BookDatabaseDesign {
	
	public static boolean insertData(BookBuisnessLogic book) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Activated (insert)");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db23?characterEncoding=latin1&useConfigs=maxPerformance", "root", "");
            System.out.println("Database Connected (insert)");
            PreparedStatement pst = con.prepareStatement("insert into book values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, book.bookid);
            pst.setString(2, book.bookname);
            pst.setString(3, book.authornames);
            pst.setString(4, book.publication);
            pst.setDate(5, new java.sql.Date(book.dop.getTime()));
            pst.setDouble(6, book.price);
            pst.setInt(7, book.quantity);
            pst.setDouble(8, book.totalcost);
            pst.execute();
            pst.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Book ID already exists. Please use a different ID.");
            return false;
        }
    }
	
	public static void deleteData(int bid)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Activated (deleting)");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db23?characterEncoding=latin1&useConfigs=maxPerformance","root","");
			System.out.println("Database Connected (deleting)");
			PreparedStatement pst = con.prepareStatement("delete from book where bid = ?");
			pst.setInt(1, bid);
			pst.execute();
			pst.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	
	public static void updateData(BookBuisnessLogic book) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Activated (update)");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db23?characterEncoding=latin1&useConfigs=maxPerformance", "root", "");
            System.out.println("Database Connected (update)");
            PreparedStatement pst = con.prepareStatement("update book set bname = ?, anames = ?, publication = ?, dop = ?, price = ?, quantity = ?, cost = ? where bid = ?");
            pst.setString(1, book.bookname);
            pst.setString(2, book.authornames);
            pst.setString(3, book.publication);
            pst.setDate(4, new java.sql.Date (book.dop.getTime()));
            pst.setDouble(5, book.price);
            pst.setInt(6, book.quantity);
            pst.setDouble(7, book.totalcost);
            pst.setInt(8, book.bookid);
            pst.execute();
            pst.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
	
	public static List<BookBuisnessLogic> show() {
        List<BookBuisnessLogic> bookDataList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Activated (show)");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db23?characterEncoding=latin1&useConfigs=maxPerformance", "root", "");
            System.out.println("Database Connected (show)");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book");

            while (rs.next()) {
                int bookid = rs.getInt(1);
                String bookname = rs.getString(2);
                String authornames = rs.getString(3);
                String publication = rs.getString(4);
                Date dop = rs.getDate(5);
                double price = rs.getDouble(6);
                int quantity = rs.getInt(7);
                double totalcost = rs.getDouble(8);

                BookBuisnessLogic book = new BookBuisnessLogic(bookid, bookname, authornames, publication, dop, price, totalcost, quantity);
                bookDataList.add(book);
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

        return bookDataList;
    }

	public static List<BookBuisnessLogic> search(String selectedItem, String searchdata) {
	    List<BookBuisnessLogic> searchlist = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        System.out.println("Driver Activated (searching)");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db23?characterEncoding=latin1&useConfigs=maxPerformance", "root", "");
	        System.out.println("Database Connected (searching)");
	        Statement st = con.createStatement();
	        ResultSet rs = null;

	        String query = ""; 

	        if (selectedItem.equals("Book ID")) 
	        {
	            query = "select * from book where bid = " + searchdata;
	        } 
	        else if (selectedItem.equals("Book Name")) 
	        {
	            query = "select * from book where bname = '" + searchdata + "'";
	        } 
	        else if (selectedItem.equals("Author Names")) 
	        {
	        	query = "select * from book where anames LIKE '%" + searchdata + "%'";
	        } 
	        else if (selectedItem.equals("Publication")) 
	        {
	            query = "select * from book where publication LIKE '%" + searchdata + "%'";
	        }

	        rs = st.executeQuery(query);

	        while (rs.next()) {
	            int bookid = rs.getInt(1);
	            String bookname = rs.getString(2);
	            String authornames = rs.getString(3);
	            String publication = rs.getString(4);
	            Date dop = rs.getDate(5);
	            double price = rs.getDouble(6);
	            int quantity = rs.getInt(7);
	            double totalcost = rs.getDouble(8);

	            BookBuisnessLogic book = new BookBuisnessLogic(bookid, bookname, authornames, publication, dop, price, totalcost, quantity);
	            searchlist.add(book);
	        }

	        rs.close();
	        st.close();
	        con.close();

	    } catch (Exception e) {
	        System.out.println("Error : " + e.getMessage());
	    }

	    return searchlist;
	}

	public static void changePrice() {
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Activated");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db23?characterEncoding=latin1&useConfigs=maxPerformance","root","");
			System.out.println("Database Connected");
			CallableStatement cst = con.prepareCall(" { call update_book_prices() } ");
			cst.execute();
			cst.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
		
	}

	
}
