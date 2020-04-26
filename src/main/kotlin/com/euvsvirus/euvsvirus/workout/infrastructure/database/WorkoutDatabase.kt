package com.euvsvirus.euvsvirus.workout.infrastructure.database

import com.euvsvirus.euvsvirus.common.postgres.DatabaseSession.getSession
import com.euvsvirus.euvsvirus.workout.domain.PointWithIntensity
import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
import com.euvsvirus.euvsvirus.workout.domain.Workout
import kotliquery.queryOf
import org.json.JSONArray

object WorkoutDatabase {
    fun clean() = getSession().run(queryOf("DELETE FROM public.\"Workout\";").asExecute)

    fun createWorkout(workout: Workout) {
        getSession().run(queryOf(
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
        println("lol")
    }

    fun getWorkouts(): List<Workout> {
        return getSession().run(queryOf("SELECT * FROM public.\"Workout\"")
                .map {
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