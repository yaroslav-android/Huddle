package team.uptech.huddle.builder

import team.uptech.huddle.core.BaseDialog
import team.uptech.huddle.util.Constants.DEFAULT_FONT
import team.uptech.huddle.util.dsl.ElementMarker

/**
 * Base DSL Builder for [BaseDialog]
 *
 * This class is responsible for title, message and buttons font setup.
 */
@ElementMarker
class FontBuilder {

  /**
   * The font resource for the dialog title
   *
   * By default the dialog is using default system font.
   */
  var title: Int = DEFAULT_FONT

  /**
   * The font resource for the dialog message
   *
   * By default the dialog is using default system font.
   */
  var message: Int = DEFAULT_FONT

  /**
   * The font resource for the dialog action buttons
   *
   * By default the dialog is using default system font.
   */
  var cta: Int = DEFAULT_FONT
}