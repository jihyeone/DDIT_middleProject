package kr.or.ddit.middle.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.middle.vo.MessageVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MessageDaoImpl implements IMessageDao{
	
	private SqlMapClient smc;
	private static IMessageDao dao;
	private MessageDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	public static IMessageDao getInstance() {
		if(dao == null) dao = new MessageDaoImpl();
		return dao;
	}
	
	
	
	@Override
	public List<MessageVO> getAllMessage() {
		
		List<MessageVO> list = null;
		
		try {
			list = smc.queryForList("message.getAllMessage");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public int insertMessage(MessageVO msgvo) {
		int cnt = 0;
		
		
		try {
			Object obj = smc.insert("message.insertMessage", msgvo);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	@Override
	public int checkMessage(int no) {
		int cnt = 0; 
		
		try {
			cnt = smc.update("message.checkMessage", no);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}
	
	
	@Override
	public MessageVO selectMessageNo(int no) {
		MessageVO vo = null;
		
		try {
			vo = (MessageVO) smc.queryForObject("message.selectMessageNo",no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	@Override
	public MessageVO selectMessageId(String id) {
		MessageVO vo = null;
		
		try {
			vo = (MessageVO) smc.queryForObject("message.selectMessageId", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	@Override
	public int deleteMessage(MessageVO msgvo) {
		
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("message.deleteMessage", msgvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	
	@Override
	public List<MessageVO> receiveMessageList(String id) {
		List<MessageVO> list = null;
		
		try {
			list = (List<MessageVO>)smc.queryForList("message.receiveMessageList", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	@Override
	public List<MessageVO> sendMessageList(String id) {
		List<MessageVO> list = null;
		
		try {
			list = (List<MessageVO>)smc.queryForList("message.sendMessageList", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	@Override 
	public MessageVO selectRecntMgs(String id) {
		MessageVO vo = null;
		
		try {
			vo = (MessageVO) smc.queryForObject("message.selectRecntMgs",id );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return vo;
	}
	
	
	@Override
	public int countNewMsg(String id) {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("message.countNewMsg", id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public List<MessageVO> selectByReceive(Map<String, Object> map) {
		List<MessageVO> list = null;
		
		try {
			list = smc.queryForList("message.selectByReceive", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	@Override
	public List<MessageVO> selectByPage(Map<String, Object> map) {
		List<MessageVO> list = null;
		
		try {
			list = smc.queryForList("message.selectByPage", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
