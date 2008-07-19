package slash.resourcemonitor.behaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resourcemonitor.agent.*;

public class ViolationReceiverBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -5411307240223204565L;

	private ResourceMonitorAgent rm;
	
	public ViolationReceiverBehaviour(ResourceMonitorAgent rm) {
		this.rm = rm;
	}
	
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchConversationId("contract violation");
		ACLMessage recvMsg = myAgent.receive(mt);
		if(recvMsg!=null) {
			System.out.println("Contract violated!!!");
		}
		else
			block();
	}
}
