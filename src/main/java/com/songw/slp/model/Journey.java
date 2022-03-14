package com.songw.slp.model;

import java.util.List;

public class Journey {

	
	private String journeyNumber;
	private String recordLocator;
	private List<Segment> segmentList;

	public String getJourneyNumber() {
		return journeyNumber;
	}
	

	public String getRecordLocator() {
		return recordLocator;
	}

	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}

	public void setJourneyNumber(String journeyNumber) {
		this.journeyNumber = journeyNumber;
	}

	public List<Segment> getSegmentList() {
		return segmentList;
	}

	public void setSegmentList(List<Segment> segmentList) {
		this.segmentList = segmentList;
	}
	
}
