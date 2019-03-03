package assets.shaders;

import assets.entities.Camera;
import assets.entities.Light;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;

public class TerrainShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/assets/shaders/terrainVertexShader.txt";
    private static final String FRAGMENT_FILE = "src/assets/shaders/terrainFragmentShader.txt";

    private int location_transformMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_ambientLightFactor;
    private int location_tileNum;
    private int location_skyColour;
    private int location_fogDensity;
    private int location_fogGradient;

    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformMatrix = super.getUniformLocation("transformMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColour = super.getUniformLocation("lightColour");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
        location_ambientLightFactor = super.getUniformLocation("ambientLightFactor");
        location_tileNum = super.getUniformLocation("tileNum");
        location_skyColour = super.getUniformLocation("skyColour");
        location_fogDensity = super.getUniformLocation("fogDensity");
        location_fogGradient = super.getUniformLocation("fogGradient");
    }

    public void loadFog(float sky_r, float sky_g, float sky_b, float fogDensity, float fogGradient){
        super.loadVector(location_skyColour, new Vector3f(sky_r,sky_g,sky_b));
        super.loadFloat(location_fogDensity, fogDensity);
        super.loadFloat(location_fogGradient, fogGradient);
    }

    public void loadShineVariables(float damper, float reflectivity){
        super.loadFloat(location_shineDamper, damper);
        super.loadFloat(location_reflectivity, reflectivity);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    public void loadTransformMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

    public void loadLight(Light light, float ambientLightFactor){
        super.loadVector(location_lightPosition, light.getPosition());
        super.loadVector(location_lightColour, light.getColour());
        super.loadFloat(location_ambientLightFactor, ambientLightFactor); //minimum brightness of everything
    }

    public void loadTileNum(int tileNum){
        super.loadFloat(location_tileNum, (float) tileNum);
    }

}
