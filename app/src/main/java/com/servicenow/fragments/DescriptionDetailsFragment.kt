package com.servicenow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.servicenow.exercise.R
import com.servicenow.exercise_kotlin.MainActivityKt
import com.servicenow.resources.Game
import com.servicenow.resources.NESGames
import com.servicenow.util.showErrorDialog
import kotlinx.android.synthetic.main.fragment_description_details.*

class DescriptionDetailsFragment : Fragment() {

    private var longDescription: String? = null
    private var rowPosition: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        longDescription = arguments?.getString("DESCRIPTION")
        rowPosition = arguments?.getInt("ROW_POSITION")
        var view = inflater.inflate(R.layout.fragment_description_details, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivityKt).setTitleBar(R.string.games_description)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(longDescription!=null){
            descriptionDetails.text = arguments?.getString("DESCRIPTION")
        }else{
            activity?.let { showErrorDialog(it,"There is no long description") }
        }

        val game: Game = NESGames.list[rowPosition!!]
        Glide.with(context)
            .load(Game.getIconResource(game.cover))
            .into(gameImageView)
    }
}
