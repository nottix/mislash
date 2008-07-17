package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CpuBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float cpu;
	
	private AID cmAid;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public CpuBehaviour(AID cmAid) {
		this.cmAid = cmAid;
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		cpu = (float)Math.random();
		return cpu;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(cmAid);
	    	msg.setLanguage("English");
	    	String cpuStr = String.valueOf(cpu);
	    	msg.setContent(cpuStr);
	    	msg.setConversationId("resource response");
	    	myAgent.send(msg);
		}
		else
			block();

	}
}
