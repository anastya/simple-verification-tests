package ru.akhramenko;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import ru.akhramenko.config.Cfg;


import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected static Cfg cfg;

    protected static String SERVICE_URL;
    protected static String VERSION_API;
    protected static String METHOD = "/pattern_rules_processor";

    // rules for verification_service
    protected static final String RULE_WITH_NDS = "with_nds";
    protected static final String RULE_WITHOUT_NDS = "without_nds";
    protected static final String RULE_HAS_NDS_INFO = "has_nds_info";
    protected static final String RULE_NDFL = "ndfl";
    protected static final String RULE_CORP_CARD = "corp_card";
    protected static final String RULE_HOUSEHOLD_EXPENSES = "household_expenses";
    protected static final String RULE_CLOSE_ACCOUNT = "close_account";
    protected static final String RULE_SALARY = "salary";
    protected static final String RULE_SALARY_REG = "salary_registry";
    protected static final String RULE_OWN_FOUNDS_TRANSFER = "own_founds_transfer_rule";
    protected static final String RULE_ORDERS = "orders";
    protected static final String RULE_FOR_GOODS = "for_goods";

    // code
    public static final Integer SUCCESSFUL_CODE = 200;

    //use requestSpecification to reuse common request parameter
    public static RequestSpecification spec;

    @BeforeAll
    public static void initSpec() throws Exception {
        cfg = Cfg.loadFromEnv();
        SERVICE_URL = cfg.serviceUrl();
        VERSION_API = cfg.versionApi();
        spec = new RequestSpecBuilder()
                .setBaseUri(SERVICE_URL)
                .setBasePath(VERSION_API)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected static String getBodyForPostRequest(Map<String, String> map) {
        Gson gson = new GsonBuilder().create();
        String body = gson.toJson(map);
        return body;
    }

    public static String makeBodyFromMapForRuleRequest(String text, String rule) {
        Map<String, String> map = new HashMap<>();
        map.put("texts", text);
        map.put("rules", rule);
        String body = getBodyForPostRequest(map);
        return body;
    }

}

