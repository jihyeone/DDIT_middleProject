package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.vo.Comm_CommentVO;

public interface ICB_ComentService {
	
	/**
	 * 커뮤니티 댓글등록
	 * @return Comm_CommentVO
	 */
	public int insertReply(Comm_CommentVO vo);
	
	/**
	 * 커뮤니티 댓글 수정
	 * @param vo
	 * @return
	 */
	public int modifyReply(Comm_CommentVO vo);
	
	
	/**
	 * 커뮤니티 댓글삭제
	 * @param renum
	 * @return
	 */
	public int deleteReply(String renum);
	
	/**
	 * 커뮤니티 댓글 리스트
	 * @param cNo
	 * @return
	 */
	public List<Comm_CommentVO> selectReply(String cNo);
	
	
}
