package slash.slachecker.behaviour;

import jade.core.behaviours.TickerBehaviour;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.Tuple;
import slash.entity.SLAContract;
import slash.slachecker.agent.SLACheckerAgent;
import slash.util.PropertiesReader;

public class SLAReceiverBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3897026802009387366L;

	private SLACheckerAgent sc;
	private DsmClient dsmClient;
	
	public SLAReceiverBehaviour(SLACheckerAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("slareceiver.tick")));
		this.sc = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		Tuple tuple = dsmClient.in("slacontract", "slacontract");
		if(tuple!=null && tuple.getValue()!=null) {
			System.out.println("Added Contract: "+((SLAContract)tuple.getValue()).getLatency());
			sc.getContractList().add((SLAContract)tuple.getValue());
		}
	}
}
