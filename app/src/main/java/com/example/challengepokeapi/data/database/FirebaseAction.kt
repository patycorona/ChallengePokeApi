package com.example.challengepokeapi.data.database

import com.example.challengepokeapi.data.model.request.UserRequest
import com.example.challengepokeapi.data.model.response.UserResponse
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.CODE
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.ERROR
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.MSG_ERROR_AUTH
import com.example.challengepokeapi.domain.model.ConstantGeneral.Companion.MSG_LOGIN_SUCCESS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single
import javax.inject.Inject

class FirebaseAction @Inject constructor() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun loginFireBase(userRequest: UserRequest): Single<UserResponse> {
        var userResponse = UserResponse(CODE, MSG_LOGIN_SUCCESS, true)

        firebaseAuth.signInWithEmailAndPassword(userRequest.email, userRequest.pwd)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful){
                    userResponse = UserResponse(CODE, MSG_LOGIN_SUCCESS, true)
                    val user = task.result.user
                    updateUI(user)
                } else{
                    UserResponse(ERROR, MSG_ERROR_AUTH, false)
                    val user = task.result.user
                    updateUI(null)
                }
            }

        return Single.just(userResponse)
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            val emailVerified = it.isEmailVerified

            val uid = it.uid
        }
    }
}