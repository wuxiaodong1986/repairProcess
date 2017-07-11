package com.college.dto;

import com.common.utils.tree.Tree;

public class Address extends Tree
{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String dscb;

	public Integer getId() {
		return (Integer)id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getP_id() {
		return (Integer)p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDscb() {
		return dscb;
	}

	public void setDscb(String dscb) {
		this.dscb = dscb;
	}
}
