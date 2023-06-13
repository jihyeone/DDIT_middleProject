package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class CounselDaoImpl implements ICounselDao{
	
	private SqlMapClient smc;
	
	private static ICounselDao dao;
	
	private CounselDaoImpl() {
		smc =SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ICounselDao getInstance() {
		if(dao == null) dao = new CounselDaoImpl();
		return dao;
	}
	
	@Override
	public List<CounselVO> getCounselList() {
		List<CounselVO> list = null; 
		
		try {
			list = smc.queryForList("counsel.getCounselList");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public List<CounselVO> selectCounselList(String stuId) {
		List<CounselVO> list = null;
		
		try {
			list = smc.queryForList("counsel.selectCounselList",stuId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CounselVO counselDetail(String cnsNo) {
		CounselVO vo = null;
		
		try {
			vo = (CounselVO) smc.queryForObject("counsel.counselDetail",cnsNo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int insertCounsel(CounselVO vo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("counsel.insertCounsel",vo);
			
			if(obj == null) cnt = 1;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteCounsel(String cnsNo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("counsel.deleteCounsel",cnsNo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}

	
	
	// 교수------------------------------------------------------------------------
	@Override
	public List<CounselVO> getProCnsList(String proId) {
		List<CounselVO> clist = null;
		
		try {
			clist = smc.queryForList("counsel.getProCnsList", proId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return clist;
	}

	@Override
	public int updateCheck(String cnsNo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("counsel.updateCheck", cnsNo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	//--- 교수가 받은 최근 상담목록
	@Override
	public CounselVO selectRecentCns(String proId) {
		CounselVO cvo = null;
		
		try {
			cvo = (CounselVO) smc.queryForObject("counsel.selectRecentCns", proId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cvo;
	}
	

	@Override
	public int countCnsCheckN(String proId) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("counsel.countCnsCheckN",proId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	
	@Override
	public List<CounselVO> recentCnsList(String proId) {
		List<CounselVO> list = null;
		
		try {
			list = smc.queryForList("counsel.recentCnsList",proId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

	
	//------------------------------------page
	
	// 조건에 따라서 리스트 보기
	@Override
	public List<CounselVO> CnsListbypage(Map<String, Object> map) {
		List<CounselVO> list = null;
		
		try {
			list = smc.queryForList("counsel.CnsListbypage", map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 총 글갯수 구하기 = 교수가 상담을 받은 글 갯수
	@Override
	public int totalCount(Map<String, Object> map) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("counsel.totalCount", map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//---------학생이 보낸 상담글 페이징

	@Override
	public List<CounselVO> StuCnsBypage(Map<String, Object> map) {
		List<CounselVO> list = null;
		
		try {
			list = smc.queryForList("counsel.StuCnsBypage", map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int StuCnsCount(Map<String, Object> map) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("counsel.StuCnsCount", map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
}
