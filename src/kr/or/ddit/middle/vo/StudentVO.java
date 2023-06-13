package kr.or.ddit.middle.vo;

public class StudentVO {  
	
	private String stu_id     ;
	private String stu_nm     ;
	private String stu_reg    ;
	private String stu_pw     ;
//	private String stu_as     ;
	private String stu_addr   ;
	private String stu_tel    ;
	private String stu_mail   ;
	private String stu_fin    ;
	private String class_no   ;
	private String stu_poto  ; //Bfile??
	private String stu_drop   ;
	
	private int stu_NewmsgCnt ;
	
	
	public int getStu_NewmsgCnt() {
		return stu_NewmsgCnt;
	}
	public void setStu_NewmsgCnt(int stu_NewmsgCnt) {
		this.stu_NewmsgCnt = stu_NewmsgCnt;
	}
	public String getStu_poto() {
		return stu_poto;
	}
	public void setStu_poto(String stu_poto) {
		this.stu_poto = stu_poto;
	}

	private String stu_zip    ;
	
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_nm() {
		return stu_nm;
	}
	public void setStu_nm(String stu_nm) {
		this.stu_nm = stu_nm;
	}
	public String getStu_reg() {
		return stu_reg;
	}
	public void setStu_reg(String stu_reg) {
		this.stu_reg = stu_reg;
	}
	public String getStu_pw() {
		return stu_pw;
	}
	public void setStu_pw(String stu_pw) {
		this.stu_pw = stu_pw;
	}
	
//	
//	
//	public String getStu_as() {
//		return stu_as;
//	}
//	public void setStu_as(String stu_as) {
//		this.stu_as = stu_as;
//	}
//	
	
	
	public String getStu_addr() {
		return stu_addr;
	}
	public void setStu_addr(String stu_addr) {
		this.stu_addr = stu_addr;
	}
	public String getStu_tel() {
		return stu_tel;
	}
	public void setStu_tel(String stu_tel) {
		this.stu_tel = stu_tel;
	}
	public String getStu_mail() {
		return stu_mail;
	}
	public void setStu_mail(String stu_mail) {
		this.stu_mail = stu_mail;
	}
	public String getStu_fin() {
		return stu_fin;
	}
	public void setStu_fin(String stu_fin) {
		this.stu_fin = stu_fin;
	}

	public String getClass_no() {
		return class_no;
	}
	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}
	
	
	
	
	public String getStu_zip() {
		return stu_zip;
	}
	public void setStu_zip(String stu_zip) {
		this.stu_zip = stu_zip;
	}
	public String getStu_drop() {
		return stu_drop;
	}
	public void setStu_drop(String stu_drop) {
		this.stu_drop = stu_drop;
	}
	
	
}
