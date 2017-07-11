package com.college.dto;

public class Operator 
{
	private Integer id = 0;
	
	private String username;
	
	private String name;
	
	private String password;
	
	private Integer status = 0;
	
	private Integer opr_type = 0;
	
	private Integer sex = 0;
	
	private String phone;
	
	private String weixin;
	
	private String staff_org_id;
	
	private String staff_position;
	
	private Integer staff_workaddress;
	
	private Integer staff_room;
	
	private String stu_grade;
	
	private String stu_major_id;
	
	private String stu_dorm_id;
	
	private String stu_room;

	public String getStaff_position() {
		return staff_position;
	}

	public void setStaff_position(String staff_position) {
		this.staff_position = staff_position;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOpr_type() {
		return opr_type;
	}

	public void setOpr_type(Integer opr_type) {
		this.opr_type = opr_type;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getStaff_org_id() {
		return staff_org_id;
	}

	public void setStaff_org_id(String staff_org_id) {
		this.staff_org_id = staff_org_id;
	}

	public Integer getStaff_workaddress() {
		return staff_workaddress;
	}

	public void setStaff_workaddress(Integer staff_workaddress) {
		this.staff_workaddress = staff_workaddress;
	}

	public Integer getStaff_room() {
		return staff_room;
	}

	public void setStaff_room(Integer staff_room) {
		this.staff_room = staff_room;
	}

	public String getStu_grade() {
		return stu_grade;
	}

	public void setStu_grade(String stu_grade) {
		this.stu_grade = stu_grade;
	}

	public String getStu_major_id() {
		return stu_major_id;
	}

	public void setStu_major_id(String stu_major_id) {
		this.stu_major_id = stu_major_id;
	}

	public String getStu_dorm_id() {
		return stu_dorm_id;
	}

	public void setStu_dorm_id(String stu_dorm_id) {
		this.stu_dorm_id = stu_dorm_id;
	}

	public String getStu_room() {
		return stu_room;
	}

	public void setStu_room(String stu_room) {
		this.stu_room = stu_room;
	}
}
