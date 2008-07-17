package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReliabilityBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float reliability;
	
	private AID cmAid;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public ReliabilityBehaviour(AID cmAid) {
		this.cmAid = cmAid;
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		reliability = (float)Math.random();
		return reliability;
	}
	
	public void action() {
		System.out.println("Receiving...");
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(cmAid);
	    	msg.setLanguage("English");
	    	String cpuStr = String.valueOf(reliability);
	    	msg.setContent(cpuStr);
	    	msg.setConversationId("context response");
	
	    	myAgent.send(msg);
		}
		else
			block();

	}
}
