/**============================================================
 * 版权：kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.security.KelvemFilterSecurityInterceptor.java
 * 所含类: KelvemFilterSecurityInterceptor.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-04      zhaobai       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * <p>KelvemFilterSecurityInterceptor</p>
 *
 * <p>权限控制的过滤器类</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName KelvemFilterSecurityInterceptor
 * @author zhaobai
 * @version 1.0
 */
public class KelvemFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	
	// 权限控制的数据源
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	/**
	 * <p>为过滤器植入方法</p>
	 * 
	 * @param fi 过滤器调用对象
	 * @throws IOException
	 * @throws ServletException
	 * @return void
	 * @see
	 */
	public void invoke(FilterInvocation fi) throws IOException,
			ServletException {
		
		// if need sso, write here
		// to be continue
		

		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}
	
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	/**
	 * @param securityMetadataSource the newSource to set
	 */
	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource newSource) {
		this.securityMetadataSource = newSource;
	}

	/**
	 * @return the securityMetadataSource
	 */
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
}