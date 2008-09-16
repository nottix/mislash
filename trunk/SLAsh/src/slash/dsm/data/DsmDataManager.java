package slash.dsm.data;

import java.util.*;
import slash.dsm.tuple.*;
import slash.entity.*;
import java.io.*;
import java.math.*;

public class DsmDataManager implements Serializable {

	Hashtable<String, Hashtable<String, Queue<Object>>> tupleSpace;
	
	public DsmDataManager() {
		this.tupleSpace = new Hashtable<String, Hashtable<String, Queue<Object>>>();
	}
	
	public synchronized int out(String index, String type, Object value) {	
		if(index==null)
			System.out.println("ERRORE");
		else if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Queue<Object> queue = this.tupleSpace.get(index).get(type);
				queue.offer(value);
			}
			else {
				Hashtable<String, Queue<Object>> oldType = this.tupleSpace.get(index);
				Queue<Object> queue = new LinkedList<Object>();
				queue.offer(value);
				oldType.put(type, queue);
			}
		} //Se hashtable per index non esistente
		else {
			Hashtable<String, Queue<Object>> newType = new Hashtable<String, Queue<Object>>();
			Queue<Object> queue = new LinkedList<Object>();
			queue.offer(value);
			newType.put(type, queue);
			
			this.tupleSpace.put(index, newType);
		}
		
		return 0;
	}
	
	public synchronized int update(String index, String type, Object value) {	
		if(index==null)
			System.out.println("ERRORE");
		else if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Queue<Object> queue = this.tupleSpace.get(index).get(type);
				queue.clear();
				queue.offer(value);
			}
			else {
				Hashtable<String, Queue<Object>> oldType = this.tupleSpace.get(index);
				Queue<Object> queue = new LinkedList<Object>();
				queue.offer(value);
				oldType.put(type, queue);
			}
		} //Se hashtable per index non esistente
		else {
			Hashtable<String, Queue<Object>> newType = new Hashtable<String, Queue<Object>>();
			Queue<Object> queue = new LinkedList<Object>();
			queue.offer(value);
			newType.put(type, queue);
			
			this.tupleSpace.put(index, newType);
		}
		
		return 0;
	}
	
	public synchronized Tuple in(String index, String type) {
		if(index==null) {
			Enumeration<Hashtable<String, Queue<Object>>> enumeration = this.tupleSpace.elements();
			Enumeration<String> keys = this.tupleSpace.keys();
			while(enumeration.hasMoreElements() && keys.hasMoreElements()) {
				String key = keys.nextElement();
				//System.out.println("TYPE: "+key);
				Hashtable<String, Queue<Object>> typeTable = enumeration.nextElement();
				//if((Math.random()%2)==1) {
				//	System.out.println("continue");
				//	continue;
				//}
				Queue<Object> queue = typeTable.get(type);
				//System.out.println("IN -> type: "+type);
				if(queue!=null) {
					//System.out.println("QUEUE OK");
					Object obj = queue.poll();
					return new Tuple(Tuple.IN, type, key, obj);
				}
			}
		}
		else if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Queue<Object> queue = this.tupleSpace.get(index).get(type);
				Object obj = queue.poll();
				return new Tuple(Tuple.IN, type, index, obj);
			}
		}
		
		return null;
	}
	
	public synchronized Tuple read(String index, String type) {
		//System.out.println("READ: "+index+", type: "+type);
		if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Queue<Object> queue = this.tupleSpace.get(index).get(type);
				Object obj = queue.peek();
				return new Tuple(Tuple.IN, type, index, obj);
			}
		}
		
		return null;
	}
}
