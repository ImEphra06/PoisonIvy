package fr.imephra.lonkuta_natureo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.imephra.lonkuta_natureo.MainActivity
import fr.imephra.lonkuta_natureo.PlantRepository.Singleton.plantList
import fr.imephra.lonkuta_natureo.PlantModel
import fr.imephra.lonkuta_natureo.R
import fr.imephra.lonkuta_natureo.adapter.PlantAdapter
import fr.imephra.lonkuta_natureo.adapter.PlantItemDecoration

class HomeFragment (
        private val context: MainActivity
): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
                val view = inflater?.inflate(R.layout.fragment_home, container, false)

        // Récupération du RecyclerView
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_horizontal_plant)

        // Récupération du RecyclerView
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())
        return view
    }
}