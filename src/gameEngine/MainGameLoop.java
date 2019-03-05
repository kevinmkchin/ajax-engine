package gameEngine;

import assets.entities.Camera;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.MainRenderer;
import renderEngine.ModelLoader;
import scenes.Scene;
import scenes.TestScene1;
import tools.editor.MousePicker;

public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        ///IMPORTANT GAME OBJECTS
        ///======================
        ModelLoader loader = new ModelLoader();
        MainRenderer mainRenderer = new MainRenderer();
        Camera mainCamera = new Camera();
        ///======================

        MousePicker picker = new MousePicker(mainCamera, mainRenderer.getProjectionMatrix());

        Scene scene = new TestScene1(loader);

        while(!Display.isCloseRequested()){ //main loop with game logic, rendering, and updating display
            mainCamera.update(); //update camera position

            //INITIALIZE SCENES HERE BY CHECKING FOR STATE CHANGE
            // for example
            // if (gameState_justSwitched) then
            //     if (gameState_1) then scene = new FirstScene();
            //     if (gameState_2) then scene = new SecondScene();

            // ==== testing ====
            picker.update();
            //System.out.println(picker.getCurrentRay());
            scene.renderScene(mainRenderer, mainCamera);
            // ==== testing ====

            DisplayManager.updateDisplay();
        }

        mainRenderer.cleanUp();
        loader.cleanMemory();
        DisplayManager.closeDisplay();
    }

}
