package kr.or.ddit.middle.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.CB_AttachVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class CB_AttachDaoImpl implements ICB_AttachDao {
	
	private SqlMapClient smc;
	
	private static ICB_AttachDao dao;
	
	private CB_AttachDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ICB_AttachDao getInstance() {
		if(dao == null) dao = new CB_AttachDaoImpl();
		return dao;
	}

	@Override
	public List<CB_AttachVO> getCFileList() {
		List<CB_AttachVO> list = null;
		
		return list;
	}

}
