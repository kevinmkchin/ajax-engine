package renderEngine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class ModelLoader {

    private List<Integer> vaos = new ArrayList<>(); //list of all vaos
    private List<Integer> vbos = new ArrayList<>(); //list of all vbos

    //===== Load a float array(.obj) to VAO RawModel =====
    public RawModel loadToVAO(float[] positions, int[] indices){
        int vaoID = createVAO();
        vaos.add(vaoID);
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, positions); //load positions VBO to index 0 of VAO
        unbindVAO();

        return new RawModel(vaoID, indices.length);
    }

    //clean up VAO memory at the end of the game
    public void cleanMemory(){
        for(int vao : vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo : vbos){
            GL15.glDeleteBuffers(vbo);
        }
    }

    //create the VAO and get its ID
    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays(); //generate empty VAO and return its ID
        GL30.glBindVertexArray(vaoID); //activate/bind the VAO

        return vaoID;
    }

    //store VBO data into attribute slot of VAO
    private void storeDataInAttributeList(int attributeNumber, float[] data){
        int vboID = GL15.glGenBuffers(); //generate empty VBO and return its ID
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID); //GL_ARRAY_BUFFER specifies type is data
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); //GL_STATIC_DRAW is the usage

        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0,0);

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