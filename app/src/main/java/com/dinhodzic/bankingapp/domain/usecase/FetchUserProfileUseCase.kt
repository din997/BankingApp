package com.dinhodzic.bankingapp.domain.usecase

import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.data.repository.BankingRepository
import com.dinhodzic.bankingapp.domain.model.BankingProfile
import javax.inject.Inject

class FetchUserProfileUseCase @Inject constructor(
    private val repository: BankingRepository
) {
    suspend operator fun invoke(id: Int): ResultWrapper<BankingProfile> {
        return repository.fetchUserProfile(id)
    }
}