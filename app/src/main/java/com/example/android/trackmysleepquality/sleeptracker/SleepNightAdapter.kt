package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.SleepViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        return SleepViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = data.size

    class SleepViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        val res = itemView.context.resources
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val sleepQuality: TextView = itemView.findViewById(R.id.sleep_quality)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(currentItem: SleepNight) {
            sleepLength.text = convertDurationToFormatted(currentItem.startTimeMilli, currentItem.endTimeMilli, res)
            sleepQuality.text = convertNumericQualityToString(currentItem.sleepQuality, res)
            qualityImage.setImageResource(
                when (currentItem.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )
        }
        companion object {
            fun from(parent: ViewGroup): SleepViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_sleep_night, parent, false)
                return SleepViewHolder(view)
            }
        }
    }


}