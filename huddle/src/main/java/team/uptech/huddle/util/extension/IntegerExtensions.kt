package team.uptech.huddle.util.extension

import android.content.res.Resources
import android.util.TypedValue


val Int.dp: Int
  get() = TypedValue.complexToDimensionPixelSize(this, Resources.getSystem().displayMetrics)

val Int.sp: Int
  get() = this.toFloat().sp.toInt()
