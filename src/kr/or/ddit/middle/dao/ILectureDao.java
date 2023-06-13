package kr.or.ddit.middle.dao;

import java.util.List;

import kr.or.ddit.middle.vo.LectureVO;

public interface ILectureDao {
	
	/**
	 * 모든 강의리스트 가져오기
	 * @return
	 */
	public List<LectureVO> getAllLectList();
}
