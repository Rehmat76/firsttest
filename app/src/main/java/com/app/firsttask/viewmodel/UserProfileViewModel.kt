package com.app.firsttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.firsttask.datasource.reporsitory.DataRepository
import com.app.firsttask.models.generalModels.UserListModel
import com.app.firsttask.utils.general.AppConstants.Companion.STARTING_PAGE_LIMIT
import com.app.firsttask.utils.network.ResultWrapper
import kotlinx.coroutines.launch

class UserProfileViewModel(private val dataRepository: DataRepository) : BaseAndroidViewModel() {

    private val _userList = MutableLiveData<List<UserListModel>>()
    private val _isError = MutableLiveData<Boolean>()

    val usersList: LiveData<List<UserListModel>>
        get() = _userList


    fun getUsersList(offset: Int) {
        viewModelScope.launch {
            dataRepository.getUserList(
                STARTING_PAGE_LIMIT, offset
            ).let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success -> {
                        _userList.value = response.value
                    }
                    else -> {
                        _isError.value = true
                        handleErrorType(response)
                    }
                }
            }
        }
    }

    fun callListUserData(offset: Int) {
        viewModelScope.launch {
            getUsersList(offset)
        }
    }
}