/**============================================================
 * 版权：kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.security.KelvemUserDetailService.java
 * 所含类: KelvemUserDetailService.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-05      zhaobai       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kelvem.sample.struts2.model.SysUser;
import com.kelvem.sample.struts2.model.security.SysAuthority;

/**
 * 
 * <p>KelvemUserDetailService</p>
 *
 * <p>获取用户及角色信息</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName KelvemUserDetailService
 * @author zhaobai
 * @version 1.0
 */
public class KelvemUserDetailService implements UserDetailsService {

	private static final Log log = LogFactory.getLog(KelvemUserDetailService.class);
	
	/**
     * 
     * <p>根据用户名加载用户及角色信息</p>
     * 
     * @param username 用户名
     * 
     * @return UserDetails 用户信息
     * @see
     */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException { 
		/*
		// System.out.println("UmpUserDetailService:loadUserByUsername# " + username);
    	
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		List<CSysUser> users = cSysUserDao.findByUserLogonName(username);		
		
		if ( users.size() == 1){
			CSysUser sysUser = users.get(0);
			
			Set<CSysRole> roles = sysUser.getRoles();
			for ( CSysRole role : roles ){
				GrantedAuthorityImpl auth = new GrantedAuthorityImpl(role.getRoleName());
				// ### System.err.println(sysUser.getUserLogonName() + " : " + role.getRoleName());
				auths.add(auth);
			}			
		
			SysUserVO loginUser = this.getBeanMapper().map(sysUser, SysUserVO.class);
			SecurityUserDetails user = new SecurityUserDetails(loginUser, auths);
						
			return user;
		} else {
			
			SysUserVO loginUser = new SysUserVO();
			loginUser.setUserLogonName(" ");
			loginUser.setUserPassword(" ");
			
			return new SecurityUserDetails(loginUser, new ArrayList<GrantedAuthority>());
		}
		*/
		
		log.info("用户(" + username + ")登录中");
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

		SysUser loginUser = this.getSysUser(username);
		
		if ( loginUser == null || loginUser.getDelFlag() != 0 ){
			log.info("用户(" + username + ")不存在！");
			return new SecurityUserDetails(new SysUser(),
					new ArrayList<GrantedAuthority>());
		} else if (loginUser.getStatusCode() != 10){
			log.info("用户(" + username + ")处于停用状态！");
			return new SecurityUserDetails(new SysUser(),
					new ArrayList<GrantedAuthority>());
		}
		
		List<SysAuthority> listAuth = loginUser.getAuthorityList();
		
		if (listAuth != null){
	        for (SysAuthority vo : listAuth) {
				GrantedAuthorityImpl auth = new GrantedAuthorityImpl(vo.getAuthorityName());
				auths.add(auth);
			}
		}
		
        // ### Temp begin
		GrantedAuthorityImpl auth = new GrantedAuthorityImpl("ROLE_ANONYMOUS");
//		// auths.clear();
		auths.add(auth);
		// ##  end
		
		SecurityUserDetails user = new SecurityUserDetails(loginUser, auths);
		//log.info("用户(" + username + ")登录成功！");
		return user;
	}

	private SysUser getSysUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
}





