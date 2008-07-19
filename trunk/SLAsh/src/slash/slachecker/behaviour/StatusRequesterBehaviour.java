package slash.slachecker.behaviour;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import slash.slachecker.agent.SLACheckerAgent;

public class StatusRequesterBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 1451348345165211858L;

	private AID rmAid;
	private SLACheckerAgent sc;
	
	public StatusRequesterBehaviour(SLACheckerAgent agent) {
		super(agent, 1000);
		this.sc = agent;
	}
	
	protected void onTick() {
		for(int i=0; i<sc.getContractList().size(); i++) {
			rmAid = sc.getContractList().get(i).getSubscriber();
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(rmAid);
			msg.setLanguage("English");
			msg.setConversationId("status request");
			myAgent.send(msg);
			
			rmAid = sc.getContractList().get(i).getPublisher();
			msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(rmAid);
			msg.setLanguage("English");
			msg.setConversationId("status request");
			myAgent.send(msg);
		}
	}
}
