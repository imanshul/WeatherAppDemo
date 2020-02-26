package com.example.weatherappdemo.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappdemo.R
import com.example.weatherappdemo.model.ListForecastData
import com.example.weatherappdemo.utils.DateTimeUtils
import com.example.weatherappdemo.utils.OnItemClick
import kotlinx.android.synthetic.main.item_forecast_weather.view.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


class WeatherForecastAdapter(callback: OnItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val EMPTY_VIEW: Int = 555

    val list = ArrayList<ListForecastData>()

    fun addData(newList: List<ListForecastData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearData(){
        list.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (list.size == 0) {
            return EMPTY_VIEW
        }
        return super.getItemViewType(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == EMPTY_VIEW) {
            EmptyViewHolder(
                inflater.inflate(
                    R.layout.item_no_data,
                    parent,
                    false
                )
            )
        } else {
            ContactsHistoryViewHolder(
                inflater.inflate(
                    R.layout.item_forecast_weather,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int = if (list.size > 0) list.size else 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            EMPTY_VIEW -> {
                (holder as EmptyViewHolder).bind()
            }
            else -> {
                (holder as ContactsHistoryViewHolder).bindData(list.get(position))
            }
        }

    }

    inner class ContactsHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(data: ListForecastData) {
            itemView.apply {
                tvDay.text = DateTimeUtils.getDayOfWeekFromMillis(data.dt!!)
                tvDayTemp.text = "${data.main?.temp?.toInt()} \u00B0"
                tvMin.text = data.main?.tempMin?.roundToInt().toString()
                tvMax.text = data.main?.tempMax?.roundToInt().toString()
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {}
    }
}