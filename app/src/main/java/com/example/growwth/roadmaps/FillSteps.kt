package com.example.growwth.roadmaps
object FillSteps {
    private val titleListAndroid : ArrayList<String> = ArrayList()
    private val descListAndroid : ArrayList<String> = ArrayList()

    private val titleListFullStack : ArrayList<String> = ArrayList()
    private val descListFullStack : ArrayList<String> = ArrayList()

    private val titleListDevOps : ArrayList<String> = ArrayList()
    private val descListDevOps : ArrayList<String> = ArrayList()

    fun getTitleDataAndroid() : ArrayList<String>{
        titleListAndroid.add("Learn the Fundamentals")
        titleListAndroid.add("Version Control System")
        titleListAndroid.add("Interface & Navigation")
        titleListAndroid.add("Jetpack Compose")
        titleListAndroid.add("Application Components")
        titleListAndroid.add("Architecture & Design Patterns")
        titleListAndroid.add("Storage")
        titleListAndroid.add("Network")
        titleListAndroid.add("Asynchronism")
        titleListAndroid.add("Firebase")
        titleListAndroid.add("Common Services")
        titleListAndroid.add("Testing")
        titleListAndroid.add("Distribution")

        return titleListAndroid
    }

    fun getDescDataAndroid() : ArrayList<String>{
        descListAndroid.add("Development of IDE, Basics of Kotlin, Basics of OOP")
        descListAndroid.add("Git + GitHub/Gitlab/BitBucket")
        descListAndroid.add("Learn about basic Views, ViewGroups & Layouts")
        descListAndroid.add("App Shortcuts & Navigation Components")
        descListAndroid.add("Activity, Services, Content Provider, Broadcast Receiver, Intent")
        descListAndroid.add("MVVM, MCI, MVP, MVC")
        descListAndroid.add("Shared Preferences, DataStore, Room Database, File System")
        descListAndroid.add("Retrofit, OkHttp")
        descListAndroid.add("Coroutines, Threads, RxJava, RxKotlin")
        descListAndroid.add("Authentication, Cloud Messaging, FireStore, etc")
        descListAndroid.add("Google ADMob, Google Play Services, Google Maps")
        descListAndroid.add("Espresso, JUnit")
        descListAndroid.add("Firebase Distribution & Google Play Store")

        return descListAndroid
    }

    fun getTitleDataFullStack() : ArrayList<String>{
        titleListFullStack.add("Learn the Fundamentals")

        return titleListFullStack
    }

    fun getDescDataFullStack() : ArrayList<String>{
        descListFullStack.add("Learn the Fundamentals")

        return descListFullStack
    }

    fun getTitleDataDevOps() : ArrayList<String>{
        titleListDevOps.add("Learn the Fundamentals")
        return titleListDevOps
    }

    fun getDescDataDevOps() : ArrayList<String>{
        descListDevOps.add("Learn the Fundamentals")
        return descListDevOps
    }
}