package com.github.rluisb.ruling;

import com.github.rluisb.ruling.cucumber.World;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		classes = {
				RulingServiceApplication.class,
				World.class
		})
public class TestConfig {}
