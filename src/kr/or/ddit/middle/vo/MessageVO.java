package kr.or.ddit.middle.vo;

public class MessageVO {
	
	private String msg_no     ;
	private String send_id    ;
	private String reciv_id   ;
	private String msg_ttl    ;
	private String msg_cn     ;
	private String readyn     ;
	private String send_date  ;
	
	//---- JOIN 컬럼 getter
	private String send_nm;
	private String reciv_nm;
	
	
	public String getSend_nm() {
		return send_nm;
	}
	public String getReciv_nm() {
		return reciv_nm;
	}
	public String getMsg_no() {
		return msg_no;
	}
	public void setMsg_no(String msg_no) {
		this.msg_no = msg_no;
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public String getReciv_id() {
		return reciv_id;
	}
	public void setReciv_id(String reciv_id) {
		this.reciv_id = reciv_id;
	}
	public String getMsg_ttl() {
		return msg_ttl;
	}
	public void setMsg_ttl(String msg_ttl) {
		this.msg_ttl = msg_ttl;
	}
	public String getMsg_cn() {
		return msg_cn;
	}
	public void setMsg_cn(String msg_cn) {
		this.msg_cn = msg_cn;
	}
	public String getReadyn() {
		return readyn;
	}
	public void setReadyn(String readyn) {
		this.readyn = readyn;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	
	
}
