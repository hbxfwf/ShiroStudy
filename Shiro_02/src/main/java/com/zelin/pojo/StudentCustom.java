package com.zelin.pojo;

public class StudentCustom  {
	
	private Student student;
	private MyPage mypage;

	
	public StudentCustom() {
		super();
	}

	public StudentCustom(Student student, MyPage mypage) {
		super();
		this.student = student;
		this.mypage = mypage;
	}

	public MyPage getMypage() {
		return mypage;
	}

	public void setMypage(MyPage mypage) {
		this.mypage = mypage;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}