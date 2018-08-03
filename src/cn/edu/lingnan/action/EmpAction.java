package cn.edu.lingnan.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Dept;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Humanres;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.pojo.Leavejob;
import cn.edu.lingnan.service.DeptService;
import cn.edu.lingnan.service.EmpService;
import cn.edu.lingnan.service.HumanresService;
import cn.edu.lingnan.service.JobService;

public class EmpAction extends BaseAction {

	@Autowired
	private EmpService empService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private JobService  jobService;
	
	@Autowired
	private HumanresService resService;
	
	private List<Emp> empList;
	private List<Job> jobList;
	private List<Dept> deptList;
	
	private Emp example;   //查询示例、离职信息
	private Emp hireinfo;  //雇佣信息
	private long resid;    //人才库id
	private Humanres humres;  //该员工的人才库信息
	private Leavejob leavejob;  //离职信息
	private long empid;       //离职员工编号  查询离职员工信息
	
	private long deptid;  //插入时获取到的部门id
	private long jobid;    //插入时获取到的岗位id
	
	private long finddeptid;  //多条件查询的部门id
	private long findjobid;   //多条件查询的岗位id
	
	//分页需要的变量
	public static final int PAGESIZE = 10;
	private int pageNo;   //当前页
	private int next;     //下一页
	private int prev;     //前一页
	private int allPages; //所有页
	
	private String msg;   //错误信息
	private int toid;     //跳转到哪个分页
	
	/**
	 * 分页查询所有员工
	 * author 赵桂钊
	 * @return
	 */
	public String queryAllByPage(){
		
		if(finddeptid!=0){
			Dept dept=deptService.findDeptById(finddeptid);
			example.setDept(dept);
		}
		if(findjobid!=0){
			Job job=jobService.findJobById(findjobid);
			example.setJob(job);
		}
		
		setPageNo();
		
		empList=empService.findEmpByPage(PAGESIZE, pageNo, example);
		
		if(resid!=0){
			humres=resService.findHumanresByID(resid);
			toid=1;   //标记跳转到添加员工页
		}
		jobList=jobService.getAllJobList();
		deptList=deptService.getAllDept();
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
		int countPage=(int) empService.getAllCount(example);
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
	 * 跳转到雇佣员工页面
	 * author 赵桂钊
	 * @return
	 */
	public String toHireEmp(){
		if(resid!=0){
			humres=resService.findHumanresByID(resid);
		}
		jobList=jobService.getAllJobList();
		deptList=deptService.getAllDept();
		return SUCCESS;
	}
	
	/**
	 * 跳转到员工离职页面
	 * @return
	 */
	public String toFireEmp(){
		example=empService.findEmpById(empid);
		return SUCCESS;
	}
	
	/**
	 * 雇佣员工
	 * author 赵桂钊
	 * @return
	 */
	public String hireEmp(){
		empService.insertEmp(hireinfo, deptid, jobid, resid);
		return SUCCESS;
	}
	
	public void validateHireEmp(){
		if(hireinfo.getWorktype()==null||hireinfo.getWorktype().equals("")){
			super.addActionError("工作类型不能为空");
			msg="工作类型不能为空";
		}
		if(hireinfo.getWorkdate()==null){
			super.addActionError("工作日期不能为空");
			msg="工作日期不能为空";
		}
		if(hireinfo.getIdentnum()==null||hireinfo.getIdentnum().equals("")){
			super.addActionError("身份证不能为空");
			msg="身份证不能为空";
		}
		if(hireinfo.getBirthdate()==null){
			super.addActionError("生日不能为空");
			msg="生日不能为空";
		}
		if(hireinfo.getSex()==null||hireinfo.getSex().equals("")){
			super.addActionError("性别不能为空");
			msg="性别不能为空";
		}
		if(hireinfo.getEname()==null||hireinfo.getEname().equals("")){
			super.addActionError("员工姓名不能为空");
			msg="员工姓名不能为空";
		}
	}
	
	/**
	 * 员工离职
	 * @return
	 */
	public String fireEmp(){
		empService.fireEmp(leavejob);
		return SUCCESS;
	}
	
	/**
	 * 查询单个员工信息
	 * @return
	 */
	public String singEmpInfo(){
		example=empService.findEmpById(empid);
		return SUCCESS;
	}
	/**
	 * 修改员工信息
	 * @return
	 */
	public String alterEmpInfo(){
		empService.alterEmpInfo(example.getEmpid(), example);
		return SUCCESS;
	}
	/**
	 * 查询离职员工信息
	 * @return
	 */
	public String queryLeavejobByEmpid(){
		example=empService.findEmpById(empid);
		leavejob=empService.findLeavejobByEmpId(empid);
		return SUCCESS;
	}

	public List<Emp> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Emp> empList) {
		this.empList = empList;
	}

	public Emp getExample() {
		return example;
	}

	public void setExample(Emp example) {
		this.example = example;
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

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<Dept> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}

	public Humanres getHumres() {
		return humres;
	}

	public void setHumres(Humanres humres) {
		this.humres = humres;
	}

	public long getResid() {
		return resid;
	}

	public void setResid(long resid) {
		this.resid = resid;
	}

	public long getDeptid() {
		return deptid;
	}

	public void setDeptid(long deptid) {
		this.deptid = deptid;
	}

	public long getJobid() {
		return jobid;
	}

	public void setJobid(long jobid) {
		this.jobid = jobid;
	}

	public Leavejob getLeavjob() {
		return leavejob;
	}

	public void setLeavjob(Leavejob leavejob) {
		this.leavejob = leavejob;
	}

	public long getEmpid() {
		return empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public Leavejob getLeavejob() {
		return leavejob;
	}

	public void setLeavejob(Leavejob leavejob) {
		this.leavejob = leavejob;
	}

	public Emp getHireinfo() {
		return hireinfo;
	}

	public void setHireinfo(Emp hireinfo) {
		this.hireinfo = hireinfo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public long getFinddeptid() {
		return finddeptid;
	}

	public void setFinddeptid(long finddeptid) {
		this.finddeptid = finddeptid;
	}

	public long getFindjobid() {
		return findjobid;
	}

	public void setFindjobid(long findjobid) {
		this.findjobid = findjobid;
	}

}
