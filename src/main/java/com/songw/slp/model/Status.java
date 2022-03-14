package com.songw.slp.model;

public enum Status {

	
	Confirmed(0, "Confirmed"),
    Canceled(1, "Canceled"),
    Hold(2, "Hold");
	
	int status;
    String code;
    
    Status(int statusCode, String code) {
    	this.status = statusCode;
        this.code = code;
	}
    
}
