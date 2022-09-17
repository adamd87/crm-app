package pl.adamd.crm_ui.ui.utils;

public enum Menu {
    Home("Witaj"),
    Customers("Klienci"),
    Offers("Oferty"),
    Materials("Produkty"),
    Agreements("Umowy"),
    Realizations("Realizacje"),
    Agents("Agenci"),
    Finances("Wyniki");


    private String title;

    Menu(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFxml() {
        return String.format("%s.fxml", name());
    }
}
