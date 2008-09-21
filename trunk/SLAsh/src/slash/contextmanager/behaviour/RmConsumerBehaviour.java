package slash.contextmanager.behaviour;

import jade.core.behaviours.TickerBehaviour;
import slash.contextmanager.agent.ContextManagerAgent;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.Tuple;
import slash.util.PropertiesReader;

public class RmConsumerBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private ContextManagerAgent agent;
	private DsmClient dsmClient;
	
	public RmConsumerBehaviour(ContextManagerAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("resourceconsumer.tick")));
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		Tuple tuple = dsmClient.read(myAgent.getLocalName(), "rm-cpu");
		if(tuple!=null) {
			agent.getContext().addCpuValue((Float)tuple.getValue());
		}
		
		tuple = dsmClient.read(myAgent.getLocalName(), "rm-energy");
		if(tuple!=null)
			agent.getContext().addEnergyValue((Float)tuple.getValue());
		
		tuple = dsmClient.read(myAgent.getLocalName(), "rm-memory");
		if(tuple!=null)
			agent.getContext().addMemoryValue((Float)tuple.getValue());
		
		tuple = dsmClient.read(myAgent.getLocalName(), "rm-ram");
		if(tuple!=null)
			agent.getContext().addRamValue((Float)tuple.getValue());
		
		tuple = dsmClient.read(myAgent.getLocalName(), "rm-latency");
		if(tuple!=null)
			agent.getContext().addLatencyValue((Float)tuple.getValue());
		
		tuple = dsmClient.read(myAgent.getLocalName(), "rm-reliability");
		if(tuple!=null)
			agent.getContext().addReliabilityValue((Float)tuple.getValue());
		
		tuple = dsmClient.read(myAgent.getLocalName(), "rm-reqInterval");
		if(tuple!=null)
			agent.getContext().addReqIntervalValue((Float)tuple.getValue());
	}

}
