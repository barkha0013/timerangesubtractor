package com.test.time;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SubtractorTest {
	
	@Test
	void getResultTimeRangeWithinTest(){
		Subtractor subtractor = new Subtractor();
		
		String[] a = {"09:00-10:00"};
		String[] b = {"09:00-09:30"};
		List<String> lsResult = Arrays.asList("09:30-10:00");
		
		List<TimeRange> lsA = getListObj(a, subtractor);
		List<TimeRange> lsB = getListObj(b, subtractor);
		List<String> lsC = new ArrayList<>();
		
		subtractor.getResult(lsA, lsB, lsC);
		
		assertIterableEquals(lsResult, lsC);
	}
	
	@Test
	void getResultSameTimeRangeTest(){
		Subtractor subtractor = new Subtractor();
		
		String[] a = {"09:00-10:00"};
		String[] b = {"09:00-10:00"};
		
		List<TimeRange> lsA = getListObj(a, subtractor);
		List<TimeRange> lsB = getListObj(b, subtractor);
		List<String> lsC = new ArrayList<>();
		
		subtractor.getResult(lsA, lsB, lsC);
		
		assertIterableEquals(new ArrayList<>(), lsC);
	}
	
	@Test
	void getResultTimeRangeNotWithinTest(){
		Subtractor subtractor = new Subtractor();
		
		String[] a = {"09:00-09:30"};
		String[] b = {"09:30-15:00"};
		List<String> lsResult = Arrays.asList("09:00-09:30");
		
		List<TimeRange> lsA = getListObj(a, subtractor);
		List<TimeRange> lsB = getListObj(b, subtractor);
		List<String> lsC = new ArrayList<>();
		
		subtractor.getResult(lsA, lsB, lsC);
		
		assertIterableEquals(lsResult, lsC);
	}
	
	@Test
	void getResultTimeRangeWithinAndSplitTest(){
		Subtractor subtractor = new Subtractor();
		
		String[] a = {"09:00-09:30", "10:00-10:30"};
		String[] b = {"09:15-10:15"};
		List<String> lsResult = Arrays.asList("09:00-09:15", "10:15-10:30");
		
		List<TimeRange> lsA = getListObj(a, subtractor);
		List<TimeRange> lsB = getListObj(b, subtractor);
		List<String> lsC = new ArrayList<>();
		
		subtractor.getResult(lsA, lsB, lsC);
		
		assertIterableEquals(lsResult, lsC);
	}
	
	@Test
	void getResultTimeRangeWithinAndSplitAndOutsideRangeTest(){
		Subtractor subtractor = new Subtractor();
		
		String[] a = {"9:00-11:00", "13:00-15:00"};
		String[] b = {"9:00-9:15", "10:00-10:15", "12:30-16:00"};
		List<String> lsResult = Arrays.asList("09:15-10:00", "10:15-11:00");
		
		List<TimeRange> lsA = getListObj(a, subtractor);
		List<TimeRange> lsB = getListObj(b, subtractor);
		List<String> lsC = new ArrayList<>();
		
		subtractor.getResult(lsA, lsB, lsC);
		
		assertIterableEquals(lsResult, lsC);
	}
	
	@Test
	void getResultTimeRangeWithinAndSplitUnsortedTest(){
		Subtractor subtractor = new Subtractor();
		
		String[] a = {"11:00-12:00", "9:00-10:00"};
		String[] b = {"9:00-9:30","10:30-11:15"};
		List<String> lsResult = Arrays.asList("09:30-10:00", "11:15-12:00");
		
		List<TimeRange> lsA = getListObj(a, subtractor);
		List<TimeRange> lsB = getListObj(b, subtractor);
		List<String> lsC = new ArrayList<>();
		
				
		subtractor.getResult(lsA, lsB, lsC);
		
		assertIterableEquals(lsResult, lsC);
	}
	
	private List<TimeRange> getListObj(String[] arr, Subtractor subtractor){
		List<TimeRange> ls = new ArrayList<>();
		for(String i : arr) {
			ls.add(subtractor.getTimeRangeObj(i));
			Collections.sort(ls);
		}
		
		return ls;
	}
}


