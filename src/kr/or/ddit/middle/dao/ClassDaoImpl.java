package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class ClassDaoImpl implements IClassDao {
	
	private SqlMapClient smc;
	private static IClassDao dao;
	private ClassDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	public static IClassDao getInstance() {
		if(dao == null) dao = new ClassDaoImpl();
		return dao;
	}
	
	
	
	@Override
	public List<ClassClassVO> getAllclass() {
		List<ClassClassVO> list = null;
		
		try {
			list = smc.queryForList("class.getAllclass");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	
	
	@Override
	public ClassClassVO SelectJoinClass(String classNo) {
		ClassClassVO classVo =null;
		
		try {
			classVo = (ClassClassVO) smc.queryForObject("class.SelectJoinClass",classNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return classVo;
	}
	
	
}


