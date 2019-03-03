package assets.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private boolean editorMode = true;

    private Vector3f position = new Vector3f(0,10,0);
    private float pitch = 0;
    private float yaw = 0;
    private float roll;
    private float sensitivity = 0.1f;
    private float cameraSpeed = 0.8f;

    public void update(){
        //TODO CHANGE CAMERA MOVEMENT

        //Calculate direction vector from Pitch and Yaw
        float x = (float) (Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)));
        float y = (float) -Math.sin(Math.toRadians(pitch));
        float z = (float) (Math.cos(Math.toRadians(pitch)) * -Math.cos(Math.toRadians(yaw)));
        Vector3f towardsCentre = new Vector3f(x,y,z);
        towardsCentre.scale(cameraSpeed);
        Vector3f perpendicularVector = new Vector3f(towardsCentre.z, 0, -towardsCentre.x);
        perpendicularVector.scale(cameraSpeed);

        if(editorMode) {
            if(Mouse.isButtonDown(1)) { //only move camera when holding right click
                calculatePitch();
                calculateYaw();
                if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                    Vector3f.add(position, towardsCentre, position);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                    Vector3f.sub(position, towardsCentre, position);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                    Vector3f.add(position, perpendicularVector, position);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                    Vector3f.sub(position, perpendicularVector, position);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
                    position.y += cameraSpeed;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
                    position.y -= cameraSpeed;
                }
            }
        }


    }

    //get camera position, find vector towards 0, 0 on the screen, normalize to unit vector, multiply unit vector by speed, then add that to camera position

    public Vector3f getPosition() {
        return position;
    }
    public float getPitch() {
        return pitch;
    }
    public float getYaw() {
        return yaw;
    }
    public float getRoll() {
        return roll;
    }
    public void setEditorMode(Boolean bool){
        this.editorMode = bool;
    }
    public boolean getEditorMode(){
        return editorMode;
    }

    private void calculatePitch(){
        float pitchChange = Mouse.getDY() * sensitivity;
        pitch -= pitchChange;
    }
    private void calculateYaw(){
        float yawChange = Mouse.getDX() * sensitivity;
        yaw += yawChange;
    }


}
