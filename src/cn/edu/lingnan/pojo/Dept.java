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
 * Dept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEPT", schema = "SSH")
public class Dept implements java.io.Serializable {

	// Fields

	private Long deptid;
	private Dept dept;
	private String dname;
	private String tel;
	private String fax;
	private String descr;
	private Date builddate;
	private Set<Deptadjust> deptadjusts = new HashSet<Deptadjust>(0);
	private Set<Emp> emps = new HashSet<Emp>(0);
	private Set<Dept> depts = new HashSet<Dept>(0);

	// Constructors

	/** default constructor */
	public Dept() {
	}

	/** minimal constructor */
	public Dept(String dname, Date builddate) {
		this.dname = dname;
		this.builddate = builddate;
	}

	/** full constructor */
	public Dept(Dept dept, String dname, String tel, String fax, String descr,
			Date builddate, Set<Deptadjust> deptadjusts, Set<Emp> emps,
			Set<Dept> depts) {
		this.dept = dept;
		this.dname = dname;
		this.tel = tel;
		this.fax = fax;
		this.descr = descr;
		this.builddate = builddate;
		this.deptadjusts = deptadjusts;
		this.emps = emps;
		this.depts = depts;
	}

	// Property accessors
	@SequenceGenerator(name = "generator_deptid",allocationSize=1,sequenceName="seq_deptid")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator_deptid")
	@Column(name = "DEPTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getDeptid() {
		return this.deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MGRID")
	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Column(name = "DNAME", nullable = false, length = 50)
	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	@Column(name = "TEL", length = 13)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "FAX", length = 13)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "DESCR", length = 100)
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BUILDDATE", nullable = false, length = 7)
	public Date getBuilddate() {
		return this.builddate;
	}

	public void setBuilddate(Date builddate) {
		this.builddate = builddate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dept")
	public Set<Deptadjust> getDeptadjusts() {
		return this.deptadjusts;
	}

	public void setDeptadjusts(Set<Deptadjust> deptadjusts) {
		this.deptadjusts = deptadjusts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dept")
	public Set<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dept")
	public Set<Dept> getDepts() {
		return this.depts;
	}

	public void setDepts(Set<Dept> depts) {
		this.depts = depts;
	}

}