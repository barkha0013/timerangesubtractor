package com.test.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * ASSUMPTIONS:
 * Time ranges are passed in valid format HH:MI-HH:MI
 * End Time will always be greater than start time in a time range
 * Time Ranges within the array is non-overlapping
 */

public class Subtractor {
	
	public static void main(String[] args) {
		String[] a = {"9:00-10:00"};	String[] b = {"9:00-9:30"};		//(9:30-10:00)
		//String[] a = {"9:00-10:00"};	String[] b = {"9:00-10:00"};	//()
		//String[] a = {"9:00-9:30"};	String[] b = {"9:30-15:00"};	//(9:00-9:30)
		//String[] a = {"9:00-9:30", "10:00-10:30"};	String[] b = {"9:15-10:15"};	//(9:00-9:15, 10:15-10:30)
		//String[] a = {"9:00-11:00", "13:00-15:00"}; 	String[] b = {"9:00-9:15", "10:00-10:15", "12:30-16:00"};	//(9:15-10:00, 10:15-11:00)
		//String[] a = {"11:00-12:00", "9:00-10:00"};	String[] b = {"9:00-9:30","10:30-11:15"};		//(9:30-10:00, 11:15-12:00)

		List<TimeRange> lsA = new ArrayList<>();
		List<TimeRange> lsB = new ArrayList<>();
		
		for(String i : a) {
			lsA.add(getTimeRangeObj(i));
			Collections.sort(lsA);
		}
		
		for(String i : b) {
			lsB.add(getTimeRangeObj(i));
			Collections.sort(lsB);
		}
		
		List<String> lsC = new ArrayList<>();
		getResult(lsA, lsB, lsC);
		if(!lsC.isEmpty())
			System.out.println(Arrays.toString(lsC.toArray()));
		
	}
	
	private static void getResult(List<TimeRange> lsA, List<TimeRange> lsB, List<String> lsC) {
		int idx_b = 0;
		
	    for(TimeRange trA : lsA) {
	    	while(true){
	            if(idx_b >= lsB.size()) {
	                // There are no more time ranges in the B list, so just add A interval as it is and move on to next A interval
	                lsC.add(TimeUtil.getTimeFromEpoch(trA.getStart()) + "-" + TimeUtil.getTimeFromEpoch(trA.getEnd()));
	                break;
	            }
	            else{
	                TimeRange trB = lsB.get(idx_b);
	                
	                if( trB.getEnd() <= trA.getStart())
	                    // This B interval falls before the A interval, therefore, move to the next B interval.
	                {    
	                	idx_b += 1;
	                    continue;
	                }
	                else if (trB.getStart() >= trA.getEnd())
	                    // This B interval falls after the A interval, therefore add A interval as it is and move on to next A interval.
	                {
	                	lsC.add(TimeUtil.getTimeFromEpoch(trA.getStart()) + "-" + TimeUtil.getTimeFromEpoch(trA.getEnd()));
	                	break;
	                }
	                else{
	                    // This is the where interval A and B overlaps
	                    
	                    if(trA.getStart() < trB.getStart())
	                        // fraction of interval A starts before interval B (from aStart to bStart) is added. 
	                    {
	                    	lsC.add(TimeUtil.getTimeFromEpoch(trA.getStart()) + "-" + TimeUtil.getTimeFromEpoch(trB.getStart()));
	                    }
	                    if( trB.getEnd() < trA.getEnd())
	                        // Reassign A start interval after subtracting B interval. 
	                        // But we continue to check, if the next B interval overlaps with it.
	                    {
	                    	trA.setStart(trB.getEnd());
	                        idx_b += 1;
	                        continue;
	                    }
	                    else {
	                    	// Move to next A interval
	                        break;
	                    }
	                }
	            }
	        }
	    }
	}
	
	private static TimeRange getTimeRangeObj(String arrElem) {
		String[] tStartEnd = arrElem.split("-");
		return new TimeRange(TimeUtil.getEpochTime(tStartEnd[0]), TimeUtil.getEpochTime(tStartEnd[1]));
	}
	
}


