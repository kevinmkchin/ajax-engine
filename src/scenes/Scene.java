package scenes;

import assets.models.TexturedModel;
import assets.shaders.StaticShader;
import assets.textures.ModelTexture;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;
import renderEngine.OBJLoader;

public abstract class Scene {

    //A Scene class stores information about the scene displayed to the Player.
    //This includes:
    // - What entities are on the screen
    // - Their corresponding 3D model and texture
    // - Their transformation data
    //Create entire levels (i.e. loading .objs and setting their transforms, etc) in Scene classes
    /*
    1. === Declare and initialize the TexturedModels ===
        - Call the makeModel(String, String) method to create a TexturedModel
        - e.g. TexturedModel someModel = makeModel("someModel", "textureSomeModel");
        - If the .obj file is located inside a sub-folder of "/res/objs/", then the inputted model name must
          include the sub-folder. (e.g. instead of "mazda", input "cars/mazda") Same for textures.

    2. === Declare and initialize the Entities ===
        - Declare and initialize the Entity type you want (e.g. DefaultEntity)
        - For entity type DefaultEntity, it takes in a TexturedModel (which you probably made in step 1),
          a new Vector3 of the entity's x y z position in 3d space,
          the entity's rotation around x-axis, rotation around y-axis, rotation around z-axis,
          and the scale factor.
        - e.g. Entity someEntity1337 = new DefaultEntity(someModel, new Vector3f(10f,-3f,-30f),0,20,0,1);

    3. === Render the Entities
        - Inside renderScene method, put a call to the render method of an instance of ModelRenderer for each Entity
        - e.g. renderer.render(someEntity1337, shader);
        - Do this for each entity

    NOTE:
        - renderScene method runs every tick/frame;
          put methods for entities etc that should be run every tick inside renderScene
    */

    ModelLoader loader;

    // CONSTRUCTOR
    public Scene(ModelLoader loader){
        this.loader = loader;
    }

    // RENDER THE SCENE (all Scene subclasses must have a renderScene method where they render the entities)
    public abstract void renderScene(ModelRenderer renderer, StaticShader shader);

    // CREATE A TEXTURED MODEL WITH obj model file & texture file
    // REQUIRES: modelFileName & textureFileName does not include file extension
    //           model file is a .obj     texture file is a .png
    protected TexturedModel makeModel(String modelFileName, String textureFileName){

        return new TexturedModel(OBJLoader.loadObjModel(modelFileName, loader),
                new ModelTexture(loader.loadTexture(textureFileName)));

    }

}
