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

public class StatusConsumerBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -7122302752623856233L;
	
	private SLACheckerAgent sc;
	private DsmClient dsmClient;
	
	public StatusConsumerBehaviour(SLACheckerAgent agent) {
		super(agent, 500);
		this.sc = agent;
		this.dsmClient = new DsmClient(agent);
	}

	protected void onTick() {
		Tuple tuple = dsmClient.in("status");
		if(tuple!=null) {
			sc.getStatusTable().put(new AID("rm"+tuple.getIndex(), AID.ISLOCALNAME), (Status)tuple.getValue());
		}
	}
}
