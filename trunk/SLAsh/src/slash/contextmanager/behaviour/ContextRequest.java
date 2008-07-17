package slash.contextmanager.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import slash.contextmanager.agent.*;
import slash.df.DFUtil;

public class ContextRequest extends TickerBehaviour {

	private static final long serialVersionUID = 8209781904561474880L;
	
	private AID cpuAid;

	public ContextRequest(ContextManagerAgent agent) {
		super(agent, 1000);
		cpuAid = DFUtil.search(agent, "rs"+agent.getName().charAt(2)+"-cpu", "resource");
	}
	
	protected void onTick() {
		System.out.println("Context request...");
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
    	msg.addReceiver(cpuAid);
		msg.setLanguage("English");
		msg.setContent("actual context");
		msg.setConversationId("richiesta contesto attuale");
		myAgent.send(msg);
	}
	
}
