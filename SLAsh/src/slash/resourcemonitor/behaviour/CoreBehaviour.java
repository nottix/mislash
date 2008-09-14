package slash.resourcemonitor.behaviour;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import slash.resourcemonitor.agent.*;
import jade.core.*;

/**
 * OBSOLETO
 * @author avenger
 *
 */
public class CoreBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 4263686587549217966L;
	
	private ResourceMonitorAgent rm;
	
	private AID latencyAid;
	private AID reliabilityAid;
	private AID reqIntervalAid;
	
	public CoreBehaviour(ResourceMonitorAgent agent) {
		super(agent, 1000);
		this.rm = agent;
		
		latencyAid = new AID("latency"+myAgent.getLocalName().charAt(2), AID.ISLOCALNAME);
		reliabilityAid = new AID("reliability"+myAgent.getLocalName().charAt(2), AID.ISLOCALNAME);
		reqIntervalAid = new AID("reqInterval"+myAgent.getLocalName().charAt(2), AID.ISLOCALNAME);
	}
	
	protected void onTick() {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
    	msg.addReceiver(latencyAid);
		msg.setLanguage("English");
		msg.setConversationId("status resource request");
		myAgent.send(msg);
		
		msg = new ACLMessage(ACLMessage.REQUEST);
    	msg.addReceiver(reliabilityAid);
		msg.setLanguage("English");
		msg.setConversationId("status resource request");
		myAgent.send(msg);
		
		msg = new ACLMessage(ACLMessage.REQUEST);
    	msg.addReceiver(reqIntervalAid);
		msg.setLanguage("English");
		msg.setConversationId("status resource request");
		myAgent.send(msg);
	}

}
