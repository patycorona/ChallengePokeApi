package com.example.challengepokeapi.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengepokeapi.data.database.FirebaseAction
import com.example.challengepokeapi.data.model.request.UserRequest
import com.example.challengepokeapi.data.model.response.UserResponse
import com.example.challengepokeapi.domain.model.ResultModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import com.nhaarman.mockitokotlin2.verify
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

class FbAuthRepositoryImplTest {

    private lateinit var fbAuthRepositoryImpl: FbAuthRepositoryImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  apiService: FirebaseAction

    private val userRequest = UserRequest(email = "test@admin.com", pwd = "Password123")
    private val userRequestError = UserRequest(email = "test@admin.com", pwd = "")

    private var resultModel = ResultModel()
    var resultModelError = ResultModel()

    private lateinit var userResponse: UserResponse
    private lateinit var userResponseError: UserResponse

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
    }

    private fun initObjectMock() {
        userResponse = UserResponse(
            code = "0",
            message = "Sucessfull",
            isSuccess = true
        )

        userResponseError = UserResponse(
            code = "-1",
            message = "Error",
            isSuccess = true
        )

        resultModel = ResultModel(
            code = "0",
            message = "Sucessfull"
        )

        resultModelError = ResultModel(
            code = "-1",
            message = "Error"
        )
    }


    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private fun initControllers() {
        whenever(apiService.loginFireBase(userRequest)).thenReturn(
            Single.just(
                userResponse
            )
        )
        whenever(apiService.loginFireBase(userRequestError)).thenReturn(
            Single.just(
                userResponseError
            )
        )
    }

    private fun initializeViewModel() {
        fbAuthRepositoryImpl = FbAuthRepositoryImpl(
            apiService
        )
    }

    @Test
    fun `When call userAccess then execute one time userAccess `() {
        initializeViewModel()
        val userRequest = UserRequest("test@admin.com", "Password123")
        fbAuthRepositoryImpl.authFireBase(userRequest)
        verify(apiService, times(1)).loginFireBase(userRequest)
    }

    @Test
    fun `When call userAccess then return sucessfull response `() {
        initializeViewModel()
        val userRequest = UserRequest("test@admin.com", "Password123")
        fbAuthRepositoryImpl.authFireBase(userRequest)
        Assert.assertEquals(resultModel.code, "0")
        Assert.assertEquals(resultModel.message, "Sucessfull")
    }

    @Test
    fun `When call userAccess then return error response `() {
        initializeViewModel()
        val userRequest = UserRequest("test@admin.com", "")
        fbAuthRepositoryImpl.authFireBase(userRequest)
        Assert.assertEquals(resultModelError.code, "-1")
    }

}