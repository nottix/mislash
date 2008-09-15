package slash.slachecker.behaviour;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import slash.slachecker.agent.*;
import slash.dsm.client.DsmClient;
import slash.entity.*;
import slash.dsm.tuple.*;
import jade.core.*;

public class ContextConsumerBehaviour extends TickerBehaviour {
	
	private static final long serialVersionUID = 5235996069711181357L;

	private SLACheckerAgent sc;
	private DsmClient dsmClient;

	public ContextConsumerBehaviour(SLACheckerAgent agent) {
		super(agent, 100); //TODO: da verificare starvation
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
