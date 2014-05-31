package com.emobuzz.util;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;


public class QueueExecution {
	
	public final static String POST = "post";
	public final static String GET = "get";
	
	private Queue queue=null;
	public QueueExecution(){
    	queue = QueueFactory.getDefaultQueue();
	}	
	
	public void getQueue(){
		queue = QueueFactory.getDefaultQueue();
	}
	public void getQueue(String queue_str){
		queue = QueueFactory.getQueue(queue_str);
	}
	
	private volatile String[] parameters;
	public void setParameters(String[] parameters) {
		
		if(parameters==null){
			System.err.println("Parameters can not be null");
			return;
		}
		this.parameters = parameters;
	}

	private volatile String[] value;
	public void setValue(String[] value) {
		if(value==null){
			System.err.println("Values must not be null");
			return;
		}
		if(value.length!=this.parameters.length){
			System.err.println("No of values must be equals to no of parameters");
			return;
		}
		this.value = value;
	}
	
	public void fireLocalQueue(final String serveletToCall, final String method_type){
		TaskOptions tashOpt = TaskOptions.Builder.withUrl("/"+serveletToCall);
		if(method_type.equals(POST)){
			tashOpt.method(Method.POST);
			execute(tashOpt);
		}
		else if(method_type.equals(GET)){
			tashOpt.method(Method.GET);
			execute(tashOpt);
		}
		else{
			System.err.println("Failed to excute queue : unidentified request type");
		}
	}
	
	public void fireQueue(final String serveletToCall, final String method_type, String backendName){
		TaskOptions tashOpt = TaskOptions.Builder.withUrl(serveletToCall);
		if(method_type.equals(POST)){
			tashOpt.method(Method.POST);
			
		}
		else if(method_type.equals(GET)){
			tashOpt.method(Method.GET);
			
		}
		else{
			System.err.println("Failed to excute queue : unidentified request type");
		}
		if(backendName!=null){
			tashOpt.header("Host",backendName);
		}
		execute(tashOpt);
	}
	
	private void execute(TaskOptions tashOpt){
		for(int i = 0, size=parameters.length; i < size; i ++){
			tashOpt.param(parameters[i], value[i]);
		}
		queue.add(tashOpt);
	}
}