package gameEngine;

import assets.collisions.AABB;
import assets.entities.Camera;
import assets.entities.CollisionEntity;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.MainRenderer;
import renderEngine.ModelLoader;
import scenes.Scene;
import scenes.TestScene1;
import tools.MousePicker;

import javax.swing.*;
import java.awt.*;

public class SceneEditorMode {

    JFrame frame;
    JPanel panel1;
    JTextField textField1;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 800;
    private Color bgColor = Color.BLACK;
    private Color fgColor = Color.WHITE;
    private Font font1 = new Font("Century", 0, 22);


    private static CollisionEntity pickedEntity;
    private static CollisionEntity tempEntity;


    public SceneEditorMode(){
        gui();
    }

    public void gui(){
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        textField1 = new JTextField();
        textField1.setBackground(bgColor);
        textField1.setForeground(fgColor);
        textField1.setFont(font1);

        panel1.add(textField1);

        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ajax Editor");
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new SceneEditorMode(); //make gui

        DisplayManager.createDisplay();
        ModelLoader loader = new ModelLoader();
        MainRenderer mainRenderer = new MainRenderer();
        Camera mainCamera = new Camera();
        mainCamera.setEditorMode(true);
        MousePicker picker = new MousePicker(mainCamera, mainRenderer.getProjectionMatrix());
        Scene scene = new TestScene1(loader);


        while(!Display.isCloseRequested()){
            mainCamera.update();
            picker.update();

            tempEntity = mouseRayCheck(picker);
            if(tempEntity != null){
                pickedEntity = tempEntity;
                //update editor window here
            }


            scene.renderScene(mainRenderer, mainCamera);

            DisplayManager.updateDisplay();
        }


        mainRenderer.cleanUp();
        loader.cleanMemory();
        DisplayManager.closeDisplay();
    }

    //Return reference to entity clicked on
    private static CollisionEntity mouseRayCheck(MousePicker picker){
        while(Mouse.next()) {
            if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
                for(AABB box : Scene.boundingBoxes){
                    if(picker.isRayHittingBox(box)){
                        System.out.println("Selected Entity HashCode: " + box.getEntity().hashCode());
                        return box.getEntity();
                    }
                }
            }
        }
        return null;
    }

}
