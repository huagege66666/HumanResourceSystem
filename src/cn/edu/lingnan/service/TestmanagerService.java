package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Testmanager;


public interface TestmanagerService {
	/**
	 * תΪ��ʽԱ��
	 * @param info
	 */
	String regularEmp(Testmanager testManager);
	
	/**
	 * ����
	 * @param info
	 */
	String delayEmp(Testmanager testManager);
	
	/**
	 * δͨ��
	 * @param info
	 */
	String fireEmp(Testmanager testManager);

	/**
	 * �ж���ת��,����,δͨ��
	 * @param testManager
	 */
	String updateTestInfo(Testmanager testManager);
}
