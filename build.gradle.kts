// Top-level build file where you can add configuration options common to all sub-modules/projects.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
