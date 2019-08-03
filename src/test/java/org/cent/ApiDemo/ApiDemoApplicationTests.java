package org.cent.ApiDemo;

import org.cent.ApiDemo.model.pojo.ItemTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

	@Autowired
	ItemTemplate itemTemplate;

	@Test
	public void contextLoads() {
		System.out.println(itemTemplate.getItem("test"));
	}

}
