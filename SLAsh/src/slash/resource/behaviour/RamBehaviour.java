package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resource.agent.*;
import slash.util.DataWriter;

public class RamBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float ram;
	
	private AID cmAid;
	private RamAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public RamBehaviour(AID cmAid, RamAgent agent) {
		this.cmAid = cmAid;
		this.agent = agent;
		
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		ram = (float)((Math.random()*100)%60);
		if(agent.isLocalSC()) {
			//ram += (float)((Math.random()*100)%40);
			ram += 40;
		}
		
		return ram;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();
			DataWriter.writeData(myAgent.getLocalName(), ram);

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(cmAid);
	    	msg.setLanguage("English");
	    	String cpuStr = String.valueOf(ram);
	    	msg.setContent(cpuStr);
	    	msg.setConversationId("resource response");
	
	    	myAgent.send(msg);
		}
		else
			block();

	}
}
