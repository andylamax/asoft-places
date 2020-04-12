fun asoftCore(lib: String, platform: String): String {
    return "com.github.andylamax.asoft-core:asoft-$lib-$platform:${versions.asoft.core}"
}

fun asoftFirebase(platform: String): String {
    return "com.github.andylamax.asoft-firebase:asoft-firebase-$platform:${versions.asoft.firebase}"
}

fun asoftTest(platform: String) = asoftCore("test", platform)
