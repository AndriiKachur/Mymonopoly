package net.mymonopoly.web.game;

import junit.framework.Assert;
import net.mymonopoly.service.GameServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class GameControllerTest{
	
	@Autowired
	private GameServiceImpl gameService;
	
	@Test
	public void test1(){
		Assert.assertNotNull(gameService);
	}
	
	

}
