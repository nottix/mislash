package slash.contextmanager.behaviour;

import jade.core.behaviours.TickerBehaviour;
import slash.contextmanager.agent.ContextManagerAgent;
import slash.dsm.client.DsmClient;
import slash.util.PropertiesReader;

public class ContextProducerBehaviour extends TickerBehaviour{

	private static final long serialVersionUID = -884539926477308910L;
	
	private ContextManagerAgent cm;
	private DsmClient dsmClient;
	
	public ContextProducerBehaviour(ContextManagerAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("contextproducer.tick")));
		this.cm = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
    	dsmClient.out(myAgent.getLocalName(), "context", cm.getContext());
	}
	
}
