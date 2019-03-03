# Ajax Engine
#### Entity-based OpenGL 3D graphics engine written in Java
---
### Example VAO in ajax engine:

0: vertices coordinates (x,y,z)\
1: texture coordinates (u,v)\
Each VAO has a special slot for the index buffer (not part of attribute array).

### Item Data

Store data for items inside an Excel sheet and assign each item a unique ID.

##### Make creating lots of content and data at once easy.

### Display

### Entities

When initializing an entity, it takes in:\
(TexturedModel, Vector3 of Position, x rotation, y rotation, z rotation, scale factor) 

##### Lights


### Scenes

A Scene class stores information about the scene displayed to the Player.
This includes:
- What entities are on the screen
- Their corresponding 3D model and texture
- Their transformation data

Create entire levels (i.e. loading .objs and setting their transforms, etc) in Scene classes

Each scene is a subclass of the Scene class.\
Each scene has:
- Data for objects and models of the scene
- Constructor to initialize local ModelLoader field.
- renderScene method to render the models, etc.

1. Declare and initialize the TexturedModels
    - Call the makeModel(String, String) method to create a TexturedModel
    - e.g. *TexturedModel someModel = makeModel("someModel", "textureSomeModel");*
    - If the .obj file is located inside a sub-folder of "/res/objs/", then the inputted model name must include the sub-folder. (e.g. instead of "mazda", input "cars/mazda") Same for textures.
    - If the model is a specular model, include the shine damping factor and reflectivity when calling makeModel
    - e.g. *TexturedModel someModel = makeModel("someModel", "textureSomeModel", 10, 1);*

2. Declare and initialize the Entities
    - Declare and initialize the Entity type you want (e.g. DefaultEntity)
    - For entity type DefaultEntity, it takes in a TexturedModel (which you probably made in step 1), 
    a new Vector3 of the entity's x y z position in 3d space,
    the entity's rotation around x-axis, rotation around y-axis, rotation around z-axis,
    and the scale factor.
    - e.g. *Entity someEntity1337 = new DefaultEntity(someModel, new Vector3f(10f,-3f,-30f),0,20,0,1);*

3. Render the Entities
    - Inside renderScene method, put a call to the render method of an instance of ModelRenderer for each Entity
    - e.g. *renderer.render(someEntity1337, shader);*
    - Do this for each entity

NOTE:
- renderScene method runs every tick/frame; put methods for entities etc that should be run every tick inside renderScene.

### Collisions
##### Two Types of Collisions:

- Collisions with the terrain
- Collisions with Oriented Bounding Boxes OR another more complex collision box
