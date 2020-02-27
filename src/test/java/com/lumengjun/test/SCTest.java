package com.lumengjun.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lumengjun.entity.ShouCang;
import com.lumengjun.service.ShouCangService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class SCTest {

	@Autowired
	ShouCangService cangService;
	
	/**
	 * 测试添加收藏夹的方法
	 */
	@Test
	public void addShouCang(){
		ShouCang sc =new ShouCang(null, "hhhhh", "https://www.baidu.com/", 69, null);
		int i =cangService.addShouCang(sc);
		if(i>0){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}
	/**
	 * 测试删除收藏夹的方法
	 */
	@Test
	public void delShouCang(){
		cangService.delShouCangById(3);
	}
	
	/**
	 * 测试查询收藏夹的方法
	 */
	@Test
	public void findShouCang(){
		List<ShouCang> list= cangService.findShouCang(69);
		for (ShouCang shouCang : list) {
			System.out.println(shouCang);
		}
	}
	
}
