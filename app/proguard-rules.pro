# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#Testing
#-keep class com.google.gson.** { *; }
#-keep class com.google.inject.** { *; }
#-keep class org.apache.http.** { *; }
#-keep class org.apache.james.mime4j.** { *; }
#-keep class javax.inject.** { *; }
#-keep class retrofit2.** { *; }
#
##Picasso
#-dontwarn com.squareup.okhttp.**
#
#-dontwarn okhttp3.internal.platform.ConscryptPlatform
#
#-keepattributes Signature
#
#-keep class org.apache.** { *; }
#
#-keepclassmembers class com.zeus.smartnews.models.** { *; }
#-keepclassmembers class com.zeus.smartnews.models.CountriesClass.** { *; }
#-keepclassmembers class com.zeus.smartnews.models.ArticleModel.** { *; }
#-keepclassmembers class com.zeus.smartnews.models.NewsModel.** { *; }
