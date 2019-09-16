package io.github.hastar.VO;

public class LoginVO {
	
	int no;
	String id;
	String name;
	String timeLog;
	String image;
	
	public LoginVO(String id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTimeLog() {
		return timeLog;
	}
	
	public void setTimeLog(String timeLog) {
		this.timeLog = timeLog;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "LoginVO [no=" + no + ", id=" + id + ", name=" + name + ", timeLog=" + timeLog + ", image=" + image
				+ "]";
	}
	
}
