package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sun.javafx.css.SubCssMetaData;

import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.Comm_CodeVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class CBoardDaoImpl implements ICBoardDao {

	private SqlMapClient smc;
	
	private static ICBoardDao dao;
	
	private CBoardDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ICBoardDao getInstance() {
		if(dao == null) dao = new CBoardDaoImpl();
		return dao;
	}
	//내가 쓴글
	@Override
	public List<Comm_BoardVO> AllMyBoard(String stu_id) {
		List<Comm_BoardVO> list = null;
		
		try {
			list =smc.queryForList("CBoard.AllMyBoard",stu_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Comm_BoardVO ViewMyBoard(String comm_no) {
		Comm_BoardVO vo = null;
		
		try {
			vo =(Comm_BoardVO) smc.queryForObject("CBoard.ViewMyBoard",comm_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int UpdateMyBoard(Comm_BoardVO comm_boardvo) {
		int cnt = 0;
		try {
			cnt = smc.update("CBoard.UpdateMyBoard",comm_boardvo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int DeleteMyBoard(String comm_no) {
		int cnt = 0;
		try {
			cnt = smc.delete("CBoard.DeleteMyBoard", comm_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public List<Comm_CodeVO> getcomcode() {
		List<Comm_CodeVO> list =null;
		try {
			list = smc.queryForList("CBoard.getcomcode");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	//커뮤니티게시판
	@Override
	public List<Comm_BoardVO> getAllCBoardList() {
		//아직 없음
		return null;
	}

	@Override
	public Comm_CodeVO getcomgu(String comm_no) {
		Comm_CodeVO vo =null;
		try {
			vo = (Comm_CodeVO)smc.queryForObject("CBoard.getcomgu", comm_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}

//커뮤니티 게시판
	@Override
	public List<Comm_BoardVO> ComBoardList() {
		List list = null;
		try {
			list = smc.queryForList("CBoard.ComBoardList");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Comm_BoardVO ViewComBoard(String comm_no) {
		Comm_BoardVO vo = null;
		
		try {
			vo = (Comm_BoardVO) smc.queryForObject("CBoard.ViewComBoard",comm_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int modifyComBoard(Comm_BoardVO comm_boardvo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("CBoard.modifyComBoard",comm_boardvo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteComBoard(Comm_BoardVO comm_boardvo) {
		int cnt = 0;
		try {
			cnt = smc.delete("CBoard.deleteComBoard",comm_boardvo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateRc(String comm_no) {
		int cnt = 0;
		try {
			cnt = smc.update("CBoard.updateRc",comm_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertComboard(Comm_BoardVO comm_boardvo) {
		int cnt = 0;
		
		Object obj;
		try {
			obj = smc.insert("CBoard.insertComboard", comm_boardvo);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//------------------------------- 혜연
	@Override
	public List<Comm_BoardVO> recentCBoardList() {
		List<Comm_BoardVO> list = null;
		
		try {
			list = smc.queryForList("CBoard.recentCBoardList");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	//----------------- 페이징
	@Override
	public List<Comm_BoardVO> CBoardBypage(Map<String, Object> map) {
		List<Comm_BoardVO> list = null;
		
		try {
			list = smc.queryForList("CBoard.CBoardBypage", map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int totalCount(Map<String, Object> map) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("CBoard.totalCount", map);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	

}
