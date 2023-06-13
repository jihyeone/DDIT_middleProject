package kr.or.ddit.middle.service;

import java.util.List;

import kr.or.ddit.middle.dao.IProfessorDao;
import kr.or.ddit.middle.dao.ProfessorDaoImpl;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ProfessorVO;

public class ProfessorServiceImpl implements IProfessorService {
	
	private IProfessorDao dao;
	private static IProfessorService service;
	private ProfessorServiceImpl() {
		dao = ProfessorDaoImpl.getInstance();
	}

	public static IProfessorService getInstance() {
		if(service == null) service = new ProfessorServiceImpl();
		return service;
	}
	
	
	@Override
	public List<ProfessorVO> getAllprofesser() {
		return dao.getAllprofesser();
	}

	@Override
	public ProfessorVO professerone(String pro_id) {
		return dao.professerone(pro_id);
	}
	
	
	@Override
	public ProfessorVO getProfessor(ProfessorVO vo) {
		return dao.getProfessor(vo);
	}
	
	@Override
	public ProfessorVO findId(ProfessorVO pvo) {
		return dao.findId(pvo);
		
	}

	
	@Override
	public String subreg(ProfessorVO pvo) {
		return dao.subreg(pvo);
	}

	@Override
	public int resetpw(ProfessorVO pvo) {
		
		return dao.resetpw(pvo);
	}
	
	@Override
	public int updatepro(ProfessorVO pvo) {
		// TODO Auto-generated method stub
		return dao.updatepro(pvo);
	}

	

	@Override
	public int modifyPw(ProfessorVO pvo) {
		// TODO Auto-generated method stub
		return dao.modifyPw(pvo);
	}
	//-----------------230208 진주
	


	@Override
	public List<ClassClassVO> selectClass(String id) {
		return dao.selectClass(id);
	}

	@Override
	public List<ClassClassVO> selectClassStuList(String cno) {
		return dao.selectClassStuList(cno);
	}
}