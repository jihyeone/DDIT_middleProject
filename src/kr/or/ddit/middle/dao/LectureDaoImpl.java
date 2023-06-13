package kr.or.ddit.middle.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.LectureVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class LectureDaoImpl implements ILectureDao {
	
	private SqlMapClient smc;
	private static ILectureDao dao;
	private LectureDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ILectureDao getInstance() {
		if(dao == null) dao = new LectureDaoImpl();
		return dao;
	}
	
	@Override
	public List<LectureVO> getAllLectList() {
		List<LectureVO> list = null;
		return list;
	}

}
