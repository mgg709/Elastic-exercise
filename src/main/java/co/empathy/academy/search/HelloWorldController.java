package co.empathy.academy.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    public String getElasticVersion() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9200";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode version = root.path("version");
        JsonNode number = version.path("number");
        String result = number.toString();
        result = result.substring(1,7);

        return result;
    }
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name){
        return "Hello " + name;
    }

    @GetMapping("/search")
    public Map<String, String> search(@RequestParam(name="query") String query) throws JsonProcessingException {
        String response = getElasticVersion();
        Map<String, String> data = new HashMap<>();
        data.put("query", query);
        data.put("response",response);
        return data;
    }
}
