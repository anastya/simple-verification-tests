package ru.akhramenko;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PatternRulesHasNdsInfoTest extends BaseTest {

    @ParameterizedTest(name = "checkTruePatternRulesHasNdsInfoWithout {0}")
    @CsvFileSource(resources = "/pattern-rules/PatternWithoutNds.csv")
    public void checkTruePatternRulesHasNdsInfoWithout(String textPattern) {
        // create json with pattern
        String body = makeBodyFromMapForRuleRequest(textPattern, RULE_HAS_NDS_INFO);

        // post request with pattern and verify response
        Boolean result = given()
                .spec(spec)
                .body(body)
                .when()
                .post(METHOD)
                .then()
                .assertThat()
                .statusCode(SUCCESSFUL_CODE)
                .extract()
                .path("results.result[0].value[0]");

        assertThat(result)
                .overridingErrorMessage("Expected the response for \"%s\" pattern to be: %s but it was: %s", textPattern, true, result)
                .isEqualTo(true);
    }

    @ParameterizedTest(name = "checkTruePatternRulesHasNdsInfoWith {0}")
    @CsvFileSource(resources = "/pattern-rules/PatternWithNds.csv")
    public void checkTruePatternRulesHasNdsInfoWith(String textPattern) {
        // create json with pattern
        String body = makeBodyFromMapForRuleRequest(textPattern, RULE_HAS_NDS_INFO);

        // post request with pattern and verify response
        Boolean result = given()
                .spec(spec)
                .body(body)
                .when()
                .post(METHOD)
                .then()
                .assertThat()
                .statusCode(SUCCESSFUL_CODE)
                .extract()
                .path("results.result[0].value[0]");

        assertThat(result)
                .overridingErrorMessage("Expected the response for \"%s\" pattern to be: %s but it was: %s", textPattern, true, result)
                .isEqualTo(true);
    }
}
