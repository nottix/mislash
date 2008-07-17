package slash.contextmanager.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import slash.contextmanager.agent.*;
import slash.df.DFUtil;

public class ResourceRequesterBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 8209781904561474880L;
	
	private AID cpuAid;
	private AID energyAid;
	private AID memoryAid;
	private AID ramAid;

	public ResourceRequesterBehaviour(ContextManagerAgent agent) {
		super(agent, 1000);
		cpuAid = DFUtil.search(agent, "cpu"+agent.getName().charAt(2), "resource");
		energyAid = DFUtil.search(agent, "energy"+agent.getName().charAt(2), "resource");
		memoryAid = DFUtil.search(agent, "memory"+agent.getName().charAt(2), "resource");
		ramAid = DFUtil.search(agent, "ram"+agent.getName().charAt(2), "resource");
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
