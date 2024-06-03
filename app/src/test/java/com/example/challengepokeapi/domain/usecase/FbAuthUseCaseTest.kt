package com.example.challengepokeapi.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengepokeapi.data.model.request.UserRequest
import com.example.challengepokeapi.data.model.response.UserResponse
import com.example.challengepokeapi.data.repository.FbAuthRepository
import com.example.challengepokeapi.domain.model.ResultModel
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

class FbAuthUseCaseTest {

    private lateinit var fbAuthUseCase: FbAuthUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fbAuthRepository: FbAuthRepository

    private val userRequest = UserRequest(email = "test@admin.com", pwd = "Password123")
    private val userRequestError = UserRequest(email = "test@admin.com", pwd = "")

    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeViewModel()
    }

    private fun initObjectMock() {
        resultModel = ResultModel(
            code = "0",
            message = "Sucessfull",
            isSuccess = true
        )

        resultModelError = ResultModel(
            code = "-1",
            message = "Error",
            isSuccess = true
        )
    }

    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(fbAuthRepository.authFireBase(userRequest)).thenReturn(
            Single.just(
                resultModel
            )
        )
        whenever(fbAuthRepository.authFireBase(userRequestError)).thenReturn(
            Single.just(
                resultModelError
            )
        )
    }

    private fun initializeViewModel() {
        fbAuthUseCase = FbAuthUseCase(
            fbAuthRepository
        )
    }

    @Test
    fun `When call userAccess then execute one time userAccess `() {

        val userRequest = UserRequest("test@admin.com", "Password123")
        fbAuthUseCase.authFireBase(userRequest)
        verify(fbAuthRepository, times(1)).authFireBase(userRequest)
    }

    @Test
    fun `When call userAccess then return sucessfull response `() {
        val userRequest = UserRequest("test@admin.com", "Password123")
        fbAuthUseCase.authFireBase(userRequest)
        Assert.assertEquals(resultModel.code, "0")
        Assert.assertEquals(resultModel.message, "Sucessfull")
    }

    @Test
    fun `When call userAccess then return error response `() {
        val userRequest = UserRequest("test@admin.com", "")
        fbAuthUseCase.authFireBase(userRequest)
        Assert.assertEquals(resultModelError.code, "-1")
    }
}