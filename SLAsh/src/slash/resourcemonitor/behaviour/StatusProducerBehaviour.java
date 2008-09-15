package slash.resourcemonitor.behaviour;

import java.io.IOException;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.resourcemonitor.agent.ResourceMonitorAgent;

public class StatusProducerBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 4138378472593121103L;
	
	private ResourceMonitorAgent rm;
	private DsmClient dsmClient;
	
	public StatusProducerBehaviour(ResourceMonitorAgent agent) {
		super(agent, 1000);
		this.rm = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		dsmClient.out(myAgent.getLocalName(), "status", rm.getStatus());
		System.out.println("Status produced on "+myAgent.getLocalName());
	}
}
