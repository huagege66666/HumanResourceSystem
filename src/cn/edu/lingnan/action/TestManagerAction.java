package cn.edu.lingnan.action;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Testinfo;
import cn.edu.lingnan.pojo.Testmanager;
import cn.edu.lingnan.service.TestInfoService;
import cn.edu.lingnan.service.TestmanagerService;


public class TestManagerAction extends BaseAction {
	
	@Autowired
	TestmanagerService testManagerService;
	@Autowired
	TestInfoService testInfoService;
	
	//试用员工管理页面传过来的试用信息编号
	private Long testInfoId;
	
	//试用员工管理页面传过来的试用员工管理对象
	private Testmanager testManager;
	
	//传递到网页的信息
	private String message;
	
	/**
	 * 管理试用员工   1.'转正' 2.'延期' 3.'不予录用'
	 * @return
	 */
	public String toManagerTestInfo(){
		/*TestManager testManager01 = new TestManager();
		TestInfo testInfo = testInfoService.getTestInfoById(1);
		testManager01.setTestInfo(testInfo);
		testManager01.setMandate(new Date());
		testManager01.setResult(3);*/
		//System.out.println(testInfoId);
		Testinfo testInfo = testInfoService.getTestInfoById(testInfoId);
		if(testInfo!=null){
			testManager.setTestinfo(testInfo);
			message = testManagerService.updateTestInfo(testManager);
		}
		
		return "success";
	}
	
	
	public Testmanager getTestManager() {
		return testManager;
	}

	public void setTestManager(Testmanager testManager) {
		this.testManager = testManager;
	}


	public Long getTestInfoId() {
		return testInfoId;
	}


	public void setTestInfoId(Long testInfoId) {
		this.testInfoId = testInfoId;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
}
