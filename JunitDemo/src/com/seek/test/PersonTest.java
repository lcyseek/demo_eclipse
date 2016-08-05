package com.seek.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;

import com.seek.hal.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//@RunWith 指定运行器
//Parameterized.class 参数化运行器，配合@Parameters使用JUnit的参数化功能
//Suite.class
//	@SuiteClasses({ATest.class,BTest.class,CTest.class})
//	测试集运行器配合使用测试集功能

//JUnit4 默认运行器
//@RunWith(JUnit4.class)


//@Parameters:用于JUnit的参数化功能，用来标记准备数据的方法。模拟传入不同的参数来测试方法
//要求:
//	1.该方法必须是public static的
//	2.返回值必修是java.util.Collection类型的
//	3.名字不作要求
//	4.没有参数

/**
 * 步骤
 * 1.位准备使用参数化得测试类指定特殊运行器 Parameterized
 * 2.为测试类声明几个变量，分别存放期望值和测试所用的数据
 * 3.位测试类声明一个带有参数的公共构造函数，并在其中为第二个环节中声明的几个变量赋值。
 * 4.为测试类声明一个使用注解Parameters修饰的，返回值为Collection的静态方法，并在此方法中初始化所有需要测试的参数对
 * 5.编写测试方法，使用定义的变量作为参数进行测试
 *
 */

/**
 * 在一个项目里，有很多测试类，一个一个的测试是不可能的。所以需要打包测试
 * @RunWith(Suite.class)
 * @Suite.SuiteClasses({ATest.class,BTest.class,....})
 *
 *可以新建一个测试文件
 * @RunWith(Suite.class)
 * @Suite.SuiteClasses({ATest.class,BTest.class,....})
 * 
 * public class AllTest{
 * }
 * 有了这两个标注之后，上面的类就无关紧要了，内容全部为空即可
 *
 */


@RunWith(Parameterized.class)
public class PersonTest {

	private int paramA;//参数
	private int paramB;//参数
	private int result;//期望值
	
	public PersonTest(int paramA, int paramB,int result) {
		super();
		this.paramA = paramA;
		this.paramB = paramB;
		this.result = result;
	}

	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{{2,4,6},{3,5,8},{4,7,11},{11,20,31},{40,20,60}});
	}
	
	@Test
	public void testCal(){
		assertEquals(result, p.cal(paramA, paramB));
	}
	
	private Person p = null;
	
	/**
	 * @Before 和 @After每一个测试方法调用前都会先调用此方法！
	 * @BeforeClass 和 @AfterClass 是在测试用例的初始化和结束时才调用的。
	 * 比如一个类负责处理大文件500MB的。每个测试方法调用的时候都去读取一次这个文件显示不合适。所以应该使用@BeforeClass 和 @AfterClass
	 */
	@BeforeClass
	public static void init(){
		System.out.println("BeforeClass");
	}
	
	@AfterClass
	public static void end(){
		System.out.println("AfterClass");
	}
	
	@Before
	public void setUp() throws Exception {
		p = new Person(11, "小李飞刀");
		System.out.println("Before");
	}
	
	@After
	public void setAfter(){
		System.out.println("After");
	}
	
	

	@Test
	public void testGetAge() {
		assertEquals(p.getAge(), 11);
	}

	@Test
	public void testSetAge() {
		p.setAge(14);
	}

	@Test
	public void testGetName() {
		assertEquals(p.getName(), "小李飞刀");
	}

	@Test
	public void testSetName() {
		p.setName("hahaha");
	}

	//测试是否抛出ArithmeticException异常
	@Test(expected=ArithmeticException.class)
	public void testDiv(){
		p.div(11, 0);
	}
	
	@Test
	public void testShowFrame(){
		p.showFrame();
	}
	
	@Test
	public void testToString() {
		System.out.println(p);
	}
	
	@Ignore
	//某些方法可能会耗时很久，指定timeout可以给他设定执行时间，超过会报错
	@Test(timeout=3000)
	public void testUseTime() {
		p.useTime();
	}
}
