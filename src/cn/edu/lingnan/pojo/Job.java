package cn.edu.lingnan.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Job entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JOB", schema = "SSH")
public class Job implements java.io.Serializable {

	// Fields

	private Long jobid;
	private String jname;
	private String type;
	private Integer count;
	private Set<Emp> emps = new HashSet<Emp>(0);

	// Constructors

	/** default constructor */
	public Job() {
	}

	/** minimal constructor */
	public Job(String jname, String type) {
		this.jname = jname;
		this.type = type;
	}

	/** full constructor */
	public Job(String jname, String type, Integer count, Set<Emp> emps) {
		this.jname = jname;
		this.type = type;
		this.count = count;
		this.emps = emps;
	}

	// Property accessors
	@SequenceGenerator(name = "generator_jobid",allocationSize=1,sequenceName="seq_jobid")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator_jobid")
	@Column(name = "JOBID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getJobid() {
		return this.jobid;
	}

	public void setJobid(Long jobid) {
		this.jobid = jobid;
	}

	@Column(name = "JNAME", nullable = false, length = 50)
	public String getJname() {
		return this.jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	@Column(name = "TYPE", nullable = false, length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "COUNT", precision = 5, scale = 0)
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")
	public Set<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

}