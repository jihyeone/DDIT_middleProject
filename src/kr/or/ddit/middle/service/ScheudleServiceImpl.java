package kr.or.ddit.middle.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.dao.IscheudleDao;
import kr.or.ddit.middle.dao.SchedeudleDaoImpl;
import kr.or.ddit.middle.vo.ScheduleVO;

public class ScheudleServiceImpl implements IscheudleService {
	
	
	private IscheudleDao dao;
	private static IscheudleService service;
	private ScheudleServiceImpl() {
		dao = SchedeudleDaoImpl.getInstance();
	}
	public static IscheudleService getInstance() {
		if(service == null) service = new ScheudleServiceImpl();
		return service;
	}
	
	@Override
	public List<ScheduleVO> getAllScheudle() {
		return dao.getAllScheudle();
	}

	@Override
	public int insertschedle(ScheduleVO vo) {
		return dao.insertschedle(vo);
	}
	
	
	
//	@Override
//	public Map<String, ScheudleVO> calenList() {
//		return dao.calenList();
//	}
	
	@Override
	public List<Map<String, ScheduleVO>> calenAllList(String classNo) {
		return dao.calenAllList(classNo);
	}
	@Override
	public List<ScheduleVO> selectSubject() {
		return dao.selectSubject();
	}
	
	
	
	
	@Override
	public int insertsAddchedule(ScheduleVO vo) {
		return dao.insertsAddchedule(vo);
	}
	
	
	@Override
	public int deleteSchedule(String scheduleNo) {
		return dao.deleteSchedule(scheduleNo);
	}
	

}
