package org.apache.basedao;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="test")
public class TestDomain {

	@Id
	private long id ;
	
	private String name ;
	
	private Date birth ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	
}
