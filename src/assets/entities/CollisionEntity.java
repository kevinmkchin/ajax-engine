package assets.entities;

import assets.collisions.AABB;
import assets.collisions.CollisionCreator;
import assets.models.TexturedModel;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import scenes.Scene;
import tools.Maths;

public class CollisionEntity extends DefaultEntity{

    //Collision Entity and AABoundingBox have a bi-directional relationship.
    private AABB boundingBox;

    public CollisionEntity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);

        if(model.getVerticesForAABB() != null){
            CollisionCreator cc = new CollisionCreator();
            Matrix4f transformMatrix = Maths.createTransformMatrix(position, rotX, rotY, rotZ, scale);

            this.boundingBox = cc.createAABB(model.getVerticesForAABB(), transformMatrix);
            boundingBox.setEntity(this);

            Scene.boundingBoxes.add(boundingBox);
            cc = null;
        } else {
            System.err.println("Given TexturedModel for CollisionEntity does not contain vertices for AABB.");
        }
    }

    public AABB getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void increasePosition(float dx, float dy, float dz){
        super.increasePosition(dx, dy, dz);
        boundingBox.updateBoundingBox(dx, dy, dz); //update the bounding box with translation
    }

}
