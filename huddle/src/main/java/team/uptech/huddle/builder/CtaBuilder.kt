package team.uptech.huddle.builder

import androidx.core.content.ContextCompat
import team.uptech.huddle.Huddle
import team.uptech.huddle.R
import team.uptech.huddle.util.Constants.DEFAULT_COLOR
import team.uptech.huddle.util.dsl.ElementMarker


/**
 * The DSL Builder for [Huddle]
 *
 * This class is responsible for handling buttons for the dialog.
 */
@ElementMarker
class CtaBuilder {
  var onClick: ((dialog: Huddle) -> Unit)? = null

  /**
   * The text for the button.
   */
  var text: String = ""

  /**
   * The text color resource for the dialog's button.
   *
   * You should pass the resource [R.color] but not a result of the [ContextCompat.getColor]
   */
  var textColor: Int = DEFAULT_COLOR

  /**
   * The color resource for the dialog's button background.
   *
   * You should pass the resource [R.color] but not a result of the [ContextCompat.getColor]
   */
  var backgroundColor: Int = DEFAULT_COLOR

  /**
   * The color resource for the dialog's button ripple.
   *
   * You should pass the resource [R.color] but not a result of the [ContextCompat.getColor]
   */
  var rippleColor: Int = DEFAULT_COLOR

  @ElementMarker
  fun onClick(block: (dialog: Huddle) -> Unit) {
    onClick = block
  }
}