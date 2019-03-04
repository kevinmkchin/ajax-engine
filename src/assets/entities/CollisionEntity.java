package assets.entities;

import assets.collisions.AABB;
import assets.collisions.CollisionCreator;
import assets.models.TexturedModel;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

public class CollisionEntity extends DefaultEntity{

    private AABB boundingBox;

    public CollisionEntity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);

        if(model.getVerticesForAABB() != null){
            CollisionCreator cc = new CollisionCreator();
            Matrix4f transformMatrix = Maths.createTransformMatrix(position, rotX, rotY, rotZ, scale);
            this.boundingBox = cc.createAABB(model.getVerticesForAABB(), transformMatrix);
            cc = null;
        }
    }

    public AABB getBoundingBox() {
        return boundingBox;
    }

}
