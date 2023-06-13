package kr.or.ddit.middle.dao;

import java.util.List;

import kr.or.ddit.middle.vo.StudentVO;

public interface IStudentDao {
	// test StudentAll
	
	/**
	 * 전체 학생목록 출력 - 파라미터 없음
	 * @return 리턴 : List<StudentVO>
	 */
	public List<StudentVO> getAllstudent();
	
	public StudentVO getone(String id);
	
	/**
	 * 학생 로그인 확인
	 * @param vo
	 * @return
	 */
	public StudentVO getStudent(StudentVO vo);
	
	
	/**
	 * 학생 아이디 찾기
	 * @param svo
	 * @return
	 */
	public StudentVO findId(StudentVO svo);
	
	/**
	 * 학생 비밀번호 찾기
	 * @param svo
	 * @return
	 */
	public StudentVO findPass(StudentVO svo);
	public String subreg(StudentVO svo);
	public int resetpw(StudentVO svo);
	
	
	/**
	 * 회원 정보를 update하는 메서드
	 * @param memVo update할 회원 정보가 저장된 MemberVO객체
	 * @return  작업성공:1, 작업실패:0
	 */
	public int updateStudent(StudentVO stdVo);
	
	/**
	 * 검색된 회원 정보를 가져오는 메서드
	 * @param memId 검색할 회원 ID
	 * @return 검색된 회원 정보가 저장된 MemberVO객체
	 */
	public StudentVO getStuInfo(String studentId);
	
	
	/**
	 * 비밀번호 변경을 위한 메서드
	 * @param stdvo
	 * @return
	 */
	public int modifyPw(StudentVO stdvo);
	
	/**
	 * 비밀번호 변경을 위한 현재 비밀번호를 찾는 메서드
	 * @param studentId
	 * @return
	 */
	public String pwCheck(String studentId);
	//-------------------230208 진주
	
	/**
	 * 학생 수료 여부 확인
	 * @param studentId
	 * @return
	 */
	public String checkFinal(String studentId);
	//---------------230209 채현
	
	
	/**
	 * 아이디 검색
	 * @param studentId
	 * @return
	 */
	public List<StudentVO> ReceiveSelect(String studentId);
	
	/**
	 * 해당학급의 학생목록
	 * @param classNo
	 * @return
	 */
	public List<StudentVO> getAllstudent(String classNo);
}
