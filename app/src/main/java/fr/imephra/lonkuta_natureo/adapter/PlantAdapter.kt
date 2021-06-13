package fr.imephra.lonkuta_natureo.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.imephra.lonkuta_natureo.*

class PlantAdapter (
        val context: MainActivity,
        private val plantList: List<PlantModel>,
        private val layoutId : Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder> () {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val planImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName:TextView? = view.findViewById(R.id.name_item)
        val plantDescription:TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = plantList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPlant = plantList[position]

        // Recuperer le repository
        val repo = PlantRepository()

        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.planImage)

        // Maj nom de la plante
        holder.plantName?.text = currentPlant.name

        // Maj description
        holder.plantDescription?.text = currentPlant.description

        // Plante liked?
        if (currentPlant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_instar)
        }

        // Rajouter une interraction sur l'étoile
        holder.starIcon.setOnClickListener {

            // Inverse si le bouton est liked
            currentPlant.liked = !currentPlant.liked

            // Maj objet plant
            repo.updatePlant(currentPlant)

        }

        // Interaction lors du clic sur une plante
        holder.itemView.setOnClickListener {
            // Afficher Popup
            PlantPopup(this, currentPlant).show()
        }
    }
}