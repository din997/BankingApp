package com.dinhodzic.bankingapp.domain.usecase

import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.data.repository.BankingRepository
import com.dinhodzic.bankingapp.domain.model.BankCard
import javax.inject.Inject

class FetchCreditCardsUseCase @Inject constructor(
    private val repository: BankingRepository
) {
    suspend operator fun invoke(): ResultWrapper<List<BankCard>> {
        return repository.fetchBankCards()
    }
}