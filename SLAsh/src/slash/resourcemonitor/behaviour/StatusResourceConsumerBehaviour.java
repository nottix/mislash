package slash.resourcemonitor.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.resourcemonitor.agent.*;
import slash.util.PropertiesReader;
import slash.dsm.tuple.*;

public class StatusResourceConsumerBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private ResourceMonitorAgent rm;
	private DsmClient dsmClient;
	
	public StatusResourceConsumerBehaviour(ResourceMonitorAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("statusresourceconsumer.tick")));
		this.rm = agent;
		this.dsmClient = new DsmClient(agent);
	}

	protected void onTick() {
		
		Tuple tuple = dsmClient.in(myAgent.getLocalName(), "latency");
		if(tuple!=null)
			rm.getStatus().addLatencyValue((Float)tuple.getValue());
		
		tuple = dsmClient.in(myAgent.getLocalName(), "reliability");
		if(tuple!=null)
			rm.getStatus().addReliabilityValue((Float)tuple.getValue());
		
		tuple = dsmClient.in(myAgent.getLocalName(), "reqInterval");
		if(tuple!=null)
			rm.getStatus().addReqIntervalValue((Float)tuple.getValue());
	}
}
