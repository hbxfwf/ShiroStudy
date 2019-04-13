package com.zelin.pojo;


import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

public class Classes {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer cid;
    private String cname;
 
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

	@Override
	public String toString() {
		return "Classes [cid=" + cid + ", cname=" + cname + "]";
	}
    
    
}