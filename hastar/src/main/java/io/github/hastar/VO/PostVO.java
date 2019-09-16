package io.github.hastar.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PostVO {
	private String no;
	private String title;
	private String content;
	private String name;
	private String timeLog;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
}
