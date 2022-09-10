package pl.adamd.crm_ui.ui.controllers.popups;

import pl.adamd.crm_ui.model.CustomerUI;

import java.util.function.Consumer;

public class CustomerEdit {

    private CustomerUI customerUI;
    private Consumer<CustomerUI> saveHandler;

    public static void addNew(Consumer<CustomerUI> saveHandler) {
        edit(null, saveHandler);
    }

    public static void edit(CustomerUI customerUI, Consumer<CustomerUI> saveHandler) {

    }
}
