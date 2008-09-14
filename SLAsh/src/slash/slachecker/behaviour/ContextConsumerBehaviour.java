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
		super(agent, 500); //TODO: da verificare starvation
		this.sc = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		Tuple tuple = dsmClient.in("context");
		if(tuple != null) {
			this.sc.getContextTable().put(new AID("rm"+tuple.getIndex(), AID.ISLOCALNAME), (Context)tuple.getValue());
		}
	}

}
