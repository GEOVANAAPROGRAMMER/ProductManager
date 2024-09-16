// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Declaração dos plugins que são aplicados a nível de projeto
    id("com.android.application") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false

    // Adiciona o plugin para serviços do Google
    id("com.google.gms.google-services") version "4.4.2" apply false
}
