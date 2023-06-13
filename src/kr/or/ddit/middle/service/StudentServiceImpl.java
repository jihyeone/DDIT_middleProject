package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.dao.IStudentDao;
import kr.or.ddit.middle.dao.StudentDaoImpl;
import kr.or.ddit.middle.vo.StudentVO;

public class StudentServiceImpl implements IStudentService{
	
	private IStudentDao dao;
	private static IStudentService service;
	
	private StudentServiceImpl () {
		dao = StudentDaoImpl.getInstance();
	}
	
	public static IStudentService getInstance() {
		if(service == null) service = new StudentServiceImpl();
		return service;
	}
	
	@Override
	public List<StudentVO> getAllstudent() {
		return dao.getAllstudent();
	}

	@Override
	public StudentVO getone(String id) {
		return dao.getone(id);
	}
	
	
	@Override
	public StudentVO getStudent(StudentVO vo) {
		return dao.getStudent(vo);
	}
	

	@Override
	public StudentVO findId(StudentVO svo) {
		return dao.findId(svo);
		
	}

	
	@Override
	public String subreg(StudentVO svo) {
		return dao.subreg(svo);
	}

	@Override
	public int resetpw(StudentVO svo) {
		
		return dao.resetpw(svo);
	}
	
	
	
	@Override
	public int updateStudent(StudentVO stdVo) {
		// TODO Auto-generated method stub
		return dao.updateStudent(stdVo);
	}

	@Override
	public StudentVO getStuInfo(String studentId) {
		// TODO Auto-generated method stub
		return dao.getStuInfo(studentId);
	}

	@Override
	public int modifyPw(StudentVO stdvo) {
		// TODO Auto-generated method stub
		return dao.modifyPw(stdvo);
	}

	@Override
	public String pwCheck(String studentId) {
		// TODO Auto-generated method stub
		return dao.pwCheck(studentId);
	}
	
	//----------------------------------------
	@Override
	public String checkFinal(String studentId) {
		// TODO Auto-generated method stub
		return dao.checkFinal(studentId);
	}
	
	//---------------------채현
	
	@Override
	public List<StudentVO> ReceiveSelect(String studentId) {
		return dao.ReceiveSelect(studentId);
	}
	//---------------------채현 메세지 학생 검색

	@Override
	public List<StudentVO> getAllstudent(String classNo) {
		return dao.getAllstudent(classNo);
	}
	//-------------혜연
}
