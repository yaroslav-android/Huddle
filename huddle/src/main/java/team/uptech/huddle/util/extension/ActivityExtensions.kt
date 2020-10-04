package team.uptech.huddle.util.extension

import android.app.Activity
import android.util.DisplayMetrics
import android.view.Surface
import androidx.annotation.RestrictTo
import kotlin.math.min

/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Activity.getScreenWidth(): Int {
  val display = windowManager.defaultDisplay
  val metrics = DisplayMetrics()
  display.getMetrics(metrics)

  val widthPixels = metrics.widthPixels
  val heightPixels = metrics.heightPixels

  return when (display.rotation) {
    Surface.ROTATION_0, Surface.ROTATION_180 -> widthPixels
    Surface.ROTATION_90, Surface.ROTATION_270 -> widthPixels
    else -> min(heightPixels, widthPixels)
  }
}