package com.xingguang.activiti.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingguang.activiti.service.ILeaveService;

@Service
public class LeaveServiceImpl implements ILeaveService {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	@Override
	public void startProcess() {
		Map<String , Object> vars = new HashMap<String , Object>();
		vars.put("applyUser", "zhangsan");
		runtimeService.startProcessInstanceByKey("leaveProcess" , vars);
	}

	@Override
	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}

}
