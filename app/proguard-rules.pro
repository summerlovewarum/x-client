# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in the Android SDK.

# Keep application classes
-keep public class com.xweb.client.** { *; }

# Keep Custom Tabs and Browser related classes
-keep class androidx.browser.** { *; }

# Keep Shortcut classes
-keep class androidx.core.content.pm.** { *; }

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
}
