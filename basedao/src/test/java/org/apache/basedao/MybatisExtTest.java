package org.apache.basedao;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.base.dao.table.Insert;
import org.apache.base.dao.table.Select;
import org.apache.base.dao.table.Update;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class MybatisExtTest extends TestCase {

	private SqlSession sqlSession;
	
	private TestMapper testMapper  ;
	
	private IdWorker idWorker ;

	@Before
	public void setUp() throws Exception {
		String resource = "mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = factory.openSession();
		testMapper = sqlSession.getMapper(TestMapper.class) ;
		
		inputStream.close(); 
		
		idWorker = new IdWorker(0, 0) ;
		
	}
	
	@Test
	public void testInsert() {
		TestDomain test = new TestDomain() ;
		test.setId(idWorker.nextId());
		test.setName("huangyw");
		test.setBirth(new Date());
		
		TestDomain test1 = new TestDomain() ;
		test1.setId(idWorker.nextId());
		test1.setName("zhangfenglan");
		test1.setBirth(new Date());
		
		Insert insert = Insert.newInsert(new TestDomain[]{test,test1}) ;
		testMapper.insert(insert) ;
		sqlSession.commit();
	}
	


	@Test
	public void testSelelct() {
		Select select = Select.newSelect(TestDomain.class) ;
		List<TestDomain> domains = testMapper.select(select) ;
		Assert.assertNotNull(domains);
		Assert.assertEquals(4, domains.size());
		
		Select selectOne = Select.newSelect(TestDomain.class) ;
		selectOne.where().equals("id", domains.get(0).getId()) ;
		List<TestDomain> onlyOne = testMapper.select(selectOne) ;
		Assert.assertEquals(1, onlyOne.size());
		Assert.assertNotNull(onlyOne.get(0).getName());
		
		Select selectProperties = Select.newSelect(TestDomain.class,new String[]{"name"}) ;
		selectProperties.where().equals("id", domains.get(0).getId()) ;
		List<TestDomain> byProperties = testMapper.select(selectProperties) ;
		Assert.assertEquals(1, byProperties.size());
		Assert.assertNotNull(byProperties.get(0).getName());
		Assert.assertEquals(0,byProperties.get(0).getId());
		Assert.assertNull(byProperties.get(0).getBirth());
		
		Select selectConditions = Select.newSelect(TestDomain.class) ;
		selectConditions.where().equals("name", "huangyw") ;
		List<TestDomain> conditionDomains = testMapper.select(selectConditions) ;
		Assert.assertTrue(conditionDomains != null && conditionDomains.size() > 0);
		for(TestDomain domain : conditionDomains){
			Assert.assertEquals("huangyw", domain.getName());
		}
		
	}



	@Test
	public void testSelelctUpdateById() {

		Select select = Select.newSelect(TestDomain.class) ;
		List<TestDomain> domains = testMapper.select(select) ;
		Assert.assertTrue(domains != null && domains.size() > 0);
		
		TestDomain updateDomain = domains.get(0) ;
		updateDomain.setName("huangywwwwwwww");
		
		Update update = Update.newUpdateNotNullById(updateDomain);
		testMapper.updateNotNullById(update);
		
		
		Select selectCondition = Select.newSelect(TestDomain.class) ;
		selectCondition.where().equals("id", updateDomain.getId()) ;
		List<TestDomain> results = testMapper.select(selectCondition) ;
		Assert.assertEquals(results.get(0).getName(), "huangywwwwwwww");
		
		sqlSession.commit();
		
		
	}
	
	public void testSelelctCount() {

	}
	
	public void testDelete(){
		
	}

	
	
	
}
