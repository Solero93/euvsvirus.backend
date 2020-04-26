/*
package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.domain.SpherePoint
import com.euvsvirus.euvsvirus.workout.domain.SphereRectangle
import com.euvsvirus.euvsvirus.workout.domain.SphereVector
import kotlin.math.floor
import kotlin.random.Random

object PoissonDiscSampling {
    private const val NUMBER_OF_SAMPLES_PER_ITERATION = 30.0
    private const val MAXIMUM_DISTANCE_IN_METRES = 2.0
    private const val SPACE_DIMENSION = 2.0 // Working in a plane
    private const val MAX_CELL_RADIUS = MAXIMUM_DISTANCE_IN_METRES / SPACE_DIMENSION
    fun getPoints(bounds: SphereRectangle) {
        val NUMBER_OF_CELLS_IN_GRID = SphereVector(
                latitude = (bounds.maxLatitude - bounds.minLatitude).div(MAX_CELL_RADIUS),
                longitude = (bounds.maxLongitude - bounds.minLongitude).div(MAX_CELL_RADIUS)
        )


        fun getBackgroundGridPositionOfPoint(point: SpherePoint): Pair<Int, Int> {
            val translatedPointToZero = point.subtractDeltaVector(bounds.topLeft)
            return Pair(floor(translatedPointToZero.latitude / MAX_CELL_RADIUS).toInt(),
                    floor(translatedPointToZero.longitude / MAX_CELL_RADIUS).toInt()
            )
        }

        val activeList: MutableList<SpherePoint> = mutableListOf()
        val mapOfSpherePoints = mutableMapOf<Pair<Int, Int>, SpherePoint>()
        val mapOfPointPositions = mutableMapOf<SpherePoint, Pair<Int, Int>>()
        val random = Random.Default
        val x0 = SpherePoint(
                latitude = random.nextDouble(bounds.minLatitude, bounds.maxLatitude),
                longitude = random.nextDouble(bounds.minLongitude, bounds.maxLongitude)
        )
        activeList.add(x0)
        val positionInGrid = getBackgroundGridPositionOfPoint(x0)

        mapOfSpherePoints[positionInGrid] = x0
        mapOfPointPositions[x0] = positionInGrid

        fun isValid(point: SpherePoint): Boolean {
            if (!bounds.isInside(point))
                return false;
            val positionOfPoint = getBackgroundGridPositionOfPoint(x0)
            fun notHas(first: Int, second: Int): Boolean = !mapOfSpherePoints.containsKey(Pair(first, second))
            return notHas(positionOfPoint.first - 1, positionOfPoint.second) &&
                    notHas(positionOfPoint.first, positionOfPoint.second - 1) &&
                    notHas(positionOfPoint.first - 1, positionOfPoint.second - 1) &&
                    notHas(positionOfPoint.first + 1, positionOfPoint.second - 1) &&
                    notHas(positionOfPoint.first - 1, positionOfPoint.second + 1) &&
                    notHas(positionOfPoint.first, positionOfPoint.second + 1) &&
                    notHas(positionOfPoint.first + 1, positionOfPoint.second) &&
                    notHas(positionOfPoint.first + 1, positionOfPoint.second + 1)
        }

        fun generateSamples(point: SpherePoint): List<SpherePoint> {

        }

        while (activeList.isNotEmpty()) {
            val randomIndex = random.nextInt(0, activeList.size)
            val currentPoint: SpherePoint = activeList[randomIndex]
            val samples = generateSamples(currentPoint)
            val validSample = samples.firstOrNull { isValid(it) }
            if (validSample != null) {
                activeList.add(validSample)
                val validSamplePositionInGrid = getBackgroundGridPositionOfPoint(validSample)
                mapOfSpherePoints[validSamplePositionInGrid] = validSample
                mapOfPointPositions[validSample] = validSamplePositionInGrid
            } else {
                activeList.removeAt(randomIndex)
            }
        }
    }
}
}*/
