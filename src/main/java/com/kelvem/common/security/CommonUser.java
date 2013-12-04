/**============================================================
 * 版权：kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.security.CommonUser.java
 * 所含类: CommonUser.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-04      zhaobai       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kelvem.sample.struts2.model.SysUser;
import com.kelvem.sample.struts2.model.security.SysAuthority;

/**
 * <p>数据权限及其用户权限类</p>
 *
 * <p>用于获取登录用户LoginUser的各种属性和权限。以及常用的权限比较方法。</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName KelvemFilterSecurityInterceptor
 * @author zhaobai
 * @version 1.0
 */

public class CommonUser {
	
	public static final int STATUS_STOP = 0;
	public static final int STATUS_NORMAL = 10;

	private static ThreadLocal<SysUser> loginUser = new ThreadLocal<SysUser>(); 
	
	public static ThreadLocal<SysUser> getLoginUserThreadLocal()
	{
		return loginUser;
	}
	
	private static boolean isTest = false;
	private static SysUser testUser = null;
	
	public static void setTestMode(){
		isTest = true;
	}
	
	public static SysUser getTestUserLogin(){

		SysUser user = new SysUser();
		user.setUserId(5);
		user.setUserLoginName("ztc");
		user.setUserPassword("ztc");
		user.setUserTypeCode(1);
		user.setUserDescs("咱只用于单元测试，别的事免谈！");
		user.setStatusCode(10);
		user.setDelFlag(0);
		user.setDelTime(new Date());
		user.setProvinceCenterId(0);
		user.setCityCenterId(null);
		user.setCountyCenterId(null);
		user.setPersonInfoId(625226);
		List<SysAuthority> authorityList = new ArrayList<SysAuthority>();
		for (int i=0; i<3; i++) {
			SysAuthority anthority = new SysAuthority();
			authorityList.add(anthority);
		}
		user.setAuthorityList(authorityList);
		
		return user;
	}
	
	public static void setTestUserLogin(SysUser user){

		testUser = user;
	}
	
	public static SysUser getLoginUser()
	{	
		if ( isTest ){	
			if ( testUser == null ){
				testUser = getTestUserLogin();
			}
			return testUser;
		} else {
			return loginUser.get();
		}
	}
	
	public static void setLoginUser(SysUser user)
	{
		testUser = user;
		loginUser.set(user);
	}
	
	public static void removeLoginUser()
	{
		loginUser.remove();
	}
	
	/**
	 * <p>方法描述：检查url地址是否满足loginUser的访问权限范围。</p>
	 * 
	 * @param url
	 * @return
	 * @return boolean
	 * @see
	 */
	public static boolean checkUserAuthority(String url)
	{
		return checkUserAuthority(loginUser.get().getAuthorityList(),url);
	}
	
	/**
	 * <p>方法描述：检查url地址是否满足权限List的访问权限范围。</p>
	 * 
	 * @param authList
	 * @param url
	 * @return
	 * @return boolean
	 * @see
	 */
	public static boolean checkUserAuthority(List<SysAuthority> authList,String url)
	{
		boolean result = false;
		if(authList!=null && authList.size()>0)
		{
			ArrayList<String> urlList = new ArrayList<String>();
			for(SysAuthority auth : authList)
			{
				urlList.add(auth.getAuthorityUrl());
			}
			if(urlList.contains(url))
			{
				return true;
			}
		}
		return result;
	}
	
	/**
	 * <p>将List<Integer>转化为List<String></p>
	 * 
	 * @param list
	 * @return
	 * @return List<String>
	 * @see
	 */
	public static List<String> listIntToString(List<Integer> list)
	{
		List<String> result = new ArrayList<String>();
		if(list != null && list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
				result.add(String.valueOf(list.get(i)));
			}
		}
		else
		{
			result = null;
		}
		return result;
	}
	
	/**
	 * <p>将String[]转化为List<String></p>
	 * 
	 * @param str
	 * @return
	 * @return List<String>
	 * @see
	 */
	public static List<String> arrayToList(String[] str)
	{
		List<String> result = new ArrayList<String>();
		if(str != null && str.length>0)
		{
			for(int i=0;i<str.length;i++)
			{
				result.add(str[i]);
			}
		}
		else
		{
			result = null;
		}
		return result;
	}
	
	/**
	 * <p>将String[]转化为List<Integer></p>
	 * 
	 * @param str
	 * @return
	 * @return List<String>
	 * @see
	 */
	public static List<Integer> arrayToIntegerList(String[] str)
	{
		List<Integer> result = new ArrayList<Integer>();
		if(str != null && str.length>0)
		{
			for(int i=0;i<str.length;i++)
			{
				result.add(Integer.parseInt(str[i]));
			}
		}
		else
		{
			result = null;
		}
		return result;
	}
	
	/**
	 * <p>将List<String>转化为逗号(,)分隔符分隔的字符串</p>
	 * 
	 * @param list
	 * @return
	 * @return String
	 * @see
	 */
	public  static String listTOString(List<Object> list)
	{
		return listTOString(list,',');
	}

	/**
	 * <p>将List<String>转化为分隔符分隔的字符串</p>
	 * 
	 * @param list
	 * @param pattern
	 * @return
	 * @return String
	 * @see
	 */
	public static String listTOString(List<Object> list,char pattern)
	{
		if(list!=null && list.size()>0)
		{
			StringBuffer result = new StringBuffer();
			for(int i=0;i<list.size();i++)
			{
				result.append(list.get(i).toString());
				if(i<list.size()-1)
					result.append(pattern);
			}
			return result.toString();
		}
		return null;
	}
	
	/**
	 * <p>方法描述：将List<String>中每个元素加上exPattern以后，再转化为分隔符分隔的字符串</p>
	 * 
	 * @param list
	 * @param pattern
	 * @param exPattern
	 * @return
	 * @return String
	 * @see
	 */
	public static String listTOString(List<Object> list,char pattern,String exPattern)
	{
		if(list!=null && list.size()>0)
		{
			StringBuffer result = new StringBuffer();
			for(int i=0;i<list.size();i++)
			{
				result.append(exPattern);
				result.append(list.get(i).toString());
				result.append(exPattern);
				if(i<list.size()-1)
					result.append(pattern);
			}
			return result.toString();
		}
		return null;
	}
}
