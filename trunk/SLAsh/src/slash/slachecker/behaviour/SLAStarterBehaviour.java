package slash.slachecker.behaviour;

import java.io.IOException;

import slash.entity.Notify;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import slash.slachecker.agent.*;
import slash.slachecker.util.*;

public class SLAStarterBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -1382758463374415598L;

	private SLACheckerAgent sc;
	
	public void action() {
		MigrationUtil.notifyMigration(myAgent, 0, 1); //Avvertire il primo nodo della presenza dello sc
	}
}
