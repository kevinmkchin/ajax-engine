package assets.models;

import assets.textures.ModelTexture;

import java.util.List;

public class TexturedModel {

    private RawModel rawModel;
    private ModelTexture texture;
    private List<Vertex> verticesForAABB = null;

    public TexturedModel(RawModel rawModel, ModelTexture texture){
        this.rawModel = rawModel;
        this.texture = texture;
        if(rawModel.getVerticesForAABB() != null){
            this.verticesForAABB = rawModel.getVerticesForAABB();
        }
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public List<Vertex> getVerticesForAABB() {
        return verticesForAABB;
    }
}
