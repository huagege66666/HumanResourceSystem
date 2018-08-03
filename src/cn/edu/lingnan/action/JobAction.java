package cn.edu.lingnan.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Job;
import cn.edu.lingnan.service.JobService;

public class JobAction extends BaseAction {
	@Autowired
	private JobService jobService;
	
	private long jobid;
	private Job job;
	private String jname;
	private Integer count;
	private String type;
	private String message;
	
	private List<Job> jobList;
	private List<Job> expJobList;
	
	//��ҳ����
	private int pageNo;
	private int next;
	private int prev;
	private int allPages;
	private int allCounts;
	
	//ÿҳ��¼��
	private static final int PAGESIZE = 10;
		
	//��ϲ�ѯ�����ķ�װ����
	private Job jobExample;
	
	/*
	 * method
	 * 
	 * 
	 */
	
	//����id����job
	public String findJobById(){
		job = jobService.findJobById(jobid);
		return SUCCESS;
	}
	
	//����job
	public String addJob(){
		if(jobService.insertJob(job)==false){
			this.message="��д�ĸ�λ�Ѵ��ڣ���������д��";
			return ERROR;
		}
		this.message="��λ��ӳɹ���";
		return SUCCESS;
	}
	//����job����֤��
	/*
	 * 
	public void validateAddJob(){
		if(job.getJname()==null||"".equals(job.getJname())){
			super.addActionError("��λ���Ʋ���Ϊ�գ�");
			this.message="��λ���Ʋ���Ϊ�գ�";
		}
		if(job.getType()==null||"".equals(job.getType())){
			super.addActionError("��λ���Ͳ���Ϊ�գ�");
			this.message="��λ���Ͳ���Ϊ�գ�";
		}
		if(job.getCount()==null||"".equals(job.getCount())){
			super.addActionError("��λ�������Ʋ���Ϊ�գ�");
			this.message="��λ�������Ʋ���Ϊ�գ�";
		}
	}
	*/
	//ɾ��job
	public String deleteJob(){
		job=jobService.findJobById(jobid);
		if(jobService.getJobhumanCount(jobid)>0){
			job=null;
			this.message="�ø�λ����Ա����ɾ��ʧ�ܣ�";
			return ERROR;
		}
		jobService.deleteJob(job);
		this.message="��λɾ���ɹ���";
		return SUCCESS;
	}
	
	//�޸�job
	public String updateJob(){
		if(job==null){
			return ERROR;
		}
		if(jobService.alterJob(job)==false){
			this.message="�ø�λ�Ѵ��ڣ��޸�ʧ�ܣ�";
			return ERROR;
		}
		this.message="��λ�޸ĳɹ���";
		return SUCCESS;
	}
	
	//��ҳ������ϲ�ѯ
	public String loadJob(){
		if(pageNo==0){
			pageNo=1;
		}
		jobList = jobService.queryJobByPage(pageNo, PAGESIZE, job);
		expJobList=jobService.getAllJobList();
		allCounts = (int)jobService.getAllCounts(job);
		allPages = (allCounts % PAGESIZE ==0)?(allCounts / PAGESIZE )
					:(allCounts / PAGESIZE + 1);

		next = pageNo+1;
		prev = pageNo-1;
		if(next>allPages)
			next--;
		if(prev<1)
			prev++;
		return SUCCESS;
	}
	
	//�������и�λ
		public String showAllJob(){
			this.jobList = jobService.getAllJobList();
			return SUCCESS;
		}

	
	/*
	 * Get and Set
	 * 
	 * 
	 */
	
		
	public long getJobid() {
		return jobid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setJobid(long jobid) {
		this.jobid = jobid;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public Job getJobExample() {
		return jobExample;
	}

	public void setJobExample(Job jobExample) {
		this.jobExample = jobExample;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<Job> getExpJobList() {
		return expJobList;
	}

	public void setExpJobList(List<Job> expJobList) {
		this.expJobList = expJobList;
	}
}
