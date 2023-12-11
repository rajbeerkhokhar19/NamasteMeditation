package com.demo.namastemeditation.search.model

data class SearchResponse(
    var id: Int,
    var title: String,
    var image: String,
    var time: String,
    var type: String,
    var soundUrl: String
)

object SearchResponseList {
    fun getModel(): List<SearchResponse> {
        
        val itemModel1 = SearchResponse(
            1,
            "Your Meditation Companion for Inner Peace",
            "https://images.pexels.com/photos/5184327/pexels-photo-5184327.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            type="stress",
            soundUrl = "sound1"
        )

        val itemModel2 = SearchResponse(
            2,
            "A Starter's Guide to Meditation Mastery",
            "https://images.pexels.com/photos/4325478/pexels-photo-4325478.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            type="stress",
            soundUrl = "sound2"
        )


        val itemModel3 = SearchResponse(
            3,
            "Unlocking the Mysteries",
            "https://images.pexels.com/photos/289586/pexels-photo-289586.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "2 minutes",
            type="sleep",
            soundUrl = "sound3"
        )

        val itemModel4 = SearchResponse(
            4,
            "The Lost Code to Radiant Success",
            "https://images.pexels.com/photos/775417/pexels-photo-775417.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "8 minutes",
            type="sleep",
            soundUrl = "sound4"
        )


        val itemModel5 = SearchResponse(
            5,
            "Unveiling the Secrets of Dazzling Achievement",
            "https://images.pexels.com/photos/214574/pexels-photo-214574.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            type="sleep",
            soundUrl = "sound1"
        )

        val itemModel6 = SearchResponse(
            6,
            "Your Beginner's Guide to Mindfulness",
            "https://images.pexels.com/photos/247851/pexels-photo-247851.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "2 minutes",
            type="stress",
            soundUrl = "sound3"
        )

        val itemModel7 = SearchResponse(
            7,
            "Inner Peace",
            "https://images.pexels.com/photos/5184327/pexels-photo-5184327.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            type="sleep",
            soundUrl = "sound1"
        )

        val itemModel8 = SearchResponse(
            8,
            "Meditation Mastery",
            "https://images.pexels.com/photos/4325478/pexels-photo-4325478.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            type="stress",
            soundUrl = "sound2"
        )


        val itemModel9 = SearchResponse(
            9,
            "Unlocking the Meditation Experience",
            "https://images.pexels.com/photos/289586/pexels-photo-289586.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "2 minutes",
            type="stress",
            soundUrl = "sound3"
        )

        val itemModel10 = SearchResponse(
            10,
            "The Radiant Success",
            "https://images.pexels.com/photos/775417/pexels-photo-775417.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "5.6 minutes",
            type="sleep",
            soundUrl = "sound1"
        )


        val itemList: ArrayList<SearchResponse> = ArrayList()
        itemList.add(itemModel1)
        itemList.add(itemModel2)
        itemList.add(itemModel3)
        itemList.add(itemModel4)
        itemList.add(itemModel5)
        itemList.add(itemModel6)
        itemList.add(itemModel7)
        itemList.add(itemModel8)
        itemList.add(itemModel9)
        itemList.add(itemModel10)
        return itemList
    }

}