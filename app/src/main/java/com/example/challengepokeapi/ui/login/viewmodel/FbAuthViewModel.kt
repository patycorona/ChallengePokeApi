package com.example.challengepokeapi.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengepokeapi.R
import com.example.challengepokeapi.data.model.request.UserRequest
import com.example.challengepokeapi.domain.model.ConstantGeneral
import com.example.challengepokeapi.domain.model.ResultModel
import com.example.challengepokeapi.domain.model.UserModel
import com.example.challengepokeapi.domain.usecase.FbAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FbAuthViewModel @Inject constructor(
    private var fbAuthUseCase: FbAuthUseCase
): ViewModel(){
    private val compositeDisposable = CompositeDisposable()

    val userResultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }
    fun authFireBase(userModel: UserModel){
        val userModel = UserRequest(email = userModel.email, pwd = userModel.pwd)

        compositeDisposable += fbAuthUseCase.authFireBase(userModel)
            .subscribeOn(Schedulers.io())
            .subscribe({ loginResponse ->
                userResultModel.postValue(loginResponse)
            }, {
                userResultModel.postValue(
                    ResultModel(
                        code = ConstantGeneral.ERROR,
                        message = R.string.msg_error.toString() + " "+ ConstantGeneral.MSG_ERROR
                    )
                )
            })
    }
}