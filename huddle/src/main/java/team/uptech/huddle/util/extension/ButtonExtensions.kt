package team.uptech.huddle.util.extension

import android.view.View


fun View.disable() {
  isEnabled = false
}

fun View.enable() {
  isEnabled = true
}

inline fun View.makeEnabledOrDisabled(isEnabled: () -> Boolean = { false }) {
  if (isEnabled()) enable() else disable()
}