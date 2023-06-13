package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.dao.CB_ComentDaoImpl;
import kr.or.ddit.middle.dao.ICB_ComentDao;
import kr.or.ddit.middle.vo.Comm_CommentVO;

public class CB_ComentServiceImpl implements ICB_ComentService {

	private ICB_ComentDao dao;
	
	private static ICB_ComentService service;
	
	private CB_ComentServiceImpl() {
		dao = CB_ComentDaoImpl.getInstance();
	}
	
	public static ICB_ComentService getInstance() {
		if(service == null) service = new CB_ComentServiceImpl();
		return service;
	}	
	
	@Override
	public int insertReply(Comm_CommentVO vo) {
		// TODO Auto-generated method stub
		return dao.insertReply(vo);
	}

	@Override
	public int modifyReply(Comm_CommentVO vo) {
		// TODO Auto-generated method stub
		return dao.modifyReply(vo);
	}

	@Override
	public int deleteReply(String renum) {
		// TODO Auto-generated method stub
		return dao.deleteReply(renum);
	}

	@Override
	public List<Comm_CommentVO> selectReply(String bonum) {
		// TODO Auto-generated method stub
		return dao.selectReply(bonum);
	}

	

}
