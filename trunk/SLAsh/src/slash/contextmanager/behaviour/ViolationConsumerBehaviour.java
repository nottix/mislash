package slash.contextmanager.behaviour;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.dsm.client.DsmClient;
import slash.contextmanager.agent.*;
import slash.util.PropertiesReader;
import slash.dsm.tuple.*;

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
