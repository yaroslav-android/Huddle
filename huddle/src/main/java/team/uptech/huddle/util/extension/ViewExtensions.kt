package team.uptech.huddle.util.extension

import android.view.View


/**
 * Set view's visibility to [View.GONE].
 */
fun View.makeGone() {
  visibility = View.GONE
}

/**
 * Set view's visibility to [View.VISIBLE].
 */
fun View.makeVisible() {
  visibility = View.VISIBLE
}

/**
 * Set view's visibility to [View.INVISIBLE].
 */
fun View.makeInvisible() {
  visibility = View.INVISIBLE
}

/**
 * Based on the result of the [lambda][condition] this view will change its visibility state.
 *
 * @param condition true if this view should be visible, false otherwise.
 *
 * @see makeVisible
 * @see makeGone
 */
inline fun View.makeVisibleOrGone(condition: () -> Boolean) {
  if (condition()) makeVisible() else makeGone()
}

/**
 * Based on the result of the [lambda][condition] this view will change its visibility state.
 *
 * @param condition true if this view should be visible, false otherwise.
 *
 * @see makeVisible
 * @see makeInvisible
 */
inline fun View.makeVisibleOrInvisible(condition: () -> Boolean) {
  if (condition()) makeVisible() else makeInvisible()
}