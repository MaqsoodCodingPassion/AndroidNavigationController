package com.servicenow

import androidx.lifecycle.LiveData
import com.servicenow.model.GamesListResponse
import com.servicenow.service.Service
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GamesListRepository @Inject constructor(private val service: Service) {

    fun fetchGamesListFromService(apiKey: String): Single<GamesListResponse> {
        return service.getGamesList(apiKey)
    }
}
