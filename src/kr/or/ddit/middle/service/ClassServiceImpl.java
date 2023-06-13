package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.dao.ClassDaoImpl;
import kr.or.ddit.middle.dao.IClassDao;
import kr.or.ddit.middle.vo.ClassClassVO;

public class ClassServiceImpl implements IClassService {
	
	private IClassDao dao;
	private static IClassService service;
	private ClassServiceImpl() {
		dao = ClassDaoImpl.getInstance();
	}
	
	public static IClassService getInstance() {
		if(service == null) service = new ClassServiceImpl();
		return service;
	}
	
	@Override
	public List<ClassClassVO> getAllclass() {
		return dao.getAllclass();
	}
	
	@Override
	public ClassClassVO SelectJoinClass(String classNo) {
		return dao.SelectJoinClass(classNo);
	}
}
