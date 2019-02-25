package engineTest;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;

public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        while(!Display.isCloseRequested()){ //main loop with game logic, rendering, and updating display




            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();

    }

}
