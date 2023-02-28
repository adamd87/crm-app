package pl.adamd.crm_ui.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OfferUI {

    private Long id;
    private Long clientId;
    private String clientFullName;
    private List<MaterialUI> materialList;
    private BigDecimal costOfMaterials;
    private BigDecimal myWarmthACost;
    private BigDecimal myWarmthBCost;
    private BigDecimal cleanAirA;
    private BigDecimal cleanAirB;
    private String comment;
}
