package Runner;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "C:\\Users\\sides\\eclipse-workspace\\git_project\\src\\test\\java\\Features\\Git_scenarios.feature",
glue = {"StepDefs"},
strict = true)
public class TestRunner {

}
