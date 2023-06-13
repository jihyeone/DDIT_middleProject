package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.LB_AttachVO;
import kr.or.ddit.middle.vo.LB_CodeVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class Lect_BoardDaompl implements ILect_BoardDao{
	
	private SqlMapClient smc;
	private static ILect_BoardDao dao;
	private Lect_BoardDaompl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ILect_BoardDao getInstance() {
		if(dao == null) dao = new Lect_BoardDaompl();
		return dao;
	}
	
	@Override
	public List<Lect_BoardVO> getAllLBList() {
		List<Lect_BoardVO> list = null;
		try {
			list = smc.queryForList("LBoard.getAllLBoardList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getLBoardCount(Map<String, Object> map) {
		int res = 0;
		try {
			res = (int) smc.queryForObject("LBoard.getLBoardCount", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public List<Lect_BoardVO> joinlboard() {
		List<Lect_BoardVO> list = null;
		try {
			list = smc.queryForList("LBoard.joinlboard");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Lect_BoardVO getLBDetail(String lbNo) {
		Lect_BoardVO lbVO = null;
		try {
			lbVO = (Lect_BoardVO) smc.queryForObject("LBoard.getLBDetail", lbNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lbVO;
	}
	
	@Override
	public int insertLBoard(Lect_BoardVO lbVO) {
		int res = 0;
		try {
//			Object obj = smc.insert("LBoard.insertLBoard", lbVO);
//			if(obj == null) res = 1;
			res = Integer.parseInt( (String)smc.insert("LBoard.insertLBoard", lbVO) );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteLBoard(String lbNo) {
		int res = 0;
		try {
			res = smc.delete("LBoard.deleteLBoard", lbNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateLBoard(Lect_BoardVO lbVO) {
		int res = 0;
		try {
			res = smc.update("LBoard.updateLBoard", lbVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateLBoardRC(String lbNo) {
		int res = 0;
		try {
			res = smc.update("LBoard.updateLBoardRC", lbNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	

	@Override
	public List<Lect_BoardVO> recentLBoardList() {
		List<Lect_BoardVO> list = null;
		try {
			list = smc.queryForList("LBoard.recentLBoardList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	**********************************************************

	@Override
	public List<LB_CodeVO> getAllCode() {
		List<LB_CodeVO> list = null;
		try {
			list = smc.queryForList("LBoard.getAllCode");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public LB_CodeVO joinLBCode(String lbNo) {
		LB_CodeVO lbcdVO = null;
		try {
			lbcdVO = (LB_CodeVO) smc.queryForObject("LBoard.joinLBCode", lbNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lbcdVO;
	}


//	**********************************************************
	
	@Override
	public int insertLBfile(LB_AttachVO lbatVO) {
		int res = 0;
		try {
			Object obj = smc.insert("LBoard.insertLBfile", lbatVO);
			if(obj == null) res = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<LB_AttachVO> selectAllLBfile() {
		List<LB_AttachVO> list = null;
		try {
			list = smc.queryForList("LBoard.selectAllLBfile");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public LB_AttachVO getlbFileNo(String lfNo) {
		LB_AttachVO lbatVO = null;
		try {
			lbatVO = (LB_AttachVO) smc.queryForObject("LBoard.getlbFileNo", lfNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lbatVO;
	}

	@Override
	public LB_AttachVO getFileLBNo(String lbNo) {
		LB_AttachVO lbatVO = null;
		try {
			lbatVO = (LB_AttachVO) smc.queryForObject("LBoard.getFileLBNo", lbNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lbatVO;
	}

	@Override
	public LB_AttachVO joinLBFile(String lfNo) {
		LB_AttachVO lbatVO = null;
		try {
			lbatVO = (LB_AttachVO) smc.queryForObject("LBoard.joinLBFile", lfNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lbatVO;
	}

	@Override
	public int deleteFile(String lbNo) {
		int res = 0;
		try {
			res = smc.delete("LBoard.deleteFile", lbNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	
	
	//-------------------페이징 
	@Override
	public List<Lect_BoardVO> LBListBypage(Map<String, Object> map) {
		List<Lect_BoardVO> list = null;
		try {
			list = smc.queryForList("LBoard.LBListBypage", map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int LBtotalCount(Map<String, Object> map) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("LBoard.LBtotalCount" ,map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	
	
	
//	@Override
//	public int updateFile(LB_AttachVO lbatVO) {
//		int res = 0;
//		try {
//			res = smc.update("LBoard.updateFile", lbatVO);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
	
}
