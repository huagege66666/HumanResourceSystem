package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Testmanager;


public interface TestmanagerService {
	/**
	 * 转为正式员工
	 * @param info
	 */
	String regularEmp(Testmanager testManager);
	
	/**
	 * 延期
	 * @param info
	 */
	String delayEmp(Testmanager testManager);
	
	/**
	 * 未通过
	 * @param info
	 */
	String fireEmp(Testmanager testManager);

	/**
	 * 判断是转正,延期,未通过
	 * @param testManager
	 */
	String updateTestInfo(Testmanager testManager);
}
