package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.dao.ILectureDao;
import kr.or.ddit.middle.dao.LectureDaoImpl;
import kr.or.ddit.middle.vo.LectureVO;

public class LectureServiceImpl implements ILectureService {
	
	private ILectureDao dao;
	private static ILectureService service;
	private LectureServiceImpl() {
		dao = LectureDaoImpl.getInstance();
	}
	
	
	
	@Override
	public List<LectureVO> getAllLectList() {
		List<LectureVO> list = null;
		return list;
	}

}
