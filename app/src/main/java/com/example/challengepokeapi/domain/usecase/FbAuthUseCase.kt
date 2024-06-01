package com.example.challengepokeapi.domain.usecase

import com.example.challengepokeapi.data.model.request.UserRequest
import com.example.challengepokeapi.data.repository.FbAuthRepository
import com.example.challengepokeapi.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class FbAuthUseCase @Inject constructor(
    private var fbAuthRepository: FbAuthRepository
    ) {

    fun authFireBase(userRequest: UserRequest): Single<ResultModel> =
        fbAuthRepository.authFireBase(userRequest)

}