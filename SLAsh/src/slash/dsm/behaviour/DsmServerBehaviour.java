package slash.dsm.behaviour;

import java.io.IOException;

import jade.core.behaviours.*;
import jade.core.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import slash.dsm.agent.*;
import slash.dsm.tuple.*;
import slash.dsm.data.*;

public class DsmServerBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 6270841980860732385L;
	
	private DsmDataManager dsmDataManager;
	
	public DsmServerBehaviour(DsmServerAgent agent) {
		this.dsmDataManager = new DsmDataManager();
	}
	
	public void action() {
		try {
			MessageTemplate mt = MessageTemplate.MatchConversationId("dsm");
			ACLMessage recvMsg = myAgent.receive(mt);
			Tuple tuple = null;
			if(recvMsg!=null) {
				System.out.println("msg received from client");
				tuple = (Tuple)recvMsg.getContentObject();
				doOperation(tuple, recvMsg.getSender());
			}
			else
				block();
		} catch (UnreadableException e) {
			e.printStackTrace();
		}
	}
	
	private int doOperation(Tuple tuple, AID sender) {
		try {
			if(tuple!=null) {
				if(tuple.getOperation().equals(Tuple.IN)) {
					Tuple tupleRes = dsmDataManager.in(tuple.getIndex(), tuple.getType());
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(sender);
					msg.setLanguage("English");
					msg.setConversationId("dsm");
					msg.setContentObject(tupleRes);
					myAgent.send(msg);
				}
				else if(tuple.getOperation().equals(Tuple.READ)) {
					dsmDataManager.read(tuple.getIndex(), tuple.getType());
				}
				else if(tuple.getOperation().equals(Tuple.OUT)) {
					dsmDataManager.out(tuple.getIndex(), tuple.getType(), tuple.getValue());
				}
				else if(tuple.getOperation().equals(Tuple.UPDATE)) {
					
				}
				else if(tuple.getOperation().equals(Tuple.EVAL)) {
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
