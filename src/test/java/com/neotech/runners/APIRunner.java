package com.neotech.runners;

// API Class, Lesson 06, Part-01

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = "src/test/resources/features/",
		glue = "com.neotech.steps",
		dryRun = false,
		monochrome = true,
		tags = "@OneClass",

		plugin = { 
				// "pretty",
				"html:target/db-example-report.html", // This is from "target" folder.
				"json:target/cucumber.json",
		}
)

public class APIRunner {

}
