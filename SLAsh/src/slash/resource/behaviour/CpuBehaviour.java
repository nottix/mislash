package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resource.agent.*;
import slash.entity.*;

public class CpuBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float cpu;
	
	private AID cmAid;
	private CpuAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public CpuBehaviour(AID cmAid, CpuAgent agent) {
		this.cmAid = cmAid;
		this.agent = agent;
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		cpu = (float)((Math.random()*100)%60);
		if(agent.isLocalSC()) {
			System.out.println("LOCAL: "+myAgent.getLocalName());
			cpu += (float)((Math.random()*100)%40);
		}
			
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
