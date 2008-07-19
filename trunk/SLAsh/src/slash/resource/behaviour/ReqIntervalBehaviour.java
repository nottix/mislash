package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReqIntervalBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float reqInterval;
	
	private AID rmAid;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public ReqIntervalBehaviour(AID cmAid) {
		this.rmAid = cmAid;
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		reqInterval = (float)Math.random();
		return reqInterval;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(rmAid);
	    	msg.setLanguage("English");
	    	String reqIntervalStr = String.valueOf(reqInterval);
	    	msg.setContent(reqIntervalStr);
	    	msg.setConversationId("status resource response");
	
	    	myAgent.send(msg);
		}
		else
			block();

	}
}
