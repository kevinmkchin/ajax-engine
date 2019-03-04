package assets.collisions;

import assets.models.Vertex;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

import java.util.ArrayList;
import java.util.List;

public class CollisionCreator {

    private List<Vector3f> transformedVertices = new ArrayList<>();

    public AABB createAABB(List<Vertex> vertices, Matrix4f transformMatrix){
        applyTransformations(vertices, transformMatrix);
        return calculateBoundingBox();
    }

    private void applyTransformations(List<Vertex> vertices, Matrix4f transformMatrix){
        for(Vertex vertex : vertices){
            Vector3f transformedVertex = Maths.applyTransformationMatrix(vertex.getPosition(), transformMatrix);
            transformedVertices.add(transformedVertex);
        }
    }

    private AABB calculateBoundingBox(){
        float tempXMax = 0;
        float tempXMin = 0;
        float tempZMax = 0;
        float tempZMin = 0;
        float tempYMax = 0;
        float tempYMin = 0;
        boolean initialValuesSet = false;

        for(Vector3f vec3 : transformedVertices){
            if(initialValuesSet) {
                if (vec3.x >= tempXMax) {
                    tempXMax = vec3.x;
                }
                if (vec3.x <= tempXMin) {
                    tempXMin = vec3.x;
                }
                if (vec3.z >= tempZMax) {
                    tempZMax = vec3.z;
                }
                if (vec3.z <= tempZMin) {
                    tempZMin = vec3.z;
                }
                if (vec3.y >= tempYMax) {
                    tempYMax = vec3.y;
                }
                if (vec3.y <= tempYMin) {
                    tempYMin = vec3.y;
                }
            }else{
                tempXMax = vec3.x;
                tempXMin = vec3.x;
                tempZMax = vec3.z;
                tempZMin = vec3.z;
                tempYMax = vec3.y;
                tempYMin = vec3.y;
                initialValuesSet = true;
            }
        }
        return new AABB(tempXMax, tempXMin, tempZMax, tempZMin, tempYMax, tempYMin);
    }

}
