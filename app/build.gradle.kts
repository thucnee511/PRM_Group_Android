plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}
val apiUrl: String by project
android {
    namespace = "vn.edu.fpt.electricalconponents"
    compileSdk = 35

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "vn.edu.fpt.electricalcomponents"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "API_URL", "\"$apiUrl\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.java.dotenv)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(libs.adapter.rxjava3)
    implementation(libs.play.services.auth)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
}