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

}
