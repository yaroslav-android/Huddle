package team.uptech.huddle.util.extension

import android.os.Build

fun buildVersionGE(version: Int): Boolean = Build.VERSION.SDK_INT >= version
