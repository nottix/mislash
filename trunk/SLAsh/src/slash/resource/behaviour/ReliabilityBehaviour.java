package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.entity.Context;
import slash.resource.agent.ReliabilityAgent;
import slash.util.DataWriter;

public class ReliabilityBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float reliability;
	
	private AID rmAid;
	private ReliabilityAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public ReliabilityBehaviour(AID cmAid, ReliabilityAgent agent) {
		this.rmAid = cmAid;
		this.agent = agent;
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		reliability = (float)((Math.random()*100)%60);
		if(agent.getNetwork() == Context.WIRED)
			reliability += (float)(((Math.random()*100)*(agent.getBandwidth()/50))%40);
		//if(agent.getNetwork() == Context.WIRED)
		//	reliability *= 2;
		return reliability;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();
			DataWriter.writeData(myAgent.getLocalName(), reliability);

			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	    	msg.addReceiver(rmAid);
	    	msg.setLanguage("English");
	    	String reliabilityStr = String.valueOf(reliability);
	    	msg.setContent(reliabilityStr);
	    	msg.setConversationId("status resource response");
	
	    	myAgent.send(msg);
		}
		else
			block();

	}
}
