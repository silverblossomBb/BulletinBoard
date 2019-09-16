package spring.project.summer.vo;

public class LoginVO {
	
	int no;
	String id;
	String name;
	String imageUrl;
	String timeLog;
	
	public LoginVO(String id, String name, String imageUrl) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
}
