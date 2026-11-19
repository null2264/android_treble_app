plugins {
    id("com.android.application")
    kotlin("android")
}

val kotlin_version: String by rootProject.extra

val platformKeystore = rootProject.file("keys/platform.jks")

android {
    if (platformKeystore.exists()) {
        signingConfigs {
            create("platform") {
                storeFile = platformKeystore
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
            }
        }
    }

    namespace = "me.phh.treble.app"
    compileSdk = 33
    defaultConfig {
        applicationId = "me.phh.treble.app"
        minSdk = 27
        targetSdk = 33
        versionCode = 2
        versionName = "1.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            if (platformKeystore.exists()) {
                signingConfig = signingConfigs.getByName("platform")
            }
        }
        debug {
            if (platformKeystore.exists()) {
                signingConfig = signingConfigs.getByName("platform")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    compileOnly(fileTree(mapOf("dir" to "libs", "include" to "*.jar")))
    implementation(files("libs/vendor.hct.hardware.gesturewake-V1.0-java.jar"))
    implementation(files("libs/vendor.ims.zenmotion-V1.0-java.jar"))
    implementation(files("libs/vendor.xiaomi.hw.touchfeature-V1-java.jar"))
    implementation(files("libs/vendor.mediatek.hardware.agolddaemon-V1-java.jar"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("org.tukaani:xz:1.9")
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
}
