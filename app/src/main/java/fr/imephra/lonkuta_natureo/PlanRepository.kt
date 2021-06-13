package fr.imephra.lonkuta_natureo

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.imephra.lonkuta_natureo.PlantRepository.Singleton.databaseRef
import fr.imephra.lonkuta_natureo.PlantRepository.Singleton.plantList

class PlantRepository {

    object Singleton {
        // Se connecter à la reférence plante
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        // Créer une liste qui va contenir plante
        val plantList = arrayListOf<PlantModel>()
    }

    fun updateData(callback: () -> Unit) {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciennes plantes
                plantList.clear()

                // Liste de plantes
                for (ds in snapshot.children) {
                    // Construire un objet plante
                    val plant = ds.getValue(PlantModel::class.java)

                    // Verifier que la plante n'est pas présente
                    if (plant != null) {
                        plantList.add(plant)
                    }
                }

                // Actionner callback
                callback()
            }
        })

        // Mettre à jour l'element plante
        fun updatePlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)
    }

    fun updatePlant(currentPlant: PlantModel) {
        TODO("Not yet implemented")
    }

    // Supprimer une plante de la base
    fun deletePlant(plant: PlantModel) = databaseRef.child(plant.id).removeValue()


}