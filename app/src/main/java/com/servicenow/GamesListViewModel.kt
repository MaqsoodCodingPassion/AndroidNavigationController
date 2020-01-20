package com.servicenow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.servicenow.model.GamesListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GamesListViewModel(val repository: GamesListRepository) : ViewModel() {

    val gamesListResponse: MutableLiveData<GamesListResponse> = MutableLiveData()

    fun fetchGamesListFromService(
        apiKey: String
    ): MutableLiveData<GamesListResponse> {

        val observable = repository.fetchGamesListFromService(apiKey)

        observable.map<GamesListResponse> {
            it
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    gamesListResponse.value = it
                },
                {
                    gamesListResponse.value = null
                })

        return gamesListResponse
    }
}