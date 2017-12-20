package com.xingguang.activiti.service;

import java.util.List;

import org.activiti.engine.task.Task;

public interface IMyService {
	
	public String leaveApply(String applyUser , String auditUser , int days);
	
	public void leaveAudit(String taskId , String approveUser);
	
	public List<Task> getTasks(String assignee);

}
