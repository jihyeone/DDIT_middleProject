package kr.or.ddit.middle.vo;

public class ProfessorVO {
	                         
	private String pro_id    ;
	private String pro_nm    ;
	private String pro_reg   ;
	private String pro_pw    ;
	private String pro_addr  ;
	private String pro_mail  ;
	private String pro_tel   ;
	private String sub_cd    ;
	private String pro_poto  ;  //bfile??
	private String pro_zip;
	
	private int pro_NewCnsCnt ;
	
	public int getPro_NewCnsCnt() {
		return pro_NewCnsCnt;
	}
	public void setPro_NewCnsCnt(int pro_NewCnsCnt) {
		this.pro_NewCnsCnt = pro_NewCnsCnt;
	}
	
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_nm() {
		return pro_nm;
	}
	public void setPro_nm(String pro_nm) {
		this.pro_nm = pro_nm;
	}
	public String getPro_reg() {
		return pro_reg;
	}
	public void setPro_reg(String pro_reg) {
		this.pro_reg = pro_reg;
	}
	public String getPro_pw() {
		return pro_pw;
	}
	public void setPro_pw(String pro_pw) {
		this.pro_pw = pro_pw;
	}
	public String getPro_addr() {
		return pro_addr;
	}
	public void setPro_addr(String pro_addr) {
		this.pro_addr = pro_addr;
	}
	public String getPro_mail() {
		return pro_mail;
	}
	public void setPro_mail(String pro_mail) {
		this.pro_mail = pro_mail;
	}
	public String getPro_tel() {
		return pro_tel;
	}
	public void setPro_tel(String pro_tel) {
		this.pro_tel = pro_tel;
	}
	public String getSub_cd() {
		return sub_cd;
	}
	public void setSub_cd(String sub_cd) {
		this.sub_cd = sub_cd;
	}

	
	
	public String getPro_poto() {
		return pro_poto;
	}
	public void setPro_poto(String pro_poto) {
		 this.pro_poto = pro_poto;
	}
	public String getPro_zip() {
		return pro_zip;
	}
	public void setPro_zip(String pro_zip) {
		this.pro_zip = pro_zip;
	}
	
	
}	
