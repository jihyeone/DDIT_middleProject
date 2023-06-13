package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ProfessorVO;

public interface IProfessorService {
	/**
	 * 교수 리스트 정보 불러오기
	 * @return 리턴값 리스트
	 */
	public List<ProfessorVO> getAllprofesser();
	
	/**
	 * 교수 1명 정보 불러오기
	 * @return
	 */
	public ProfessorVO professerone(String pro_id);
	
	
	
	/**
	 * 교수 로그인 확인
	 * @param vo
	 * @return
	 */
	public ProfessorVO getProfessor(ProfessorVO vo);
	
	/**
	 * 교수 ID찾기
	 * @param pvo
	 * @return
	 */
    public ProfessorVO findId(ProfessorVO pvo);
	
    
	/**
	 * 교수 비밀번호 찾기(초기화)
	 * @param svo
	 * @return
	 */
	public String subreg(ProfessorVO pvo);
	public int resetpw(ProfessorVO pvo);
	
	
	/**
	 * 교수 정보를 update하는 메서드
	 * @param ProfessorVO update할 회원 정보가 저장된 ProfessorVO객체
	 * @return  작업성공:1, 작업실패:0
	 */
	public int updatepro(ProfessorVO pvo);
	
	
	
	/**
	 * 비밀번호 변경을 위한 메서드
	 * @param stdvo
	 * @return
	 */
	public int modifyPw(ProfessorVO pvo);
	//-------------230208 진주
	
	
	

	/**
	 * 담당 학급 목록 보기
	 * @param id
	 * @return
	 */
	public List<ClassClassVO> selectClass(String id);
	
	
	/**
	 * 담당 학급 학생 목록
	 * @param cno
	 * @return
	 */
	public List<ClassClassVO> selectClassStuList(String cno);
	
	
	
}
