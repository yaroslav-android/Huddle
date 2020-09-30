package team.uptech.huddle.util.extension

import android.widget.TextView


/**
 * `setText()` used for proper work for derived classes
 */
@Suppress("UsePropertyAccessSyntax")
fun TextView.erase() {
  setText("")
}