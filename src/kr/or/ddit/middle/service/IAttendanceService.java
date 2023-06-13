package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.vo.AttendanceVO;

public interface IAttendanceService {
	/**
	 * 모든 출석 불러오기
	 * @return
	 */
	public List<AttendanceVO> getAllAttList();
}
