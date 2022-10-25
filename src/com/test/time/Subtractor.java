package com.test.time;

import java.util.List;

/*
 * ASSUMPTIONS:
 * Time ranges are passed in valid format HH:MI-HH:MI
 * End Time will always be greater than start time in a time range
 * Time Ranges within the array is non-overlapping
 */

public class Subtractor {
	
	public void getResult(List<TimeRange> lsA, List<TimeRange> lsB, List<String> lsC) {
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
	
	public TimeRange getTimeRangeObj(String arrElem) {
		String[] tStartEnd = arrElem.split("-");
		return new TimeRange(TimeUtil.getEpochTime(tStartEnd[0]), TimeUtil.getEpochTime(tStartEnd[1]));
	}
	
}


