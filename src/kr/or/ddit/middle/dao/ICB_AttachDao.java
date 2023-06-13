package kr.or.ddit.middle.dao;

import java.util.List;

import kr.or.ddit.middle.vo.CB_AttachVO;

public interface ICB_AttachDao {
	
	/**
	 * 커뮤니티파일 리스트 가져오기
	 * @return
	 */
	public List<CB_AttachVO> getCFileList();
}
