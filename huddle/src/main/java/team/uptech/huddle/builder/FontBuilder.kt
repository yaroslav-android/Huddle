package team.uptech.huddle.builder

import team.uptech.huddle.util.Constants
import team.uptech.huddle.util.ElementMarker

/**
 * Base DSL Builder for TODO: add docs
 *
 * @property titleFont the font resource for the dialog title
 * @property messageFont the font resource for the dialog message
 * @property ctaFont the font resource for the dialog action button
 */
@ElementMarker
class FontBuilder {
  var titleFont: Int = Constants.DEFAULT_FONT
  var messageFont: Int = Constants.DEFAULT_FONT
  var ctaFont: Int = Constants.DEFAULT_FONT
}