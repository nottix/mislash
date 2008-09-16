package slash.slachecker.util;

import jade.core.*;
import jade.lang.acl.ACLMessage;
import slash.dsm.client.*;
import java.io.IOException;

import slash.entity.Notify;

public class MigrationUtil {
	
	private static DsmClient dsmClient = null; 
	
	public static void notifyMigration(Agent agent, int src, int dest) {
		if(dsmClient==null)
			dsmClient = new DsmClient(agent);
		Notify notify = new Notify(src, dest);
		//dsmClient.out("migration", "notify", notify);

		dsmClient.out("notify", "notify", notify);
	}
}
