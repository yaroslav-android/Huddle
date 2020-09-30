package team.uptech.huddle.builder

import team.uptech.huddle.util.Constants.DEFAULT_COLOR
import team.uptech.huddle.util.ElementMarker


/**
 * Base DSL Builder for TODO: add docs
 *
 * @property titleRes the color resource for the dialog title
 * @property messageRes the color resource for the dialog message
 */
@ElementMarker
class ColorBuilder {
  var titleRes: Int = DEFAULT_COLOR
  var messageRes: Int = DEFAULT_COLOR
}