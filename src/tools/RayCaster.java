package tools;

import assets.collisions.AABB;
import org.lwjgl.util.vector.Vector3f;

public class RayCaster {

    public boolean rayCastHit(Vector3f origin, Vector3f rayDirection, int rayLength, AABB target){
        Vector3f checkPoint;
        int twiceLength = rayLength * 2;

        for(int i = 1; i < twiceLength; i++) {
            checkPoint = new Vector3f();
            Vector3f checkRay = new Vector3f(rayDirection.x, rayDirection.y, rayDirection.z);
            checkRay.scale((float) i / 2);
            Vector3f.add(origin, checkRay, checkPoint);

            if(target.isPointColliding(checkPoint)){
                return true;
            }
        }

        return false;
    }

}
