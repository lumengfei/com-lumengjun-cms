package com.lumengjun.entity;

import java.io.Serializable;
/**
 * 2020-02-18
 * @author ASUS
 *	卢梦均   收藏夹类
 */
public class ShouCang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private Integer id;//主键
	 
	 private String test;//收藏夹文本
	 
	 private String url;//收藏夹地址
	 
	 private Integer user_id;//所属用户
	 
	 private String created;//添加时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "ShouCang [id=" + id + ", test=" + test + ", url=" + url
				+ ", user_id=" + user_id + ", created=" + created + "]";
	}

	public ShouCang(Integer id, String test, String url, Integer user_id,
			String created) {
		super();
		this.id = id;
		this.test = test;
		this.url = url;
		this.user_id = user_id;
		this.created = created;
	}

	public ShouCang() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((test == null) ? 0 : test.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShouCang other = (ShouCang) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (test == null) {
			if (other.test != null)
				return false;
		} else if (!test.equals(other.test))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	 
	 
	 
	

}
