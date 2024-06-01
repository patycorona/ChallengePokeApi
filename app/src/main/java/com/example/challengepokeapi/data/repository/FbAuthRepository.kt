package com.example.challengepokeapi.data.repository

import com.example.challengepokeapi.data.model.request.UserRequest
import com.example.challengepokeapi.domain.model.ResultModel
import io.reactivex.Single

interface FbAuthRepository {

    fun authFireBase(userRequest: UserRequest): Single<ResultModel>
}