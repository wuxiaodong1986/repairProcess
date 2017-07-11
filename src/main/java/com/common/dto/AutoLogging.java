package com.common.dto;

public class AutoLogging 
{
	private int oid;
	
	private String username;
	
	private String name;
	
	private String logname;
	
	private String url;
	
	private String mid;
	
	private String parameters;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "AutoLogging [oid=" + oid + ", username="
				+ username + ", name=" + name + ", logname=" + logname
				+ ", url=" + url + ", mid=" + mid + ", parameters="
				+ parameters + "]";
	}
	
}
