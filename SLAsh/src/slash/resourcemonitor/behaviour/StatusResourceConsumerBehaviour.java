package slash.resourcemonitor.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.resourcemonitor.agent.*;

public class StatusResourceConsumerBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private ResourceMonitorAgent rm;
	private DsmClient dsmClient;
	
	public StatusResourceConsumerBehaviour(ResourceMonitorAgent agent) {
		super(agent, 1000);
		this.rm = agent;
		this.dsmClient = new DsmClient(agent);
	}

	protected void onTick() {
		
		rm.getStatus().addLatencyValue((Float)dsmClient.in(myAgent.getLocalName(), "latency"));
		rm.getStatus().addReliabilityValue((Float)dsmClient.in(myAgent.getLocalName(), "reliability"));
		rm.getStatus().addReqIntervalValue((Float)dsmClient.in(myAgent.getLocalName(), "reqInterval"));
	}
}
