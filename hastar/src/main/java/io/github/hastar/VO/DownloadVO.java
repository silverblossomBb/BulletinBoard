package io.github.hastar.VO;

public class DownloadVO {
	
	String id;
	String uuid;
	
	public DownloadVO(String id, String uuid) {
		this.id = id;
		this.uuid = uuid;
	}

	public String getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}
	
}
