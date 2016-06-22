package com.yunshan.testframe.util.config;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ReloadMonitor {

	private ConcurrentHashMap objMap=new ConcurrentHashMap();
	private int interval;
	private Timer timer;
	
	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void addReloadListener(String module,ReloadListener l){
		MonitoredObj obj=(MonitoredObj)objMap.get(module);
		if(obj!=null){
			obj.addListener(l);
		}else{
			obj=new MonitoredObj(module);
			obj.addListener(l);
			objMap.put(module,obj);
		}
	}
	
	public void removeReloadListener(String module,ReloadListener l){
		MonitoredObj obj=(MonitoredObj)objMap.get(module);
		if(obj!=null)
			obj.removeListener(l);
		
	}
	
	public abstract long getLastModified(String module);
	
	public void start(){
		
		timer=new Timer(true);
		timer.schedule(new CheckReloadTask(),interval*1000,interval*1000);
		
	}
	
	public void stop(){
		timer.cancel();
	}
	
	private class CheckReloadTask extends TimerTask{

		@Override
		public void run() {
			Iterator iter=objMap.values().iterator();
			while(iter.hasNext()){
				MonitoredObj obj=(MonitoredObj)iter.next();
				long time=getLastModified(obj.getModule());
				if(time>obj.getLastModified()){
					obj.setLastModified(time);
					obj.notifyListener();
				}
			}
		}
		
	}
	
	private class MonitoredObj{
		private String module;
		private long lastModified;
		private List listeners;
		
		public MonitoredObj(String module){
			this.module=module;
			lastModified=System.currentTimeMillis();
			listeners=new LinkedList();
		}
		
		public synchronized void addListener(ReloadListener l){
			listeners.add(l);
		}
		
		public synchronized void removeListener(ReloadListener l){
			listeners.remove(l);
		}
		
		public void notifyListener(){
			Object obj[];
			
			synchronized(this){
				obj=listeners.toArray();
			}
			
			if(obj==null)
				return;
			for(int i=0;i<obj.length;i++){
				ReloadListener l=(ReloadListener)obj[i];
				l.reload(module);
			}
			
		}

		public String getModule() {
			return module;
		}

		public void setModule(String key) {
			this.module = key;
		}

		public long getLastModified() {
			return lastModified;
		}

		public void setLastModified(long lastModified) {
			this.lastModified = lastModified;
		}
	}
}
