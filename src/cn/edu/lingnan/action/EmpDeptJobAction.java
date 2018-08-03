package cn.edu.lingnan.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Dept;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.DeptService;
import cn.edu.lingnan.service.EmpDeptJobService;
import cn.edu.lingnan.service.EmpService;
import cn.edu.lingnan.service.JobService;

public class EmpDeptJobAction extends EmpAction {

	@Autowired
	private EmpDeptJobService empDeptJobService;
	@Autowired
	private EmpService empService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private JobService jobService;
	
	//判断跳转到哪个分页
	int adjustid;

	// 多条件查询员工编号 员工部门岗位页面传过来的empid
	private Long empid;

	// 多条件查询员工姓名
	private String empName;

	// 多条件查询部门名称
	private String deptName;

	// 多条件查询岗位名称
	private String jobName;

	// 传到部门岗位调动页面的emp
	private Emp emp;

	// 多条件查询的emp
	private Emp empExample;
	
	// 传到部门岗位调动的部门集合
	private List<Dept> deptList1;

	// 传到部门岗位调动的岗位集合
	private List<Job> jobList1;
	
	//传送到员工部门岗位信息页面的集合
	private List<Emp> list1;
	
	//记录当前第3个大分页
	private Integer toid1;

	// 分页需要的变量
	public static final int PAGESIZE = 7;
	private int pageNo1; // 当前页
	private int next1; // 下一页
	private int prev1; // 前一页
	private int allPages1; // 所有页

	/**
	 * 分页可条件查找全部员工信息
	 * 
	 * @return
	 */
	public String getAllEmpDeptJob() {
		super.queryAllByPage();
		Dept dept = new Dept();
		//deptName="人事部";
		dept.setDname(deptName);
		

		Job job = new Job();
		//jobName="管理岗位";
		job.setJname(jobName);
		
		empExample = new Emp();
		//empid=1L;
		empExample.setEmpid(empid);
		//empName="员工01";
		empExample.setEname(empName);
		empExample.setDept(dept);
		empExample.setJob(job);
		setPageNo();
		list1 = empDeptJobService.getEmpDeptJobByPage(PAGESIZE, pageNo1, empExample);
		//System.out.println(list);
		return SUCCESS;
	}

	/**
	 * 设置分页变量,以及总记录数
	 * 
	 */
	private void setPageNo() {
		if (pageNo1 == 0) {
			pageNo1 = 1;
		}
		int countPage = (int) empDeptJobService.getEmpDeptJobAllCount(empExample);
		//System.out.println(countPage);
		if (countPage % PAGESIZE == 0) {
			allPages1 = countPage / PAGESIZE;
		} else {
			allPages1 = (countPage / PAGESIZE) + 1;
		}
		next1 = pageNo1;
		prev1 = pageNo1;
		if (pageNo1 <= 1) {
			if (allPages1 == 1) {
				next1 = 1;
				prev1 = 1;
				pageNo1 = 1;
			} else {
				next1++;
				prev1 = 1;
				pageNo1 = 1;
			}
		} else if (pageNo1 >= allPages1) {
			if (allPages1 == 1) {
				next1 = 1;
				prev1 = 1;
				pageNo1 = 1;
			} else {
				next1 = allPages1;
				prev1--;
				pageNo1 = allPages1;
			}
		} else {
			next1++;
			prev1--;
		}
	}

	/**
	 * 得到单个员工，所有部门，所有岗位到员工调动部门岗位页面
	 * 
	 * @return
	 */
	public String AdjustEmpDeptJob() {
		System.out.println(adjustid);
		emp = empService.findEmpById(empid);
		deptList1 = deptService.getAllDept();
		jobList1 = jobService.getAllJobList();
		return SUCCESS;
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

	public long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public EmpDeptJobService getEmpDeptJobService() {
		return empDeptJobService;
	}

	public void setEmpDeptJobService(EmpDeptJobService empDeptJobService) {
		this.empDeptJobService = empDeptJobService;
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

	public Emp getEmpExample() {
		return empExample;
	}

	public void setEmpExample(Emp empExample) {
		this.empExample = empExample;
	}

	public List<Dept> getDeptList1() {
		return deptList1;
	}

	public void setDeptList1(List<Dept> deptList1) {
		this.deptList1 = deptList1;
	}

	public List<Job> getJobList1() {
		return jobList1;
	}

	public void setJobList1(List<Job> jobList1) {
		this.jobList1 = jobList1;
	}

	public List<Emp> getList1() {
		return list1;
	}

	public void setList1(List<Emp> list1) {
		this.list1 = list1;
	}

	public Integer getToid1() {
		return toid1;
	}

	public void setToid1(Integer toid1) {
		this.toid1 = toid1;
	}

	public int getPageNo1() {
		return pageNo1;
	}

	public void setPageNo1(int pageNo1) {
		this.pageNo1 = pageNo1;
	}

	public int getNext1() {
		return next1;
	}

	public void setNext1(int next1) {
		this.next1 = next1;
	}

	public int getPrev1() {
		return prev1;
	}

	public void setPrev1(int prev1) {
		this.prev1 = prev1;
	}

	public int getAllPages1() {
		return allPages1;
	}

	public void setAllPages1(int allPages1) {
		this.allPages1 = allPages1;
	}

	public int getAdjustid() {
		return adjustid;
	}

	public void setAdjustid(int adjustid) {
		this.adjustid = adjustid;
	}
	
}
