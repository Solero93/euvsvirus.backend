package com.euvsvirus.euvsvirus

import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.TokenDatabase
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase
import com.euvsvirus.euvsvirus.workout.infrastructure.database.WorkoutDatabase

object DatabaseCleaner {
    fun clean() {
        UserDatabase.clean()
        TokenDatabase.clean()
        WorkoutDatabase.clean()
    }
}