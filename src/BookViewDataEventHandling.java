import java.util.Date;
import java.util.List;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class BookViewDataEventHandling implements ActionListener {

    ViewDataDesign ovdd;

    BookViewDataEventHandling(ViewDataDesign ovdd) {
        this.ovdd = ovdd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Search")) {
            String selectedItem = ovdd.box.getSelectedItem().toString();
            String searchdata = ovdd.data.getText();

            List<BookBuisnessLogic> searchlist = BookDatabaseDesign.search(selectedItem, searchdata);
            System.out.println("Search Result Size: " + searchlist.size());
            	updateTable(searchlist);
        } else if (e.getActionCommand().equals("View All Books")) {
            ovdd.fillTableFromDatabase();
        } else if (e.getActionCommand().equals("Update A Record")) {
        	
        	updateRecord();
            
        } else if (e.getActionCommand().equals("Delete A Record")) {
            
        	deleteRecord();
        	
        } else if (e.getActionCommand().equals("Change Price (Apply GST)")){
        	
        	BookDatabaseDesign.changePrice();
        	ovdd.fillTableFromDatabase();
        	
        } else if (e.getActionCommand().equals("Back to Home Page")) {
            
        	BookGUI obgui = new BookGUI("Book Store");
    		obgui.setVisible(true);
        }
        	
     }


    private void updateRecord() {
        String bookidupd = JOptionPane.showInputDialog(ovdd, "Enter Book ID to Update: ", "Update Record", JOptionPane.QUESTION_MESSAGE);

        if (bookidupd != null && !bookidupd.isEmpty()) {
            try {
                List<BookBuisnessLogic> searchResult = BookDatabaseDesign.search("Book ID", bookidupd);

                if (!searchResult.isEmpty()) {
                    BookBuisnessLogic book = searchResult.get(0);

                    JTextField bookNameField = new JTextField(book.bookname);
                    JTextField authorNamesField = new JTextField(book.authornames);
                    JTextField publicationField = new JTextField(book.publication);
                    JTextField dateField = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(book.dop));
                    JTextField priceField = new JTextField(String.valueOf(book.price));
                    JTextField quantityField = new JTextField(String.valueOf(book.quantity));
                    JLabel totalCostLabel = new JLabel(String.valueOf(book.totalcost));

                    // Create a panel to hold the input fields
                    JPanel inputPanel = new JPanel(new GridLayout(0, 2));
                    inputPanel.add(new JLabel("Book Name:"));
                    inputPanel.add(bookNameField);
                    inputPanel.add(new JLabel("Author Names:"));
                    inputPanel.add(authorNamesField);
                    inputPanel.add(new JLabel("Publication:"));
                    inputPanel.add(publicationField);
                    inputPanel.add(new JLabel("Date of Publication (dd/MM/yyyy):"));
                    inputPanel.add(dateField);
                    inputPanel.add(new JLabel("Price:"));
                    inputPanel.add(priceField);
                    inputPanel.add(new JLabel("Quantity:"));
                    inputPanel.add(quantityField);
                    inputPanel.add(new JLabel("Total Cost:"));
                    inputPanel.add(totalCostLabel);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Update Book Record", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        // Update the book object with new values
                        book.bookname = bookNameField.getText().trim();
                        book.authornames = authorNamesField.getText().trim();
                        book.publication = publicationField.getText().trim();
                        book.price = Double.parseDouble(priceField.getText().trim());
                        book.quantity = Integer.parseInt(quantityField.getText().trim());
                        
                        try {
                            String updatedDateStr = dateField.getText().trim();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date updatedDate = dateFormat.parse(updatedDateStr);

                            java.sql.Date sqlDate = new java.sql.Date(updatedDate.getTime());
                            book.dop = sqlDate;

                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(ovdd, "Invalid date format. Please use dd/MM/yyyy");
                            return;
                        }
                        
                        book.totalcost = book.price * book.quantity;

                        if (BookValidation.isValidUpdateData(book.bookname, book.authornames, book.publication, book.price, book.quantity)) {
                            BookDatabaseDesign.updateData(book);
                            ovdd.fillTableFromDatabase();
                        } else {
                            JOptionPane.showMessageDialog(ovdd, "Invalid data. Please check your input.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(ovdd, "Book ID not found. Please enter a valid ID");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(ovdd, "Invalid Book ID. Please enter a valid ID");
            }
        }
    }




    void updateTable(List<BookBuisnessLogic> searchlist) {
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{},
                new Object[]{"Book ID", "Book Name", "Author Names", "Publisher", "Date of Publication", "Price of Book", "Total Quantity", "Total Cost"}
        );

        for (BookBuisnessLogic rowData : searchlist) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(rowData.dop);

            Object[] row = {
                    rowData.bookid, rowData.bookname, rowData.authornames, rowData.publication,
                    formattedDate, rowData.price, rowData.quantity, rowData.totalcost
            };
            tableModel.addRow(row);
        }

        JTable newTable = new JTable(tableModel);
        newTable.setFont(ovdd.f2);
        newTable.setRowHeight(30);

        JScrollPane newScrollPane = new JScrollPane(newTable);
        newScrollPane.setBounds(20, 20, 1286, 510);

        JTableHeader header = newTable.getTableHeader();
        header.setFont(ovdd.headerFont);
        header.setPreferredSize(new java.awt.Dimension(100, 50));
        header.setBackground(Color.PINK);

        ovdd.bottom.removeAll();
        ovdd.bottom.add(newScrollPane);
        ovdd.bottom.revalidate();
    }

    
    
    private void deleteRecord()
    {
    	String bookidtodel = JOptionPane.showInputDialog("Enter BookId to delete that book : ");
    	if(bookidtodel != null && !bookidtodel.isEmpty())
    	{
    		try
    		{
    			int bookID = Integer.parseInt(bookidtodel);
    			BookDatabaseDesign.deleteData(bookID);
    			ovdd.fillTableFromDatabase();
    		}
    		catch(Exception e)
    		{
    			JOptionPane.showMessageDialog(ovdd, "Invalid BookId. Please enter a valid ID");
    		}
    	}
    }
    
   

}
