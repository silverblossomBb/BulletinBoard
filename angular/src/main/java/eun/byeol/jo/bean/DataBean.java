package eun.byeol.jo.bean;

public class DataBean {
	
	private int no;
	private String id;
	private String txt;
	
	public DataBean(int no, String id, String txt) {
		this.no = no;
		this.id = id;
		this.txt = txt;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	public int getNo() {
		return no;
	}
	
	public String getId() {
		return id;
	}
	
	public String getTxt() {
		return txt;
	}
	
}
