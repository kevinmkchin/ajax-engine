package engineTest;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;
import renderEngine.RawModel;

public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        ModelLoader loader = new ModelLoader();
        ModelRenderer renderer = new ModelRenderer();

        //sample vertices
        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };
        RawModel model = loader.loadToVAO(vertices);

        while(!Display.isCloseRequested()){ //main loop with game logic, rendering, and updating display
            renderer.prepare();


            renderer.render(model);

            DisplayManager.updateDisplay();
        }

        loader.cleanMemory();
        DisplayManager.closeDisplay();
    }

}
