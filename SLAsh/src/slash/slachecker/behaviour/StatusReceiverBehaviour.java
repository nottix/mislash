package slash.slachecker.behaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import slash.slachecker.agent.*;
import slash.entity.*;

public class StatusReceiverBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -7122302752623856233L;
	
	private SLACheckerAgent sc;
	
	public StatusReceiverBehaviour(SLACheckerAgent agent) {
		this.sc = agent;
	}

	public void action() {
		try {
			MessageTemplate mt = MessageTemplate.MatchConversationId("status response");
			ACLMessage recvMsg = myAgent.receive(mt);
			
			if(recvMsg!=null) {
				System.out.println("Received status from "+recvMsg.getSender().getLocalName()+", content: "+((Status)recvMsg.getContentObject()).getAvgLatency());
				sc.getStatusTable().put(recvMsg.getSender(), (Status)recvMsg.getContentObject());
			}
			else
				block();
		} catch (UnreadableException e) {
			e.printStackTrace();
		}
	}
}
