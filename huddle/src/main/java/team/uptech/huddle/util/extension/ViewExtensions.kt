package team.uptech.huddle.util.extension

import android.view.View


fun View.makeGone() {
  visibility = View.GONE
}

fun View.makeVisible() {
  visibility = View.VISIBLE
}

fun View.makeInvisible() {
  visibility = View.INVISIBLE
}

inline fun View.makeVisibleOrGone(isVisible: () -> Boolean = { false }) {
  if (isVisible.invoke()) makeVisible() else makeGone()
}

inline fun View.makeVisibleOrInvisible(isVisible: () -> Boolean = { false }) {
  if (isVisible.invoke()) makeVisible() else makeInvisible()
}