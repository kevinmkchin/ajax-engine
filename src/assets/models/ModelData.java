package assets.models;

import java.util.List;

public class ModelData {

    private float[] vertices;
    private float[] textureCoords;
    private float[] normals;
    private int[] indices;
    private float furthestPoint;
    private List<Vertex> verticesForAABB = null;

    public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices, float furthestPoint) {
        this.vertices = vertices;
        this.textureCoords = textureCoords;
        this.normals = normals;
        this.indices = indices;
        this.furthestPoint = furthestPoint;
    }
    public ModelData(float[] vertices, float[] textureCoords, float[] normals, int[] indices, float furthestPoint,
                     List<Vertex> verticesForAABB) {
        this.vertices = vertices;
        this.textureCoords = textureCoords;
        this.normals = normals;
        this.indices = indices;
        this.furthestPoint = furthestPoint;
        this.verticesForAABB = verticesForAABB;
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getTextureCoords() {
        return textureCoords;
    }

    public float[] getNormals() {
        return normals;
    }

    public int[] getIndices() {
        return indices;
    }

    public float getFurthestPoint() {
        return furthestPoint;
    }

    public List<Vertex> getVerticesForAABB() {
        return verticesForAABB;
    }
}