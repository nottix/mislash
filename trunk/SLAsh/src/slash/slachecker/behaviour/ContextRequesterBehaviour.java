package slash.slachecker.behaviour;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import slash.slachecker.agent.SLACheckerAgent;

public class ContextRequesterBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -426252406210286840L;
	
	private AID cmAid;
	private SLACheckerAgent sc;
	
	public ContextRequesterBehaviour(SLACheckerAgent agent) {
		super(agent, 1000);
		this.sc = agent;
	}
	
	protected void onTick() {
		for(int i=0; i<sc.getContractList().size(); i++) {
			cmAid = sc.getContractList().get(i).getCmSub();
			System.out.println("Sending context request to "+cmAid.getLocalName());
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(cmAid);
			msg.setLanguage("English");
			msg.setConversationId("context request");
			myAgent.send(msg);
			
			cmAid = sc.getContractList().get(i).getCmPub();
			System.out.println("2Sending context request to "+cmAid.getLocalName());
			msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(cmAid);
			msg.setLanguage("English");
			msg.setConversationId("context request");
			myAgent.send(msg);
		}
	}
}
