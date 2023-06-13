package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.RoomVO;
import kr.or.ddit.middle.vo.StudentVO;
import kr.or.ddit.middle.vo.TrainingVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class AdminDaoImpl implements IAdminDao{
	
	private SqlMapClient smc; 
	private static IAdminDao dao;
	private AdminDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IAdminDao getInstance() {
		if(dao == null) dao = new AdminDaoImpl();
		return dao;
	}
	
	@Override
	public AdminVO selectAdmin() {
		AdminVO vo = null;
		
		try {
			vo = (AdminVO) smc.queryForObject("admin.selectAdmin");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return vo;
	}
	
	
	@Override
	public AdminVO getAdmin(AdminVO vo) {
		AdminVO loginAdmin = null;
		
		try {
			loginAdmin = (AdminVO) smc.queryForObject("admin.getAdmin",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginAdmin;
	}
	
	
	

	@Override
	public List<ProfessorVO> getProfessorList() {
		List<ProfessorVO> proList = null;
		
		try {
			proList = smc.queryForList("admin.getProfessor");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proList;
	}

	@Override
	public int insertProfessor(ProfessorVO professorVO) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("admin.insertProfessor",professorVO);
			if(obj==null) cnt =1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteProfessor(String ProfessorId) {
		int cnt = 0;
		try {
			cnt = smc.delete("admin.deleteProfessor",ProfessorId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateProfessor(ProfessorVO professorVo) {
		int cnt = 0;
		try {
			cnt = smc.update("admin.updateProfessor",professorVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public ProfessorVO getProfessor(String professorId) {
		ProfessorVO professorVo = null;
		try {
			professorVo = (ProfessorVO)smc.queryForObject("admin.getProfessor",professorId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professorVo;
	}
	
	//----------------상엽 학급관리--------------------------
	@Override
	public int insertClass(ClassClassVO classVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("admin.InsertClass",classVo);
			if (obj==null)cnt =1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateClass(ClassClassVO classVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("admin.UpdateClass",classVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public ClassClassVO getClass(String ClassNo) {
		ClassClassVO classVo = null;
		try {
			classVo= (ClassClassVO) smc.queryForObject("admin.getClass",ClassNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
		return classVo;
	}

	@Override
	public List<StudentVO> getStudent(String ClassNo) {
		List<StudentVO> list = null;
		try {
			list = smc.queryForList("admin.getStudent",ClassNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StudentVO> Search(StudentVO stdvo) {
		List<StudentVO> list = null;
		
		try {
			list = smc.queryForList("admin.Search",stdvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//---------상엽 강의실 관리--------------------
	@Override
	public List<RoomVO> getRoom() {
		List<RoomVO> list =null;
		
		try {
			list = smc.queryForList("admin.getRoom");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RoomVO> SearchRoom(String rmtype) {
		List<RoomVO> list = null;
		try {
			list = smc.queryForList("admin.SearchRoom",rmtype);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//---------- 채현 --- 학생관리
	@Override
	public int insertStudent(StudentVO StudentVO) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("admin.insertStudent", StudentVO);
			if(obj==null) cnt =1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteStudent(String StudentId) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.delete("admin.deleteStudent", StudentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateStudent(StudentVO StudentVO) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.update("admin.updateStudent", StudentVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public StudentVO getStudentAd(String StudentId) {
		StudentVO vo = null;
		
		try {
			vo = (StudentVO)smc.queryForObject("admin.getStudentAd", StudentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public String newproId() {
		String newproId = "";
		
		try {
			newproId = (String) smc.queryForObject("admin.newproId");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return newproId;
	}
	

	@Override
	public List<TrainingVO> trainingAll() {
		List<TrainingVO> list = null;
		
		try {
			list = smc.queryForList("admin.trainingAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int deleteClass(String classNo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("admin.deleteClass",classNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public List<RoomVO> selectlecture() {
		List<RoomVO> list = null;
		
		try {
			list = smc.queryForList("admin.selectlecture");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateRoom(RoomVO roomVO) {
		int cnt = 0;
		
		try {
			cnt = smc.update("admin.UpdateRoom",roomVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public RoomVO selectRoom(String rmNo) {
		RoomVO vo =null;
		try {
			vo=(RoomVO) smc.queryForObject("admin.selectRoom",rmNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int FinClassRoomUpdate(RoomVO rmvo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("admin.FinClassRoomUpdate", rmvo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	//---------------------- 강의실 페이징
	@Override
	public List<RoomVO> roomListBypage(Map<String, Object> map) {
		 List<RoomVO> list = null;
		 
		 try {
			list = smc.queryForList("admin.roomListBypage",map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public int roomCount(Map<String, Object> map) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("admin.roomCount", map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}
}
