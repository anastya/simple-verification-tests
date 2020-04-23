package ru.akhramenko;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PatternRulesCloseAccountTest extends BaseTest {

    @ParameterizedTest(name = "checkTruePatternRulesCloseAccount {0}")
    @CsvFileSource(resources = "/pattern-rules/PatternCloseAccountTrue.csv")
    public void checkTruePatternRulesCloseAccount(String textPattern) {
        // create json with pattern
        String body = makeBodyFromMapForRuleRequest(textPattern, RULE_CLOSE_ACCOUNT);

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

    @ParameterizedTest(name = "checkFalsePatternRulesCloseAccount {0}")
    @CsvFileSource(resources = "/pattern-rules/PatternCloseAccountFalse.csv")
    public void checkFalsePatternRulesCloseAccount(String textPattern) {
        // create json with pattern
        String body = makeBodyFromMapForRuleRequest(textPattern, RULE_CLOSE_ACCOUNT);

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
                .overridingErrorMessage("Expected the response for \"%s\" pattern to be: %s but it was: %s", textPattern, false, result)
                .isEqualTo(false);
    }
}

