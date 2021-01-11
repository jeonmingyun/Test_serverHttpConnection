package model;

public class MemberBean {
	private String name;
	private String comment;
	
	public String getMemberid() {
		return name;
	}
	public void setMemberid(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return comment;
	}
	public void setPassword(String comment) {
		this.comment = comment;
	}
}
