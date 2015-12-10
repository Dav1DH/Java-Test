package Dateparse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParseDate {

	public static void main(String[] args) {
		
		String dateStr = "25.04.1984";
		
		if(validate(dateStr)){
			System.out.println(dateStr);
		}
		else{
			System.out.println(process(dateStr));
		}

	}
	
	
	public static String process(String dateStr) {
		
		Date date = null;
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if(dateStr.length() == 2){
			try{
				int age = Integer.parseInt(dateStr);
				
				Calendar cal = Calendar.getInstance();
				int currentYear = cal.get(Calendar.YEAR);
				int birthYear = currentYear - age;
				
				cal.set(Calendar.YEAR, birthYear);
				cal.set(Calendar.MONTH, Calendar.JANUARY);
				cal.set(Calendar.DAY_OF_MONTH,1);
				
				date = cal.getTime();
				return outputFormat.format(date);
				
			}
			catch(NumberFormatException e){
				return "Invalid age";
			}
		}
		else if(dateStr.length() == 4){
			try{
				int birthYear = Integer.parseInt(dateStr);

				while(birthYear < 1905){
					birthYear += 100;
				}
				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, birthYear);
				cal.set(Calendar.MONTH, Calendar.JANUARY);
				cal.set(Calendar.DAY_OF_MONTH,1);
				
				date = cal.getTime();
				return outputFormat.format(date);
				
			}
			catch(NumberFormatException e){
				return "Invalid birth year";
			}
		}

		
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy");		
		try {
			date = inputFormat.parse(dateStr);
			return outputFormat.format(date);
		} catch (ParseException e) {
			
		}
		
		inputFormat = new SimpleDateFormat("dd MM yyyy");
		try {
			date = inputFormat.parse(dateStr);
			return outputFormat.format(date);
		} catch (ParseException e) {
			
		}
		
		inputFormat = new SimpleDateFormat("dd MMM yyyy");
		try {
			date = inputFormat.parse(dateStr);
			return outputFormat.format(date);
		} catch (ParseException e) {
			
		}
		
		return "Invalid date";
	}


	public static boolean validate(String dateStr){
		boolean valid;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = sdf.parse(dateStr);
			valid = true;
		} catch (ParseException e) {
			valid = false;
		}

		return valid;
		
	}

}
