package com.thinkitiveAssignment.Assignment1;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.thinkitiveAssignment.utils.DataMultiplier;
import com.thinkitiveAssignment.utils.TextFileReader;
import com.thinkitiveAssignment.utils.TextFileWriter;

/**
* The Assignment1 program implements an application that
* takes input from text file and multiply fetched values
* by given value and write output result in text file
*
*/

public class Assignment1 {
	
	int multiplier = 2;//multiplier used to multiply fetched element
	
	/*testMultiplierMethodSingleRowResult method is used to
	 * print single row result in text file
	 */
	@Test
	public void testMultiplierMethodSingleRowResult() throws IOException
	{

		String resultString;
		TextFileReader read = new TextFileReader();//To read data from text file
		TextFileWriter write = new TextFileWriter("result.txt");//To write result data in text file
		DataMultiplier multiply = new DataMultiplier();//To multiply each element with multiplier
		String fileData = read.readFile("sample.txt");//reading input file
			if(fileData!=null)
			{
				System.out.println(fileData);
				resultString = multiply.multiplyData(fileData, multiplier);//Multiplying passed data with multiplier   
				System.out.println("Result after Muliplication is: "+resultString);			
				write.writeFile(resultString);//writing data to text file
			
			}
	}
	
	/*testMultiplierMethodMultipleRowResult method is used to
	 * print result in multiple rows in text file
	 */
	@Test
	public void testMultiplierMethodMultipleRowResult() throws IOException
	{
		String resultString;
		TextFileReader read = new TextFileReader();//To read data from text file
		TextFileWriter write = new TextFileWriter("result2.txt");//To write result data in text file
		DataMultiplier multiply = new DataMultiplier();
		ArrayList<String> fileData = read.readFileLineByLine("sample.txt");//Reading input file data line by line and storing it in ArrayList
			if(!fileData.isEmpty())
			{
			for(String lineData:fileData)//iterating ArrayList to get line data
			{
				System.out.println(lineData);
				resultString = multiply.multiplyData(lineData, multiplier);//multiplying line data
				System.out.println("Result after Muliplication is: "+resultString);			
				write.writeFile(resultString+"\n");//Writing result line by line
			}
			
			write.closeFileWriting();
			
			}
	}

}
