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
	
	private Emp example;   //��ѯʾ������ְ��Ϣ
	private Emp hireinfo;  //��Ӷ��Ϣ
	private long resid;    //�˲ſ�id
	private Humanres humres;  //��Ա�����˲ſ���Ϣ
	private Leavejob leavejob;  //��ְ��Ϣ
	private long empid;       //��ְԱ�����  ��ѯ��ְԱ����Ϣ
	
	private long deptid;  //����ʱ��ȡ���Ĳ���id
	private long jobid;    //����ʱ��ȡ���ĸ�λid
	
	private long finddeptid;  //��������ѯ�Ĳ���id
	private long findjobid;   //��������ѯ�ĸ�λid
	
	//��ҳ��Ҫ�ı���
	public static final int PAGESIZE = 10;
	private int pageNo;   //��ǰҳ
	private int next;     //��һҳ
	private int prev;     //ǰһҳ
	private int allPages; //����ҳ
	
	private String msg;   //������Ϣ
	private int toid;     //��ת���ĸ���ҳ
	
	/**
	 * ��ҳ��ѯ����Ա��
	 * author �Թ���
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
			toid=1;   //�����ת�����Ա��ҳ
		}
		jobList=jobService.getAllJobList();
		deptList=deptService.getAllDept();
		return SUCCESS;
	}
	
	/**
	 * ���õ�ǰҳ��
	 * author �Թ���
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
	 * ��ת����ӶԱ��ҳ��
	 * author �Թ���
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
	 * ��ת��Ա����ְҳ��
	 * @return
	 */
	public String toFireEmp(){
		example=empService.findEmpById(empid);
		return SUCCESS;
	}
	
	/**
	 * ��ӶԱ��
	 * author �Թ���
	 * @return
	 */
	public String hireEmp(){
		empService.insertEmp(hireinfo, deptid, jobid, resid);
		return SUCCESS;
	}
	
	public void validateHireEmp(){
		if(hireinfo.getWorktype()==null||hireinfo.getWorktype().equals("")){
			super.addActionError("�������Ͳ���Ϊ��");
			msg="�������Ͳ���Ϊ��";
		}
		if(hireinfo.getWorkdate()==null){
			super.addActionError("�������ڲ���Ϊ��");
			msg="�������ڲ���Ϊ��";
		}
		if(hireinfo.getIdentnum()==null||hireinfo.getIdentnum().equals("")){
			super.addActionError("���֤����Ϊ��");
			msg="���֤����Ϊ��";
		}
		if(hireinfo.getBirthdate()==null){
			super.addActionError("���ղ���Ϊ��");
			msg="���ղ���Ϊ��";
		}
		if(hireinfo.getSex()==null||hireinfo.getSex().equals("")){
			super.addActionError("�Ա���Ϊ��");
			msg="�Ա���Ϊ��";
		}
		if(hireinfo.getEname()==null||hireinfo.getEname().equals("")){
			super.addActionError("Ա����������Ϊ��");
			msg="Ա����������Ϊ��";
		}
	}
	
	/**
	 * Ա����ְ
	 * @return
	 */
	public String fireEmp(){
		empService.fireEmp(leavejob);
		return SUCCESS;
	}
	
	/**
	 * ��ѯ����Ա����Ϣ
	 * @return
	 */
	public String singEmpInfo(){
		example=empService.findEmpById(empid);
		return SUCCESS;
	}
	/**
	 * �޸�Ա����Ϣ
	 * @return
	 */
	public String alterEmpInfo(){
		empService.alterEmpInfo(example.getEmpid(), example);
		return SUCCESS;
	}
	/**
	 * ��ѯ��ְԱ����Ϣ
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
