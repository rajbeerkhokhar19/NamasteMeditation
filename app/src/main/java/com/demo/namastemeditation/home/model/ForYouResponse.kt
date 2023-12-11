package com.demo.namastemeditation.home.model

data class ForYouResponse(
    var id: Int,
    var title: String,
    var image: String,
    var time: String,
    var type: String,
    var soundUrl: String,
    var description: String,
    var rating: Double
)

object ForYouResponseList {

    fun getModel(): List<ForYouResponse> {


        val itemModel1 = ForYouResponse(
            1,
            "Your Meditation Companion for Inner Peace",
            "https://images.pexels.com/photos/5184327/pexels-photo-5184327.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            type="stress",
            soundUrl = "sound1",
            description = "\"Your Meditation Companion for Inner Peace\" is a comprehensive resource dedicated to guiding individuals on a transformative journey towards inner tranquility. With a variety of expertly crafted guided meditations, calming visuals, and soothing sounds, this companion provides a versatile and immersive experience tailored to individual preferences. It incorporates mindful breathing exercises, progress tracking, and personalized meditation plans to support users in cultivating a consistent and fulfilling meditation practice. Daily reminders ensure adherence to routines, while a supportive community fosters a sense of connection and encouragement. Embrace this holistic tool to unlock the profound benefits of mindfulness and embark on a path to lasting inner peace and balance in your daily life.",
            rating = 4.0
        )

        val itemModel2 = ForYouResponse(
            2,
            "A Starter's Guide to Meditation Mastery",
            "https://images.pexels.com/photos/4325478/pexels-photo-4325478.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            type="stress",
            soundUrl = "sound2",
            description = "Discover serenity in every breath with 'Your Meditation Companion for Inner Peace.' This unique guide is crafted to be your ally in the pursuit of tranquility, providing a diverse array of guided meditations, immersive visuals, and soothing sounds. Tailor your experience with personalized meditation plans and track your progress, creating a seamless integration of mindfulness into your daily routine. Gentle reminders and a supportive community enhance your journey, making this companion an indispensable tool for those seeking not just a meditation app, but a holistic path to sustained inner peace and well-being.",
            rating = 3.5
        )


        val itemModel3 = ForYouResponse(
            3,
            "Unlocking the Mysteries",
            "https://images.pexels.com/photos/289586/pexels-photo-289586.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "2 minutes",
            type="sleep",
            soundUrl = "sound3",
            description = "Introducing 'Your Meditation Companion for Inner Peace,' a transformative tool designed to elevate your meditation experience and foster a profound sense of tranquility. Immerse yourself in expertly curated guided meditations, accompanied by calming visuals and soothing sounds that create an immersive and serene atmosphere. This companion goes beyond by offering mindful breathing exercises, personalized meditation plans, and progress tracking, empowering you to tailor your journey toward inner peace. With gentle daily reminders and a supportive community, this companion is more than just an app; it's your dedicated partner in cultivating a consistent meditation practice and nurturing a harmonious and balanced life.",
            rating = 5.0
        )

        val itemModel4 = ForYouResponse(
            4,
            "The Lost Code to Radiant Success",
            "https://images.pexels.com/photos/775417/pexels-photo-775417.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "8 minutes",
            type="sleep",
            soundUrl = "sound4",
            description = "Step into a realm of calmness with 'Your Meditation Companion for Inner Peace.' This distinctive guide is more than just an app; it's your dedicated partner in the quest for tranquility. Immerse yourself in expertly designed guided meditations, accompanied by serene visuals and soothing sounds that transport you to a place of inner serenity. Tailor your meditation journey with personalized plans, track your progress, and receive gentle daily reminders for a consistent practice. This companion isn't just about meditation; it's a holistic approach to fostering balance and peace in your daily life, providing a supportive community to amplify your mindfulness experience. Embrace this transformative tool and unlock the door to lasting inner peace.",
            rating = 3.9
        )


        val itemModel5 = ForYouResponse(
            5,
            "Unveiling the Secrets of Dazzling Achievement",
            "https://images.pexels.com/photos/214574/pexels-photo-214574.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "4 minutes",
            type="sleep",
            soundUrl = "sound1",
            description = "Embark on a transformative journey towards inner calm with 'Your Meditation Companion for Inner Peace.' This comprehensive tool is your dedicated guide to mindfulness, offering a diverse range of guided meditations, immersive visuals, and soothing sounds to create a serene meditation experience. Personalize your practice with tailored meditation plans, track your progress, and receive gentle reminders for consistent engagement. Beyond just an app, this companion fosters a sense of community support, allowing you to connect with like-minded individuals on a shared path to tranquility. Elevate your meditation practice and embrace a harmonious lifestyle with this all-encompassing companion for inner peace.",
            rating = 2.5
        )

        val itemModel6 = ForYouResponse(
            6,
            "Your Beginner's Guide to Mindfulness",
            "https://images.pexels.com/photos/247851/pexels-photo-247851.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            time = "2 minutes",
            type="stress",
            soundUrl = "sound3",
            description = "Introducing 'Your Meditation Companion for Inner Peace'—more than an app, it's a gateway to tranquility. Immerse yourself in a rich tapestry of guided meditations, enveloped by calming visuals and soothing sounds that transport you to a space of profound serenity. This companion goes beyond traditional meditation apps, offering personalized plans to tailor your journey and track your progress seamlessly. With gentle daily reminders and a supportive community, it becomes an integral part of your daily routine, fostering a holistic approach to inner peace. Embrace a mindful lifestyle with a companion that understands your unique path to tranquility, making it an essential partner on your journey to lasting well-being.",
            rating = 4.8
        )


        val itemList: ArrayList<ForYouResponse> = ArrayList()
        itemList.add(itemModel1)
        itemList.add(itemModel2)
        itemList.add(itemModel3)
        itemList.add(itemModel4)
        itemList.add(itemModel5)
        itemList.add(itemModel6)
        return itemList
    }

}