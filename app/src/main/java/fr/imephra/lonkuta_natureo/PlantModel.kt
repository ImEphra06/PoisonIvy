package fr.imephra.lonkuta_natureo

class PlantModel (
        val id: String = "plant0",
        val name: String = "Rose",
        val description: String = "Petite description",
        val imageUrl: String = "http://graven.yt/plant.jpg",
        val grow: String = "Faible",
        val water: String = "Moyenne",
        var liked: Boolean = false
)