package team.uptech.huddle.builder

import androidx.core.content.ContextCompat
import team.uptech.huddle.Huddle
import team.uptech.huddle.R
import team.uptech.huddle.util.Constants
import team.uptech.huddle.util.dsl.ElementMarker

/**
 * The DSL Builder for [Huddle].
 *
 * This is responsible for dialog's progress setup.
 */
@ElementMarker
class ProgressBuilder {

  /**
   * The color resource of progress circle on the primary button
   *
   * You should pass the resource [R.color] but not a result of the [ContextCompat.getColor]
   */
  var progressColor: Int = Constants.DEFAULT_COLOR
}