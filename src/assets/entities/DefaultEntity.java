package assets.entities;

import assets.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class DefaultEntity extends Entity {

    public DefaultEntity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale){
        super(model, position, rotX, rotY, rotZ, scale);
    }

}
