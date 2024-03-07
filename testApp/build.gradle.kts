import java.io.DataInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

repositories {
    google()
    mavenCentral()
    maven {
        url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
        authentication.create<BasicAuthentication>("basic")
        credentials.username = "mapbox"

        // Use the secret token stored in local.properties as the password
        credentials.password = loadLocalPropertyString("MAPBOX_DOWNLOADS_TOKEN")
    }
}

fun loadLocalPropertyString(key: String): String {
    try {
        val properties = Properties()
        properties.load(DataInputStream(rootProject.file("local.properties").inputStream()))
        return properties.getProperty(key)
    } catch (e: Throwable) {
        throw IllegalStateException("Problem reading local.properties value $key, reference README for setup", e)
    }
}

android {
    namespace = "com.mapbox.android.example.app"
    buildFeatures.buildConfig = true

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

    buildTypes {
        debug {
            resValue(
                type = "string",
                name = "MAPBOX_ACCESS_TOKEN",
                value = loadLocalPropertyString("MAPBOX_ACCESS_TOKEN"),
            )
        }
    }
}

dependencies {
    implementation(libs.androidX.activity)
    implementation(libs.androidX.interpolator)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)
    implementation(libs.mapbox.android)
}