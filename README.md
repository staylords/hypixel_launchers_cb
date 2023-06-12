# hypixel_launchers_cb
 
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
https://github.com/staylords/hypixel_launchers_cb/assets/80008857/d2b9a7b7-1ab1-497a-8187-4522e9ebbf2e

~~~~
Made a curve off 4 hard-coded locations
- -7921.5 90.0 4489.5
- -7919.47 95.1 4498.138
- -7908.895 77.75985 4513.014
- -7903.473 67.0 4522.475
~~~~

[Cubic Bézier Curves]: https://en.wikipedia.org/wiki/B%C3%A9zier_curve
