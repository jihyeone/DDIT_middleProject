package kr.or.ddit.middle.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.vo.MessageVO;

public interface IMessageDao {
	
	/**
	 * 모든 쪽지 출력
	 * @return
	 */
	public List<MessageVO> getAllMessage();
	
	
	/**
	 * 쪽지 보내기
	 * @param msgvo
	 * @return
	 */
	public int insertMessage(MessageVO msgvo);
	
	
	/**
	 * 읽음 확인
	 * @param msgvo
	 * @return
	 */
	public int checkMessage(int no);
	
	
	/**
	 * 번호로 쪽지 확인
	 * @param no
	 * @return
	 */
	public MessageVO selectMessageNo(int no);
	
	
	/**
	 * 아이디로 쪽지 확인
	 * @param id
	 * @return
	 */

	public MessageVO selectMessageId(String id);
	
	
	/**
	 * 쪽지 삭제
	 * @param msgvo
	 * @return
	 */
	public int deleteMessage(MessageVO msgvo);
	
	
//	/**
//	 * 받은 쪽지 상세 보기
//	 * @param id
//	 * @return
//	*/
//	public MessageVO receiveMessage(String id);
//	
//	
//	/**
//	 * 보낸 쪽지 상세 보기
//	 * @param id
//	 * @return
//	 */
//	public MessageVO sendMessage(String id);
	
	
	/**
	 * 받은 쪽지 목록
	 * @param id
	 * @return
	 */
	public List<MessageVO> receiveMessageList(String id);
	
	
	/**
	 * 보낸 쪽지 목록
	 * @param id
	 * @return
	 */
	public List<MessageVO> sendMessageList(String id);
	
	/**
	 * 보낸 쪽지목록중 제일 최신
	 * @param id
	 * @return
	 */
	public MessageVO selectRecntMgs(String id);
	
	/**
	 * 읽지 않은 메세지 갯수
	 * @param id
	 * @return
	 */
	public int countNewMsg(String id);
	
	/**
	 * 쪽지 받는 사람 검색
	 * @param map
	 * @return
	 */
	public List<MessageVO> selectByReceive(Map<String, Object> map);
	
	
	/**
	 * 쪽지 목록 페이징
	 * @param map
	 * @return
	 */
	public List<MessageVO> selectByPage(Map<String, Object> map);
}
