package team.uptech.huddle.util.extension

import android.widget.EditText
import android.widget.TextView


/**
 * Can be used for [EditText] or [TextView] to clear the text field.
 *
 * `setText()` used for proper work for derived classes.
 *
 * @suppress UsePropertyAccessSyntax
 */
@Suppress("UsePropertyAccessSyntax")
fun TextView.erase() {
  setText("")
}