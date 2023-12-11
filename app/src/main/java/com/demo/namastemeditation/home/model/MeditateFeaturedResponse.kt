package com.demo.namastemeditation.home.model

data class SleepFeaturedResponse(
    var id: Int,
    var title: String,
    var image: String,
    var time: String,
    var soundUrl: String,
    var rating: Double
)

object SleepFeaturedResponseList {

    fun getModel(): List<SleepFeaturedResponse> {

        val itemModel1 = SleepFeaturedResponse(
            1,
            "Burn The Ships",
            "https://images.pexels.com/photos/5184327/pexels-photo-5184327.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            soundUrl = "sound1",
            rating = 4.0
        )

        val itemModel2 = SleepFeaturedResponse(
            2,
            "The Power of Notes",
            "https://images.pexels.com/photos/4325478/pexels-photo-4325478.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            soundUrl = "sound2",
            rating = 3.5
        )


        val itemModel3 = SleepFeaturedResponse(
            3,
            "The Inferno of Serenity",
            "https://images.pexels.com/photos/289586/pexels-photo-289586.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "2 minutes",
            soundUrl = "sound3",
            rating = 4.5
        )

        val itemModel4 = SleepFeaturedResponse(
            4,
            "From Silicon to Ash",
            "https://images.pexels.com/photos/775417/pexels-photo-775417.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "8 minutes",
            soundUrl = "sound4",
            rating = 4.7
        )


        val itemList: ArrayList<SleepFeaturedResponse> = ArrayList()
        itemList.add(itemModel1)
        itemList.add(itemModel2)
        itemList.add(itemModel3)
        itemList.add(itemModel4)
        return itemList
    }

}