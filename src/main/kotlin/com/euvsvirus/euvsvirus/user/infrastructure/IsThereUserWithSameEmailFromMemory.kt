package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.domain.IsThereUserWithSameEmailRepository
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase
import org.springframework.stereotype.Repository

@Repository
class IsThereUserWithSameEmailFromMemory : IsThereUserWithSameEmailRepository {
    override fun isThereUserWithSameEmail(email: String): Boolean = UserDatabase.isThereUserWithSameEmail(email)
}