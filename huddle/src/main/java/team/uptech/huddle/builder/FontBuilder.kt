package team.uptech.huddle.builder

import team.uptech.huddle.util.Constants.DEFAULT_FONT
import team.uptech.huddle.util.dsl.ElementMarker

/**
 * Base DSL Builder for TODO: add docs
 *
 * @property title the font resource for the dialog title
 * @property message the font resource for the dialog message
 * @property cta the font resource for the dialog action button
 */
@ElementMarker
class FontBuilder {
  var title: Int = DEFAULT_FONT
  var message: Int = DEFAULT_FONT
  var cta: Int = DEFAULT_FONT
}