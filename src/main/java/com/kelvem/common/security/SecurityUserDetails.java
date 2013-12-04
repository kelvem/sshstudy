/**============================================================
 * 版权：kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.security.SecurityUserDetails.java
 * 所含类: SecurityUserDetails.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-04      zhaobai       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kelvem.sample.struts2.model.SysUser;

/**
 * <p>SecurityUserDetails</p>
 *
 * <p>用户权限的模型类</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName SecurityUserDetails
 * @author zhaobai
 * @version 1.0
 */
public class SecurityUserDetails extends User{

	/**
	 * 登陆用户信息对象
	 */
	private SysUser sysUser;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3854827415704889610L;

	/**
	 * <p>构造函数</p>
	 * 
	 * @param sysUser
	 * @param authorities
	 */
	public SecurityUserDetails(SysUser sysUser,
			Collection<? extends GrantedAuthority> authorities) {
		super(sysUser.getUserLoginName(), sysUser.getUserPassword(), true, true, true,
				true, authorities);
		// TODO Auto-generated constructor stub
		this.sysUser = sysUser;
	}

	/**
	 * @return the sysUser
	 */
	public SysUser getSysUser() {
		return sysUser;
	}

	/**
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	
}
