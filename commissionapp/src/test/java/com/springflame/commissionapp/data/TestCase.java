package com.springflame.commissionapp.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

@Getter
@AllArgsConstructor
public class TestCase {

    private String name;
    private LinkedHashMap<String, String> requestResponsePairs;

    static TestCase of(String name, String... filePaths) {
        LinkedHashMap<String, String> requestResponsePairs = new LinkedHashMap<>();
        for(int i = 0; i < filePaths.length; i += 2) {
            requestResponsePairs.put(readFile(filePaths[i]), readFile(filePaths[i+1]));
        }
        return new TestCase(name, requestResponsePairs);
    }

    private static String readFile(String fileName) {
        try {
            InputStream resource = new ClassPathResource(fileName).getInputStream();
            return new String(resource.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize test data, root cause: " + e);
        }
    }
}
