package tools.editor;

import assets.entities.Entity;

public class SelectionHandler {

    private Entity selectedEntity;

    public void setSelectedEntity(Entity selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
    public Entity getSelectedEntity() {
        return selectedEntity;
    }
}
