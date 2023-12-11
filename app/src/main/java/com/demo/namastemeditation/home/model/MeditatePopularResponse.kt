package com.demo.namastemeditation.home.model

data class SleepPopularResponse(
    var id: Int,
    var title: String,
    var image: String,
    var time: String,
    var soundUrl: String,
    var rating: Double
)

object SleepPopularResponseList {

    fun getModel(): List<SleepPopularResponse> {

        val itemModel1 = SleepPopularResponse(
            1,
            "Mysteries of Supreme Achievement",
            "https://images.pexels.com/photos/247851/pexels-photo-247851.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            soundUrl = "sound1",
            rating = 2.5
        )

        val itemModel2 = SleepPopularResponse(
            2,
            "Rediscover Your Brilliance",
            "https://images.pexels.com/photos/214574/pexels-photo-214574.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            soundUrl = "sound2",
            rating = 4.8
        )


        val itemModel3 = SleepPopularResponse(
            3,
            "Unveiling the Secrets of Dazzling Achievement",
            "https://images.pexels.com/photos/6787202/pexels-photo-6787202.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "2 minutes",
            soundUrl = "sound3",
            rating = 4.5
        )

        val itemModel4 = SleepPopularResponse(
            4,
            "A Sleepwalker's Revelation",
            "https://images.pexels.com/photos/1583582/pexels-photo-1583582.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "8 minutes",
            soundUrl = "sound4",
            rating = 4.0
        )

        val itemModel5 = SleepPopularResponse(
            5,
            "Unraveling the Secrets of Dazzling Success",
            "https://images.pexels.com/photos/287240/pexels-photo-287240.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            soundUrl = "sound2",
                    rating = 3.5
        )

        val itemModel6 = SleepPopularResponse(
            6,
            "A Guide to Dazzling Success",
            "https://images.pexels.com/photos/3764579/pexels-photo-3764579.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            soundUrl = "sound1",
            rating = 4.9
        )


        val itemList: ArrayList<SleepPopularResponse> = ArrayList()
        itemList.add(itemModel1)
        itemList.add(itemModel2)
        itemList.add(itemModel3)
        itemList.add(itemModel4)
        itemList.add(itemModel5)
        itemList.add(itemModel6)
        return itemList
    }

}