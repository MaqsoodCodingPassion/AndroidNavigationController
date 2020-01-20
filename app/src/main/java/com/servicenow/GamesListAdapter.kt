package com.servicenow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.servicenow.exercise.R
import com.servicenow.model.GamesListResponse
import com.servicenow.resources.Game
import com.servicenow.resources.NESGames

class GamesListAdapter(
    private val context: Context,
    private var repDataList: List<GamesListResponse.GamesItem>
) : RecyclerView.Adapter<GamesListAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.game_title)
        var shortDesc: TextView = view.findViewById(R.id.game_description)
        var imageView: ImageView = view.findViewById(R.id.game_imageView)
    }

    fun setDataList(repDataList: List<GamesListResponse.GamesItem>){
        this.repDataList = repDataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_list_row_item, parent, false)

        return RepositoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repoItem = repDataList[position]
        holder.title.text = repoItem.name
        holder.shortDesc.text = repoItem.shortDescription
        val game: Game = NESGames.list[position]
        Glide.with(context)
            .load(Game.getIconResource(game.cover))
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return repDataList.size
    }
}
