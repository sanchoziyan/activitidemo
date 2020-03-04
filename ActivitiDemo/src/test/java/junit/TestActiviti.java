package junit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class TestActiviti {
	/**
	 * ʹ�ô��봴����������Ҫ��23�ű�
	 * 
	 */
	@Test
	public void createTable() {
		//�������ݿ�����
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		processEngineConfiguration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
		//&amp;  jdbc:mysql://localhost:3306/activitidemo?useUnicode=true&amp;characterEncoding=utf8
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activitidemo?serverTimezone=UTC");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("root");
		
		/**
		 * 	DB_SCHEMA_UPDATE_FALSE = "false";�����Զ���������Ҫ�����
		 *  DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";��ɾ�����ٴ�����
		 *  DB_SCHEMA_UPDATE_TRUE = "true";��������ڣ��Զ�������
		 */
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//�������ĺ��Ķ���processEngine���������������
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println("processEngine:"+processEngine);
	}
	
	/**
	 * ʹ�������ļ�������������Ҫ��23�ű�
	 */
	@Test
	public void createTable_2() {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
//		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
		System.out.println("processEngine:"+processEngine);
	}
}
