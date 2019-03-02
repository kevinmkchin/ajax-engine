# Ajax Engine
#### Entity-based OpenGL 3D graphics engine written in Java

#### Example VAO in ajax engine:

0: vertices coordinates (x,y,z)\
1: texture coordinates (u,v)\
Each VAO has a special slot for the index buffer (not part of attribute array).

#### Item Data

Store data for items inside an Excel sheet and assign each item a unique ID.

#### Make creating lots of content and data at once easy.

#### Display

#### Entities

#### Scenes

Each scene is a subclass of the Scene class.\
Each scene has:
- Data for objects and models of the scene
- Constructor to initialize local ModelLoader field.
- renderScene method to render the models, etc.


#### Collisions
##### Two Types of Collisions:

- Collisions with the terrain
- Collisions with Oriented Bounding Boxes OR another more complex collision box