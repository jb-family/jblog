package com.javaex.vo;

public class BlogVo {
	
	private int userNo;
	private String userName;
	private String password;
	private String joinDate;
	private String id;
	private String blogTitle;
	private String logoFile;
	
	public BlogVo() {
	}

	public BlogVo(int userNo, String userName, String password, String joinDate, String id, String blogTitle,
			String logoFile) {
		this.userNo = userNo;
		this.userName = userName;
		this.password = password;
		this.joinDate = joinDate;
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	@Override
	public String toString() {
		return "BlogVo [userNo=" + userNo + ", userName=" + userName + ", password=" + password + ", joinDate="
				+ joinDate + ", id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + "]";
	}

}
