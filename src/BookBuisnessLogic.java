import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BookBuisnessLogic implements Serializable{

	String bookname,authornames,publication;
	Date dop;
	double price,totalcost;
	int quantity,bookid;

	public BookBuisnessLogic(int bookid, String bookname, String authornames, String publication, Date dop, double price, double totalcost, int quantity)
	{
		this.bookid = bookid;
		this.bookname = bookname;
		this.authornames = authornames;
		this.publication = publication;
		this.dop = dop;
		this.price = price;
		this.totalcost = totalcost;
		this.quantity = quantity;
	}
}
