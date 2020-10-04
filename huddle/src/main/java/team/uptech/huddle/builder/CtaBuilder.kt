package team.uptech.huddle.builder

import team.uptech.huddle.Huddle
import team.uptech.huddle.util.Constants.DEFAULT_COLOR
import team.uptech.huddle.util.ElementMarker


/**
 * DSL Builder for TODO: add docs
 *
 * Allows to build a dialog with single or dual buttons
 *
 * @property text the text for the cta
 * @property textColor the color resource of the cta text
 * @property backgroundColor the color resource of the cta background
 * @property rippleColor the color resource of the cta ripple
 * @property onClick the lambda for the cta clicks
 */
@ElementMarker
class CtaBuilder {
  var onClick: ((dialog: Huddle) -> Unit)? = null

  var text: String = ""
  var textColor: Int = DEFAULT_COLOR
  var backgroundColor: Int = DEFAULT_COLOR
  var rippleColor: Int = DEFAULT_COLOR

  fun onClick(block: (dialog: Huddle) -> Unit) {
    onClick = block
  }
}