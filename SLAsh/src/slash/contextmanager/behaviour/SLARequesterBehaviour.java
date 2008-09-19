package slash.contextmanager.behaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import slash.df.DFUtil;
import slash.dsm.client.DsmClient;
import slash.contextmanager.agent.*;

public class SLARequesterBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 1897715821469100876L;
	
	private DsmClient dsmClient;
	private ContextManagerAgent cm;
	
	public SLARequesterBehaviour(ContextManagerAgent agent) {
		this.cm = agent;
		dsmClient = new DsmClient(agent);
	}
	
	public void action() {
		try {
			DFAgentDescription[] res = DFUtil.search(myAgent, "publisher");
			int index = (int)(Math.random()%(res.length-1));
			AID publisher = res[index].getName();
			System.out.println("Selected publisher: "+publisher.getLocalName());
			
			dsmClient.out("slacontract", "slacontract-request", myAgent.getLocalName());
			//System.out.println("Request sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
