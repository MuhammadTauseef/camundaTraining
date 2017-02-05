package org.camunda.bpm.demo.lab;



import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;

import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;

import java.util.HashMap;
import java.util.Map;


//import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  private static final String PROCESS_DEFINITION_KEY = "lab";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() {
	  //ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
	  
	  // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testStartProcess() {
	  Map<String, Object> variables = new HashMap<String, Object>();
	  variables.put("content", "Process variables using API 2. Approved=true Cheers MUHAMMAD TAUSEEF");

//  .getTaskService().complete(task.getId(), variables);
    

	  ProcessInstance processInstance = 
			  processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY,variables);

	 
	  assertThat(processInstance).isStarted().isWaitingAt("user_task_review_tweet").task().isAssignedTo("demo");

complete(task(), Variables.createVariables().putValue("approved", Boolean.TRUE));
//    Task task = rule.getTaskService().createTaskQuery().taskCandidateGroup("demo").singleResult();
//    variables.put("approved", Boolean.FALSE);
//    variables.put("comments", "No, we will not publish this on Twitter");
//    rule.getTaskService().complete(task.getId(), variables);
	  assertThat(processInstance).isEnded();
  }

}
