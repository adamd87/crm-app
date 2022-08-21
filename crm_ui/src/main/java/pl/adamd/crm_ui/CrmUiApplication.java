package pl.adamd.crm_ui;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import pl.adamd.crm_ui.ui.MainApplication;

@SpringBootApplication
@Configuration
public class CrmUiApplication {

	public static void main(String[] args) {
		Application.launch(MainApplication.class, args);
	}

}
