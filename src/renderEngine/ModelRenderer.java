package renderEngine;

import assets.entities.Entity;
import assets.models.RawModel;
import assets.models.TexturedModel;
import assets.shaders.StaticShader;
import assets.textures.ModelTexture;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import tools.Maths;

import java.util.List;
import java.util.Map;

public class ModelRenderer {

    private StaticShader staticShader;


    //Perspective projection renderer
    public ModelRenderer(StaticShader shader, Matrix4f projectionMatrix){
        this.staticShader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    //Render each entity from the HashMap
    public void render(Map<TexturedModel, List<Entity>> entities){
        for(TexturedModel model : entities.keySet()){
            prepareTexturedModel(model); //bind/prepare once for each TexturedModel
            List<Entity> batch = entities.get(model); //get all entities of TexturedModel
            for(Entity entity : batch){ //for each entity of TexturedModel, actually render it
                prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(),
                        GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel(); //unbind once for each TexturedModel
        }
    }

    private void prepareTexturedModel(TexturedModel model){
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0); //index 0 is positions of vertices
        GL20.glEnableVertexAttribArray(1); //texture coords
        GL20.glEnableVertexAttribArray(2); //normals
        ModelTexture texture = model.getTexture();
        staticShader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
    }

    private void unbindTexturedModel(){
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity){
        Matrix4f transformMatrix = Maths.createTransformMatrix(entity.getPosition(),
                entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        staticShader.loadTransformMatrix(transformMatrix);
    }

}
