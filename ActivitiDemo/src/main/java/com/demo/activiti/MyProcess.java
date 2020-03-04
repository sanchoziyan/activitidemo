package com.demo.activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class MyProcess {
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程定义
	 */
	@Test
	public void deploymentProcessDefinition() {
		Deployment deploy = processEngine.getRepositoryService()//流程定义和部署对象相关service
										.createDeployment() //创建一个部署对象
										.name("activitidemo")
										.addClasspathResource("diagrams/MyProcess.bpmn")//从classpath的资源中加载，一次只能加载一个文件
										.addClasspathResource("diagrams/MyProcess.png")
										.deploy(); //完成部署 返回一个部署对象
		System.out.println(deploy);
		System.out.println("部署id:"+deploy.getId()+"， 部署名称："+deploy.getName());
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void startProcessInstance() {
		String processDefinitonKey = "myProcess"; //流程定义的key
		ProcessInstance p1 = processEngine.getRuntimeService() //正在执行的流程实例和执行对象相关的service
		.startProcessInstanceByKey(processDefinitonKey); //使用流程定义的key启动流程实例，key 对应myprocess.bpmn文件中的properties id的值myProcess
														//使用key 启用默认按照流程最新的版本的流程定义启动
		System.out.println("流程实例id:"+p1.getId());
		System.out.println("流程定义id:"+p1.getProcessDefinitionId());
	}
	
	/**
	 * 查询当前人的个人任务
	 */
	@Test
	public void findMyProcessTask() {
		String assignee = "王五"; //张三 李四 王五
		List<Task> list = processEngine.getTaskService() //与正在执行的任务管理相关的service
						.createTaskQuery() //创建任务查询对象
						.taskAssignee(assignee) //指定个人任务查询，指定办理人
						.list();
		if(list !=null) {
			for(Task task:list) {
				System.out.println("任务id："+task.getId()+"，任务名称:"+task.getName()+",任务的创建时间："+task.getCreateTime()+",流程实例id:"+task.getProcessInstanceId());
				System.out.println("执行对象的id："+task.getExecutionId()+",流程定义id:"+task.getProcessDefinitionId());
			}
		}
	}
	

	/**
	 * 完成我的任务
	 */
	@Test
	public void completeMyProcessTask() {
		String taskId = "12502"; //2508 10002 12502
		processEngine.getTaskService() //与正在执行的任务管理相关的service
		.complete(taskId);
		System.out.println("完成任务,任务id："+taskId);
	}
}
