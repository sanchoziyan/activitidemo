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
	 * �������̶���
	 */
	@Test
	public void deploymentProcessDefinition() {
		Deployment deploy = processEngine.getRepositoryService()//���̶���Ͳ���������service
										.createDeployment() //����һ���������
										.name("activitidemo")
										.addClasspathResource("diagrams/MyProcess.bpmn")//��classpath����Դ�м��أ�һ��ֻ�ܼ���һ���ļ�
										.addClasspathResource("diagrams/MyProcess.png")
										.deploy(); //��ɲ��� ����һ���������
		System.out.println(deploy);
		System.out.println("����id:"+deploy.getId()+"�� �������ƣ�"+deploy.getName());
	}
	
	/**
	 * ��������ʵ��
	 */
	@Test
	public void startProcessInstance() {
		String processDefinitonKey = "myProcess"; //���̶����key
		ProcessInstance p1 = processEngine.getRuntimeService() //����ִ�е�����ʵ����ִ�ж�����ص�service
		.startProcessInstanceByKey(processDefinitonKey); //ʹ�����̶����key��������ʵ����key ��Ӧmyprocess.bpmn�ļ��е�properties id��ֵmyProcess
														//ʹ��key ����Ĭ�ϰ����������µİ汾�����̶�������
		System.out.println("����ʵ��id:"+p1.getId());
		System.out.println("���̶���id:"+p1.getProcessDefinitionId());
	}
	
	/**
	 * ��ѯ��ǰ�˵ĸ�������
	 */
	@Test
	public void findMyProcessTask() {
		String assignee = "����"; //���� ���� ����
		List<Task> list = processEngine.getTaskService() //������ִ�е����������ص�service
						.createTaskQuery() //���������ѯ����
						.taskAssignee(assignee) //ָ�����������ѯ��ָ��������
						.list();
		if(list !=null) {
			for(Task task:list) {
				System.out.println("����id��"+task.getId()+"����������:"+task.getName()+",����Ĵ���ʱ�䣺"+task.getCreateTime()+",����ʵ��id:"+task.getProcessInstanceId());
				System.out.println("ִ�ж����id��"+task.getExecutionId()+",���̶���id:"+task.getProcessDefinitionId());
			}
		}
	}
	

	/**
	 * ����ҵ�����
	 */
	@Test
	public void completeMyProcessTask() {
		String taskId = "12502"; //2508 10002 12502
		processEngine.getTaskService() //������ִ�е����������ص�service
		.complete(taskId);
		System.out.println("�������,����id��"+taskId);
	}
}
