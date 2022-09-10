package pl.adamd.crm_ui.web.service.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.adamd.crm_ui.model.CustomerUI;
import pl.adamd.crm_ui.model.OfferUI;

import java.util.List;

@Component
public class OfferClientServiceImpl
        implements OfferClientService {

    private static HttpEntity<?> getHttpEntity(String token, OfferUI offerUI) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(offerUI, headers);
    }

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<OfferUI> getAllOffers(String token) {

        HttpEntity<?> entityReq = getHttpEntity(token, null);
        String defaultUrl = "http://localhost:8080/api/auth/offer/all";
        ResponseEntity<List<OfferUI>> responseEntity =
                restTemplate.exchange(defaultUrl, HttpMethod.GET, entityReq, new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    @Override
    public OfferUI getOfferById(String token, Long id) {
        HttpEntity<?> entityReq = getHttpEntity(token, null);
        String defaultUrl = "http://localhost:8080api/auth/offer/by-id/{id}";
        String url = defaultUrl.replace("{id}", String.valueOf(id));

        return restTemplate.exchange(url, HttpMethod.GET, entityReq, OfferUI.class)
                           .getBody();

    }

    @Override
    public OfferUI addOffer(String token, OfferUI offerUI) {
        String postUrl = "http://localhost:8080/api/auth/offer/add";

        HttpEntity<?> entityReq = getHttpEntity(token, offerUI);

        return restTemplate.exchange(postUrl, HttpMethod.POST, entityReq, OfferUI.class)
                           .getBody();

    }

    @Override
    public OfferUI updateOffer(String token, Long id, OfferUI offerUI) {
        HttpEntity<?> entityReq = getHttpEntity(token, offerUI);
        String defaultUrl = "http://localhost:8080/api/auth/offer/update/{id}";
        String patchUrl = defaultUrl.replace("{id}", String.valueOf(id));

        return restTemplate.exchange(patchUrl, HttpMethod.PATCH, entityReq, OfferUI.class)
                           .getBody();

    }
}
