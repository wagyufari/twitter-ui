plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.1")

    defaultConfig {
        applicationId = "com.mayburger.twitter"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("default")

    productFlavors {
        create("production") {
            setDimension("default")
        }
        create("development") {
            setDimension("default")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(hiltDependencies.hilt)
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(hiltDependencies.hiltCompiler)
    implementation(hiltDependencies.hiltViewModel)

    implementation(kotlinDependencies.kotlin)
    implementation(kotlinDependencies.coroutinesCore)
    implementation(kotlinDependencies.coroutinesAndroid)

    implementation(jetpackDependencies.coreKtx)
    implementation(jetpackDependencies.appCompat)
    implementation(jetpackDependencies.constraintLayout)
    implementation(jetpackDependencies.legacySupport)
    implementation(jetpackDependencies.animatedVectorDrawable)
    implementation(jetpackDependencies.cardView)
    implementation(jetpackDependencies.multiDex)
    implementation(jetpackDependencies.lifecycleExtensions)
    implementation(jetpackDependencies.lifecycleCompiler)
    implementation(jetpackDependencies.lifecycleViewModel)
    implementation(jetpackDependencies.lifecycleLivedata)
    implementation(jetpackDependencies.lifecycleCoroutines)
    implementation(jetpackDependencies.cameraXCore)
    implementation(jetpackDependencies.cameraX2)

    implementation(googleDependencies.material)
    implementation(googleDependencies.flexbox)
    implementation(googleDependencies.maps)

    implementation(glideDependencies.glide)
    kapt(glideDependencies.glideCompiler)

    implementation(otherDependencies.fragmentViewBindingDelegate)

    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

}

