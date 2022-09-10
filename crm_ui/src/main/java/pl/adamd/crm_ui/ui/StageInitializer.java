package pl.adamd.crm_ui.ui;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.adamd.crm_ui.ui.controllers.Login;

@Component
@AllArgsConstructor
public class StageInitializer
        implements ApplicationListener<MainApplication.StageReadyEvent> {

    private Login login;

    @Override
    public void onApplicationEvent(MainApplication.StageReadyEvent event) {
        login.loadView(event.getStage());
    }


}
