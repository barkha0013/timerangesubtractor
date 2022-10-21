package com.test.time;

public class TimeRange implements Comparable<TimeRange>{
	long start;
	long end;
	
	public TimeRange(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	
	@Override
	public int compareTo(TimeRange o) {
		if(this.start < o.start)
			return -1;
		else if(this.start > o.start)
			return 1;
		else {
			if(this.end < o.end)
				return -1;
			else if(this.end > o.end)
				return 1;
			else
				return 0;
		}
	}
	
	@Override
	public String toString() {
		return this.start + ":" + this.end;
	}

}
