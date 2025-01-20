package com.megandemo.spring_boot_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Value("Value annotations can directly specify strings")
    private String valueAnnotationString;

    @Value("${my.list.values}")
    private List<String> listValues;

    @Value("#{${dbValues}}")
    private Map<String, String> dbValues;

    @Autowired
    private DbSettings dbSettings;

    @Autowired
    private Environment env;

    @GetMapping("/greeting")
    public String hello() {
        return greetingMessage;
    }

    @GetMapping("/list")
    public List<String> list() {
        return listValues;
    }

    @GetMapping("/map")
    public Map<String, String> map() {
        return dbValues;
    }

    @GetMapping("/db")
    public String db() {
        return dbSettings.getConnection() + ", " + dbSettings.getHost() + ", " + dbSettings.getPort();
    }

    @GetMapping("/envdetails")
    public String envDetails() {
        return env.toString();
    }
}
