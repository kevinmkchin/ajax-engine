package engineTest;

import assets.entities.DefaultEntity;
import assets.entities.Entity;
import assets.models.RawModel;
import assets.models.TexturedModel;
import assets.shaders.StaticShader;
import assets.textures.ModelTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;

public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        ModelLoader loader = new ModelLoader();
        ModelRenderer renderer = new ModelRenderer();

        StaticShader shader = new StaticShader();

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

        Entity entity1 = new DefaultEntity(texturedModel, new Vector3f(-0.7f,0,0),20,0,0,1);

        while(!Display.isCloseRequested()){ //main loop with game logic, rendering, and updating display
            entity1.increasePosition(0.001f,0,0);
            entity1.increaseRotation(0,1, 0);

            renderer.prepare();
            shader.start();

            // ==== testing ====
            renderer.render(entity1, shader);


            // ==== testing ====

            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanMemory();
        DisplayManager.closeDisplay();
    }

}
