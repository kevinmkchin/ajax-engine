package engineTest;

import assets.models.TexturedModel;
import assets.shaders.StaticShader;
import assets.textures.ModelTexture;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;
import assets.models.RawModel;

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

        while(!Display.isCloseRequested()){ //main loop with game logic, rendering, and updating display
            renderer.prepare();
            shader.start();

            // ==== testing ====
            renderer.render(texturedModel);


            // ==== testing ====

            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanMemory();
        DisplayManager.closeDisplay();
    }

}
