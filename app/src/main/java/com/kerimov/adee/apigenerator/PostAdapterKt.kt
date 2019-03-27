package com.kerimov.adee.apigenerator

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class PostAdapterKt() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var context: Context? = null
    var postsList: List<Post>? = null
    var weathersList: List<Weather>? = null
    val TYPE_POST = 0
    val TYPE_WEATHER = 1
    constructor(context: Context,postsList:List<Post>,weathersList:List<Weather>) : this() {
        this.context = context
        this.postsList = postsList
        this.weathersList = weathersList
        Collections.shuffle(postsList)
        Collections.shuffle(weathersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        if (p1 == TYPE_POST) {
            var view: View = LayoutInflater.from(context).inflate(R.layout.post_list_item,p0,false)
            return PostAdapterKt.PostViewHolder(view)
        } else {
            var view: View = LayoutInflater.from(context).inflate(R.layout.weather_list_item,p0,false)
            return PostAdapterKt.WeatherViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        if (p0.itemViewType == TYPE_POST) {
            var postViewHolder = p0 as PostViewHolder
            postViewHolder.tvTitle.text(postsList.get(p1).title)
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvText: TextView = itemView.findViewById(R.id.tv_text)
        var parentLayout: LinearLayout = itemView.findViewById(R.id.parent_layout)
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNameWeahter: TextView = itemView.findViewById(R.id.tv_name_weather)
    }


}