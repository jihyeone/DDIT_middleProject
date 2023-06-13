package kr.or.ddit.middle.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.dao.IMessageDao;
import kr.or.ddit.middle.dao.MessageDaoImpl;
import kr.or.ddit.middle.vo.MessageVO;

public class MessageServiceImpl implements IMessageService {
	
	private IMessageDao dao;
	private static IMessageService service;
	private MessageServiceImpl() {
		dao = MessageDaoImpl.getInstance();
	}
	public static IMessageService getInstance() {
		if(service == null) service = new MessageServiceImpl();
		return service;
	}
	
	
	@Override
	public List<MessageVO> getAllMessage() {
		return dao.getAllMessage();
	}
	
	
	@Override
	public int insertMessage(MessageVO msgvo) {
		return dao.insertMessage(msgvo);
	}
	
	
	@Override
	public int checkMessage(int no) {
		return dao.checkMessage(no);
	}

	
	@Override
	public MessageVO selectMessageNo(int no) {
		return dao.selectMessageNo(no);
	}

	
	@Override
	public MessageVO selectMessageId(String id) {
		return dao.selectMessageId(id);	
	}

	
	@Override
	public int deleteMessage(MessageVO msgvo) {
		return dao.deleteMessage(msgvo);
	}


	@Override
	public List<MessageVO> receiveMessageList(String id) {
		return dao.receiveMessageList(id);
	}

	@Override
	public List<MessageVO> sendMessageList(String id) {
		return dao.sendMessageList(id);
	}
	
	
	@Override
	public MessageVO selectRecntMgs(String id) {
		return dao.selectRecntMgs(id);
	}
	
	
	@Override
	public int countNewMsg(String id) {
		return dao.countNewMsg(id);
	}
	
	
	
	@Override
	public List<MessageVO> selectByReceive(Map<String, Object> map) {
		return dao.selectByReceive(map);
	}
	
	@Override
	public List<MessageVO> selectByPage(Map<String, Object> map) {
		return dao.selectByPage(map);
	}
	
	
}
