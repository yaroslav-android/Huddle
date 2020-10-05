package team.uptech.huddle.util.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.Gravity
import android.graphics.Typeface
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.use
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import team.uptech.huddle.Huddle
import team.uptech.huddle.core.BaseBuilder
import team.uptech.huddle.util.Constants.DEFAULT_COLOR
import team.uptech.huddle.util.RootMarker
import kotlin.math.roundToInt


/**
 * Use it for creation dialog with builder
 *
 * @see DialogBuilder
 */
@RootMarker
inline fun <reified Builder : BaseBuilder> Huddle.create(builder: Builder.() -> Unit): Huddle {
  val dialogBuilder = Builder::class.java.newInstance()
  return importSettings(dialogBuilder.apply(builder))
}

/**
 * TODO: add docs
 */
fun DialogFragment?.isLaunched() = this?.isResumed == true || this?.dialog?.isShowing == true

/**
 * TODO: add docs
 */
fun DialogFragment?.isNotLaunched() = !isLaunched()

/**
 * TODO: add docs
 */
inline fun <reified T : DialogFragment> T.compose(from: AppCompatActivity): T {
  if (from.isFinishing) return this

  tryAddDialogToStack(this, from.supportFragmentManager)
  return this
}

/**
 * TODO: add docs
 */
inline fun <reified T : DialogFragment> T.compose(from: Fragment): T {
  val activity = from.activity ?: return this
  if (activity.isFinishing) return this

  tryAddDialogToStack(this, from.childFragmentManager)
  return this
}

/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
inline fun <reified T : DialogFragment> tryAddDialogToStack(dialog: T, fragmentManager: FragmentManager) {
  with(fragmentManager) {
    if (!isDestroyed && !isStateSaved) {
      beginTransaction()
        .add(dialog, T::class.java.name)
        .commitNow()
    }
  }
}

/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Dialog.setWidthRelativeToParent(activity: Activity?, percentage: Int) {
  val screen = activity ?: return

  val dynamicWidth = (percentage / 100.0f * screen.getScreenWidth())

  window?.setGravity(Gravity.CENTER or Gravity.CENTER_HORIZONTAL)
  window?.setLayout(dynamicWidth.roundToInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}

/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Context.getColorIfNotDefault(@ColorRes colorRes: Int, action: (color: Int) -> Unit) {
  getColorIfNotDefaultWithFallback(colorRes, action, fallback = {})
}

/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Context.getColorIfNotDefaultWithFallback(
  @ColorRes colorRes: Int,
  action: (color: Int) -> Unit,
  fallback: () -> Unit
) {
  if (colorRes != DEFAULT_COLOR) action(ContextCompat.getColor(this, colorRes))
  else fallback()
}

/**
 * Retrieves colors from theme attributes
 *
 * @throws Resources.NotFoundException Throws NotFoundException if the given ID does not exist.
 * @throws UnsupportedOperationException if the attribute is defined but is
 *         not an integer color or color state list.
 * @hide
 */
@ColorInt
@SuppressLint("Recycle")
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Context.getThemeColor(@AttrRes colorAttrId: Int): Int {
  return obtainStyledAttributes(intArrayOf(colorAttrId))
    .use { it.getColor(0, Color.WHITE) }
}

/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun Context.getFont(@FontRes fontResId: Int): Typeface? {
  return try {
    if (buildVersionGE(Build.VERSION_CODES.O)) {
      resources.getFont(fontResId)
    } else {
      ResourcesCompat.getFont(this, fontResId)
    }
  } catch (e: Exception) {
    val font = Typeface.create("sans-serif", Typeface.NORMAL)
    when (e) {
      is Resources.NotFoundException,
      is NullPointerException -> font
      else -> font
    }
  }
}