package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.ScheduleVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class SchedeudleDaoImpl implements IscheudleDao {
	
	private SqlMapClient smc;
	private static IscheudleDao dao;
	private SchedeudleDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IscheudleDao getInstance () {
		if(dao == null) dao = new SchedeudleDaoImpl();
		return dao;
	}
	
	@Override
	public List<ScheduleVO> getAllScheudle() {
		List<ScheduleVO> list = null; 
		
		try {
			list = smc.queryForList("schedule.getAllScheudle");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertschedle(ScheduleVO vo) {
		
		int cnt = 0;
		
		try {
			Object obj = smc.insert("schedule.insertschedle", vo);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

//
//	@Override
//	public Map<String, ScheudleVO> calenList() {
//		Map<String, ScheudleVO> map = new HashMap<String, ScheudleVO>();
//		 
//		 try {
//			 map = (Map<String, ScheudleVO>) smc.queryForObject("scheudle.calenList");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return map;
//	}

	@Override
	public List<Map<String, ScheduleVO>> calenAllList(String classNo) {
		List<Map<String, ScheduleVO>> maplist = null;
		
		try {
			maplist = smc.queryForList("schedule.calenAllList" , classNo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return maplist;
	}

	
	// 과목리스트 불러오기
	@Override
	public List<ScheduleVO> selectSubject() {
		List<ScheduleVO> list = null;
		
		try {
			list = smc.queryForList("schedule.selectSubject");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 신규 스케줄 등록
	@Override
	public int insertsAddchedule(ScheduleVO vo) {
		
		int cnt = 0;
		
		try {
			cnt = (int) smc.insert("schedule.insertsAddchedule",vo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	@Override
	public int deleteSchedule(String scheduleNo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("schedule.deleteSchedule", scheduleNo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
}
