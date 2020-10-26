package team.uptech.huddle.builder

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.content.ContextCompat
import team.uptech.huddle.Huddle
import team.uptech.huddle.R
import team.uptech.huddle.util.Constants.DEFAULT_COLOR
import team.uptech.huddle.util.Constants.NO_IMAGE_RES
import team.uptech.huddle.util.Constants.NO_SIZE
import team.uptech.huddle.util.dsl.ElementMarker


/**
 * DSL Builder for [Huddle]
 *
 * This class is responsible for dialog's image setup.
 */
@ElementMarker
class ImageBuilder {

  /**
   * The drawable resource of image ([width] and [height] required)
   */
  var resource: Int = NO_IMAGE_RES

  /**
   * The bitmap for the dialog's image. The [resource] is primary value to be set in the [ImageView].
   * Make sure that you did not pass any value if you want to use [bitmap].
   */
  var bitmap: Bitmap? = null

  /**
   * The integer value for the width of the dialog's image.
   *
   * You should pass a calculated dp value but not just the desired dp value.
   */
  var width: Int = NO_SIZE

  /**
   * The integer value for the height of the dialog's image.
   *
   * You should pass a calculated dp value but not just the desired dp value.
   */
  var height: Int = NO_SIZE

  /**
   * Options for scaling the bounds of an image in the dialog.
   *
   * @see ImageView.ScaleType
   */
  var scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER

  /**
   * The color resource for the dialog's image.
   *
   * You should pass the resource [R.color] but not a result of the [ContextCompat.getColor]
   */
  var tint: Int = DEFAULT_COLOR
}