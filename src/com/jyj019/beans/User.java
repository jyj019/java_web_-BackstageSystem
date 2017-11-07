package com.jyj019.beans;

/**
 * 
 * @author jyj019
 *
 * @param <T>
 */
public class User {
	private String uid;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String identity;
	private String school;
	private String telephone;
	private String email;
	private int state=0;
	private String code;
	private String data1;
	private String data2;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public User(String uid, String username, String password, String name, String sex, String identity, String school,
			String telephone, String email, int state, String code, String data1, String data2) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.identity = identity;
		this.school = school;
		this.telephone = telephone;
		this.email = email;
		this.state = state;
		this.code = code;
		this.data1 = data1;
		this.data2 = data2;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name + ", sex="
				+ sex + ", identity=" + identity + ", school=" + school + ", telephone=" + telephone + ", email="
				+ email + ", state=" + state + ", code=" + code + "]";
	}
}
