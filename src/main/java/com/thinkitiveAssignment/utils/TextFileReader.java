package com.thinkitiveAssignment.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * TextFileReader program is used to read content from text file
 */


public class TextFileReader {
	
	
	
	public String readFile(String inputFileName) throws IOException//This method take file name as argument and return read content as String value
	{
		String fileData = "";
		String line;
		File inputFile = new File(System.getProperty("user.dir")+"//src//main//java//com//thinkitiveAssignment//inputFile//"+inputFileName);
		if(inputFile.length()!=0 && inputFile.exists())
		{
		InputStream ips = new FileInputStream(inputFile);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		
		while((line = br.readLine())!=null)
		{
			fileData = fileData.concat(line+'|');			
		}
		System.out.println("Data reading completed");
		br.close();
		}
		else
			System.out.println("File does not exist or not have any data");
		
		return fileData;
	}
	
	public ArrayList<String> readFileLineByLine(String inputFileName) throws IOException //This method take file name as argument  and return ArrayList of String value of each line
	{
		ArrayList<String> fileData = new ArrayList<String>();
		String line;
		File inputFile = new File(System.getProperty("user.dir")+"//src//main//java//com//thinkitiveAssignment//inputFile//"+inputFileName);
		if(inputFile.length()!=0 && inputFile.exists())
		{
		InputStream ips = new FileInputStream(inputFile);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		
		while((line = br.readLine())!=null)
		{
			fileData.add(line);
		}
		System.out.println("Data reading completed");
		br.close();
		}
		else
			System.out.println("File does not exist or not have any data");
		
		return fileData;
	}

}
