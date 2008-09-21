package slash.resourcemonitor.behaviour;

import jade.core.behaviours.TickerBehaviour;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.Tuple;
import slash.resourcemonitor.agent.ReqIntervalRmAgent;
import slash.util.PropertiesReader;

public class ReqIntervalResourceBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private ReqIntervalRmAgent agent;
	private DsmClient dsmClient;
	
	public ReqIntervalResourceBehaviour(ReqIntervalRmAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("resourceconsumer.tick")));
		this.agent = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		Tuple tuple = dsmClient.read(myAgent.getLocalName(), "reqInterval");
		if(tuple!=null) {
			dsmClient.update(myAgent.getLocalName(), "rm-reqInterval", tuple.getValue());
		}
	}

}
