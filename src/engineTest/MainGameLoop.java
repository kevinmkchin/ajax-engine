package engineTest;

import assets.entities.Camera;
import assets.shaders.StaticShader;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;
import scenes.Scene;
import scenes.TestScene1;

public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        ///IMPORTANT GAME OBJECTS
        ModelLoader loader = new ModelLoader();
        StaticShader shader = new StaticShader();
        ModelRenderer renderer = new ModelRenderer(shader);
        Camera camera = new Camera();
        ///======================

        Scene scene = new TestScene1(loader);

        while(!Display.isCloseRequested()){ //main loop with game logic, rendering, and updating display
            renderer.prepare();
            shader.start();
            camera.move();
            shader.loadViewMatrix(camera);

            //INITIALIZE SCENES HERE BY CHECKING FOR STATE CHANGE
            // for example
            // if (gameState_justSwitched) then
            //     if (gameState_1) then scene = new FirstScene();
            //     if (gameState_2) then scene = new SecondScene();

            // ==== testing ====
            scene.renderScene(renderer, shader);
            // ==== testing ====

            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanMemory();
        DisplayManager.closeDisplay();
    }

}
