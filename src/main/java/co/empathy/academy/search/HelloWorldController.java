package co.empathy.academy.search;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    public String getElasticVersion(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9200"
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(url, entity, HttpMethod.GET, String.class);
        String responseBody = response.getBody();


        return responseBody;
    }
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name){
        return "Hello " + name;
    }

    @GetMapping("/search")
    public Map<String, String> search(@RequestParam(name="query") String query){
        String response = getElasticVersion();
        Map<String, String> data = new HashMap<>();
        data.put("query", query);
        data.put("response",response);
        data.put("clusterName", response);
        return data;
    }
}
