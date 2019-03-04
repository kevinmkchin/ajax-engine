package assets.collisions;

import assets.entities.CollisionEntity;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

//Kevin's Axis Aligned Bounding Box
public class AABB {

    private float xMax;
    private float xMin;
    private float zMax;
    private float zMin;
    private float yMax;
    private float yMin;
    private Vector3f maxCorner;
    private Vector3f minCorner;

    private CollisionEntity entity = null;

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

    public boolean isBoxColliding(AABB otherBox){
        float oxMax = otherBox.getMaxCorner().x;
        float oxMin = otherBox.getMinCorner().x;
        float oyMax = otherBox.getMaxCorner().y;
        float oyMin = otherBox.getMinCorner().y;
        float ozMax = otherBox.getMaxCorner().z;
        float ozMin = otherBox.getMaxCorner().z;
        boolean xBound = Maths.isNumBetween(xMin, xMax, oxMax) || Maths.isNumBetween(xMin, xMax, oxMin);
        boolean yBound = Maths.isNumBetween(yMin, yMax, oyMax) || Maths.isNumBetween(yMin, yMax, oyMin);
        boolean zBound = Maths.isNumBetween(zMin, zMax, ozMax) || Maths.isNumBetween(zMin, zMax, ozMin);

        return xBound && yBound && zBound;
    }

    public boolean isPointColliding(Vector3f point){
        boolean xBound = Maths.isNumBetween(xMin, xMax, point.x);
        boolean yBound = Maths.isNumBetween(yMin, yMax, point.y);
        boolean zBound = Maths.isNumBetween(zMin, zMax, point.z);

        return xBound && yBound && zBound;
    }

    //if isn't already tied to an entity,
    public void setEntity(CollisionEntity entity){
        if(this.entity == null) {
            this.entity = entity;
        }
    }
    public CollisionEntity getEntity(){
        return entity;
    }

    public Vector3f getMaxCorner() {
        return maxCorner;
    }
    public Vector3f getMinCorner() {
        return minCorner;
    }

    public void updateBoundingBox(float dx, float dy, float dz){
        xMax += dx;
        xMin += dx;
        yMax += dy;
        yMin += dy;
        zMax += dz;
        zMin += dz;
        maxCorner = new Vector3f(xMax, yMax, zMax);
        minCorner = new Vector3f(xMin, yMin, zMin);
    }

}
