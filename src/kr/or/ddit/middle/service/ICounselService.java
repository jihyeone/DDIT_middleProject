package kr.or.ddit.middle.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.ProfessorVO;

public interface ICounselService {
	/**
	 * 상담게시판 리스트 불러오기
	 * @return
	 */
	public List<CounselVO> getCounselList();
	
	
	/**
	 * 학생이 쓴 상담요청 목록 리스트
	 * @return
	 */
	public List<CounselVO> selectCounselList(String stuId);
	
	/**
	 * 상담요청 내용 상세정보 보기
	 * @param cnsNo
	 * @return 
	 */
	public CounselVO counselDetail(String cnsNo);
	
	
	/**
	 * 상담신청 등록
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertCounsel(CounselVO vo);
	
	/**
	 * 상담신청 삭제
	 * @param cnsNo
	 * @return 성공 : 1, 실패 : 0
	 */
	public int deleteCounsel(String cnsNo);
	
	/**
	 * 교수가 받은 상담 내역리스트 불러오기
	 * @param proId
	 * @return 리스트
	 */
	public List<CounselVO> getProCnsList (String proId);
	
	/**
	 * 내역을 승인하고 답변확인 Y / N update
	 * @param cnsNo
	 * @return
	 */
	public int updateCheck(String cnsNo);
	
	
	/**
	 * 교수가 받은 최근 상담목록
	 * @param proId
	 * @return
	 */
	public CounselVO selectRecentCns(String proId);
	
	/**
	 * 교수가 승인하지 않은 상담 갯수
	 * @param proId
	 * @return
	 */
	public int countCnsCheckN(String proId);
	
	/**
	 * 교수 메인페이지 상담게시판 최근 리스트
	 * @param proId
	 * @return
	 */
	public List<CounselVO> recentCnsList(String proId);
	
	
	
	//---------------------------------------페이지
	/**
	 * 리스트 보기 조건에 따라서 
	 * @param map
	 * @return
	 */
	public List<CounselVO> CnsListbypage(Map<String, Object> map);
	
	
	// 글 전체 갯수 구하기 - 조건에 따라서
	public int totalCount(Map<String, Object> map);
	
	
	public PageVO pageInfo(int page, String stype, String sword, String proId);
	
	//----------------------------------------------
	
	//---------학생 상담글 페이징
	
	public List<CounselVO> StuCnsBypage(Map<String, Object> map);
	
	// 학생이 보낸 총 상담글 갯수
	public int StuCnsCount(Map<String, Object> map);
	
	public PageVO pageInfoStu(int page, String stype, String sword, String stuId);
	
}
