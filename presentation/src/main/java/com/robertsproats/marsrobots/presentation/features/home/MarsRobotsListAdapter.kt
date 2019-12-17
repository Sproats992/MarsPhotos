package com.robertsproats.marsrobots.presentation.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robertsproats.marsrobots.presentation.R
import com.robertsproats.marsrobots.presentation.model.PresentationSimpleMarsRobotsItem
import com.robertsproats.marsrobots.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.recyclerview_grid_item.view.*

class MarsRobotsListAdapter(private val context: Context,
                            private val listener: MarsRobotsDetailListener) :
        RecyclerView.Adapter<MarsRobotsListAdapter.ViewHolder>() {

    private var marsRobotsList: List<PresentationSimpleMarsRobotsItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_grid_item, parent, false))
    }

    override fun getItemCount(): Int {
        return marsRobotsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        marsRobotsList?.apply {
            this[position].imageAddress?.let {
                ImageLoader.loadImage(context, it, holder.itemImage)
            }
            this[position].title?.let {
                holder.itemTitle?.text = it
            }
            this[position].date?.let {
                holder.itemDate?.text = String.format(context.getString(R.string.date_created), it)
            }
            holder.itemView.setOnClickListener {
                listener.onDetailSelected(position)
            }
        }

    }

    fun updateList(marsList: List<PresentationSimpleMarsRobotsItem>?) {
        marsRobotsList = marsList
        this.notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage = view.itemImage
        val itemTitle = view.itemTitle
        val itemDate = view.itemDate
    }

}