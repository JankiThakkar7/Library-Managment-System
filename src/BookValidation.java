import java.util.Calendar;

public class BookValidation {

	public static boolean isValidBookID(String bookID)
	{
		int n = Integer.parseInt(bookID);
		if(n > 0 && n <= 9999)
		{
			return true;
		}
		return false;
	}
	
	public static boolean isValidBookName(String bookname)
	{
		if (bookname.isEmpty()) {
	        return false;
	    }
		char firstChar = bookname.charAt(0);
	    if (!Character.isUpperCase(firstChar)) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isValidAuthorNames(String authornames)
	{
		return authornames.matches("([A-Za-z]+[\\s,]*)*[A-Za-z]+");
	}
	
	public static boolean isValidPublication(String publication)
	{
		return publication.matches("([A-Za-z]+[\\s,]*)*[A-Za-z]+");
	}
	
	public static boolean isValidPrice(String price)
	{
		return price.matches("\\d+(\\.\\d+)?");
	}
	
	public static boolean isValidTotalQuantity(String totalQuantity)
	{
		return totalQuantity.matches("\\d+");
	}
	
	public static boolean isValidDate(String day, String month, String year) {
        return isValidDay(day) && isValidMonth(month) && isValidYear(year);
    }

    private static boolean isValidDay(String day) {
        return day.matches("[0-3]{1}[0-9]{1}");
    }

    private static boolean isValidMonth(String month) {
        return month.matches("[0-1]{1}[1-9]{1}");
    }

    private static boolean isValidYear(String year) {
        if (!year.matches("\\d{4}")) {
            return false;
        }

        int yearInt = Integer.parseInt(year);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return yearInt <= currentYear && yearInt > 0;
    }

    public static boolean isValidDayOfMonth(String day, String month, String year) {
        try {
            if (day.isEmpty() || month.isEmpty() || year.isEmpty()) {
                return false;
            }

            int dayInt = Integer.parseInt(day);
            int monthInt = Integer.parseInt(month);
            int yearInt = Integer.parseInt(year);

            if (monthInt < 1 || monthInt > 12 || dayInt < 1) {
                return false;
            }

            if (monthInt == 2) {
                boolean isLeapYear = (yearInt % 4 == 0 && yearInt % 100 != 0) || (yearInt % 400 == 0);
                return (isLeapYear && dayInt <= 29) || (!isLeapYear && dayInt <= 28);
            } else {
                return (monthInt <= 7 && monthInt % 2 == 1 && dayInt <= 31) ||
                        (monthInt >= 8 && monthInt % 2 == 0 && dayInt <= 31) ||
                        (monthInt <= 7 && monthInt % 2 == 0 && monthInt != 2 && dayInt <= 30) ||
                        (monthInt >= 8 && monthInt % 2 == 1 && dayInt <= 30);
            }
        } catch (NumberFormatException e) {
            return false; 
        }
    }

    
    
    public static boolean isValidUpdateData(String updatedBookName, String updatedAuthorNames, String updatedPublication,
            double updatedPrice, int updatedQuantity) {
        return BookValidation.isValidBookName(updatedBookName) && 
               BookValidation.isValidAuthorNames(updatedAuthorNames) &&
               BookValidation.isValidPublication(updatedPublication) &&
               BookValidation.isValidPrice(String.valueOf(updatedPrice)) &&
               BookValidation.isValidTotalQuantity(String.valueOf(updatedQuantity));
    }

}
