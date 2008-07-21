package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resource.agent.*;

public class MemoryBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float memory;
	
	private AID cmAid;
	private MemoryAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public MemoryBehaviour(AID cmAid, MemoryAgent agent) {
		this.cmAid = cmAid;
		this.agent = agent;
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		memory = (float)((Math.random()*100)%60);
		if(agent.isLocalSC())
			memory +=(float)((Math.random()*100)%40);
		return memory;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(cmAid);
	    	msg.setLanguage("English");
	    	String cpuStr = String.valueOf(memory);
	    	msg.setContent(cpuStr);
	    	msg.setConversationId("resource response");
	
	    	myAgent.send(msg);
		}
		else
			block();

	}
}
