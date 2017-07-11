package com.college.dto;

public class ProcessConfig
{
	private int id;
	
	private String code;
	
	private int roleid;
	
	private int list;
	
	private String implbean;
	
	private String dscb;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDscb() {
		return dscb;
	}

	public void setDscb(String dscb) {
		this.dscb = dscb;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public int getList() {
		return list;
	}

	public void setList(int list) {
		this.list = list;
	}

	public String getImplbean() {
		return implbean;
	}

	public void setImplbean(String implbean) {
		this.implbean = implbean;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessConfig other = (ProcessConfig) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
