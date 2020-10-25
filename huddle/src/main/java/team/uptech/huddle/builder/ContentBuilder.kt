package team.uptech.huddle.builder

import android.text.SpannableString
import team.uptech.huddle.util.dsl.ElementMarker


/**
 * Base DSL Builder for TODO: add docs
 *
 * @property title the text for the dialog title
 * @property message the text for the dialog message
 * @property messageSpan the `SpannableString` for the dialog message
 */
@ElementMarker
class ContentBuilder {
  val color: ColorBuilder = ColorBuilder()

  var title: String = ""
  var message: String = ""
  var messageSpan: SpannableString = SpannableString("")

  @ElementMarker
  fun color(init: ColorBuilder.() -> Unit) {
    color.apply(init)
  }
}