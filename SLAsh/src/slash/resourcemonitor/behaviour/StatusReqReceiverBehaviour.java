package slash.resourcemonitor.behaviour;

import java.io.IOException;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resourcemonitor.agent.ResourceMonitorAgent;

public class StatusReqReceiverBehaviour extends CyclicBehaviour {
	private ResourceMonitorAgent rm;
	
	public StatusReqReceiverBehaviour(ResourceMonitorAgent agent) {
		this.rm = agent;
	}
	
	public void action() {
		try {
			MessageTemplate mt = MessageTemplate.MatchConversationId("status request");
			ACLMessage recvMsg = myAgent.receive(mt);
			if(recvMsg!=null) {
				AID sender = recvMsg.getSender();
				
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(sender);
				msg.setLanguage("English");
				msg.setContentObject(rm.getStatus());
				msg.setConversationId("status response");
				myAgent.send(msg);
			}
			else
				block();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
