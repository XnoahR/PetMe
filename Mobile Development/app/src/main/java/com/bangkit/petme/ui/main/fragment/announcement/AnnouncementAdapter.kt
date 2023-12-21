package com.bangkit.petme.ui.main.fragment.announcement

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.petme.R
import com.bangkit.petme.api.Response.DataItem
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.PetsCollectionRepository
import com.bangkit.petme.ui.main.fragment.petscollection.EditPetActivity
import com.bangkit.petme.utils.UtilsRange
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class AnnouncementAdapter (private val listAnnouncement: List<DataItem>) : RecyclerView.Adapter<AnnouncementAdapter.ListViewHolder>() {


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_announcement, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.tvTitle.text = listAnnouncement[position].title
        holder.tvDescription.text = listAnnouncement[position].description
        holder.tvDate.text = listAnnouncement[position].date
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        try {
            val date: Date = inputFormat.parse(listAnnouncement[position].date)

            // Menggunakan SimpleDateFormat lagi untuk memformat Date ke dalam format yang diinginkan
            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
            val outputDateString: String = outputFormat.format(date)

            // Menampilkan tanggal yang diformat
            holder.tvDate.text = outputDateString
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = listAnnouncement.size
}