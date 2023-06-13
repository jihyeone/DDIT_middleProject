package kr.or.ddit.middle.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.AttendanceVO;

public interface IAttendanceDao {
	
	/**
	 * 모든 출석 불러오기
	 * @return
	 */
	public List<AttendanceVO> getAllAttList();
	
	
	
}
