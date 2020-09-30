package team.uptech.huddle.util.extension

import android.app.Activity
import android.util.DisplayMetrics
import androidx.annotation.RestrictTo
import kotlin.math.min

/**
 * TODO: add docs
 * @hide
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Activity.getMinWidthValue(): Int {
  val display = windowManager.defaultDisplay
  val metrics = DisplayMetrics()
  display.getMetrics(metrics)

  val heightPixels = metrics.heightPixels
  val widthPixels = metrics.widthPixels

  return min(heightPixels, widthPixels)
}