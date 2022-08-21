package pl.adamd.crm_ui.web.service.material;

import pl.adamd.crm_ui.model.MaterialUI;

import java.util.List;

public interface MaterialClientService {

    List<MaterialUI> getAllMaterials(String token);

    MaterialUI getMaterialById(String token, Long id);

    MaterialUI addNewMaterial(String token, MaterialUI materialUI);

    MaterialUI updateMaterial(String token, Long id, MaterialUI materialUI);

}
