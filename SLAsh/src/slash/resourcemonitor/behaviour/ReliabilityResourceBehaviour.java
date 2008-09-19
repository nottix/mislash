package slash.resourcemonitor.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resourcemonitor.agent.*;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.*;
import slash.util.PropertiesReader;

public class ReliabilityResourceBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private ReliabilityRmAgent agent;
	private DsmClient dsmClient;
	
	public ReliabilityResourceBehaviour(ReliabilityRmAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("resourceconsumer.tick")));
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		Tuple tuple = dsmClient.in(myAgent.getLocalName(), "reliability");
		if(tuple!=null) {
			//System.out.println("Resource consumed -> cpu: "+(Float)tuple.getValue());
			dsmClient.out(myAgent.getLocalName(), "rm-reliability", tuple.getValue());
		}
	}

}
