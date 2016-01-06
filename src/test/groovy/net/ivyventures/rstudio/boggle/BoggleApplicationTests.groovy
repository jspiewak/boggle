package net.ivyventures.rstudio.boggle

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = BoggleApplication)
@WebAppConfiguration
class BoggleApplicationTests {

	@Test
	void contextLoads() {
	}

}
