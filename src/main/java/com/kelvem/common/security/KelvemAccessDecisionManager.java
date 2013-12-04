/**============================================================
 * 版权：kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.security.KelvemAccessDecisionManager.java
 * 所含类: KelvemAccessDecisionManager.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-05      zhaobai       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.security;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * <p>KelvemAccessDecisionManager</p>
 *
 * <p>判断用户的角色是否有该对象的访问权限</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName KelvemAccessDecisionManager
 * @author zhaobai
 * @version 1.0
 */
public class KelvemAccessDecisionManager implements AccessDecisionManager {
	
	private static Log log = LogFactory.getLog(KelvemAccessDecisionManager.class);
	/**
	 * 
	 * <p>判断用户角色是否有该URL的访问权限</p>
	 * 
	 * @param authentication 权限类
	 * @param object URL对象
	 * @param configAttributes
	 * 
	 * @throws AccessDeniedException 权限不足异常
	 * @throws InsufficientAuthenticationException
	 */
	@Override
    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
    	
        if(configAttributes == null){
            return ;
        }
        log.info("UmpAccessDecisionManager:decide# " + object.toString());
        
        // 判断访问权限
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authentication.getAuthorities()){
                if(needRole.equals(ga.getAuthority())){  //ga is user's role.
                    return;
                }
            }
        }
        
        // 当没有访问权限时
        throw new AccessDeniedException("no right");
    }
	
    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


}
