package com.demo.namastemeditation.home.model

data class RatingsAndReviewResponse(
    var id: Int,
    var name: String,
    var image: String,
    var description: String,
    var rating: Double
)

object RatingsAndReviewResponseList {

    fun getModel(): List<RatingsAndReviewResponse> {

        val itemModel1 = RatingsAndReviewResponse(
            1,
            "Rajbeer Kaur",
            "https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            description = "Discover the best music I've ever heard – a sublime fusion of melodies and rhythms that captivate the soul. Pure musical perfection",
            rating = 4.0
        )

        val itemModel2 = RatingsAndReviewResponse(
            2,
            "Pawandeep kaur",
            "https://images.pexels.com/photos/34534/people-peoples-homeless-male.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            description = "Embark on a sonic journey with the absolute best music I've encountered – a mesmerizing blend of melodies and beats that redefine musical brilliance. Simply unparalleled.",
            rating = 4.5
        )


        val itemModel3 = RatingsAndReviewResponse(
            3,
            "Unlocking the Mysteries",
            "https://images.pexels.com/photos/289586/pexels-photo-289586.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            description = "Unveiling the epitome of musical brilliance – the best tunes I've ever encountered. A harmonious masterpiece that speaks directly to the heart and soul.",
            rating = 5.0
        )

        val itemList: ArrayList<RatingsAndReviewResponse> = ArrayList()
        itemList.add(itemModel1)
        itemList.add(itemModel2)
        itemList.add(itemModel3)
        return itemList
    }

}