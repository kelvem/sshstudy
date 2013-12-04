/**============================================================
 * 版权：kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.security.UmpUserTagService.java
 * 所含类: UmpUserTagService.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-04      zhaobai       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.security;

import java.util.ArrayList;
import java.util.List;

import com.kelvem.sample.struts2.model.SysUser;
import com.kelvem.sample.struts2.model.security.SysAuthority;

/**
 * <p>类名：权限tag应用类</p>
 *
 * <p>类用途详细说明：在对页面中的空间进行tag标签控制后，调用此类的方法来完成相应的权限审核和计算。</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName UmpUserTagService
 * @author zhaobai
 * @version 1.0
 */

public class KelvemUserTagService {
	
	private ISysAuthorityDao sysAuthorityDao;
	
	public boolean getAuthority(String urls)
	{
		return getAuthority(CommonUser.getLoginUser(), urls);
	}
	
	public boolean getAuthority(SysUser loginUser, String urls) {
		boolean result = true;
		if (loginUser == null || loginUser.getAuthorityList() == null || loginUser.getAuthorityList().size() == 0
				|| loginUser.getStatusCode().equals(CommonUser.STATUS_STOP)) {
			return false;
		}
		if (urls != null) {
			String[] urlArray = urls.split(",");
			List<String> authorityUrls = new ArrayList<String>(loginUser.getAuthorityList().size());
			for (SysAuthority sysAuthority : loginUser.getAuthorityList()) {
				authorityUrls.add(sysAuthority.getAuthorityUrl());
			}
			// 如果没有访问权限则返回false
			if (authorityUrls == null || !authorityUrls.containsAll(CommonUser.arrayToList(urlArray))) {
				return false;
			}
		}
		return result;
	}
	
	public boolean getUrlAuthority(SysUser loginUser, String urls) {
		boolean result = true;
		if (loginUser == null || loginUser.getAuthorityList() == null
				|| loginUser.getAuthorityList().size() == 0
				|| loginUser.getStatusCode().equals(CommonUser.STATUS_STOP)) {
			return false;
		}
		if (urls != null) {
			String[] urlArray = urls.split(",");
			List<String> authorityUrls = new ArrayList<String>();
			for (SysAuthority sysAuthority : loginUser.getAuthorityList()) {
				authorityUrls.add(sysAuthority.getAuthorityUrl());
			}
			// 如果没有访问权限则返回false
			if (authorityUrls==null||!authorityUrls.containsAll(CommonUser.arrayToList(urlArray))) {
				return false;
			}
		}
		return result;
	}
	
	public ISysAuthorityDao getSysAuthorityDao() {
		return sysAuthorityDao;
	}
	
	public void setSysAuthorityDao(ISysAuthorityDao sysAuthorityDao) {
		this.sysAuthorityDao = sysAuthorityDao;
	}

	
}
