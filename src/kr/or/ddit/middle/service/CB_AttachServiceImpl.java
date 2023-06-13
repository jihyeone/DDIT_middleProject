package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.dao.CB_AttachDaoImpl;
import kr.or.ddit.middle.dao.ICB_AttachDao;
import kr.or.ddit.middle.vo.CB_AttachVO;

public class CB_AttachServiceImpl implements ICB_AttachService {

	private ICB_AttachDao dao;
	
	private static ICB_AttachService service;
	
	private CB_AttachServiceImpl() {
		dao = CB_AttachDaoImpl.getInstance();
	}
	
	public static ICB_AttachService getInstance() {
		if(service == null) service = new CB_AttachServiceImpl();
		return service;
	}
	
	@Override
	public List<CB_AttachVO> getCFileList() {
		return dao.getCFileList();
	}

}
