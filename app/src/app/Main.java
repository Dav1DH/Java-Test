/**
 * 
 */
package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author David
 *
 */
public class Main {
	
	private static final String fileName = "C:/Users/David/file.txt";
    private static final String fileStrings = "C:/Users/David/Desktop/file_strings.txt";
    private static final String fileNumbers = "C:/Users/David/Desktop/file_numbers.txt";  
    

	/**
	 * @param args
	 * @throws FileNotFoundException  
	 */
	public static void main(String[] args) throws FileNotFoundException  {
		// TODO Auto-generated method stub
		File file = new File(fileName);
		
		final HashSet<Integer> numbers = new HashSet<Integer>();
		final HashSet<String> strings = new HashSet<String>();
		
		BufferedReader bufReader = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		String text;
		try {
			
			while ((text = bufReader.readLine()) != null) {
				final String finalText = text;
				Runnable myRunnable = new Runnable(){
			        public void run(){
			        	try {
							BufferedReader bufReader = new BufferedReader(new FileReader(finalText));
							String line;
							while ((line = bufReader.readLine()) != null) {
								try{
									Integer num = new Integer(line);
									if(!numbers.contains(num)){
										numbers.add(num);
										PrintWriter wr = new PrintWriter(new FileWriter(fileNumbers, true));
										wr.println(num);
										wr.flush();
										wr.close();
									}
								}catch(NumberFormatException ex){
									if(!strings.contains(line)){
										strings.add(line);
										PrintWriter wr = new PrintWriter(new FileWriter(fileStrings, true));
										wr.println(line);
										wr.flush();
										wr.close();
									}
								}
							}
							bufReader.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
				};
				new Thread(myRunnable).start();
			}
			
			bufReader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

