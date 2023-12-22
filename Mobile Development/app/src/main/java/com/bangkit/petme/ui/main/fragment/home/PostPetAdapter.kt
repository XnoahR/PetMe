package com.bangkit.petme.ui.main.fragment.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.petme.R
import com.bangkit.petme.api.Response.FavoritePetsResponseItem
import com.bangkit.petme.api.Response.PostPetResponse
import com.bangkit.petme.api.Response.PostPetResponseItem
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.ui.detail.DetailPetActivity
import com.bangkit.petme.utils.UtilsRange
import com.bumptech.glide.Glide

class PostPetAdapter(private val postPet: List<PostPetResponseItem>) : RecyclerView.Adapter<PostPetAdapter.ListViewHolder>() {


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvPetType: TextView = itemView.findViewById(R.id.tv_type)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvRange: TextView = itemView.findViewById(R.id.tv_range)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_pet, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val preference: Preferences = Preferences(holder.itemView.context.applicationContext)

        holder.tvName.text = postPet[position].title
        if(postPet[position].idAnimal == 1){
            holder.tvPetType.text = "Cat"
        }else{
            holder.tvPetType.text = "Dog"
        }
        Glide.with(holder.itemView.context)
            .load(postPet[position].postPicture) // URL Gambar
            .into(holder.ivPhoto)
        holder.tvDescription.text = postPet[position].description
        val range = UtilsRange.calculateHaversineDistance(preference.getLatitude().toString().toDouble(), preference.getLongitude().toString().toDouble(), postPet[position].latitude.toString().toDouble(), postPet[position].longitude.toString().toDouble())
        holder.tvRange.text = "${range.toInt()} KM"
        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context, DetailPetActivity::class.java).apply {
                putExtra(DetailPetActivity.ID, postPet[position].id.toInt())
            })
        }
    }

    override fun getItemCount(): Int = postPet.size
}