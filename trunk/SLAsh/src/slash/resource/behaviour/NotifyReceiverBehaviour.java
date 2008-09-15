package slash.resource.behaviour;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import slash.resource.agent.*;
import slash.dsm.client.DsmClient;
import slash.entity.*;
import slash.dsm.tuple.*;

public class NotifyReceiverBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 4984068838158410640L;

	private ResourceAgent ra;
	private DsmClient dsmClient;
	
	public NotifyReceiverBehaviour(ResourceAgent ra) {
		super(ra, 500);
		this.ra = ra;
		this.dsmClient = new DsmClient(ra);
	}

	protected void onTick() {
		Tuple tuple = dsmClient.in(ra.getLocalName(), "notify"); //TODO: controllare index
		Notify notify = null;
		if(tuple!=null) {
			notify = (Notify)tuple.getValue();
		}
		if(notify!=null) {
			System.out.println("NOTIFY recv: "+notify.getDest()+" on "+myAgent.getLocalName());
			int num = Integer.valueOf(String.valueOf(myAgent.getLocalName().charAt(myAgent.getLocalName().length()-1)));
			
			if(notify.getSrc() == num) {
				ra.setLocalSC(false);
			}
			else if(notify.getDest() == num) {
				ra.setLocalSC(true);
			}
			else
				ra.setLocalSC(false);
		}
	}

}
