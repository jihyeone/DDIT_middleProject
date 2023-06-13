package kr.or.ddit.middle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.dao.AdminDaoImpl;
import kr.or.ddit.middle.dao.IAdminDao;
import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.RoomVO;
import kr.or.ddit.middle.vo.StudentVO;
import kr.or.ddit.middle.vo.TrainingVO;

public class AdminServiceImpl implements IAdminService {
	
	private IAdminDao dao;
	private static IAdminService service;
	private AdminServiceImpl() {
		dao = AdminDaoImpl.getInstance();
	}
	
	public static IAdminService getInstance() {
		if(service == null) service = new AdminServiceImpl();
		return service;
	}
	
	
	@Override
	public AdminVO selectAdmin() {
		return dao.selectAdmin();
	}
	
	@Override
	public AdminVO getAdmin(AdminVO vo) {
		return dao.getAdmin(vo);
	}
	
	
	
	//---------상엽 교수관리 --------------------
	@Override
	public List<ProfessorVO> getProfessorList() {
		return dao.getProfessorList();
	}

	@Override
	public int insertProfessor(ProfessorVO professorVO) {
		return dao.insertProfessor(professorVO);
	}

	@Override
	public int deleteProfessor(String ProfessorId) {
		return dao.deleteProfessor(ProfessorId);
	}

	@Override
	public int updateProfessor(ProfessorVO professorVo) {
		// TODO Auto-generated method stub
		return dao.updateProfessor(professorVo);
	}

	@Override
	public ProfessorVO getProfessor(String professorId) {
		return dao.getProfessor(professorId);
	}
	//---------상엽 교수관리 --------------------
	//---------상엽 학급관리 --------------------
	
	@Override
	public int insertClass(ClassClassVO classVo) {
		return dao.insertClass(classVo);
	}

	@Override
	public int updateClass(ClassClassVO classVo) {
		return dao.updateClass(classVo);
	}

	@Override
	public ClassClassVO getClass(String ClassNo) {
		return dao.getClass(ClassNo);
	}

	@Override
	public List<StudentVO> getStudent(String classNo) {
		return dao.getStudent(classNo);
	}

	@Override
	public List<StudentVO> Search(StudentVO stdVo) {
		return dao.Search(stdVo);
	}
	//---------상엽 강의실 관리--------------------
	@Override
	public List<RoomVO> getRoom() {
		return dao.getRoom();
	}

	@Override
	public List<RoomVO> SearchRoom(String rmtype) {
		return dao.SearchRoom(rmtype);
	}
	//--------------------------------------------
	@Override
	public int insertStudent(StudentVO StudentVO) {
		return dao.insertStudent(StudentVO);
	}

	@Override
	public int deleteStudent(String StudentId) {
		return dao.deleteStudent(StudentId);
	}

	@Override
	public int updateStudent(StudentVO StudentVO) {
		return dao.updateStudent(StudentVO);
	}

	@Override
	public StudentVO getStudentAd(String StudentId) {
		return dao.getStudentAd(StudentId);
	}
	//---------------- 채현 학생관리---------------

	@Override
	public String newproId() {
		return dao.newproId();
	}
	@Override
	public List<TrainingVO> trainingAll() {
		return dao.trainingAll();
	}
	//-------- 상엽 교육정보
	
	@Override
	public int deleteClass(String classNo) {
		return dao.deleteClass(classNo);
	}

	@Override
	public List<RoomVO> selectlecture() {
		return dao.selectlecture();
	}

	@Override
	public int updateRoom(RoomVO roomVO) {
		return dao.updateRoom(roomVO);
	}

	@Override
	public RoomVO selectRoom(String rmNo) {
		return dao.selectRoom(rmNo);
	}

	@Override
	public int FinClassRoomUpdate(RoomVO rmvo) {
		return dao.FinClassRoomUpdate(rmvo);
	}
	
	//--------------------- 페이징

	@Override
	public List<RoomVO> roomListBypage(Map<String, Object> map) {
		return dao.roomListBypage(map);
	}

	@Override
	public int roomCount(Map<String, Object> map) {
		return dao.roomCount(map);
	}
	
	
	@Override
	public PageVO pageInfoRm(int page, String rm_type) {
		PageVO vo = new PageVO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rm_type", rm_type);
		
		// 전체 페이지 수 구하기
		int count = this.roomCount(map);
		
		// 전체 글 갯수 구하기 - totalPage
		int perList = PageVO.getPerList();
		int totalPage = (int) (Math.ceil((double) count / perList));
		
		// 글을 삭제해서 페이지수 변경처리
		// page가 8 마지막 페이지였는데 글 모두 지워져서 8페이지가 사라짐
		// => 페이지 번호를 7 (앞의 번호로) 수정
		if(page > totalPage) page = totalPage;
		
		
		
		// start. end 구하기 - page기준으로
		int start = (page - 1) * perList + 1;
		int end = start + perList - 1;
		
		if(end > count) end = count;
		
		
		// startPage , endPage
		int perPage = PageVO.getPerPage() ;
		int startPage = ((page - 1) / perPage * perPage) + 1 ;
		int endPage = startPage + perPage - 1;
		
		if(endPage > totalPage) endPage = totalPage;
		
		// pageVo 에 저장
		vo.setStart(start);
		vo.setEnd(end);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotalPage(totalPage);
		
		return vo;
	}

	
}
