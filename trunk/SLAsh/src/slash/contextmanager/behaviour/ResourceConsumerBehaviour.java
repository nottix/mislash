package slash.contextmanager.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.contextmanager.agent.ContextManagerAgent;
import slash.dsm.client.DsmClient;

public class ResourceConsumerBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private ContextManagerAgent agent;
	private DsmClient dsmClient;
	
	public ResourceConsumerBehaviour(ContextManagerAgent agent) {
		super(agent, 1000);
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		agent.getContext().addCpuValue((Float)dsmClient.in(myAgent.getLocalName(), "cpu"));
		agent.getContext().addEnergyValue((Float)dsmClient.in(myAgent.getLocalName(), "energy"));
		agent.getContext().addMemoryValue((Float)dsmClient.in(myAgent.getLocalName(), "memory"));
		agent.getContext().addRamValue((Float)dsmClient.in(myAgent.getLocalName(), "ram"));
	}

}
