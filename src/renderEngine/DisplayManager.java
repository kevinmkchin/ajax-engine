package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private static final int WIDTH = 1600;
    private static final int HEIGHT = 900;
    private static final int FPS_CAP = 60;
    private static boolean vSyncOn = false;

    public static void createDisplay(){

        ContextAttribs attribs = new ContextAttribs(3,2); //OpenGL 3.2
        attribs.withForwardCompatible(true);
        attribs.withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), attribs);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0,0,WIDTH,HEIGHT); //game takes up entire display

    }

    public static void updateDisplay(){

        Display.sync(FPS_CAP);
        Display.setVSyncEnabled(vSyncOn);

        Display.update();

    }

    public static void closeDisplay(){
        Display.destroy();
    }

}
