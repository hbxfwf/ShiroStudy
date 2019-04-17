package com.zelin.pojo;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class SysUser implements Serializable {
    @Id
    private String id;

    private String usercode;

    private String username;

    private String password;

    private String salt;

    private String locked;
    
    private List<SysPermission> menus;			//得到当前用户下的所有菜单集合
    private List<SysPermission> permissions;	//得到当前用户下的所有权限集合
    
    public List<SysPermission> getMenus() {
		return menus;
	}

	public List<SysPermission> getPermissions() {
		return permissions;
	}

	public void setMenus(List<SysPermission> menus) {
		this.menus = menus;
	}

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", usercode=" + usercode + ", username=" + username + ", password=" + password
				+ ", salt=" + salt + ", locked=" + locked + ", menus=" + menus + ", permissions=" + permissions + "]";
	}

	
    
    
}