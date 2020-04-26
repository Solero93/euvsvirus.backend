package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.domain.SphereRectangle
import java.time.ZonedDateTime

data class GetWorkoutsRequest(val bounds: SphereRectangle, val datetime: ZonedDateTime?)