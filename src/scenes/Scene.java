package scenes;

import assets.entities.Camera;
import assets.models.ModelData;
import assets.models.RawModel;
import assets.models.TexturedModel;
import assets.textures.ModelTexture;
import renderEngine.MainRenderer;
import renderEngine.ModelLoader;
import renderEngine.OBJFileLoader;

public abstract class Scene {

    //A Scene class stores information about the scene displayed to the Player.
    //This includes:
    // - What entities are on the screen
    // - Light and Terrain data
    // - Their corresponding 3D model and texture
    // - Their transformation data
    //Create entire levels (i.e. loading .objs and setting their transforms, etc) in Scene classes
    /*
    1. === Declare and initialize the TexturedModels ===
        - Call the makeModel(String, String) method to create a TexturedModel
        - e.g. TexturedModel someModel = makeModel("someModel", "textureSomeModel");
        - If the .obj file is located inside a sub-folder of "/res/objs/", then the inputted model name must
          include the sub-folder. (e.g. instead of "mazda", input "cars/mazda") Same for textures.
        - If the model is a specular model, include the shine damping factor and reflectivity when calling makeModel
        - e.g. TexturedModel someModel = makeModel("someModel", "textureSomeModel", 10, 1);

    2. === Declare and initialize the Entities ===
        - Declare and initialize the Entity type you want (e.g. DefaultEntity)
        - For entity type DefaultEntity, it takes in a TexturedModel (which you probably made in step 1),
          a new Vector3 of the entity's x y z position in 3d space,
          the entity's rotation around x-axis, rotation around y-axis, rotation around z-axis,
          and the scale factor.
        - e.g. Entity someEntity1337 = new DefaultEntity(someModel, new Vector3f(10f,-3f,-30f),0,20,0,1);

    3. === Render the Entities
        - Inside renderScene method, put a call to the process_______ Method of the MainRenderer for each Entity
        - e.g. renderer.processEntity(someEntity1337);
        - Do this for each entity
        - At the end of the renderScene method, make sure to call the MainRenderer's render method with the Camera,
          the light source, and ambient light factor to actually render everything.
        - e.g. renderer.render(camera, light1, 0.2f);

    NOTE:
        - renderScene method runs every tick/frame;
          put methods for entities etc that should be run every tick inside renderScene
    */

    ModelLoader loader;

    // CONSTRUCTOR
    // Constructor takes in the ModelLoader
    public Scene(ModelLoader loader){
        this.loader = loader;
    }

    // RENDER THE SCENE (all Scene subclasses must have a renderScene method where they render the entities)
    // takes in the MainRenderer and the main Camera
    public abstract void renderScene(MainRenderer renderer, Camera camera);
    //renderScene needs to
    // 1. renderer.processEntity or processTerrain
    // 2. renderer.render

    // CREATE A TEXTURED MODEL WITH obj model file & texture file
    // REQUIRES: modelFileName & textureFileName does not include file extension
    //           model file is a .obj     texture file is a .png
    protected TexturedModel makeModel(String modelFileName, String textureFileName, boolean hasCollision){
        RawModel rawModel = getRawModel(modelFileName, hasCollision);
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(textureFileName));

        return new TexturedModel(rawModel, modelTexture);
    }
    protected TexturedModel makeModel(String modelFileName, String textureFileName, boolean hasCollision,
                                      float shineDamper, float reflectivity){
        RawModel rawModel = getRawModel(modelFileName, hasCollision);
        ModelTexture specularTexture = new ModelTexture(loader.loadTexture(textureFileName));
        specularTexture.setShineDamper(shineDamper);
        specularTexture.setReflectivity(reflectivity);

        return new TexturedModel(rawModel, specularTexture);
    }
    private RawModel getRawModel(String modelFileName, boolean createAABB){
        ModelData modelData = OBJFileLoader.loadOBJ(modelFileName, createAABB);
        RawModel rawModel = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(),
                modelData.getNormals(), modelData.getIndices());
        if(modelData.getVerticesForAABB() != null){
            rawModel.setVerticesForAABB(modelData.getVerticesForAABB());
        }

        return rawModel;
    }



}
