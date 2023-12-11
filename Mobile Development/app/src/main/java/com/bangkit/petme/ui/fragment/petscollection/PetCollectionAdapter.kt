package com.bangkit.petme.ui.fragment.petscollection

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.petme.R
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.ui.EditPetActivity
import com.bumptech.glide.Glide

class PetCollectionAdapter(private val petCollection: List<PetCollection>) : RecyclerView.Adapter<PetCollectionAdapter.ListViewHolder>() {


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_pet_name)
        val tvPetType: TextView = itemView.findViewById(R.id.tv_pet_type)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvRange: TextView = itemView.findViewById(R.id.tv_range)
        val btnEdit: Button = itemView.findViewById(R.id.btn_edit)
        val btnUpdate: Button = itemView.findViewById(R.id.btn_update)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetCollectionAdapter.ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_pet_collection, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetCollectionAdapter.ListViewHolder, position: Int) {
        val (id, name, type,image, description, range) = petCollection[position]
        holder.tvName.text = name
        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.ivPhoto)
        holder.tvPetType.text = type
        holder.tvDescription.text = description
        holder.tvRange.text = "$range KM"
        holder.btnEdit.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, EditPetActivity::class.java).apply {
                    putExtra(EditPetActivity.NAME, name)
                    putExtra(EditPetActivity.TYPE, type)
                    putExtra(EditPetActivity.DESCRIPTION, description)
                    putExtra(EditPetActivity.IMAGE, image)
                }
            )
        }
    }

    override fun getItemCount(): Int = petCollection.size
}