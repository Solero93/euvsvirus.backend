package com.euvsvirus.euvsvirus.workout.infrastructure.database

import com.euvsvirus.euvsvirus.common.postgres.DatabaseSession.getSession
import com.euvsvirus.euvsvirus.workout.api.GetWorkoutsRequest
import com.euvsvirus.euvsvirus.workout.domain.PointWithIntensity
import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
import com.euvsvirus.euvsvirus.workout.domain.Workout
import kotliquery.queryOf
import org.json.JSONArray

object WorkoutDatabase {
    fun clean() = getSession().use { it.run(queryOf("DELETE FROM public.\"Workout\";").asExecute) }

    fun createWorkout(workout: Workout) {
        getSession().use {
            it.run(queryOf(
                    """
            INSERT INTO public."Workout" (id, userid, datetimestart, datetimeend, sport, points, raster)
            VALUES(?, ?, ?, ?, ?, ?, ?)
        """.trimIndent(),
                    workout.id,
                    workout.userId,
                    workout.datetimeStart,
                    workout.datetimeEnd,
                    workout.sport,
                    workout.points.map { it.toDoubleList().toString() }.toTypedArray(),
                    workout.raster.map { it.toDoubleList().toString() }.toTypedArray()
            ).asExecute) // TODO on conflict, update
        }
    }

    fun getWorkouts(getWorkoutsRequest: GetWorkoutsRequest): List<Workout> {
        val query = if (getWorkoutsRequest.datetime == null) {
            queryOf("SELECT * FROM public.\"Workout\"")
        } else {
            queryOf("""
                SELECT * FROM public."Workout"
                WHERE ? BETWEEN datetimestart AND datetimeend
            """.trimIndent(), getWorkoutsRequest.datetime)
        }
        return getSession().use {
            it.run(query.map {
                Workout(
                        id = it.string("id"),
                        userId = it.string("userid"),
                        datetimeStart = it.zonedDateTime("datetimestart"),
                        datetimeEnd = it.zonedDateTime("datetimeend"),
                        sport = it.string("sport"),
                        raster = it.array<String>("raster").map { strings ->
                            val array = JSONArray(strings)
                            PointWithIntensity.fromNumbers(array.getDouble(0), array.getDouble(1), array.getDouble(2))
                        },
                        points = it.array<String>("points").map { strings ->
                            val array = JSONArray(strings)
                            SphereCircle.fromNumbers(array.getDouble(0), array.getDouble(1), array.getDouble(2))
                        }
                )
            }.asList)
        }
    }
}