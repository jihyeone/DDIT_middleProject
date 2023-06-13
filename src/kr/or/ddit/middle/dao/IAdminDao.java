package kr.or.ddit.middle.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.RoomVO;
import kr.or.ddit.middle.vo.StudentVO;
import kr.or.ddit.middle.vo.TrainingVO;

public interface IAdminDao {
	/**
	 * 관리자 계정 찾기
	 * @return 리턴 : 관리자 객체
	 */
	public AdminVO selectAdmin();
	
	/**
	 * 관리자 계정확인
	 * @param vo
	 * @return
	 */
	public AdminVO getAdmin(AdminVO vo);
	
	//상엽 교수관리 --------------------------------
	public List<ProfessorVO> getProfessorList();
	
	public int insertProfessor(ProfessorVO professorVO);
	
	public int deleteProfessor(String ProfessorId);

	public int updateProfessor(ProfessorVO professorVo);
	
	public ProfessorVO getProfessor(String professorId);
	//상엽--------------------------------
	
	//상엽 학급 관리--------------------------------
	public ClassClassVO getClass(String ClassNo);
	
	public int insertClass(ClassClassVO classVo);
	
	public int updateClass(ClassClassVO classVo);
	
	public int deleteClass(String classNo);
	
	public List<StudentVO> getStudent(String classNo);
	
	public List<StudentVO> Search(StudentVO stdvo);
	
	//상엽 강의실 관리------------------------------
	
	public List<RoomVO> getRoom();
	
	public List<RoomVO> SearchRoom(String rmtype);
	
	public List<RoomVO>	selectlecture();
	
	public RoomVO selectRoom(String rmNo);
	
	public int updateRoom(RoomVO roomVO);
	
	// 채현 학생관리 -------------------------------
	
	
	public int insertStudent(StudentVO StudentVO);
	
	public int deleteStudent(String StudentId);
	
	public int updateStudent(StudentVO StudentVO);
	
	public StudentVO getStudentAd(String StudentId);
	
	//-----------혜연 관리자 신규 교수 등록시 아이디 뒷번호
	public String newproId();
	
	//--------------상엽 교육과정 관리--------------
	public List<TrainingVO> trainingAll();
	
	//-------학급이 수료로 변경되면 강의실 사용여부 N으로 변경
	public int FinClassRoomUpdate(RoomVO rmvo);
	
	
	//------- 강의실관리 페이징
	
	public List<RoomVO> roomListBypage(Map<String, Object> map);
	
	public int roomCount (Map<String, Object> map);
}
