package com.example.androidolympiadsemifinal.view.adapter

import android.content.Context
import android.service.autofill.Validators.or
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidolympiadsemifinal.R
import com.example.androidolympiadsemifinal.model.ServiceModel

class RecyclerViewAdapter(
    private var dataList : ServiceModel,
    private val context: Context
    ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView2)
        val itemNameTextView: TextView = view.findViewById(R.id.itemNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemNameTextView.text = dataList.items[position].name
            downloadImage(context, holder.imageView, dataList.items[position].icon_url)
    }

    private fun downloadImage(context: Context, imageView:ImageView, url: String){
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.baseline_loop_24)
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return dataList.items.size
    }

    fun refreshUI(){
        notifyDataSetChanged()
    }

}
