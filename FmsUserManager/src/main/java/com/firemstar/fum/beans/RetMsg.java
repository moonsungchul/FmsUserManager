package com.firemstar.fum.beans;

public class RetMsg {
	
	private int success_no;
	private String success_msg;
	private int error_no;
	private String error_msg;
	
	public RetMsg() {
		
	}

	public RetMsg(int success_no, String success_msg, int error_no, String error_msg) {
		super();
		this.success_no = success_no;
		this.success_msg = success_msg;
		this.error_no = error_no;
		this.error_msg = error_msg;
	}

	public int getSuccess_no() {
		return success_no;
	}

	public void setSuccess_no(int success_no) {
		this.success_no = success_no;
	}

	public String getSuccess_msg() {
		return success_msg;
	}

	public void setSuccess_msg(String success_msg) {
		this.success_msg = success_msg;
	}

	public int getError_no() {
		return error_no;
	}

	public void setError_no(int error_no) {
		this.error_no = error_no;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	@Override
	public String toString() {
		return "RetMsg [success_no=" + success_no + ", success_msg=" + success_msg + ", error_no=" + error_no
				+ ", error_msg=" + error_msg + "]";
	}
	
	

}
