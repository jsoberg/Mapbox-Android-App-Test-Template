plugins {
    id("com.android.application")
    kotlin("android")
}

repositories {
    maven {
        url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
        authentication.create<BasicAuthentication>("basic")
        credentials.username = "mapbox"
        // Use the secret token stored in gradle.properties as the password
        credentials.password = providers.gradleProperty("MAPBOX_DOWNLOADS_TOKEN").get()
    }
}

android {
    namespace = "com.mapbox.android.example.app"

	compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    compileSdk = 34
    defaultConfig {
        minSdk = 26
        targetSdk = 34

        applicationId = "com.mapbox.android.example.app"
        versionCode = 1
        versionName = "0.1"
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)
    implementation(libs.mapbox.android)
}