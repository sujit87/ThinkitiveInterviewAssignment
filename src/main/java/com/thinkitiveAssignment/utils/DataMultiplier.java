package com.thinkitiveAssignment.utils;

public class DataMultiplier {
	
	public String multiplyData(String lineData, int multiplier)
	{
		String[] numbersList;
		String[] result;
		StringBuilder resultString;
		numbersList = lineData.split("\\|");
		result = new String[numbersList.length];
		resultString = new StringBuilder();
		for(int i=0;i<numbersList.length;i++)
		{
			result[i] = String.valueOf((Integer.valueOf(numbersList[i])*multiplier));
			if(i != numbersList.length-1)
				resultString.append(result[i]+" ");
			else
				resultString.append(result[i]);
		}
		
		return resultString.toString();
	}
	
	
	public String[][] multiplyData(String[][] fileData, int multiplier)
	{
		String[][] multipliedData = new String[fileData.length][fileData[0].length];
		for(int i=0;i<fileData.length;i++)
		{
			for(int j=0;j<fileData[i].length;j++)
			{
				int number = Integer.valueOf(fileData[i][j]);
				multipliedData[i][j] = String.valueOf(number*multiplier);
				System.out.println(multipliedData[i][j]);
			}
		}
		
		return multipliedData;
	}

}
