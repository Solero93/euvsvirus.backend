//package com.euvsvirus.euvsvirus.workout.infrastructure
//
//import com.euvsvirus.euvsvirus.workout.domain.PointWithIntensity
//import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
//import com.euvsvirus.euvsvirus.workout.domain.SpherePoint
//import com.euvsvirus.euvsvirus.workout.domain.SphereRectangle
//import kotlin.math.ceil
//import kotlin.random.Random
//
//object EquidistantDiscSampling {
//    private const val NUMBER_OF_SAMPLES_PER_ITERATION = 10
//    private const val MAX_CELL_RADIUS = 0.001
//    fun sample(bounds: SphereRectangle, circles: List<SphereCircle>): List<PointWithIntensity> {
//        val NUMBER_OF_CELLS = Pair(
//                ceil((bounds.maxLatitude - bounds.minLatitude) / (MAX_CELL_RADIUS)).toInt(),
//                ceil((bounds.maxLongitude - bounds.minLongitude) / (MAX_CELL_RADIUS)).toInt()
//        )
//        val finalPoints: MutableList<SpherePoint> = mutableListOf()
//        val random = Random.Default
//        for (i in 0 until NUMBER_OF_CELLS.first) {
//            for (j in 0 until NUMBER_OF_CELLS.second) {
//                val list = List(NUMBER_OF_SAMPLES_PER_ITERATION) {
//                    SpherePoint(
//                            latitude = random.nextDouble(bounds.minLatitude + (i * MAX_CELL_RADIUS), bounds.minLatitude + ((i + 1) * MAX_CELL_RADIUS)),
//                            longitude = random.nextDouble(bounds.minLongitude + (j * MAX_CELL_RADIUS), bounds.minLongitude + ((j + 1) * MAX_CELL_RADIUS))
//                    )
//                }
//                list.firstOrNull { point ->
//                    circles.any { it.isContained(point) }
//                }?.let { finalPoints.add(it) }
//            }
//        }
//        return finalPoints.map { PointWithIntensity(it, 1.0 / finalPoints.size) }
//    }
//}