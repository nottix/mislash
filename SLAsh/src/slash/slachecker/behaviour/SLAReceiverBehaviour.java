package slash.slachecker.behaviour;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.core.behaviours.*;
import slash.slachecker.agent.*;
import slash.entity.*;

public class SLAReceiverBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3897026802009387366L;

	private SLACheckerAgent sc;
	
	public SLAReceiverBehaviour(SLACheckerAgent agent) {
		this.sc = agent;
	}
	
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchConversationId("SLAContract send");
		ACLMessage recvMsg = myAgent.receive(mt);
		if(recvMsg!=null) {
			try {
				System.out.println("Added Contract: "+((SLAContract)recvMsg.getContentObject()).getLatency());
				sc.getContractList().add((SLAContract)recvMsg.getContentObject());
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
		}
		else
			block();
	}
}
