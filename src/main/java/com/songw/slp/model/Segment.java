package com.songw.slp.model;

public class Segment {

	private String segmentid;
	private String recordLocator;
	private String origin;
	private String arrival;

	public String getSegmentid() {
		return segmentid;
	}
	public void setSegmentid(String segmentid) {
		this.segmentid = segmentid;
	}
	public String getRecordLocator() {
		return recordLocator;
	}
	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	
}
