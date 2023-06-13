package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.vo.CB_AttachVO;

public interface ICB_AttachService {
	
	/**
	 * 커뮤니티파일 리스트 가져오기
	 * @return
	 */
	public List<CB_AttachVO> getCFileList();
}
