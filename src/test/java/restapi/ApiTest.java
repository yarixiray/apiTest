package restapi;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"restapi"},
        plugin = {"pretty", "html:target/reports/CreateReport.html", "json:target/reports/CreateReport.json"}
//        ,tags = {"@AcceptanceCriteria"}
)
@Slf4j
public class ApiTest {
}
