package org.corodiak.booksys.etc;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class TempTestClass
{
	@Test
	public void test()
	{
		int tablenum = 10;
		Time startTime = Time.valueOf("17:00:00");
		Time endTime = Time.valueOf("22:00:00");
		int timegap = 30;
		
		Time time = Time.valueOf("19:00:00");
		LocalTime localTime = time.toLocalTime();
		localTime = localTime.plusMinutes(30);
		time =Time.valueOf(localTime);
		System.out.println(time.toString());
		
		LocalTime time1 = startTime.toLocalTime();
		LocalTime time2 = endTime.toLocalTime();
		
		int timeset = (int)(Duration.between(time1, time2).toMinutes() / timegap);
		int [][] bookginArray = new int[tablenum][timeset];
		System.out.println(bookginArray[0].length);
		
	}
	
	@Test
	public void test2()
	{
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int result = map.get(3) == null ? 0 : map.get(3);
		System.out.println(result);
	}
}
