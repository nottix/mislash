package slash.contextmanager.behaviour;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.contextmanager.agent.ContextManagerAgent;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.*;

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
		Tuple tuple = dsmClient.in(myAgent.getLocalName(), "cpu");
		if(tuple!=null)
			agent.getContext().addCpuValue((Float)tuple.getValue());
		
		tuple = dsmClient.in(myAgent.getLocalName(), "energy");
		if(tuple!=null)
			agent.getContext().addEnergyValue((Float)tuple.getValue());
		
		tuple = dsmClient.in(myAgent.getLocalName(), "memory");
		if(tuple!=null)
			agent.getContext().addMemoryValue((Float)tuple.getValue());
		
		tuple = dsmClient.in(myAgent.getLocalName(), "ram");
		if(tuple!=null)
			agent.getContext().addRamValue((Float)tuple.getValue());
	}

}
