package com.example.cafe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe.Database.SuggestModels

class CustomAdpterSuggest: RecyclerView.Adapter<CustomAdpterSuggest.ViewHolder>() {
    var data = listOf<SuggestModels>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Log.i("tagSuggest",data[position].nameThai)
        holder.bind(item)

        holder.txtCafeName.text = item.nameThai

        holder.imgCafeName.setOnClickListener { view ->
            view?.findNavController()
                ?.navigate(SuggestFragmentDirections.actionSuggestFragmentToDetailFragment(data[position].nameThai))
        }
    }
    fun ViewHolder.bind(item: SuggestModels) {
        txtCafeName.text = (item.nameThai)
        imgCafeName.setImageResource(
            when (item.image) {
                "1" -> R.drawable.cafe11
                "2" -> R.drawable.cafe21
                "3" -> R.drawable.cafe31
                else -> R.drawable.cafe41
            }
        )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCafeName: TextView = itemView.findViewById(R.id.txtCafeName)
        val imgCafeName: ImageView = itemView.findViewById(R.id.imgCafeName)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_view, parent, false)
                return ViewHolder(view)
            }
        }
    }

}