package io.github.hastar.VO;

public class UploadVO {
	
	int no;
	int noticeNo;
	String id;
	String name;
	String originName;
	String uuid;
	String timeLog;
	
	public UploadVO(int noticeNo, String id, String name, String originName, String uuid) {
		this.noticeNo = noticeNo;
		this.id = id;
		this.name = name;
		this.originName = originName;
		this.uuid = uuid;
	}
	
	public UploadVO() {
		
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
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

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originalName) {
		this.originName = originalName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTimeLog() {
		return timeLog;
	}

	public void setTimeLog(String timeLog) {
		this.timeLog = timeLog;
	}

	@Override
	public String toString() {
		return "UploadVO [no=" + no + ", noticeNo=" + noticeNo + ", id=" + id + ", name=" + name + ", originName="
				+ originName + ", uuid=" + uuid + ", timeLog=" + timeLog + "]";
	}
	
}
