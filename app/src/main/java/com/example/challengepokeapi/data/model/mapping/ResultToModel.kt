package com.example.challengepokeapi.data.model.mapping

import com.example.challengepokeapi.data.model.response.UserResponse
import com.example.challengepokeapi.domain.model.ResultModel

internal fun UserResponse.toModel() =
    ResultModel(code = code, message = message,isSuccess = isSuccess)