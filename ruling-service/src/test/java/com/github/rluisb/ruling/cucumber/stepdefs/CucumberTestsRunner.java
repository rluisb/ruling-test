package com.github.rluisb.ruling.cucumber.stepdefs;

import com.github.rluisb.ruling.RulingServiceApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = RulingServiceApplication.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.github.rluisb.ruling")
public class CucumberTestsRunner {
}