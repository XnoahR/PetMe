package com.bangkit.petme.ui.main.fragment.petscollection

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.petme.R
import com.bangkit.petme.utils.UtilsRange
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.PetsCollectionRepository
import com.bumptech.glide.Glide

class PetCollectionAdapter(private val petCollection: List<PetCollectionResponseItem>) : RecyclerView.Adapter<PetCollectionAdapter.ListViewHolder>() {


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_pet_name)
        val tvPetType: TextView = itemView.findViewById(R.id.tv_pet_type)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvRange: TextView = itemView.findViewById(R.id.tv_range)
        val btnDetail: Button = itemView.findViewById(R.id.btn_detail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_pet_collection, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val preference: Preferences = Preferences(holder.itemView.context.applicationContext)
        val petsCollectionRepository: PetsCollectionRepository = PetsCollectionRepository(holder.itemView.context.applicationContext)


        holder.tvName.text = petCollection[position].title
        if(petCollection[position].idAnimal == 1){
            holder.tvPetType.text = "Dog"
        }else{
            holder.tvPetType.text = "Cat"
        }
        Glide.with(holder.itemView.context)
            .load(petCollection[position].postPicture) // URL Gambar
            .into(holder.ivPhoto)
        holder.tvDescription.text = petCollection[position].description
        val range = UtilsRange.calculateHaversineDistance(preference.getLatitude().toString().toDouble(), preference.getLongitude().toString().toDouble(), petCollection[position].latitude.toString().toDouble(), petCollection[position].longitude.toString().toDouble())
        holder.tvRange.text = "${range.toInt()} KM"
        holder.btnDetail.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, EditPetActivity::class.java).apply {
                    putExtra(EditPetActivity.ID, petCollection[position].id)
                    putExtra(EditPetActivity.NAME, petCollection[position].title)
                    putExtra(EditPetActivity.TYPE, petCollection[position].breed)
                    putExtra(EditPetActivity.DESCRIPTION, petCollection[position].description)
                    putExtra(EditPetActivity.IMAGE, petCollection[position].postPicture)
                    putExtra(EditPetActivity.ID_ANIMAL, petCollection[position].idAnimal.toString())
                }
            )
        }
    }

    override fun getItemCount(): Int = petCollection.size
}