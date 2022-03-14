package com.songw.slp.model;

import java.util.HashMap;

public class Data {

	private findAllTutorials data;
	
	public HashMap<String, Tutorial> pnrList;
	
	public findAllTutorials getData() {
		return data;
	}

	public void setData(findAllTutorials data) {
		this.data = data;
	}

	public HashMap<String, Tutorial> getPnrList() {
		return pnrList;
	}

	public void setPnrList(HashMap<String, Tutorial> pnrList) {
		this.pnrList = pnrList;
	}

}
