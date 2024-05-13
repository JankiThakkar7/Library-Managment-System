import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
class BookGUI extends JFrame
{
	
	JPanel top,bottom;
	JLabel bs,bidl,bnl,anl,pl,dopl,slash,impl,pbl,tql,tcl;
	JTextField bid,bn,an,p,dopd,dopm,dopy,pb,tq,tc;
	JButton submit,reset,viewdata;
	BookEventHandling obeh;
	
	Font f = new Font("Arial Rounded MT BOLD",Font.CENTER_BASELINE,30);
	Font f1 = new Font("Times New Roman",Font.BOLD,25);
	Font f2 = new Font("Times New Roman",Font.PLAIN,20);
	Border matteBorder = BorderFactory.createMatteBorder(2, 7, 2, 2, Color.LIGHT_GRAY);
	Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);

	
	BookGUI(String s)
	{
		super(s);
		
		setLayout(null);
		
		ImageIcon backGroundImage = new ImageIcon("src//book.jpg");
		Image img = backGroundImage.getImage();
		Image temp_img = img.getScaledInstance(1600,900,Image.SCALE_SMOOTH);
		backGroundImage = new ImageIcon(temp_img);

		setContentPane(new JLabel(backGroundImage));
		
		top = new JPanel();
		top.setLayout(null);
		top.setBackground(Color.BLACK);
		top.setBounds(0,0,1500,70);
		add(top);
		
		bs = new JLabel("WELCOME TO BOOK STORE....");
		bs.setFont(f);
		bs.setBounds(450,20,500,30);
		bs.setForeground(Color.WHITE);
		top.add(bs);
		
		bottom = new JPanel();
		bottom.setBounds(20,90,1326,590);
		bottom.setBackground(new Color(0, 0, 0, 128)); 
		bottom.setLayout(null);
		bottom.setBorder(matteBorder);
		add(bottom);
		
		bidl = new JLabel("Book ID : ");
		bidl.setForeground(Color.WHITE);
		bidl.setBounds(30,30,150,25);
		bidl.setFont(f1);
		bottom.add(bidl);
		
		bid = new JTextField();
		bid.setBounds(150,30,400,25);
		bid.setFont(f2);
		bid.setBorder(lineBorder);
		bottom.add(bid);
		
		impl = new JLabel("Minimum 1 digit and maximum 4 digits");
		impl.setBounds(150, 60, 250, 15);
		impl.setForeground(Color.YELLOW);
		bottom.add(impl);
		
		bnl = new JLabel("Book Name : ");
		bnl.setForeground(Color.WHITE);
		bnl.setBounds(700,30,150,25);
		bnl.setFont(f1);
		bottom.add(bnl);
		
		bn = new JTextField();
		bn.setBounds(860,30,400,25);
		bn.setFont(f2);
		bn.setBorder(lineBorder);
		bottom.add(bn);
		
		anl = new JLabel("Author Names : ");
		anl.setForeground(Color.WHITE);
		anl.setBounds(30,130,200,25);
		anl.setFont(f1);
		bottom.add(anl);
		
		an = new JTextField();
		an.setBounds(210,130,340,25);
		an.setFont(f2);
		an.setBorder(lineBorder);
		bottom.add(an);
		
		pl = new JLabel("Publication : ");
		pl.setForeground(Color.WHITE);
		pl.setBounds(700,130,150,25);
		pl.setFont(f1);
		bottom.add(pl);
		
		p = new JTextField();
		p.setBounds(860,130,400,25);
		p.setFont(f2);
		p.setBorder(lineBorder);
		bottom.add(p);
		
		dopl = new JLabel("Date of Publication : ");
		dopl.setForeground(Color.WHITE);
		dopl.setBounds(30,230,250,25);
		dopl.setFont(f1);
		bottom.add(dopl);
		
		dopd = new JTextField();
		dopd.setBounds(260,230,25,25);
		dopd.setFont(f2);
		dopd.setBorder(lineBorder);
		bottom.add(dopd);
		
		slash = new JLabel("/");
		slash.setForeground(Color.WHITE);
		slash.setBounds(300,230,10,25);
		slash.setFont(f1);
		bottom.add(slash);
		
		dopm = new JTextField();
		dopm.setBounds(322,230,25,25);
		dopm.setFont(f2);
		dopm.setBorder(lineBorder);
		bottom.add(dopm);
		
		slash = new JLabel("/");
		slash.setForeground(Color.WHITE);
		slash.setBounds(360,230,10,25);
		slash.setFont(f1);
		bottom.add(slash);
		
		dopy = new JTextField();
		dopy.setBounds(380,230,45,25);
		dopy.setFont(f2);
		dopy.setBorder(lineBorder);
		bottom.add(dopy);
		
		impl = new JLabel("Write date in dd/mm/yyyy format");
		impl.setBounds(260,260,425,15);
		impl.setForeground(Color.YELLOW);
		bottom.add(impl);
		
		pbl = new JLabel("Price of Book : ");
		pbl.setForeground(Color.WHITE);
		pbl.setBounds(700,230,200,25);
		pbl.setFont(f1);
		bottom.add(pbl);
		
		pb = new JTextField();
		pb.setBounds(880,230,380,25);
		pb.setFont(f2);
		pb.setBorder(lineBorder);
		bottom.add(pb);
		
		tql = new JLabel("Total Quantity to Order : ");
		tql.setForeground(Color.WHITE);
		tql.setBounds(30,330,280,25);
		tql.setFont(f1);
		bottom.add(tql);
		
		tq = new JTextField();
		tq.setBounds(310,330,240,25);
		tq.setFont(f2);
		tq.setBorder(lineBorder);
		bottom.add(tq);
		
		tcl = new JLabel("Total Cost : ");
		tcl.setForeground(Color.WHITE);
		tcl.setBounds(700,330,200,25);
		tcl.setFont(f1);
		bottom.add(tcl);
		
		tc = new JTextField();
		tc.setBounds(850,330,410,25);
		tc.setFont(f2);
		tc.setBorder(lineBorder);
		tc.setEditable(false);
		bottom.add(tc);
		
		obeh = new BookEventHandling(this);
		
		submit = new JButton("Submit");
		submit.setBounds(370, 460, 150, 50);
		submit.setFont(f1);
		submit.addActionListener(obeh);
		submit.setFocusPainted(false);
		bottom.add(submit);
		
		reset = new JButton("Reset");
		reset.setBounds(570, 460, 150, 50);
		reset.setFont(f1);
		reset.addActionListener(obeh);
		reset.setFocusPainted(false);
		bottom.add(reset);
		
		viewdata = new JButton("View Data");
		viewdata.setBounds(770, 460, 150, 50);
		viewdata.setFont(f1);
		viewdata.addActionListener(obeh);
		viewdata.setFocusPainted(false);
		bottom.add(viewdata);
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

public class MainPage {

	public static void main(String[] args) {
		
		BookGUI obgui = new BookGUI("Book Store");
		obgui.setVisible(true);
	}

}
