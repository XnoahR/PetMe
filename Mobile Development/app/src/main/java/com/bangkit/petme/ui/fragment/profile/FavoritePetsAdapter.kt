package com.bangkit.petme.ui.fragment.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.petme.R
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.ui.EditPetActivity
import com.bumptech.glide.Glide

class FavoritePetsAdapter (private val petFavorite: List<PetCollection>) : RecyclerView.Adapter<FavoritePetsAdapter.ListViewHolder>() {


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_pet_name)
        val tvPetType: TextView = itemView.findViewById(R.id.tv_pet_type)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvRange: TextView = itemView.findViewById(R.id.tv_range)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritePetsAdapter.ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_favorite_pets, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritePetsAdapter.ListViewHolder, position: Int) {
        val (id, name, type,image, description, range) = petFavorite[position]
        holder.tvName.text = name
        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.ivPhoto)
        holder.tvPetType.text = type
//        holder.tvDescription.text = description
        holder.tvRange.text = "$range KM"
    }

    override fun getItemCount(): Int = petFavorite.size
}