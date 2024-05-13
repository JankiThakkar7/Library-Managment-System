import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BookEventHandling implements ActionListener{

	BookGUI obgui1;
	BookBuisnessLogic book;
	BookEventHandling(BookGUI obgui1)
	{
		this.obgui1 = obgui1;
		
		obgui1.tq.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateTotalCost();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateTotalCost();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
		
		obgui1.pb.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateTotalCost();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateTotalCost();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
			
		});
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Reset"))
		{
			clearFields();
		}
		else if(e.getActionCommand().equals("Submit"))
		{
			String validationMessage = getValidationMessage();
			if(validationMessage.isEmpty())
			{
				updateTotalCost();
				showMessage("Validated Successfully");
				book = new BookBuisnessLogic(Integer.parseInt(obgui1.bid.getText()),
						obgui1.bn.getText(),obgui1.an.getText(),obgui1.p.getText(),
						makeDate(obgui1.dopd.getText(),obgui1.dopm.getText(),obgui1.dopy.getText()),
						Double.parseDouble(obgui1.pb.getText()),
						Double.parseDouble(obgui1.tc.getText()),
						Integer.parseInt(obgui1.tq.getText()));
				if(BookDatabaseDesign.insertData(book))
				{
					showMessage("Data added to database");
				}
				clearFields();
			}
			else
			{
				showMessage(validationMessage);
				showMessage("Validation False.Please check inputs");
			}
		}
		else if(e.getActionCommand().equals("View Data"))
		{
			ViewDataDesign ovdd = new ViewDataDesign("Books Data Page");
	        ovdd.setVisible(true);
		}
		
	}
	
	private void clearFields()
	{
		obgui1.an.setText("");
        obgui1.bid.setText("");
        obgui1.bn.setText("");
        obgui1.dopd.setText("");
        obgui1.dopm.setText("");
        obgui1.dopy.setText("");
        obgui1.p.setText("");
        obgui1.pb.setText("");
        obgui1.tc.setText("");
        obgui1.tq.setText("");
	}
	
	private void updateTotalCost()
	{
		try
		{
			int quantity = Integer.parseInt(obgui1.tq.getText().trim());
			double price = Double.parseDouble(obgui1.pb.getText().trim());
			double totalCost = quantity*price;
			obgui1.tc.setText(""+totalCost);
		}
		catch(Exception e)
		{
			System.out.println("BookEventHandling Error : "+e.getMessage());
		}
	}
	
	private void showMessage(String message)
	{
		JOptionPane.showMessageDialog(obgui1, message);
	}
	
	private String getValidationMessage()
	{
		StringBuilder messages = new StringBuilder();
		if(!BookValidation.isValidBookID(obgui1.bid.getText()))
			messages.append("Invalid Book ID. It should be min. 1 digit and max. 4 digits long.\n");
		if(!BookValidation.isValidBookName(obgui1.bn.getText()))
			messages.append("Invalid Book Name. First Letter should be capital.\n");
		if (!BookValidation.isValidAuthorNames(obgui1.an.getText()))
            messages.append("Invalid Author Names. Each author should be separated by a comma and space.\n");
		if(!BookValidation.isValidPublication(obgui1.p.getText()))
			messages.append("Invalid Publication Names. Each publication should be seperated by a comma and space.\n");
		if(!BookValidation.isValidPrice(obgui1.pb.getText()))
			messages.append("Invalid Price. It should contain only digits with or without decimal point\n");
		if(!BookValidation.isValidTotalQuantity(obgui1.tq.getText()))
			messages.append("Invalid Total Quantity. It should contain only digits\n");
		if (!BookValidation.isValidDayOfMonth(obgui1.dopd.getText(), obgui1.dopm.getText(), obgui1.dopy.getText()))
	        messages.append("Invalid Date of Publication. Please enter a valid date in dd/mm/yyyy format.\n");
		if (!BookValidation.isValidDate(obgui1.dopd.getText(), obgui1.dopm.getText(), obgui1.dopy.getText()))
	        messages.append("Invalid Date. Please enter a valid date.\n");
		
		return messages.toString();
	}
	
	private Date makeDate(String day, String month, String year)
	{
		Date date = null;
		try
		{
			String wholedate = obgui1.dopd.getText()+"/"+obgui1.dopm.getText()+"/"+obgui1.dopy.getText();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			date = dateFormat.parse(wholedate);
		}
		catch(Exception e)
		{
			System.out.println("BookEventHandling Error : "+e.getMessage());
		}
		return date;
	}
}
