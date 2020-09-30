package team.uptech.huddle.builder

import android.graphics.Bitmap
import android.widget.ImageView
import team.uptech.huddle.util.Constants.NO_IMAGE_RES
import team.uptech.huddle.util.ElementMarker


/**
 * DSL Builder for TODO: add docs
 *
 * Allows to build a dialog with single or dual buttons
 *
 * @property resource the drawable resource of image ([width] and [height] required)
 * @property width the integer value for the width of the dialog image
 * @property height the integer value for the height of the dialog image
 * @property scaleType the [ImageView.ScaleType] for the dialog image
 */
@ElementMarker
class ImageBuilder {
  var resource: Int = NO_IMAGE_RES
  var bitmap: Bitmap? = null
  var width: Int = 0
  var height: Int = 0
  var scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER
}