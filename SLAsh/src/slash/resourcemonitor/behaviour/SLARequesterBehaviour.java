package slash.resourcemonitor.behaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import slash.df.DFUtil;

public class SLARequesterBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 1897715821469100876L;
	
	public void action() {
		try {
			DFAgentDescription[] res = DFUtil.search(myAgent, "publisher");
			int index = (int)(Math.random()%(res.length-1));
			AID publisher = res[index].getName();
			System.out.println("Selected publisher: "+publisher.getLocalName());
			
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(publisher);
			msg.setLanguage("English");
			msg.setConversationId("SLAContract request");
			myAgent.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
