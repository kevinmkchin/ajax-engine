package scenes;

import assets.entities.DefaultEntity;
import assets.entities.Entity;
import assets.models.TexturedModel;
import assets.shaders.StaticShader;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;

public class TestScene1 extends Scene{


    TexturedModel texturedModel = makeModel("stall", "stallTexture");

    Entity entity1 = new DefaultEntity(texturedModel, new Vector3f(0,0,-30f),0,0,0,1);
    Entity anotherStall = new DefaultEntity(texturedModel, new Vector3f(10, -1, -20f), 0, 40, 0, 1);


    public TestScene1(ModelLoader loader) {
        super(loader);
    }

    @Override
    public void renderScene(ModelRenderer renderer, StaticShader shader) {
        entity1.increaseRotation(0,1f, 0);

        renderer.render(entity1, shader);
        renderer.render(anotherStall, shader);
    }

}
