plugins { id("com.android.application") }

android {
    namespace = "io.github.asadman1523.privatespaceshortcuts"
    compileSdk = 37
    defaultConfig {
        applicationId = "io.github.asadman1523.privatespaceshortcuts"
        minSdk = 35
        targetSdk = 37
        versionCode = 3
        versionName = "0.1.0-alpha.3"
    }
    buildFeatures { buildConfig = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    signingConfigs {
        create("localRelease") {
            System.getenv("PPSS_KEYSTORE")?.let { storeFile = file(it) }
            storePassword = System.getenv("PPSS_STORE_PASSWORD")
            keyAlias = System.getenv("PPSS_KEY_ALIAS")
            keyPassword = System.getenv("PPSS_KEY_PASSWORD")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            if (System.getenv("PPSS_KEYSTORE") != null) {
                signingConfig = signingConfigs.getByName("localRelease")
            }
        }
    }
    lint { abortOnError = true }
}

dependencies {
    compileOnly("de.robv.android.xposed:api:82")
    testImplementation("junit:junit:4.13.2")
}
