package options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith ( Cucumber.class)
@CucumberOptions(
        plugin = {"pretty:target/cucumber/cucumber.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                //"html:target/cucumber/report",
                "json:target/cucumber/cucumber.json",
                "MyTestListener"
        }
        ,features= {"src/test/java/feature"}
        ,glue = {"src/test/java/stepdefination"}
        //,dryRun = true
        ,monochrome = true
        ,snippets = CucumberOptions.SnippetType.CAMELCASE
        ,tags = "@bookerAPI"
        ,publish = true
)

public class TestRunner {
}
