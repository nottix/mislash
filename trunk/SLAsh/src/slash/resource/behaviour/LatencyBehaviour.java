package slash.resource.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.entity.Context;
import slash.resource.agent.LatencyAgent;
import slash.util.DataWriter;

public class LatencyBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private float latency;
	
	private AID rmAid;
	private LatencyAgent agent;
	private MessageTemplate mt;
	private ACLMessage recvMsg;
	
	public LatencyBehaviour(AID cmAid, LatencyAgent agent) {
		this.rmAid = cmAid;
		this.agent = agent;
		mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	}
	
	private float generate() {
		latency = (float)((Math.random()*100)%60);
		if(agent.isLocalSC() || agent.getNetwork() == Context.WIRELESS) {
			latency += (float)(((Math.random()*100)/(agent.getBandwidth()/50))%40);
		}
		//latency /= ((float)agent.getBandwidth())*0.2;
		//latency %= 100;
		//if(agent.getNetwork() == Context.WIRED) {
		//	latency /= 2;
		//}
		
		return latency;
	}
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		
		if(recvMsg!=null) {
			generate();
			DataWriter.writeData(myAgent.getLocalName(), latency);
			
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
