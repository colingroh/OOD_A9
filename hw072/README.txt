Hello OOD Grader.

This is our representation of what an cs3500.animator.model.AnimationModel should look like. I'll start with the basic
interfaces first to explain what's going on.

***MODEL**
The parent model is in charge of showing a screen of things being animated. It has two methods,
one that will update the screen to the next tick, and the other that will print the animation. It
operates via a "tick" system, which each frame corresponding to a tick.

**OBJECT**
In the implementation, it holds on to a list of Animation Objects. This represents the objects that
are going to be shown during the model. Objects keep track of how they should appear, can be
updated, can be drawn, and can be printed out to the user.
The default abstract implementation (We only had one concrete implementation,
 "cs3500.animator.model.RectangleAnimationObject", for now because we didn't know what else future assignments would have)
 has a list of AnimationObjectInstructions, which are actually in charge of animating the object.

 **INSTRUCTIONS**
 Instructions are one piece of how an object should move throughout an animation. They have a list
 of cs3500.animator.model.AnimationMetadata, which are even smaller pieces of what characteristics of objects are being
 animated.

 **METADATA**
 These are in charge of things that get changed in an object. For example, one of these puppies is
 charge of updating a color, another is in charge of updating position, etc. Collectively, they
 make up a complete animation.


 OTHER THINGS TO NOTE
 So we made this as abstract as possible as to allow for any future additions to be made. For
 example, there might be more to animated than size, color, and position, so we let the Objects be
 in charge of what they need to update. They store their data in a HashMap of <String, Object> that
 their instructions can update. This way, any object can have as much or as little stuff to update
 as they want.
 This is also what the cs3500.animator.model.AnimationFields ENUM is used for. This enum is in charge of letting everyone
 know what they are allowed to change about themselves.

 That's pretty much it, thanks for coming to our TEDx Talk.