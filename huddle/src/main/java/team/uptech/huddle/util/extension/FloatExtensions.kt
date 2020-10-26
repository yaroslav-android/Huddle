package team.uptech.huddle.util.extension

import android.content.res.Resources
import android.util.TypedValue


/**
 * Transform density-independent pixels from this value.
 *
 * @see TypedValue.COMPLEX_UNIT_DIP
 */
val Float.dp: Float
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    Resources.getSystem().displayMetrics
  )

/**
 * Transform scalable pixels from this value.
 *
 * @see TypedValue.COMPLEX_UNIT_SP
 */
val Float.sp: Float
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    this,
    Resources.getSystem().displayMetrics
  )
