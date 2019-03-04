package scenes;

import assets.entities.Camera;
import assets.entities.CollisionEntity;
import assets.entities.DefaultEntity;
import assets.entities.Light;
import assets.models.TexturedModel;
import assets.terrains.Terrain;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.MainRenderer;
import renderEngine.ModelLoader;

public class CollisionTest extends Scene {

    Light sun = new Light(new Vector3f(20,80,20), new Vector3f(1,1,1));
    Terrain terrain = new Terrain(-0.5f,-0.5f, loader, "grass2");

    TexturedModel stall = makeModel("stall", "stallTexture", true);
    TexturedModel cube = makeModel("cube", "colour/red", false);

    CollisionEntity stallA =
            new CollisionEntity(stall,new Vector3f(0,0,0),0,0,0,1);
    DefaultEntity cubeA =
            new DefaultEntity(cube,new Vector3f(stallA.getBoundingBox().getxMax(),
                    stallA.getBoundingBox().getyMax(),stallA.getBoundingBox().getzMax()),0,0,0,0.3f);

    public CollisionTest(ModelLoader loader) {
        super(loader);
    }

    @Override
    public void renderScene(MainRenderer renderer, Camera camera) {

        renderer.processEntity(stallA);
        renderer.processEntity(cubeA);

        renderer.processTerrain(terrain, 80);
        renderer.render(camera, sun, 0.2f, 0.007f, 1.5f);
    }
}
