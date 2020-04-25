package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.domain.Workout

data class GetWorkoutsResponse(val results: List<Workout>)