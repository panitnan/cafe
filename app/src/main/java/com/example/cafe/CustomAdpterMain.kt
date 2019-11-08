package com.example.cafe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe.Database.CafeModels

class CustomAdpterMain : RecyclerView.Adapter<CustomAdpterMain.ViewHolder>() {
    var data = listOf<CafeModels>()
        set(value) {
            field = value
            notifyDataSetChanged()
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

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.txtCafeName.text = item.nameThai

        holder.imgCafeName.setOnClickListener { view ->
            Log.i("tag",data[position].nameThai)
            view?.findNavController()
                ?.navigate(MainFragmentDirections.actionMainFragmentToDetailFragment2(data[position].nameThai))
        }
    }

    fun ViewHolder.bind(item: CafeModels) {
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
}