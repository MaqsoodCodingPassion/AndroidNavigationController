package com.servicenow.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.servicenow.GamesListAdapter
import com.servicenow.GamesListViewModel
import com.servicenow.exercise.R
import com.servicenow.exercise_kotlin.MainActivityKt
import com.servicenow.model.GamesListResponse
import com.servicenow.util.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    lateinit var navController: NavController

    private lateinit var gamesViewModel: GamesListViewModel
    private var gamesListAdapter: GamesListAdapter? = null
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var gamesList: MutableList<GamesListResponse.GamesItem>? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gamesViewModel = ViewModelProviders.of(this, viewModelFactory).get(GamesListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivityKt).setTitleBar(R.string.toolbar_title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun initAdapter() {
        gamesList = ArrayList()
        gamesListAdapter = GamesListAdapter(activity!!, gamesList as ArrayList<GamesListResponse.GamesItem>)
        gamesRecyclerView.itemAnimator = DefaultItemAnimator()
        gamesRecyclerView.adapter = gamesListAdapter
        gamesRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                var bundle = Bundle()
                bundle.putString("DESCRIPTION",gamesList?.get(position)?.longDescription)
                bundle.putInt("ROW_POSITION",position)
                navController.navigate(R.id.action_fragmentHome_to_DescriptionDetails,bundle)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initAdapter()
        callFetchGamesListAPI()
        observeLiveData()
    }

    private fun callFetchGamesListAPI() {
        gamesViewModel.fetchGamesListFromService(API_KEY)
    }

    private fun observeLiveData() {
        gamesViewModel.gamesListResponse.observe(this, Observer {
            if (it != null) {
                gamesList?.clear()
                gamesList?.addAll(it.games!!)
                gamesList?.let { it1 -> gamesListAdapter?.setDataList(it1) }
                gamesListAdapter?.notifyDataSetChanged()
            } else {
                activity?.let { it1 -> showErrorDialog(it1,"Please check for the connectivity") }
            }
        })
    }
}
