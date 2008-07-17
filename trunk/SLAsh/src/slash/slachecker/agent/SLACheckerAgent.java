/**
 * 
 */
package slash.slachecker.agent;

import jade.core.*;
import slash.slachecker.behaviour.*;
import slash.df.*;

/**
 * @author Simone Notargiacomo, Lorenzo Tavernese, Ibrahim Khalili
 *
 */
public class SLACheckerAgent extends Agent {

	private static final long serialVersionUID = -7918542991436312908L;
	
	protected void setup() {
		System.out.println("SlaChecker: "+this.getName());
		DFUtil.register(this, this.getLocalName(), "sla-checker");
		this.addBehaviour(new ContextReceiverBehaviour(this));
	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "sla-checker");
	}

}
