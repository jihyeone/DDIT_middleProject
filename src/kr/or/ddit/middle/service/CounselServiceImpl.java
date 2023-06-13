package kr.or.ddit.middle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.dao.CounselDaoImpl;
import kr.or.ddit.middle.dao.ICounselDao;
import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.PageVO;

public class CounselServiceImpl implements ICounselService {
	
	private ICounselDao dao;
	
	private static ICounselService service;
	
	private CounselServiceImpl() {
		dao = CounselDaoImpl.getInstance();
	}
	
	public static ICounselService getInstance() {
		if(service == null) service = new CounselServiceImpl();
		return service;
	}
	
	@Override
	public List<CounselVO> getCounselList() {
		return dao.getCounselList();
	}

	
	@Override
	public List<CounselVO> selectCounselList(String stuId) {
		
		return dao.selectCounselList(stuId);
	}

	@Override
	public CounselVO counselDetail(String cnsNo) {
		
		return dao.counselDetail(cnsNo);
	}

	@Override
	public int insertCounsel(CounselVO vo) {
		return dao.insertCounsel(vo);
	}

	@Override
	public int deleteCounsel(String cnsNo) {
		return dao.deleteCounsel(cnsNo);
	}

	
	
	
	
	//교수 ----------------------------------------------------
	@Override
	public List<CounselVO> getProCnsList(String proId) {
		return dao.getProCnsList(proId);
	}

	@Override
	public int updateCheck(String cnsNo) {
		return dao.updateCheck(cnsNo);
	}

	@Override
	public CounselVO selectRecentCns(String proId) {
		return dao.selectRecentCns(proId);
	}
	
	//----------------------0213
	@Override
	public int countCnsCheckN(String proId) {
		return dao.countCnsCheckN(proId);
	}
	
	//------------- 교수메인페이지 상담 리스트
	@Override
	public List<CounselVO> recentCnsList(String proId) {
		return dao.recentCnsList(proId);
	}

	//---------------------------페이지
	// 조건에 따라서
	@Override
	public List<CounselVO> CnsListbypage(Map<String, Object> map) {
		return dao.CnsListbypage(map);
	}

	
	@Override
	public int totalCount(Map<String, Object> map) {
		return dao.totalCount(map);
	}

	
	
	@Override
	public PageVO pageInfo(int page, String stype, String sword, String pro_id) {
		PageVO vo = new PageVO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
		map.put("pro_id", pro_id);
		
		// 전체 페이지 수 구하기
		int count = this.totalCount(map);
		
		// 전체 글 갯수 구하기 - totalPage
		int perList = PageVO.getPerList();
		int totalPage = (int) (Math.ceil((double) count / perList));
		
		// 글을 삭제해서 페이지수 변경처리
		// page가 8 마지막 페이지였는데 글 모두 지워져서 8페이지가 사라짐
		// => 페이지 번호를 7 (앞의 번호로) 수정
		if(page > totalPage) page = totalPage;
		
		
		
		// start. end 구하기 - page기준으로
		int start = (page - 1) * perList + 1;
		int end = start + perList - 1;
		
		if(end > count) end = count;
		
		
		// startPage , endPage
		int perPage = PageVO.getPerPage() ;
		int startPage = ((page - 1) / perPage * perPage) + 1 ;
		int endPage = startPage + perPage - 1;
		
		if(endPage > totalPage) endPage = totalPage;
		
		// pageVo 에 저장
		vo.setStart(start);
		vo.setEnd(end);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotalPage(totalPage);
		
		return vo;
	}

	
	//------------------ 학생이 보낸 상담글 페이징
	@Override
	public List<CounselVO> StuCnsBypage(Map<String, Object> map) {
		return dao.StuCnsBypage(map);
	}

	@Override
	public int StuCnsCount(Map<String, Object> map) {
		return dao.StuCnsCount(map);
	}
	
	
	@Override
	public PageVO pageInfoStu(int page, String stype, String sword, String stuId) {
		PageVO vo = new PageVO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
		map.put("stu_id", stuId);
		
		// 전체 페이지 수 구하기
		int count = this.StuCnsCount(map);
		
		// 전체 글 갯수 구하기 - totalPage
		int perList = PageVO.getPerList();
		int totalPage = (int) (Math.ceil((double) count / perList));
		
		// 글을 삭제해서 페이지수 변경처리
		// page가 8 마지막 페이지였는데 글 모두 지워져서 8페이지가 사라짐
		// => 페이지 번호를 7 (앞의 번호로) 수정
		if(page > totalPage) page = totalPage;
		
		
		
		// start. end 구하기 - page기준으로
		int start = (page - 1) * perList + 1;
		int end = start + perList - 1;
		
		if(end > count) end = count;
		
		
		// startPage , endPage
		int perPage = PageVO.getPerPage() ;
		int startPage = ((page - 1) / perPage * perPage) + 1 ;
		int endPage = startPage + perPage - 1;
		
		if(endPage > totalPage) endPage = totalPage;
		
		// pageVo 에 저장
		vo.setStart(start);
		vo.setEnd(end);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotalPage(totalPage);
		
		return vo;
	}
	
	
	
}
