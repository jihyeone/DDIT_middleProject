package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.dao.AttendanceDaoImpl;
import kr.or.ddit.middle.dao.IAttendanceDao;
import kr.or.ddit.middle.vo.AttendanceVO;

public class AttendanceServiceImpl implements IAttendanceService {
	
	
	private IAttendanceDao dao;
	private static IAttendanceService service;
	private AttendanceServiceImpl() {
		dao = AttendanceDaoImpl.getInstance();
	}
	public static IAttendanceService getInstance() {
		if(service == null) service = new  AttendanceServiceImpl();
		return service;
	}
	
	@Override
	public List<AttendanceVO> getAllAttList() {
		List<AttendanceVO> list = null; 
		return list;
	}

}
