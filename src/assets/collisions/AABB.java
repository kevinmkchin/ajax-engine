package assets.collisions;

import org.lwjgl.util.vector.Vector3f;

public class AABB {
    //Kevin's Axis Aligned Bounding Box

    private float xMax;
    private float xMin;
    private float zMax;
    private float zMin;
    private float yMax;
    private float yMin;
    private Vector3f maxCorner;
    private Vector3f minCorner;

    public AABB(float xMax, float xMin, float zMax, float zMin, float yMax, float yMin) {
        this.xMax = xMax;
        this.xMin = xMin;
        this.zMax = zMax;
        this.zMin = zMin;
        this.yMax = yMax;
        this.yMin = yMin;
        maxCorner = new Vector3f(xMax, yMax, zMax);
        minCorner = new Vector3f(xMin, yMin, zMin);
    }

    private boolean isColliding(AABB otherBox){
        return false;
    }

    public float getxMax() {
        return xMax;
    }
    public float getxMin() {
        return xMin;
    }
    public float getzMax() {
        return zMax;
    }
    public float getzMin() {
        return zMin;
    }
    public float getyMax() {
        return yMax;
    }
    public float getyMin() {
        return yMin;
    }
    public Vector3f getMaxCorner() {
        return maxCorner;
    }
    public Vector3f getMinCorner() {
        return minCorner;
    }
}
