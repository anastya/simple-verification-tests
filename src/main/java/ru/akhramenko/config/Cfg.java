package ru.akhramenko.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

import java.util.Properties;

import static org.aeonbits.owner.Config.Sources;

@Sources({"classpath:env.properties"})
public interface Cfg extends Config {

    @Key("SERVICE_URL")
    String serviceUrl();

    @Key("VERSION_API")
    String versionApi();

    static Cfg loadFromEnv() {
        Properties props = new Properties();
        props.putAll(System.getenv());
        return ConfigFactory.create(Cfg.class, props);
    }
}

