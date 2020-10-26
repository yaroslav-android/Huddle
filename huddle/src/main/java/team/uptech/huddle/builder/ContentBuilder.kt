package team.uptech.huddle.builder

import android.text.SpannableString
import team.uptech.huddle.core.BaseDialog
import team.uptech.huddle.util.dsl.ElementMarker


/**
 * The DSL Builder for [BaseDialog]
 *
 * This class is responsible for dialog content color and text setup.
 */
@ElementMarker
class ContentBuilder {

  /**
   * Color DSL builder for title and message.
   */
  val color: ColorBuilder = ColorBuilder()

  /**
   * The dialog's title text
   */
  var title: String = ""

  /**
   * The dialog's message text
   */
  var message: String = ""

  /**
   * The dialog's message [SpannableString]
   */
  var messageSpan: SpannableString = SpannableString("")

  @ElementMarker
  fun color(init: ColorBuilder.() -> Unit) {
    color.apply(init)
  }
}