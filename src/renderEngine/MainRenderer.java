package renderEngine;

import assets.entities.Camera;
import assets.entities.Entity;
import assets.entities.Light;
import assets.models.TexturedModel;
import assets.shaders.StaticShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRenderer {

    private StaticShader staticShader = new StaticShader();
    private ModelRenderer modelRenderer = new ModelRenderer(staticShader);

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();


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

    //Actually render everything
    public void render(Camera camera, Light sun, float ambientLight){
        modelRenderer.prepare();
        staticShader.start();
        staticShader.loadLight(sun, ambientLight);
        staticShader.loadViewMatrix(camera);
        modelRenderer.render(entities);
        staticShader.stop();
        entities.clear();
    }

    //Clean up on close
    public void cleanUp(){
        staticShader.cleanUp();
    }

}
