package scenes;

import assets.entities.DefaultEntity;
import assets.entities.Entity;
import assets.models.RawModel;
import assets.models.TexturedModel;
import assets.shaders.StaticShader;
import assets.textures.ModelTexture;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;

public class TestScene1 extends Scene{

    //sample vertices
    float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
    };
    int[] indices = {
            0, 1, 3,
            3, 1, 2
    };
    float[] textureCoords = {
            0,0,
            0,1,
            1,1,
            1,0
    };
    RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
    ModelTexture texture = new ModelTexture(loader.loadTexture("stones"));
    TexturedModel texturedModel = new TexturedModel(model, texture);
    Entity entity1 = new DefaultEntity(texturedModel, new Vector3f(0,0,-1f),0,0,0,1);

    
    public TestScene1(ModelLoader loader) {
        super(loader);
    }

    @Override
    public void renderScene(ModelRenderer renderer, StaticShader shader) {
        entity1.increasePosition(0,0,-0.01f);
        entity1.increaseRotation(0,0, 0);
        renderer.render(entity1, shader);
    }

}
