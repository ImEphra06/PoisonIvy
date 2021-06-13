package fr.imephra.lonkuta_natureo

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.imephra.lonkuta_natureo.adapter.PlantAdapter

class PlantPopup(
    private val adapter: PlantAdapter,
    private val currentPlant: PlantModel
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plants_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButtom()
    }

    private fun setupDeleteButtom() {
        findViewById<ImageView>(R.id.delete).setOnClickListener{
            // Supprimer la plante de la BDD
            val repo = PlantRepository()
            repo.deletePlant(currentPlant)
            dismiss()
        }

    }

    private fun setupCloseButton(){
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            // Fermer fenetre
            dismiss()
        }
    }

    private fun setupComponents() {
        // Actualiser l'image de la plante
        val planImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(planImage)

        // Actualiser nom de la plante
        findViewById<TextView>(R.id.popup_plant_name).text = currentPlant.name

        // Actualiser description de la plante
        findViewById<TextView>(R.id.popup_plant_description_subtitle).text = currentPlant.description

        // Actualiser croissance de la plante
        findViewById<TextView>(R.id.popup_plant_grow_subtitle).text = currentPlant.grow

        // Actualiser consommation de la plante
        findViewById<TextView>(R.id.popup_plant_water_subtitle).text = currentPlant.water
    }

}