/*
 * Copyright (c) 2023 Joseph (me@staylords.com)
 *
 * Use and or redistribution of compiled JAR file and or source code is permitted only if given
 * explicit permission from original author: staylords
 */

package com.staylords.launchers

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector

/**
 * @project hypixel_launchers_cb
 *
 * @date 12/06/2023
 * @author me@staylords.com
 */
class LaunchersPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player: Player = event.player
        val playerLocation: Location = player.location

        // Check if the player is on the jump pad block
        if (playerLocation.block.type == Material.GOLD_PLATE) {
            // Spawn an invisible armor stand at the player's location
            val armorStand: ArmorStand = playerLocation.world.spawnEntity(playerLocation, EntityType.ARMOR_STAND) as ArmorStand
            armorStand.isVisible = false
            armorStand.setPassenger(player)

            // Move the armor stand along the curve until t is 1 or greater
            object : BukkitRunnable() {
                var t = 0.0
                val steps = 100.0 // Number of steps

                val curveLength = estimateCurveLength() // Estimate the length of the curve

                override fun run() {
                    if (t >= 1.0) {
                        // Remove the armor stand when the curve is completed
                        armorStand.remove()
                        cancel()
                        return
                    }

                    // Calculate the position along the curve
                    val curvePosition: Location = calculateCurvePosition(t)

                    // Calculate the next position along the curve
                    val nextPosition: Location = calculateCurvePosition(t + 1.0 / steps)

                    // Calculate the distance to be traveled in this tick
                    val distancePerTick = curveLength / steps

                    // Calculate the velocity based on the distance per tick
                    val velocity: Vector = nextPosition.toVector().subtract(curvePosition.toVector()).normalize().multiply(distancePerTick)

                    // Set the armor stand's velocity to move it along the curve
                    armorStand.velocity = velocity

                    // Increment the progress along the curve
                    t += 1.0 / steps
                }
            }.runTaskTimer(this, 0L, 1L)
        }
    }
    private fun estimateCurveLength(): Double {
        var length = 0.0
        val numSegments = 100 // Number of segments to divide the curve

        var previousPoint = calculateCurvePosition(0.0)

        for (i in 1..numSegments) {
            val t = i.toDouble() / numSegments
            val currentPoint = calculateCurvePosition(t)
            length += previousPoint.distance(currentPoint)
            previousPoint = currentPoint
        }

        return length
    }

    // Helper method to calculate the position on the curve for a given t value
    private fun calculateCurvePosition(t: Double): Location {
        // Implement the cubic Bézier curve equation here based on the provided formula.
        // The equation takes four control points: p0, p1, p2, p3.
        // You'll need to define these points based on your desired curve shape.

        //-7921.5 90.0 4489.5
        val p0 = Location(Bukkit.getWorld("world"), -7921.5 ,90.0 ,4489.5)
        //-7919.47 95.1 4498.138
        val p1 = Location(Bukkit.getWorld("world"), -7919.47 ,95.1 ,4498.138)
        //-7908.895 77.75985 4513.014
        val p2 = Location(Bukkit.getWorld("world"), -7908.895, 77.75985 ,4513.014)
        //-7903.473 67.0 4522.475
        val p3 = Location(Bukkit.getWorld("world"), -7903.473 ,67.0 ,4522.475)

        val x: Double = cubicBezier(t, p0.x, p1.x, p2.x, p3.x)
        val y: Double = cubicBezier(t, p0.y, p1.y, p2.y, p3.y)
        val z: Double = cubicBezier(t, p0.z, p1.z, p2.z, p3.z)

        return Location(Bukkit.getWorld("world"), x, y, z)
    }

    // Helper method for the cubic Bézier curve equation
    private fun cubicBezier(t: Double, p0: Double, p1: Double, p2: Double, p3: Double): Double {
        val u = 1 - t
        val tt = t * t
        val uu = u * u
        val uuu = uu * u
        val ttt = tt * t

        var result = uuu * p0
        result += 3 * uu * t * p1
        result += 3 * u * tt * p2
        result += ttt * p3

        return result
    }
}