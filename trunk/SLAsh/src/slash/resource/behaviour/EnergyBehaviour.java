package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resource.agent.*;

public class EnergyBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float energy;
	
	private AID cmAid;
	private EnergyAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public EnergyBehaviour(AID cmAid, EnergyAgent agent) {
		this.cmAid = cmAid;
		this.agent = agent;
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		energy = (float)(Math.random()*100);
		return energy;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(cmAid);
	    	msg.setLanguage("English");
	    	String cpuStr = String.valueOf(energy);
	    	msg.setContent(cpuStr);
	    	msg.setConversationId("resource response");
	
	    	myAgent.send(msg);
		}
		else
			block();

	}
}
