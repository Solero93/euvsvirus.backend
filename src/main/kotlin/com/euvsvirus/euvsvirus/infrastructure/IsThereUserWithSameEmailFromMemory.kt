package com.euvsvirus.euvsvirus.infrastructure

import com.euvsvirus.euvsvirus.domain.IsThereUserWithSameEmailRepository
import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.UserDatabase
import org.springframework.stereotype.Repository

@Repository
class IsThereUserWithSameEmailFromMemory : IsThereUserWithSameEmailRepository {
    override fun isThereUserWithSameEmail(email: String): Boolean = UserDatabase.isThereUserWithSameEmail(email)
}