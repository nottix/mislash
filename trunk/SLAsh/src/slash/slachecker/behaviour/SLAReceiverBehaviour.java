package slash.slachecker.behaviour;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.core.behaviours.*;
import slash.slachecker.agent.*;
import slash.dsm.client.DsmClient;
import slash.entity.*;
import slash.dsm.tuple.*;

public class SLAReceiverBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -3897026802009387366L;

	private SLACheckerAgent sc;
	private DsmClient dsmClient;
	
	public SLAReceiverBehaviour(SLACheckerAgent agent) {
		super(agent, 1000);
		this.sc = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	protected void onTick() {
		Tuple tuple = dsmClient.in("slacontract", "slacontract");
		//System.out.println("Checking contract");
		if(tuple!=null && tuple.getValue()!=null) {
			System.out.println("Added Contract: "+((SLAContract)tuple.getValue()).getLatency());
			sc.getContractList().add((SLAContract)tuple.getValue());
		}
	}
}
