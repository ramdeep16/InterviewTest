### Language
Kotlin
### IDE
Android Studio
### Design Pattern
 MVVM
### Networking and JSON Parser
 - Retrofit
 - Gson
 - RxJava
 - **Dependencies** 
    ```
    implementation 'com.squareup.retrofit2:retrofit:$retrofit_version'
    implementation 'com.squareup.retrofit2:converter-gson:$retrofit_version'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:$retrofit_version'
    ```
### Android Architecture Components
- View Model
- Live Data
- Room
- **Dependencies** 
    ```
    implementation 'android.arch.lifecycle:extensions:1.1.1'
    implementation "android.arch.persistence.room:runtime:1.1.1"
    implementation 'android.arch.persistence.room:rxjava2:1.1.1'
    kapt "android.arch.persistence.room:compiler:1.1.1"
    ```
### Reactive Libraries
- RxAdnroid
- RxJava
- **Dependencies** 
    ```
    implementation "io.reactivex.rxjava2:rxandroid:$rxandroid_version"
    implementation "io.reactivex.rxjava2:rxjava:$rxjava_version"
    ```
