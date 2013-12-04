package com.kelvem.sample.test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.kelvem.common.model.PageResults;
import com.kelvem.sample.struts2.model.User;
import com.kelvem.sample.struts2.service.UserService;

/**============================================================
 * 版权： kelvem 版权所有 (c) 2012 - 2013
 * 文件：.TestUserService.java
 * 所含类: TestUserService
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-6-15       kelvem       创建文件，实现基本功能
 * ============================================================*/

/**
 * <p>TestUserService</p>
 *
 * <p>类用途详细说明</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName TestUserService
 * @author kelvem
 * @version 1.0
 */
@ContextConfiguration(locations = { "classpath*:spring/**/spring-context-*.xml" })
public class TestUserService extends AbstractTransactionalJUnit4SpringContextTests{
	
//	private static Logger log = Logger.getLogger(TestUserService.class);
//	private static ApplicationContext ctx = null;

	@Autowired
	private UserService userService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		ctx = this.applicationContext;
	}
	
	/**
	 * <p>方法描述</p>
	 * 
	 * @throws java.lang.Exception void
	 * @see
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link com.kelvem.sample.struts2.service.UserService#queryUser(int, int, com.kelvem.sample.struts2.vo.UserInVO)}.
	 */
	@Test
	public void testQueryUser() {
		PageResults<User> list = userService.queryUser(1, 100, null);
		System.out.println(list.getTotalCount());
	}

	/**
	 * Test method for {@link com.kelvem.sample.struts2.service.UserService#getUserById(java.lang.Integer)}.
	 */
	@Test
	public void testGetUserById() {
		User user = userService.getUserById(1);
		System.out.println(user);
	}

	/**
	 * Test method for {@link com.kelvem.sample.struts2.service.UserService#saveUser(com.kelvem.sample.struts2.model.User)}.
	 */
	@Test
	public void testSaveUser() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.kelvem.sample.struts2.service.UserService#updateUser(com.kelvem.sample.struts2.model.User)}.
	 */
	@Test
	public void testUpdateUser() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.kelvem.sample.struts2.service.UserService#deleteUser(com.kelvem.sample.struts2.model.User)}.
	 */
	@Test
	public void testDeleteUser() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.kelvem.sample.struts2.service.UserService#getCount()}.
	 */
	@Test
	public void testGetCount() {
//		fail("Not yet implemented");
	}


}
