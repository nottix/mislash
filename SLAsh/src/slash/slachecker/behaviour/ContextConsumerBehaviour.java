package slash.slachecker.behaviour;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.Tuple;
import slash.entity.Context;
import slash.entity.SLAContract;
import slash.slachecker.agent.SLACheckerAgent;
import slash.util.PropertiesReader;

public class ContextConsumerBehaviour extends TickerBehaviour {
	
	private static final long serialVersionUID = 5235996069711181357L;

	private SLACheckerAgent sc;
	private DsmClient dsmClient;

	public ContextConsumerBehaviour(SLACheckerAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("contextconsumer.tick")));
		this.sc = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		for(int i=0; i<sc.getContractList().size(); i++) {
			SLAContract contract = sc.getContractList().get(i);

			Tuple tuple = dsmClient.in(contract.getPublisher().getLocalName(), "context");
			if(tuple!=null && tuple.getValue()!=null) {
				this.sc.getContextTable().put(new AID("cm"+tuple.getIndex(), AID.ISLOCALNAME), (Context)tuple.getValue());
			}
			
			tuple = dsmClient.in(contract.getSubscriber().getLocalName(), "context");
			if(tuple!=null && tuple.getValue()!=null) {
				this.sc.getContextTable().put(new AID("cm"+tuple.getIndex(), AID.ISLOCALNAME), (Context)tuple.getValue());
			}
		}
	}

}
