package edu.eci.arsw.bombermanapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.bomberman"})
public class bombermanApi implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate template = new StringRedisTemplate();

    ValueOperations<String, String> ops = this.template.opsForValue();

    public static void main(String[] args) {
        SpringApplication.run(bombermanApi.class, args);
    }

    @Override
    public void run(String... args) {
        ValueOperations<String, String> ops = this.template.opsForValue();

        // Add a Hello World string to your cache.
        String key = "greeting";
        if (!this.template.hasKey(key)) {
            ops.set(key, "Hello World!");
        }

        // Return the string from your cache.
        System.out.println("Return the value from the cache: {}"+ ops.get(key));
    }
}
