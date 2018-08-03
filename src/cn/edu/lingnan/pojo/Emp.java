package cn.edu.lingnan.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Emp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EMP", schema = "SSH")
public class Emp implements java.io.Serializable {

	// Fields

	private Long empid;
	private Job job;
	private Humanres humanres;
	private Dept dept;
	private String ename;
	private String sex;
	private Date birthdate;
	private String identnum;
	private Date hiredate;
	private Date workdate;
	private String worktype;
	private String res;
	private String polstatus;
	private String nation;
	private String navplace;
	private String tel;
	private String email;
	private Double height;
	private String booldtype;
	private String marry;
	private String recschool;
	private String school;
	private String major;
	private Date graddate;
	private Set<Account> accounts = new HashSet<Account>(0);
	private Set<Leavejob> leavejobs = new HashSet<Leavejob>(0);
	private Set<Deptadjust> deptadjusts = new HashSet<Deptadjust>(0);
	private Set<Testinfo> testinfos = new HashSet<Testinfo>(0);
	private Set<Jobadjust> jobadjusts = new HashSet<Jobadjust>(0);

	// Constructors

	/** default constructor */
	public Emp() {
	}

	/** minimal constructor */
	public Emp(Job job, Dept dept, String ename, String sex, Date birthdate,
			String identnum, Date hiredate, Date workdate, String worktype,
			String res) {
		this.job = job;
		this.dept = dept;
		this.ename = ename;
		this.sex = sex;
		this.birthdate = birthdate;
		this.identnum = identnum;
		this.hiredate = hiredate;
		this.workdate = workdate;
		this.worktype = worktype;
		this.res = res;
	}

	/** full constructor */
	public Emp(Job job, Humanres humanres, Dept dept, String ename, String sex,
			Date birthdate, String identnum, Date hiredate, Date workdate,
			String worktype, String res, String polstatus, String nation,
			String navplace, String tel, String email, Double height,
			String booldtype, String marry, String recschool, String school,
			String major, Date graddate, Set<Account> accounts,
			Set<Leavejob> leavejobs, Set<Deptadjust> deptadjusts,
			Set<Testinfo> testinfos, Set<Jobadjust> jobadjusts) {
		this.job = job;
		this.humanres = humanres;
		this.dept = dept;
		this.ename = ename;
		this.sex = sex;
		this.birthdate = birthdate;
		this.identnum = identnum;
		this.hiredate = hiredate;
		this.workdate = workdate;
		this.worktype = worktype;
		this.res = res;
		this.polstatus = polstatus;
		this.nation = nation;
		this.navplace = navplace;
		this.tel = tel;
		this.email = email;
		this.height = height;
		this.booldtype = booldtype;
		this.marry = marry;
		this.recschool = recschool;
		this.school = school;
		this.major = major;
		this.graddate = graddate;
		this.accounts = accounts;
		this.leavejobs = leavejobs;
		this.deptadjusts = deptadjusts;
		this.testinfos = testinfos;
		this.jobadjusts = jobadjusts;
	}

	// Property accessors
	@SequenceGenerator(name = "generator_empid",allocationSize=1,sequenceName="seq_empid")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator_empid")
	@Column(name = "EMPID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getEmpid() {
		return this.empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOBID", nullable = false)
	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESID")
	public Humanres getHumanres() {
		return this.humanres;
	}

	public void setHumanres(Humanres humanres) {
		this.humanres = humanres;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTID", nullable = false)
	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Column(name = "ENAME", nullable = false, length = 10)
	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "SEX", nullable = false, length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", nullable = false, length = 7)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "IDENTNUM", nullable = false, length = 18)
	public String getIdentnum() {
		return this.identnum;
	}

	public void setIdentnum(String identnum) {
		this.identnum = identnum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HIREDATE", nullable = false, length = 7)
	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "WORKDATE", nullable = false, length = 7)
	public Date getWorkdate() {
		return this.workdate;
	}

	public void setWorkdate(Date workdate) {
		this.workdate = workdate;
	}

	@Column(name = "WORKTYPE", nullable = false, length = 10)
	public String getWorktype() {
		return this.worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	@Column(name = "RES", nullable = false, length = 10)
	public String getRes() {
		return this.res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	@Column(name = "POLSTATUS", length = 10)
	public String getPolstatus() {
		return this.polstatus;
	}

	public void setPolstatus(String polstatus) {
		this.polstatus = polstatus;
	}

	@Column(name = "NATION", length = 10)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "NAVPLACE", length = 10)
	public String getNavplace() {
		return this.navplace;
	}

	public void setNavplace(String navplace) {
		this.navplace = navplace;
	}

	@Column(name = "TEL", length = 11)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "EMAIL", length = 15)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "HEIGHT", precision = 5)
	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Column(name = "BOOLDTYPE", length = 5)
	public String getBooldtype() {
		return this.booldtype;
	}

	public void setBooldtype(String booldtype) {
		this.booldtype = booldtype;
	}

	@Column(name = "MARRY", length = 4)
	public String getMarry() {
		return this.marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	@Column(name = "RECSCHOOL", length = 10)
	public String getRecschool() {
		return this.recschool;
	}

	public void setRecschool(String recschool) {
		this.recschool = recschool;
	}

	@Column(name = "SCHOOL", length = 20)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "MAJOR", length = 20)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GRADDATE", length = 7)
	public Date getGraddate() {
		return this.graddate;
	}

	public void setGraddate(Date graddate) {
		this.graddate = graddate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	public Set<Leavejob> getLeavejobs() {
		return this.leavejobs;
	}

	public void setLeavejobs(Set<Leavejob> leavejobs) {
		this.leavejobs = leavejobs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	public Set<Deptadjust> getDeptadjusts() {
		return this.deptadjusts;
	}

	public void setDeptadjusts(Set<Deptadjust> deptadjusts) {
		this.deptadjusts = deptadjusts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	public Set<Testinfo> getTestinfos() {
		return this.testinfos;
	}

	public void setTestinfos(Set<Testinfo> testinfos) {
		this.testinfos = testinfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	public Set<Jobadjust> getJobadjusts() {
		return this.jobadjusts;
	}

	public void setJobadjusts(Set<Jobadjust> jobadjusts) {
		this.jobadjusts = jobadjusts;
	}

}