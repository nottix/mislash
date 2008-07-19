package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class LatencyBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float latency;
	
	private AID rmAid;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public LatencyBehaviour(AID cmAid) {
		this.rmAid = cmAid;
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		latency = (float)Math.random();
		return latency;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(rmAid);
	    	msg.setLanguage("English");
	    	String latencyStr = String.valueOf(latency);
	    	msg.setContent(latencyStr);
	    	msg.setConversationId("status resource response");
	
	    	myAgent.send(msg);
		}
		else
			block();

	}
}
