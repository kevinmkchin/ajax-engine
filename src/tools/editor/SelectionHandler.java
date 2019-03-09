package tools.editor;

import assets.entities.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class SelectionHandler {

    private Entity selectedEntity = null;
    private float moveSpeed = 0.1f;
    private float rotateSpeed = 1f;

    public void setSelectedEntity(Entity selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
    public Entity getSelectedEntity() {
        return selectedEntity;
    }

    public void updateEntity(){
        if(!Mouse.isButtonDown(1)) {
            updatePosition();
            updateRotation();
        }
    }

    private void updatePosition(){
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            selectedEntity.increasePosition(0, 0, moveSpeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            selectedEntity.increasePosition(0, 0, -moveSpeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            selectedEntity.increasePosition(-moveSpeed, 0, 0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            selectedEntity.increasePosition(moveSpeed, 0, 0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            selectedEntity.increasePosition(0, moveSpeed, 0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            selectedEntity.increasePosition(0, -moveSpeed, 0);
        }
    }

    private void updateRotation(){
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            selectedEntity.increaseRotation(0, rotateSpeed, 0);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            selectedEntity.increaseRotation(0, -rotateSpeed, 0);
        }
    }

}
