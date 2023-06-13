package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;

import kr.or.ddit.middle.vo.Comm_CommentVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class CB_ComentDaoImpl implements ICB_ComentDao {
	
	private SqlMapClient smc;
	
	private static ICB_ComentDao dao;
	
	private CB_ComentDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ICB_ComentDao getInstance() {
		if(dao == null) dao = new CB_ComentDaoImpl();
		return dao;
	}

	@Override
	public int insertReply(Comm_CommentVO vo) {
		int cnt = 0;
		Object obj;
		try {
			obj = smc.insert("CReply.insertReply", vo);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cnt;
	}

	@Override
	public int modifyReply(Comm_CommentVO vo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("CReply.modifyReply", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteReply(String renum) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("CReply.deleteReply", renum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Comm_CommentVO> selectReply(String bonum) {
		List<Comm_CommentVO> list = null;
		
		try {
			list = smc.queryForList("CReply.selectReply", bonum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

}
