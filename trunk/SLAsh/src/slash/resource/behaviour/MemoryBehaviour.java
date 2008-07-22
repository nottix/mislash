package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resource.agent.*;
import slash.util.DataWriter;

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
		this.memory = (float)((Math.random()*100)%60);
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		if(agent.isLocalSC())
			memory += 0.2*(float)((Math.random()*100)%10);
		else
			memory -= 0.2*(float)((Math.random()*100)%10);
		
		if(memory<0)
			memory = 0;
		
		return memory;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();
			DataWriter.writeData(myAgent.getLocalName(), memory);

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
