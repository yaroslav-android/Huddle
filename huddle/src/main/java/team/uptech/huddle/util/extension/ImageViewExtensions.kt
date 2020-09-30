package team.uptech.huddle.util.extension

import android.widget.ImageView


/**
 * Important. If you're setting the width after the layout has already been 'laid out',
 * make sure you also call: `requestLayout()`
 */
fun ImageView.setWidth(width: Int) {
  layoutParams.width = width
}

/**
 * Important. If you're setting the height after the layout has already been 'laid out',
 * make sure you also call: `requestLayout()`
 */
fun ImageView.setHeight(height: Int) {
  layoutParams.height = height
}

fun ImageView.updateSize(width: Int, height: Int) {
  setWidth(width)
  setHeight(height)
  requestLayout()
}