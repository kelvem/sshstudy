/**============================================================
 * 版权：kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.security.KelvemInvocationSecurityMetadataSource.java
 * 所含类: KelvemInvocationSecurityMetadataSource.java
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
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.kelvem.sample.struts2.model.security.SysAuthority;

/**
 * 
 * <p>KelvemInvocationSecurityMetadataSource</p>
 *
 * <p>取到所有资源及其对应角色的定义</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName KelvemInvocationSecurityMetadataSource
 * @author zhaobai
 * @version 1.0
 */
public class KelvemInvocationSecurityMetadataSource
        implements FilterInvocationSecurityMetadataSource {

	private static final Log log = LogFactory.getLog(KelvemInvocationSecurityMetadataSource.class);
    
    /**
     * 
     * <p>根据Url获取角色信息</p>
     * 
     * @param object Url
     * 
     * @return Collection<ConfigAttribute> 角色信息
     * @see
     */
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
		log.debug("UmpInvocationSecurityMetadataSource:getAttributes# " + object.toString());
    	Collection<ConfigAttribute> result = new ArrayList<ConfigAttribute>(0);
    	
    	// 获取登录用户信息
    	Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	HttpServletRequest request = ((FilterInvocation)object).getRequest();
//    	SecurityContext context = (SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//    	if(context != null){
//    		obj = (SecurityUserDetails) context.getAuthentication().getPrincipal();
//    	}
    	SecurityUserDetails user = null;
    	if ( obj instanceof SecurityUserDetails){ 
    		user = (SecurityUserDetails) obj;    	
    	}
    	if ( user == null || user.getSysUser() == null){
    		return result;
    	}    	
    	
    	// 获取用户访问的Url
        String url = ((FilterInvocation)object).getRequestUrl();
        
        // 如下Url，登录后即可访问
        if (url.startsWith("/index.html") || url.startsWith("/proxoolAdmin")) {
        	Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        	ConfigAttribute ca = new SecurityConfig("ROLE_ANONYMOUS");
        	atts.add(ca);
        	return atts;
        }
        
        // 如下Url，有访问权限才可以访问
        List<SysAuthority> listAuth = user.getSysUser().getAuthorityList();
        for (SysAuthority vo : listAuth) {
            String resURL = vo.getAuthorityUrl();
            // if (urlMatcher.pathMatchesUrl(resURL, url)) {
            if (url.startsWith(resURL)) {
            	Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
            	ConfigAttribute ca = new SecurityConfig(vo.getAuthorityName());
            	atts.add(ca);
            	result.addAll(atts);
            } 
        }
        
        return result;
    	
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
}
