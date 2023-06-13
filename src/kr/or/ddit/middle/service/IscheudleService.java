package kr.or.ddit.middle.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.vo.ScheduleVO;

public interface IscheudleService {
	
	
	
	public List<ScheduleVO> getAllScheudle();
	
	public int insertschedle(ScheduleVO vo);
	
	
//	public Map<String, ScheudleVO> calenList();
	
	
	/**
	 * 모든 스케줄 가져오기
	 * @return
	 */
	public List<Map<String, ScheduleVO>> calenAllList(String classNo);
	
	
	/**
	 * 과목 리스트 불러오기
	 * @return
	 */
	public List<ScheduleVO> selectSubject();
	
	/**
	 * 새로운 스케줄 등록
	 * @return
	 */
	public int insertsAddchedule(ScheduleVO vo);
	
	/**
	 * 스케줄 삭제
	 * @param scheduleNo
	 * @return 성공 1 / 실패 0
	 */
	public int deleteSchedule(String scheduleNo);
}
