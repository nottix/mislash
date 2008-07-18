/**
 * 
 */
package slash.slachecker.agent;

import jade.core.*;
import slash.slachecker.behaviour.*;
import slash.df.*;
import java.util.*;
import slash.entity.*;

/**
 * @author Simone Notargiacomo, Lorenzo Tavernese, Ibrahim Khalili
 *
 */
public class SLACheckerAgent extends Agent {

	private static final long serialVersionUID = -7918542991436312908L;
	
	private LinkedList<SLAContract> contractList = null;
	
	public LinkedList<SLAContract> getContractList() {
		return this.contractList;
	}
	
	protected void setup() {
		this.contractList = new LinkedList<SLAContract>();
		System.out.println("SlaChecker: "+this.getName());
		DFUtil.register(this, this.getLocalName(), "sla-checker");
		this.addBehaviour(new ContextReceiverBehaviour(this));
		this.addBehaviour(new SLAReceiverBehaviour(this));
	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "sla-checker");
	}

}
