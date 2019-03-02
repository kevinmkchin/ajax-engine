package scenes;

import assets.shaders.StaticShader;
import renderEngine.ModelLoader;
import renderEngine.ModelRenderer;

public abstract class Scene {

    //create entire levels (i.e. loading .objs and setting their transforms, etc) in scene classes
    //when you switch levels (i.e. switch game state to a different scene) just render stuff inside the scene class

    ModelLoader loader;

    public Scene(ModelLoader loader){
        this.loader = loader;
    }

    public abstract void renderScene(ModelRenderer renderer, StaticShader shader);

}
