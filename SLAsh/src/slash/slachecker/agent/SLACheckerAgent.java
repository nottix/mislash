/**
 * 
 */
package slash.slachecker.agent;

import jade.core.*;
import slash.slachecker.behaviour.*;

/**
 * @author Simone Notargiacomo, Lorenzo Tavernese, Ibrahim Khalili
 *
 */
public class SLACheckerAgent extends Agent {

	private static final long serialVersionUID = -7918542991436312908L;
	
	protected void setup() {
		System.out.println("SlaChecker: "+this.getName());
		
		//this.addBehaviour(new ReceiveContextBehaviour(this));
	}

}
