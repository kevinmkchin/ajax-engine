package tools;

import assets.collisions.AABB;
import assets.entities.Camera;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class MousePicker {

    private final int RAY_RANGE = 140; //range of ray cast is half of this value

    private Vector3f currentRay;
    private Vector3f origin;

    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private Camera camera;

    public MousePicker(Camera camera, Matrix4f projectionMatrix){
        this.camera = camera;
        this.projectionMatrix = projectionMatrix;
        this.viewMatrix = Maths.createViewMatrix(camera);
    }

    public Vector3f getCurrentRay(){
        return currentRay;
    }

    public void update(){
        viewMatrix = Maths.createViewMatrix(camera);
        origin = camera.getPosition();
        currentRay = calculateRayCast();
    }

    //CHECK IF MOUSE RAY HIT A BOUNDING BOX
    //Iterate through increasing lengths of currentRay direction vector
    //if an iteration of a ray has its end point inside a bounding box, return true
    public boolean isRayHittingBox(AABB box){
        Vector3f checkPoint;

        for(int i = 1; i < RAY_RANGE; i++) {
            checkPoint = new Vector3f();
            Vector3f checkRay = new Vector3f(currentRay.x, currentRay.y, currentRay.z);
            checkRay.scale((float) i / 2);
            Vector3f.add(origin, checkRay, checkPoint);

            if(box.isPointColliding(checkPoint)){
                return true;
            }
        }
        return false;
    }

    private Vector3f calculateRayCast(){
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();
        Vector2f normalizedCoords = getNormalizedOpenGLCoordinates(mouseX, mouseY);
        Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1f, 1f);
        Vector4f eyeCoords = convertToEyeSpace(clipCoords);
        Vector3f worldRay = convertToWorldSpace(eyeCoords);
        return worldRay;
    }

    private Vector4f convertToEyeSpace(Vector4f clipCoords){
        Matrix4f invertedProjectionMatrix = Matrix4f.invert(projectionMatrix, null);
        Vector4f eyeCoords = Matrix4f.transform(invertedProjectionMatrix, clipCoords, null);
        return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
    }

    private Vector3f convertToWorldSpace(Vector4f eyeCoords){
        Matrix4f invertedViewMatrix = Matrix4f.invert(viewMatrix, null);
        Vector4f worldRay = Matrix4f.transform(invertedViewMatrix, eyeCoords, null);
        Vector3f mouseRay = new Vector3f(worldRay.x, worldRay.y, worldRay.z);
        mouseRay.normalise();
        return mouseRay;
    }

    // -1 to 0 to +1 screen space coordinate system
    private Vector2f getNormalizedOpenGLCoordinates(float mouseX, float mouseY){
        float x = (2f * mouseX) / Display.getWidth() - 1f;
        float y = (2f * mouseY) / Display.getHeight() - 1f;
        return new Vector2f(x, y);
    }

}
