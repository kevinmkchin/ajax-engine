package renderEngine;

import assets.models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class ModelLoader {

    private List<Integer> vaos = new ArrayList<>(); //list of all VAOs
    private List<Integer> vbos = new ArrayList<>(); //list of all VBOs
    private List<Integer> textures = new ArrayList<>(); //list of all textures

    //===== Load a float array(.obj) to VAO RawModel =====
    public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){
        int vaoID = createVAO();
        vaos.add(vaoID);
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions); //load positions VBO to index 0 of VAO
        storeDataInAttributeList(1, 2, textureCoords);
        storeDataInAttributeList(2, 3, normals);
        unbindVAO();

        return new RawModel(vaoID, indices.length);
    }

    public int loadTexture(String fileName){
        Texture texture = null;
        try {
            texture = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/"+fileName+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int textureID = texture.getTextureID();
        textures.add(textureID);

        return textureID;
    }

    //clean up VAO memory at the end of the game
    public void cleanMemory(){
        for(int vao : vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo : vbos){
            GL15.glDeleteBuffers(vbo);
        }
        for(int texture : textures){
            GL11.glDeleteTextures(texture);
        }
    }

    //create the VAO and get its ID
    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays(); //generate empty VAO and return its ID
        GL30.glBindVertexArray(vaoID); //activate/bind the VAO

        return vaoID;
    }

    //store VBO data into attribute slot of VAO
    private void storeDataInAttributeList(int attributeNumber, int coordinateSize ,float[] data){
        int vboID = GL15.glGenBuffers(); //generate empty VBO and return its ID
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID); //GL_ARRAY_BUFFER specifies type is data
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); //GL_STATIC_DRAW is the usage

        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0,0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO(){
        GL30.glBindVertexArray(0);
    }

    //assign index buffer to VAO. VAOs have special slot for index buffer(not part of attribute array).
    private void bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID); //GL_ELEMENT... specifies type is indices
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length); //create new int buffer
        buffer.put(data); //put data into buffer
        buffer.flip(); //prepare buffer to be read from
        return buffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length); //create new float buffer
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

}
