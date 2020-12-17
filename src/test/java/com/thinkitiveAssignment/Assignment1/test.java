package com.thinkitiveAssignment.Assignment1;

import org.testng.annotations.Test;

public class test {

	@Test
	public void testArray()
	{
		int []array = {1,0,0,0,1,1,0,0,0,0,1,0,0};
		int flag=0;
		int consecutiveZeroCount=0;
		for(int i=0;i<array.length;i++)
		{

			if(i <= array.length-2 && array[i]==0 && array[i+1]==0)
			{
				flag++;
			} 
			else
			{
				if(consecutiveZeroCount<flag+1)
				{
					consecutiveZeroCount = flag+1;
				}
				flag=0;
			}

		}

		System.out.println(consecutiveZeroCount);

	}

}
