package com.example.challengepokeapi.iu.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengepokeapi.data.model.request.UserRequest
import com.example.challengepokeapi.domain.model.ResultModel
import com.example.challengepokeapi.domain.model.UserModel
import com.example.challengepokeapi.domain.usecase.FbAuthUseCase
import com.example.challengepokeapi.ui.login.viewmodel.FbAuthViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FbAuthViewModelTest {

    private lateinit var fbAuthViewModel : FbAuthViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fbAuthUseCase: FbAuthUseCase

    @Mock
    private lateinit var observer : androidx.lifecycle.Observer<ResultModel>

    private val userRequest = UserRequest(email = "test@admin.com", pwd = "Password123")
    private val userRequestError = UserRequest(email = "test@admin.com", pwd = "")

    private val userModel = UserModel(email = "test@admin.com", pwd = "Password123")
    private val userModelError = UserModel(email = "test@admin.com", pwd = "")



    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeViewModel()
        initObserver()
    }

    private fun initObjectMock() {

        resultModel = ResultModel(
            code = "0",
            message = "Sucessfull"
        )

        resultModelError = ResultModel(
            code = "-1",
            message = "Error"
        )
    }

    private fun initObserver()
    {
        fbAuthViewModel.userResultModel.observeForever(observer)
    }


    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(fbAuthUseCase.authFireBase(userRequest)).thenReturn(
            Single.just(
                resultModel
            )
        )
        whenever(fbAuthUseCase.authFireBase(userRequestError)).thenReturn(
            Single.just(
                resultModelError
            )
        )
    }

    private fun initializeViewModel() {
        fbAuthViewModel = FbAuthViewModel(
            fbAuthUseCase
        )
    }

    @Test
    fun `When call userAccess then execute one time userAccess `() {
        fbAuthViewModel.authFireBase( userModel )
        verify(fbAuthUseCase, times(1)).authFireBase(userRequest)
    }

    @Test
    fun `When call userAccess then return sucessfull response `() {
        fbAuthViewModel.authFireBase( userModel )
        Assert.assertEquals(fbAuthViewModel.userResultModel.value?.code, "0")
        Assert.assertEquals(fbAuthViewModel.userResultModel.value?.message, "Sucessfull")
    }

    @Test
    fun `When call userAccess then return error response `() {
        fbAuthViewModel.authFireBase( userModelError )
        Assert.assertEquals(fbAuthViewModel.userResultModel.value?.code, "-1")
    }

}