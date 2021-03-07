# Ajax Engine
Ajax is just the name! Did not use Asynchronous JavaScript and XML.

[Download Demo](https://github.com/kevinmkchin/kevinmkchin.github.io/blob/master/ajax3d/ajax3d%20engine.zip?raw=true)

![](https://github.com/kevinmkchin/kevinmkchin.github.io/blob/master/ajax3d/ajax.gif?raw=true)

Stanford Dragon 3D Model in the Ajax Engine:
![stanford dragon](https://imgur.com/7KPh8q4.png)


Learned about:
- Collisions in 3D (axis-aligned bounding boxes)
- Matrices (transformation matrices, view matrices)
- Working with OpenGL shaders
- Vectors
- Vertex buffer objects, vertex array objects

#### Entity-based OpenGL 3D graphics engine written in Java
---
## Engine Modes
##### Scene Editor Mode
Scene editor allows for easy editing and designing of scenes and levels.
- Hold right-click and use WASD to move around, RF to move up and down.
- Left-click on entities with collision boxes to select them and edit their position, rotation, or scale.

## Entities
##### Default Entity
When initializing an entity, it takes in:\
(TexturedModel, Vector3 of Position, x rotation, y rotation, z rotation, scale factor) 

##### Collision Entity
All entities with an Axis-Aligned Bounding Box collision must use this Entity type or its subtypes.

##### Lights

## Loading 3D Models as an .obj File

- .obj exported from Blender must be smooth shading instead of flat shading.
- Include normals, Include UVs, Triangulate Faces when exporting from Blender.

##### Example VAO:

0: vertices coordinates (x,y,z)\
1: texture coordinates (u,v)\
Each VAO has a special slot for the index buffer (not part of attribute array).

## Scenes
A Scene class stores information about the scene displayed to the Player. Create entire levels (i.e. loading .objs and setting their transforms, etc) in Scene classes.\
This includes:
- Light and Terrain data
- What entities are on the screen
- Their corresponding 3D model and texture
- Their transformation data
- A list of all bounding boxes(collision boxes) in the scene

Each scene is a subclass of the Scene class.\
Each scene has:
- Constructor to initialize local ModelLoader field.
- renderScene method to render the models, etc.

##### How to make Scenes
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
    - Inside renderScene method, put a call to the process_______ Method of the MainRenderer for each Entity
    - e.g. *renderer.processEntity(someEntity1337);*
    - Do this for each entity
    - At the end of the renderScene method, make sure to call the MainRenderer's render method with the Camera, the light source, and ambient light factor to actually render everything.
    - e.g. *renderer.render(camera, light1, 0.2f);*

NOTE:
- renderScene method runs every tick/frame; put methods for entities etc that should be run every tick inside renderScene.

## Collisions
##### Types of Collisions in Ajax Engine:
- Collisions with the terrain
- Collisions with Axis-Aligned Bounding Box

##### Axis-Aligned Bounding Boxes
Collision Entity and AABoundingBox have a bi-directional relationship.

The CollisionCreator goes through every vertex of a 3D model to find the maximum X, Y, Z values and minimum X, Y, Z values.\
Max XYZ becomes the top right corner of the bounding box, Min XYZ becomes the bottom left corner of the bounding box.\
IsBoxColliding(AABB) of the AABB class checks for collision by checking if the x-max or the x-min of the other box is between x-max and x-min of the current box. Check for XYZ axis and return true if true for all 3 axis.\
IsPointColliding(Vector3) does the same as IsBoxColliding but only for a single point.

How to enable ABBB for entities:
1. When calling makeModel method for TexturedModel, enable collision.
2. Have the Entity be of type CollisionEntity.

## Display


## Item Data
Store data for items inside an Excel sheet and assign each item a unique ID.

##### Make creating lots of content and data at once easy.
