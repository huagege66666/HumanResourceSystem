package cn.edu.lingnan.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Dept;
import cn.edu.lingnan.service.DeptService;



public class DeptAction extends BaseAction {
	
	@Autowired
	private DeptService deptService;
	private List<Dept> deptList;
	
	// 分页参数
	private int pageNo;
	private int next;
	private int prev;
	private int allPages; // 总页数
	private int allCounts; // 总行数（个数）
	private static final int PAGESIZE = 10;
	
	//  获取部门 id,
	private long deptid;
	//  获取部门实体
	private Dept dept;
	//
	private Dept deptDetail;
	// 
	private Dept aldept;
	//  删除错误信息设置
	private String message;
	//  组合查询的封装条件
	private Dept deptExemple;
	
	
	/**
	 *  不带条件分页查询所有部门
	 * @return
	 */
	public String loadAllDeptByPage(){
		setPageNo();
		deptList = deptService.queryAllDeptByExample(pageNo, PAGESIZE,deptExemple);
		
		return SUCCESS;
	}
	
	/**
	 * 设置当前页码
	 * author 赵桂钊
	 */
	private void setPageNo(){
		if(pageNo==0){
			pageNo=1;
		}
		int countPage= (int) deptService.getAllCounts(deptExemple);
		allCounts =countPage;
		if(countPage%PAGESIZE==0){
			allPages=countPage/PAGESIZE;
		}
		else{
			allPages=(countPage/PAGESIZE)+1;
		}
		next=pageNo;
		prev=pageNo;
		if(allPages==1){
			next=1;
			prev=1;
			return;
		}
		if(pageNo<=1){
			next++;
			prev=1;
		}
		else{
			if(pageNo>=allPages){
				prev--;
				next=allPages;
			}
			else{
				next++;
				prev--;
			}
		}
	}

	/**
	 *  不分页带条件查询
	
	public String loadDeptByExample(){
		deptList = deptService.findDeptByName(deptExemple);
		return SUCCESS;
	}
 */
	/**
	 * 不带分页查找所有部门
	 * @return
	 */
	public String getAllDept(){
		deptList = deptService.getAllDept();
		return SUCCESS;
	}
	
	
	/**
	 * 2： 修改部门信息，获取部门id信息
	 * @return
	 */
	public String updateDeptById(){
		deptDetail = deptService.findDeptById(deptid);
		return SUCCESS;
	}
	/**
	 *  更新部门页面 提交按钮
	 * @return
	 */
	public String updateDeptSubmit(){
//		Dept dept01 = deptService.findDeptById(3);
//		dept01.setDescr("运营部");
//		deptService.alterDept(dept01);
		deptService.alterDept(dept);
		return SUCCESS;
	}
	
	/**
	 *  部门删除
	 * @return
	 */
	public String deleteDeptById(){
		try {
			deptService.deleteDept(deptid);    //2018-1-14 赵桂钊修改
		} catch (Exception e) {
			setMessage(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/***
	 * 部门添加实现方法
	 * @return
	 */
	public String addDept(){
//		Dept d = new Dept("sss","sss","sss","sss",new Date());    这是测试方法
		aldept.setBuilddate(new Date());
		deptService.insertDept(aldept);
		
		return SUCCESS;
	}
	
	
	
	
	
	public Dept getDeptExemple() {
		return deptExemple;
	}
	public void setDeptExemple(Dept deptExemple) {
		this.deptExemple = deptExemple;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getPrev() {
		return prev;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}
	public int getAllPages() {
		return allPages;
	}
	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}
	public int getAllCounts() {
		return allCounts;
	}
	public void setAllCounts(int allCounts) {
		this.allCounts = allCounts;
	}
	public long getDeptid() {
		return deptid;
	}
	public void setDeptid(long deptid) {
		this.deptid = deptid;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Dept getDeptDetail() {
		return deptDetail;
	}
	public void setDeptDetail(Dept deptDetail) {
		this.deptDetail = deptDetail;
	}
	public static int getPagesize() {
		return PAGESIZE;
	}
	public Dept getAldept() {
		return aldept;
	}
	public void setAldept(Dept aldept) {
		this.aldept = aldept;
	}
	
	

}
