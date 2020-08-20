package ee.swan.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    //sample
//    http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101

            //79bb75b01772ca1bc6f0faccbc3495e3
    @GetMapping("/getkobisData")
    public String call() throws JsonProcessingException {
       // String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
//        UriComponents uri =
//                UriComponentsBuilder
//                        .fromHttpUrl(url+"?"+"key=430156241533f1d058c603178cc3ca0e&targetDt=20200801")
//                        .build();
//
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("movie.naver.com")
                //.port(433)
                .path("/movie/sdb/rank/rmovie.nhn")
                .build()
                .encode()
                .toUri();

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

        int statusCodeValue = resultMap.getStatusCodeValue();
        HttpHeaders headers = resultMap.getHeaders();
        Map body = resultMap.getBody();

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(resultMap.getBody());

        return jsonInString;

    }
//
//    private ClientHttpRequestFactory getClientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
//                = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setConnectionRequestTimeout(5000);
//        clientHttpRequestFactory.setReadTimeout(5000);
//        return clientHttpRequestFactory;
//    }
}
