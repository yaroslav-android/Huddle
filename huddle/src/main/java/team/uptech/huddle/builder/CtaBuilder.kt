package team.uptech.huddle.builder

import team.uptech.huddle.Huddle
import team.uptech.huddle.util.Constants
import team.uptech.huddle.util.ElementMarker


/**
 * DSL Builder for TODO: add docs
 *
 * Allows to build a dialog with single or dual buttons
 *
 * @property text the text for the cta
 * @property textColorRes the color resource of the cta text
 * @property backgroundColorRes the color resource of the cta background
 * @property onClick the lambda for the cta clicks
 */
@ElementMarker
class CtaBuilder {
  var onClick: ((dialog: Huddle) -> Unit)? = null

  var text: String = ""
  var textColorRes: Int = Constants.DEFAULT_COLOR
  var backgroundColorRes: Int = Constants.DEFAULT_COLOR

  fun onClick(block: (dialog: Huddle) -> Unit) {
    onClick = block
  }
}