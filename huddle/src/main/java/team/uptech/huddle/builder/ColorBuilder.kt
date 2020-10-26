package team.uptech.huddle.builder

import androidx.core.content.ContextCompat
import team.uptech.huddle.R
import team.uptech.huddle.core.BaseDialog
import team.uptech.huddle.util.Constants.DEFAULT_COLOR
import team.uptech.huddle.util.dsl.ElementMarker


/**
 * The DSL Builder for [BaseDialog]
 *
 * This class is responsible for dialog's content color.
 */
@ElementMarker
class ColorBuilder {

  /**
   * The color resource for the dialog's title.
   *
   * You should pass the resource [R.color] but not a result of the [ContextCompat.getColor]
   */
  var title: Int = DEFAULT_COLOR

  /**
   * The color resource for the dialog's message.
   *
   * You should pass the resource [R.color] but not a result of the [ContextCompat.getColor]
   */
  var message: Int = DEFAULT_COLOR
}