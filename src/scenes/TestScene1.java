package scenes;

import assets.entities.*;
import assets.models.TexturedModel;
import assets.terrains.Terrain;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.MainRenderer;
import renderEngine.ModelLoader;

public class TestScene1 extends Scene{


    TexturedModel texturedModel =
            makeModel("stall", "stallTexture", true);
    TexturedModel dragon =
            makeModel("dragon", "colour/white", false,10, 1);
    TexturedModel grass3d =
            makeModel("grass3d", "grass3d", false);

    Entity stall1
            = new DefaultEntity(texturedModel, new Vector3f(0,0,-30f),0,0,0,1);
    Entity stall2
            = new DefaultEntity(texturedModel, new Vector3f(0,0,-40f),0,180,0,1);
    Entity stall3
            = new DefaultEntity(texturedModel, new Vector3f(-12,0,-34f),0,250,0,1);
    Entity grass
            = new DefaultEntity(grass3d, new Vector3f(0,0,0), 0, 0, 0, 1);
    CollisionEntity stallc
            = new CollisionEntity(texturedModel, new Vector3f(1,0,0),0,0,0,1);
    Entity dragon1 = new DefaultEntity(dragon, new Vector3f(10, 0, -25f), 0, 0, 0, 1);

    Light light1 = new Light(new Vector3f(0, 100, 0), new Vector3f(1,1,1));
    Terrain t1 = new Terrain(-0.5f,-0.5f, loader, "grass2");

    //How to attach game logic to entities?


    public TestScene1(ModelLoader loader) {
        super(loader);
    }

    @Override
    public void renderScene(MainRenderer renderer, Camera camera) {
        grass3d.getTexture().setHasTransparency(true);
        grass3d.getTexture().setUseFakeLighting(true);

        dragon1.increaseRotation(0,1,0);

        renderer.processEntity(stall1);
        renderer.processEntity(stall2);
        renderer.processEntity(stall3);
        renderer.processEntity(dragon1);
        renderer.processEntity(grass);
        renderer.processEntity(stallc);

        renderer.processTerrain(t1, 80);

        renderer.render(camera, light1, 0.2f, 0.007f, 1.7f);
    }

}
