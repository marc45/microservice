package org.apache.micro.eureka;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.netflix.discovery.DiscoveryManager;

public class EurekaServerApplicationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		DiscoveryManager.getInstance().getDiscoveryClient() ;
	}

}
