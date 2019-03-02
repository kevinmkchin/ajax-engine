package scenes;

import assets.entities.DefaultEntity;
import assets.entities.Entity;
import assets.entities.Light;
import assets.models.TexturedModel;
import assets.shaders.StaticShader;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;

public class TestScene1 extends Scene{


    TexturedModel texturedModel = makeModel("stall", "stallTexture");
    TexturedModel dragon = makeModel("dragon", "white");

    Entity entity1 = new DefaultEntity(texturedModel, new Vector3f(0,0,-30f),0,0,0,1);
    Entity dragon1 = new DefaultEntity(dragon, new Vector3f(10, -4, -20f), 0, 40, 0, 1);
    Light light1 = new Light(new Vector3f(0, 0, -20), new Vector3f(1,0,1));
    //How do i do multiple light sources?


    public TestScene1(ModelLoader loader) {
        super(loader);
    }

    @Override
    public void renderScene(ModelRenderer renderer, StaticShader shader) {
        entity1.increaseRotation(0,1f, 0);

        shader.loadLight(light1);
        renderer.render(entity1, shader);
        renderer.render(dragon1, shader);
    }

}
