package slash.contextmanager.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import slash.contextmanager.agent.*;
import slash.df.DFUtil;

/**
 * OBSOLETO
 * @author avenger
 *
 */
public class ResourceRequesterBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 8209781904561474880L;
	
	private AID cpuAid;
	private AID energyAid;
	private AID memoryAid;
	private AID ramAid;

	public ResourceRequesterBehaviour(ContextManagerAgent agent) {
		super(agent, 1000);
		cpuAid = new AID("cpu"+agent.getName().charAt(2), AID.ISLOCALNAME);
		energyAid = new AID("energy"+agent.getName().charAt(2), AID.ISLOCALNAME);
		memoryAid = new AID("memory"+agent.getName().charAt(2), AID.ISLOCALNAME);
		ramAid = new AID("ram"+agent.getName().charAt(2), AID.ISLOCALNAME);
	}
	
	protected void onTick() {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
    	msg.addReceiver(cpuAid);
		msg.setLanguage("English");
		msg.setContent("context");
		msg.setConversationId("resource request");
		myAgent.send(msg);
		
		msg = new ACLMessage(ACLMessage.REQUEST);
    	msg.addReceiver(energyAid);
		msg.setLanguage("English");
		msg.setContent("context");
		msg.setConversationId("resource request");
		myAgent.send(msg);
		
		msg = new ACLMessage(ACLMessage.REQUEST);
    	msg.addReceiver(memoryAid);
		msg.setLanguage("English");
		msg.setContent("context");
		msg.setConversationId("resource request");
		myAgent.send(msg);
		
		msg = new ACLMessage(ACLMessage.REQUEST);
    	msg.addReceiver(ramAid);
		msg.setLanguage("English");
		msg.setContent("context");
		msg.setConversationId("resource request");
		myAgent.send(msg);
	}
	
}
