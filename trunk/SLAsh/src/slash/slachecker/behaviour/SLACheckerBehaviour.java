package slash.slachecker.behaviour;

import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import slash.slachecker.agent.*;
import slash.entity.*;
import java.io.*;

public class SLACheckerBehaviour extends TickerBehaviour {
	
	private static final long serialVersionUID = -500981357584073158L;
	
	private SLACheckerAgent sc;
	
	public SLACheckerBehaviour(SLACheckerAgent sc) {
		super(sc, 1000);
		this.sc = sc;
	}
	
	protected void onTick() {
		for(int i=0; i<sc.getContractList().size(); i++) {
			SLAContract contract = sc.getContractList().get(i);
			Status status = sc.getStatusTable().get(contract.getPublisher());
			if((status!=null) && ((status.getAvgLatency() > contract.getLatency()) || (status.getAvgReliability() > contract.getReliability()) || (status.getAvgReqInterval() > contract.getReqInterval()))) {
				
				try {
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(contract.getPublisher());
					msg.setLanguage("English");
					msg.setContentObject((Serializable)contract);
					msg.setConversationId("contract violation");
					myAgent.send(msg);
					
					msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(contract.getSubscriber());
					msg.setLanguage("English");
					msg.setContentObject((Serializable)contract);
					msg.setConversationId("contract violation");
					myAgent.send(msg);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
