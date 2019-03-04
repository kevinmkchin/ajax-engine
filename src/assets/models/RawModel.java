package assets.models;

import java.util.List;

public class RawModel { //represents a raw model data stored in memory

    private int vaoID;
    private int vertexCount;
    private List<Vertex> verticesForAABB = null;

    public RawModel(int vaoID, int vertexCount){
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID(){
        return vaoID;
    }

    public int getVertexCount(){
        return vertexCount;
    }

    public List<Vertex> getVerticesForAABB() {
        return verticesForAABB;
    }
    public void setVerticesForAABB(List<Vertex> verticesForAABB) {
        this.verticesForAABB = verticesForAABB;
    }
}
