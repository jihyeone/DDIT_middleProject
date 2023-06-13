package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class ProfessorDaoImpl implements IProfessorDao {
	
	private SqlMapClient smc;
	private static IProfessorDao dao;
	private ProfessorDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IProfessorDao getInstance() {
		if(dao == null) dao = new ProfessorDaoImpl();
		return dao;
	}
 	
	@Override
	public List<ProfessorVO> getAllprofesser() {
		List<ProfessorVO> list = null;
		
		try {
			list = smc.queryForList("pro.getAllprofesser");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public ProfessorVO professerone(String pro_id) {
		ProfessorVO vo = null;
		
		try {
			vo = (ProfessorVO) smc.queryForObject("pro.professerone",pro_id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	
	@Override
	public ProfessorVO getProfessor(ProfessorVO vo) {
		ProfessorVO loginprofessor = null;
		
		try {
			loginprofessor = (ProfessorVO) smc.queryForObject("pro.getProfessor",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginprofessor;
	}
	
	@Override
	public ProfessorVO findId(ProfessorVO pvo) {
		ProfessorVO vo = null;
		
		try {
			vo = (ProfessorVO) smc.queryForObject("pro.findId", pvo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public String subreg(ProfessorVO pvo) {
		String res = null;
		
		try {
			res = "" + smc.queryForObject("pro.subreg", pvo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int resetpw(ProfessorVO pvo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("pro.resetpw", pvo);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	

	@Override
	public int updatepro(ProfessorVO pvo) {
		int cnt = 0;
		try {
			cnt = smc.update("pro.updatepro", pvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	

	@Override
	public int modifyPw(ProfessorVO pvo) {
		int cnt = 0;
		try {
			cnt = smc.update("pro.modifyPw", pvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
//-------------------230228 진주

	/**
	 * 담당 학급 뜨기
	 */
	@Override
	public List<ClassClassVO> selectClass(String id) {
		List<ClassClassVO> list = null;
		
		try {
			list = smc.queryForList("pro.selectClass", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * 담당 학급 학생 목록
	 */
	@Override
	public List<ClassClassVO> selectClassStuList(String cno) {
		List<ClassClassVO> list = null;
		
		try {
			list = smc.queryForList("pro.selectClassStuList", cno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
