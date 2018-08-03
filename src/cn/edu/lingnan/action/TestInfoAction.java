package cn.edu.lingnan.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Dept;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.pojo.Testinfo;
import cn.edu.lingnan.service.DeptService;
import cn.edu.lingnan.service.EmpService;
import cn.edu.lingnan.service.JobService;
import cn.edu.lingnan.service.TestInfoService;

public class TestInfoAction extends BaseAction{

	@Autowired
	private TestInfoService testInfoService;
	@Autowired
	private EmpService empService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private JobService jobService;
	
	//多条件查询员工编号
	private Long empid;
	
	//多条件查询员工姓名
	private String empName;
	
	//多条件查询部门名称
	private String deptName;
	
	//多条件查询岗位名称
	private String jobName;
	
	//已转正的员工集合
	private List<Emp> regularEmp;
	
	//所有试用员工信息
	private List<Testinfo> list;
	
	//查询条件封装
	private Testinfo testInfoExample;
	
	//分页需要的变量
	public static final int PAGESIZE = 7;
	private int pageNo;   //当前页
	private int next;     //下一页
	private int prev;     //前一页
	private int allPages; //所有页
	
	private List<Dept> deptList;
	private List<Job> jobList;
	

	
	/**
	 * 分页查找试用员工信息
	 * @return
	 */
	public String getAllTestInfoByPage(){
		
		
		Dept dept = new Dept();
		//deptName="人事部";
		dept.setDname(deptName);

		Job job = new Job();
		//jobName="管理岗位";
		job.setJname(jobName);
		
		Emp emp = new Emp();
		//empid=1L;
		emp.setEmpid(empid);
		//empName="员工01";
		emp.setEname(empName);
		emp.setDept(dept);
		emp.setJob(job);
		
		testInfoExample = new Testinfo();
		testInfoExample.setEmp(emp);
		
		/*try {	
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			Date start = sdf.parse("2017-01-15");
			Date end = sdf.parse("2018-02-01");
			testInfoExample.setStartdate(start);
			testInfoExample.setEnddate(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		deptList=deptService.getAllDept();
		jobList=jobService.getAllJobList();
		setPageNo();
		list = testInfoService.getTestInfoByPage(PAGESIZE, pageNo, testInfoExample);
		//System.out.println(list);
		return SUCCESS;
	}
	
	
	/**
	 * 设置分页变量
	 * 
	 */
	private void setPageNo(){
		if(pageNo==0){
			pageNo=1;
		}
		int countPage=(int) testInfoService.getTestInfoAllCount(testInfoExample);
		if(countPage%PAGESIZE==0){
			allPages=countPage/PAGESIZE;
		}
		else{
			allPages=(countPage/PAGESIZE)+1;
		}
		next=pageNo;
		prev=pageNo;
		/*if(allPages==1){
			next=1;
			prev=1;
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
		}*/
		if (pageNo <= 1) {
			if(allPages == 1){
				next = 1;
				prev = 1;
				pageNo = 1;
			}else{
				next ++;
				prev = 1;
				pageNo = 1;
			}
		} else if (pageNo >= allPages) {
			if(allPages == 1){
				next = 1;
				prev = 1;
				pageNo = 1;
			}else{
				next = allPages;
				prev --;
				pageNo = allPages;
			}
		} else {
			next++;
			prev--;
		}
	}
	

	/**
	 * 查找全部所有已转正员工
	 * @return
	 */
	public String findAllRegularEmp(){
		regularEmp = testInfoService.getAllRegularEmp();
		//System.out.println(regularEmp);
		return SUCCESS;
	}
	
	/**
	 * 找出所有试用员工信息
	 * @return
	 */
	public String getAllTestInfo(){
		list = testInfoService.getAllTestInfo();
		//System.out.println(list);
		return SUCCESS;
	}
	
	public List<Emp> getRegularEmp() {
		return regularEmp;
	}

	public void setRegularEmp(List<Emp> regularEmp) {
		this.regularEmp = regularEmp;
	}

	public List<Testinfo> getList() {
		return list;
	}

	public void setList(List<Testinfo> list) {
		this.list = list;
	}

	public TestInfoService getTestInfoService() {
		return testInfoService;
	}

	public void setTestInfoService(TestInfoService testInfoService) {
		this.testInfoService = testInfoService;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Testinfo getTestInfoExample() {
		return testInfoExample;
	}

	public void setTestInfoExample(Testinfo testInfoExample) {
		this.testInfoExample = testInfoExample;
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

	public static int getPagesize() {
		return PAGESIZE;
	}


	public EmpService getEmpService() {
		return empService;
	}


	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}


	public DeptService getDeptService() {
		return deptService;
	}


	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}


	public JobService getJobService() {
		return jobService;
	}


	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}


	public List<Dept> getDeptList() {
		return deptList;
	}


	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}


	public List<Job> getJobList() {
		return jobList;
	}


	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}
	
}
