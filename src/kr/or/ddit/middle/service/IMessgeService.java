package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.vo.MessageVO;

public interface IMessgeService {
	
	/**
	 * 모든 메세지 출력
	 * @return
	 */
	public List<MessageVO> getAllMessage();
}
