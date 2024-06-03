package com.example.challengepokeapi.data.repository

import com.example.challengepokeapi.data.database.FirebaseAction
import com.example.challengepokeapi.data.model.mapping.toModel
import com.example.challengepokeapi.data.model.request.UserRequest
import com.example.challengepokeapi.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class FbAuthRepositoryImpl @Inject constructor(
    private var firebaseAction: FirebaseAction
):FbAuthRepository {

    override fun authFireBase (userRequest: UserRequest): Single<ResultModel> =
        firebaseAction.loginFireBase(userRequest)
            .map { userResponse ->
                userResponse.toModel()
            }
}