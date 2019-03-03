package scenes;

import assets.entities.Camera;
import assets.entities.DefaultEntity;
import assets.entities.Entity;
import assets.entities.Light;
import assets.models.TexturedModel;
import assets.terrains.Terrain;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.MainRenderer;
import renderEngine.ModelLoader;

public class TestScene1 extends Scene{


    TexturedModel texturedModel = makeModel("stall", "stallTexture");
    TexturedModel dragon = makeModel("dragon", "white", 10, 1);

    Entity entity1 = new DefaultEntity(texturedModel, new Vector3f(0,0,-30f),0,0,0,1);
    Entity dragon1 = new DefaultEntity(dragon, new Vector3f(10, -4, -25f), 0, 0, 0, 1);
    Light light1 = new Light(new Vector3f(0, 100, 0), new Vector3f(1,1,1));

    Terrain t1 = new Terrain(0,0, loader, "grass2");
    Terrain t2 = new Terrain(0,-1, loader, "grass2");
    Terrain t3 = new Terrain(-1, 0, loader, "grass2");
    Terrain t4 = new Terrain(-1, -1, loader, "grass2");

    //How to attach game logic to entities?


    public TestScene1(ModelLoader loader) {
        super(loader);
    }

    @Override
    public void renderScene(MainRenderer renderer, Camera camera) {

        renderer.processEntity(entity1);
        renderer.processEntity(dragon1);

        renderer.processTerrain(t1, 40);
        renderer.processTerrain(t2, 40);
        renderer.processTerrain(t3, 40);
        renderer.processTerrain(t4, 40);

        renderer.render(camera, light1, 0.2f);
    }

}
