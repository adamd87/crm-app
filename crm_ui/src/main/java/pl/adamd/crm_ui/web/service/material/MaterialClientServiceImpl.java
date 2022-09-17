package pl.adamd.crm_ui.web.service.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.adamd.crm_ui.model.MaterialUI;

import java.util.List;

@Service
public class MaterialClientServiceImpl implements MaterialClientService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.url}")
    String apiHost;

    @Override
    public List<MaterialUI> getAllMaterials(String token) {

        HttpEntity<?> entityReq = getHttpEntity(token, null);
        String defaultUrl = apiHost + "api/auth/material/all";
        ResponseEntity<List<MaterialUI>> responseEntity =
                restTemplate.exchange(defaultUrl, HttpMethod.GET, entityReq, new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();

    }

    @Override
    public MaterialUI getMaterialById(String token, Long id) {
        String defaultUrl = apiHost + "api/auth/material/by-id/{id}";
        String url = defaultUrl.replace("{id}", String.valueOf(id));

        HttpEntity<?> entityReq = getHttpEntity(token, null);

        return restTemplate.exchange(url, HttpMethod.GET, entityReq, MaterialUI.class)
                           .getBody();
    }

    @Override
    public MaterialUI addNewMaterial(String token, MaterialUI materialUI) {
        String postUrl = apiHost + "api/auth/material/add";
        HttpEntity<?> entityReq = getHttpEntity(token, materialUI);

        return restTemplate.exchange(postUrl, HttpMethod.PUT, entityReq, MaterialUI.class)
                           .getBody();


    }

    @Override
    public MaterialUI updateMaterial(String token, Long id, MaterialUI materialUI) {

        HttpEntity<?> entityReq = getHttpEntity(token, materialUI);

        String defaultUrl = apiHost + "api/auth/material/update/{id}";
        String patchUrl = defaultUrl.replace("{id}", String.valueOf(id));

        return restTemplate.exchange(patchUrl, HttpMethod.PATCH, entityReq, MaterialUI.class)
                           .getBody();

    }

    private static HttpEntity<?> getHttpEntity(String token, MaterialUI materialUI) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(materialUI, headers);
    }
}
