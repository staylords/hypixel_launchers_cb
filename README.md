﻿# hypixel_launchers_cb
 
# Formula
[Cubic Bézier Curves]


# Inspiration
Jump pad launchers inspired by Hypixel's The Pit.

https://www.spigotmc.org/threads/hypixel-portals.453338/#post-4288417:
~~~~
The player is put on an invisible armor stand.

The armor stand travels along a cubic bezier curve.
You can play around with an example on this site: https://www.desmos.com/calculator/cahqdxeshd

The maths are well explained on this article: https://developer.roblox.com/en-us/articles/Bezier-curves
In the article in Cubic Bézier Curves, there's the function:
function cubicBezier(t, p0, p1, p2, p3)
As visualized above it, t is a value between 0 and 1 which represents the % progress along the curve.

So you just make a runnable, maintain a counter for the % and move the armor stand along the curve until t is 1 or greater.

One other trick is you can estimate the length of the curve and determine the %/tick to maintain the same travel speed along differently sized curves.
~~~~

# Example 
![Example](https://i.gyazo.com/eca88424d7fef0cc0f6f2c579c081235.gif)
https://i.gyazo.com/eca88424d7fef0cc0f6f2c579c081235.gif

~~~~
Made a curve off 4 hard-coded locations
1. -7921.5 90.0 4489.5
2. -7919.47 95.1 4498.138
3. -7908.895 77.75985 4513.014
4. -7903.473 67.0 4522.475
~~~~

# Encountered any bugs?
Feel free to contact me and i'll make sure to fix it ASAP, project took me ~15 minutes between testing and writing code. This readme took me more time. 

# Contact 
Joseph - [@staylords](https://twitter.com/staylords) - me@staylords.com


[Cubic Bézier Curves]: https://en.wikipedia.org/wiki/B%C3%A9zier_curve
