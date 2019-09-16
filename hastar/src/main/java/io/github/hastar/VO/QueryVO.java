package io.github.hastar.VO;

public class QueryVO {
	
	String select;
	String insert;
	String delete;
	String update;
	
	public QueryVO(String select, String insert, String delete, String update) {
		this.select = select;
		this.insert = insert;
		this.delete = delete;
		this.update = update;
	}
	
	public String getSelect() {
		return select;
	}
	
	public String getInsert() {
		return insert;
	}
	
	public String getDelete() {
		return delete;
	}
	
	public String getUpdate() {
		return update;
	}
	
}
