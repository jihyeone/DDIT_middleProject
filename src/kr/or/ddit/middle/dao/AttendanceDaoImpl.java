package kr.or.ddit.middle.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.AttendanceVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class AttendanceDaoImpl implements IAttendanceDao {
	
	
	
	private SqlMapClient smc;
	private static IAttendanceDao dao;
	private AttendanceDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	public static IAttendanceDao getInstance() {
		if(dao == null) dao = new AttendanceDaoImpl();
		return dao;
	}
	
	@Override
	public List<AttendanceVO> getAllAttList() {
		List<AttendanceVO> list = null;
		return list;
	}
	
	
	
	
	
}
