package team.uptech.huddle.util.extension

import android.view.View


/**
 * Set view's state to disabled.
 */
fun View.disable() {
  isEnabled = false
}

/**
 * Set view's state to enabled.
 */
fun View.enable() {
  isEnabled = true
}

/**
 * Based on the result of the [lambda][condition] this view will change its enabled state.
 *
 * @param condition True if this view should be enabled, false otherwise.
 *
 * @see enable
 * @see disable
 */
inline fun View.makeEnabledOrDisabled(condition: () -> Boolean) {
  if (condition()) enable() else disable()
}