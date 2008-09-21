package slash.contextmanager.behaviour;

import jade.core.behaviours.TickerBehaviour;
import slash.contextmanager.agent.ContextManagerAgent;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.Tuple;
import slash.util.PropertiesReader;

public class ViolationConsumerBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -5411307240223204565L;

	private ContextManagerAgent cm;
	private DsmClient dsmClient;
	
	public ViolationConsumerBehaviour(ContextManagerAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("violationreceiver.tick")));
		this.cm = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		Tuple tuple = dsmClient.in(myAgent.getLocalName(), "slacontract-violation"); 
		if(tuple!=null && tuple.getValue()!=null)
			System.out.println("Contract violated!!!");
	}
}
