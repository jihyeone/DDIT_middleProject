package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.vo.ClassClassVO;

public interface IClassService {
	/**
	 * 전체 클래스 리스트 불러오기
	 * @return
	 */
	public List<ClassClassVO> getAllclass();
	
	public ClassClassVO SelectJoinClass(String classNo);
}
