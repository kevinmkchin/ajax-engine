package renderEngine;

import assets.entities.Camera;
import assets.entities.Entity;
import assets.entities.Light;
import assets.models.TexturedModel;
import assets.shaders.StaticShader;
import assets.shaders.TerrainShader;
import assets.terrains.Terrain;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRenderer {
    /*
    MainRenderer()
    processEntity(Entity)
    processTerrain(Terrain)
    render(Camera, Light, Float)
    cleanUp()
    prepare()
    createProjectionMatrix()
     */

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;

    private int tileNum = 10;

    private Matrix4f projectionMatrix;

    private StaticShader staticShader = new StaticShader();
    private ModelRenderer modelRenderer;
    private TerrainShader terrainShader = new TerrainShader();
    private TerrainRenderer terrainRenderer;

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();
    private List<Terrain> terrains = new ArrayList<>();


    public MainRenderer(){
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        this.modelRenderer = new ModelRenderer(staticShader, projectionMatrix);
        this.terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    }

    //Put the entities into the HashMap
    public void processEntity(Entity entity){
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch != null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    public void processTerrain(Terrain terrain, int tileNum){
        terrains.add(terrain);
        this.tileNum = tileNum;
    }

    //Actually render everything
    public void render(Camera camera, Light sun, float ambientLight){
        prepare();
        staticShader.start();
        staticShader.loadLight(sun, ambientLight);
        staticShader.loadViewMatrix(camera);
        modelRenderer.render(entities);
        staticShader.stop();

        terrainShader.start();
        terrainShader.loadLight(sun, ambientLight);
        terrainShader.loadViewMatrix(camera);
        terrainShader.loadTileNum(tileNum);
        terrainRenderer.render(terrains);
        terrainShader.stop();

        terrains.clear();
        entities.clear();
    }

    //Clean up on close
    public void cleanUp(){
        staticShader.cleanUp();
        terrainShader.cleanUp();
    }

    //prepare for render
    public void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0, 0 , 1, 1);
    }

    private void createProjectionMatrix(){
        float aspectRatio = (float) Display.getWidth() / Display.getHeight();
        float y_scale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio;
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }

}
