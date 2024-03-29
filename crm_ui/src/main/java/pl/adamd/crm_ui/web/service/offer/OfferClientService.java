package pl.adamd.crm_ui.web.service.offer;

import pl.adamd.crm_ui.model.OfferUI;
import pl.adamd.crm_ui.model.OfferUIViewToList;

import java.util.List;

public interface OfferClientService {

    List<OfferUIViewToList> getAllOffers(String token);

    OfferUI getOfferById(String token, Long id);

    OfferUI addOffer(String token, OfferUI offerUI);

    OfferUI updateOffer(String token, Long id, OfferUI offerUI);

    List<OfferUIViewToList> getByName(String accessToken, String text);
}
