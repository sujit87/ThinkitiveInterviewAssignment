package com.thinkitiveAssignment.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * TextFileWriter program used to create and write text file
 */


public class TextFileWriter {
	
	File file;
	FileWriter fw;
	public TextFileWriter(String resultFileName) throws IOException
	{
		 file = new File(System.getProperty("user.dir")+"//src//main//java//com//thinkitiveAssignment//ResultFile//"+resultFileName);
		if(file.exists())//checking if file exist
		{
			file.delete();//Deleting existing file
		}
		
		fw = new FileWriter(file,true);
	}
	
	public void writeFile(String result) throws IOException//This method take single String argument to write it in text file
	{
		
		fw.write(result);
		fw.flush();//flushing content in text file
		
	}
	
	public void closeFileWriting() throws IOException
	{
		System.out.println("File Created");
		fw.close();//closing fileWriter
	}
	
	public void writeFile(String[][] fileData) throws IOException
	{
		for(int i=0;i<fileData.length;i++ )
		{
			for(int j=0; j<fileData[i].length;j++)
			{
				fw.write(fileData[i][j]+"|");
			}
			if(i<fileData.length-1)
			fw.write("\n");
		}
		fw.flush();
	}
	

}
