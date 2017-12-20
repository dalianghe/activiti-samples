package com.xingguang.activiti.service;

import java.util.List;

import org.activiti.engine.task.Task;

public interface ILeaveService {
	
	/**
	 * 开启流程
	 */
	public void startProcess();
	
	/**
	 * 查询待处理任务
	 * @param assignee
	 * @return
	 */
	public List<Task> getTasks(String assignee);

}
