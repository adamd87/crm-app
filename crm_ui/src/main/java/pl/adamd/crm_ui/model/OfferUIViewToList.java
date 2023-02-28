package pl.adamd.crm_ui.model;

public class OfferUIViewToList {

    private Long id;

    private String clientId;

    private String clientFullName;
    private String costOfMaterials;
    private String myWarmthACost;
    private String myWarmthBCost;
    private String cleanAirA;
    private String cleanAirB;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getCostOfMaterials() {
        return costOfMaterials;
    }

    public void setCostOfMaterials(String costOfMaterials) {
        this.costOfMaterials = costOfMaterials;
    }

    public String getMyWarmthACost() {
        return myWarmthACost;
    }

    public void setMyWarmthACost(String myWarmthACost) {
        this.myWarmthACost = myWarmthACost;
    }

    public String getMyWarmthBCost() {
        return myWarmthBCost;
    }

    public void setMyWarmthBCost(String myWarmthBCost) {
        this.myWarmthBCost = myWarmthBCost;
    }

    public String getCleanAirA() {
        return cleanAirA;
    }

    public void setCleanAirA(String cleanAirA) {
        this.cleanAirA = cleanAirA;
    }

    public String getCleanAirB() {
        return cleanAirB;
    }

    public void setCleanAirB(String cleanAirB) {
        this.cleanAirB = cleanAirB;
    }
}
