package slash.slachecker.behaviour;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import slash.slachecker.agent.*;
import slash.entity.*;

public class ContextReceiverBehaviour extends CyclicBehaviour {
	
	private static final long serialVersionUID = 5235996069711181357L;

	private SLACheckerAgent sc;

	public ContextReceiverBehaviour(SLACheckerAgent agent) {
		this.sc = agent;
	}
	
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchConversationId("context response");
		ACLMessage recvMsg = myAgent.receive(mt);
		if(recvMsg!=null) {
			try {
				//System.out.println("Recv context "+((Context)(recvMsg.getContentObject())).getAvgCpu());
				this.sc.getContextTable().put(recvMsg.getSender(), (Context)recvMsg.getContentObject());
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
		}
		else
			block();
	}

}
