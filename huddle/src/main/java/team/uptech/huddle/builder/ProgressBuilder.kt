package team.uptech.huddle.builder

import team.uptech.huddle.util.Constants
import team.uptech.huddle.util.ElementMarker

/**
 * DSL Builder for TODO: add docs
 *
 * Allows to build a dialog with single or dual buttons
 *
 * @property progressColorRes the color resource of progress circle on primary button
 */
@ElementMarker
class ProgressBuilder {
  var progressColorRes: Int = Constants.DEFAULT_COLOR
}