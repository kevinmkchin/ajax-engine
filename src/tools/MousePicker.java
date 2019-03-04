package tools;

import assets.entities.Camera;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class MousePicker {

    private Vector3f currentRay;

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
        currentRay = calculateRayCast();
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
