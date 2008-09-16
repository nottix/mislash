package slash.slachecker.behaviour;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import slash.slachecker.agent.*;
import slash.util.PropertiesReader;
import slash.dsm.client.DsmClient;
import slash.entity.*;
import slash.dsm.tuple.*;
import jade.core.*;

public class StatusConsumerBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -7122302752623856233L;
	
	private SLACheckerAgent sc;
	private DsmClient dsmClient;
	
	public StatusConsumerBehaviour(SLACheckerAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("statusconsumer.tick")));
		this.sc = agent;
		this.dsmClient = new DsmClient(agent);
	}

	protected void onTick() {
		for(int i=0; i<sc.getContractList().size(); i++) {
			SLAContract contract = sc.getContractList().get(i);

			Tuple tuple = dsmClient.in(contract.getPublisher().getLocalName(), "status");
			if(tuple!=null && tuple.getValue()!=null) {
				sc.getStatusTable().put(new AID("rm"+tuple.getIndex(), AID.ISLOCALNAME), (Status)tuple.getValue());
			}
			
			tuple = dsmClient.in(contract.getSubscriber().getLocalName(), "status");
			if(tuple!=null && tuple.getValue()!=null) {
				sc.getStatusTable().put(new AID("rm"+tuple.getIndex(), AID.ISLOCALNAME), (Status)tuple.getValue());
			}
		}
	}
}
