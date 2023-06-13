package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.StudentVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class StudentDaoImpl implements IStudentDao {
	
	private SqlMapClient smc;
	
	private static IStudentDao dao;
	
	private StudentDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IStudentDao getInstance() {
		if(dao == null) dao = new StudentDaoImpl();
		return dao;
	}
	
	@Override
	public List<StudentVO> getAllstudent() {
		List<StudentVO> list = null;
		
		try {
			list = smc.queryForList("student.getAllstudent");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public StudentVO getone(String id) {
		StudentVO vo = null;
		
		try {
			vo = (StudentVO) smc.queryForObject("student.getone",id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return vo;
	}
	
	

	@Override
	public StudentVO getStudent(StudentVO vo) {
		StudentVO loginStudent =null;
		
		try {
			loginStudent = (StudentVO) smc.queryForObject("student.getstudent",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginStudent;
	}
	
	@Override
	public StudentVO findId(StudentVO svo) {
		StudentVO vo = null;
		
		try {
			vo = (StudentVO) smc.queryForObject("student.findId", svo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public StudentVO findPass(StudentVO svo) {
		StudentVO vo = null;
		
		try {
			vo = (StudentVO) smc.queryForObject("student.findPass", svo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public String subreg(StudentVO svo) {
		String res = null;
		
		try {
			res = (String) smc.queryForObject("student.subreg", svo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int resetpw(StudentVO svo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("student.resetpw", svo);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	@Override
	public int updateStudent(StudentVO stdVo) {
		int cnt = 0;
		try {
			cnt = smc.update("student.updateStudent", stdVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public StudentVO getStuInfo(String studentId) {
		StudentVO svo = null;
		try {
			svo = (StudentVO)smc.queryForObject("student.getStuInfo", studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return svo;
	}


	@Override
	public int modifyPw(StudentVO stdvo) {
		int cnt = 0;
		try {
			cnt = smc.update("student.modifyPw", stdvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String pwCheck(String studentId) {
		String res = null;
		
		try {
			res = (String) smc.queryForObject("student.pwCheck", studentId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return res;
	}
	
	//-------------------------- 진주

	@Override
	public String checkFinal(String studentId) {
		String res = null;
		
		try {
			res = (String) smc.queryForObject("student.checkFinal", studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	//----------------------------채현
	
	
	@Override
	public List<StudentVO> ReceiveSelect(String studentId) {
		List<StudentVO> list = null;
	
		try {
			list = smc.queryForList("student.ReceiveSelect",studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//-----------------혜연
	@Override
	public List<StudentVO> getAllstudent(String classNo) {
		List<StudentVO> list = null;
		
		try {
			list = smc.queryForList("student.selectClassStuList", classNo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
}
