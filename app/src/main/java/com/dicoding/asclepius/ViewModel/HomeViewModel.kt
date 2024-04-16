package com.dicoding.asclepius.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.Data.Endpoint.ApiConfig
import com.dicoding.asclepius.Utils.ResultUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    val resultInformationListItem = MutableLiveData<ResultUser>()

    fun getListInformation() {
        viewModelScope.launch {
            resultInformationListItem.value = ResultUser.Loading(true)

            val response = try {
                withContext(Dispatchers.IO) {
                    ApiConfig.getApiService().getInformation(
                        QUERY_NEWS,
                        QUERY_CATEGORY,
                        QUERY_LANGUAGE,
                        BuildConfig.API_KEY
                    )
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                null
            }

            resultInformationListItem.value = ResultUser.Loading(false)

            response?.let {
                resultInformationListItem.value = ResultUser.Success(it.articles)
            } ?: run {
                resultInformationListItem.value = ResultUser.Error(Exception("Failed to fetch data"))
            }
        }
    }

    companion object {
        private const val QUERY_NEWS = "cancer"
        private const val QUERY_CATEGORY = "health"
        private const val QUERY_LANGUAGE = "en"
    }
}