package assets.entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {

    //Constructor takes in a xyz position in 3d space for the point light
    //and a rgb colour as a vector3

    private Vector3f position;
    private Vector3f colour;

    public Light(Vector3f position, Vector3f colour){
        this.position = position;
        this.colour = colour;
    }

    public Vector3f getPosition() {
        return position;
    }
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getColour() {
        return colour;
    }
    public void setColour(Vector3f colour) {
        this.colour = colour;
    }
}
