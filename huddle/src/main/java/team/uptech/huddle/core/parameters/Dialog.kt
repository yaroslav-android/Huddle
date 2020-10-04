package team.uptech.huddle.core.parameters

import android.graphics.Bitmap
import android.text.SpannableString
import android.widget.ImageView
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.annotation.RestrictTo
import com.google.android.material.shape.ShapeAppearanceModel
import team.uptech.huddle.Huddle
import team.uptech.huddle.model.ContentType
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.Constants
import team.uptech.huddle.util.Constants.DEFAULT_COLOR
import team.uptech.huddle.util.Constants.DEFAULT_FONT
import team.uptech.huddle.util.Constants.NO_IMAGE_RES
import team.uptech.huddle.util.Constants.NO_SIZE


/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class Dialog {
  var shape: ShapeAppearanceModel? = Constants.DEFAULT_DIALOG_SHAPE
  var ctaMode: CtaMode = CtaMode.Single
  var contentType: ContentType = ContentType.ScrollView
  var isCancelableOnTouchOutside: Boolean = false

  @IntRange(from = 40, to = 100)
  var widthPercentage: Int = 90

  @FloatRange(from = 0.0, to = 1.0)
  var dimValue: Float = 0.25f
  var enableDim: Boolean = true

  class Image {
    var resource: Int = NO_IMAGE_RES
    var bitmap: Bitmap? = null
    var width: Int = NO_SIZE
    var height: Int = NO_SIZE
    var scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER
  }

  class Text {
    var title: String = ""
    var message: String = ""
    var messageSpan: SpannableString = SpannableString("")

    var positiveCtaText: String = ""
    var negativeCtaText: String = ""
  }

  class Color {
    var shapeTint: Int = DEFAULT_COLOR
    var progress: Int = DEFAULT_COLOR
    var imageTint: Int = DEFAULT_COLOR

    var title: Int = DEFAULT_COLOR
    var message: Int = DEFAULT_COLOR

    var positiveCtaText: Int = DEFAULT_COLOR
    var negativeCtaText: Int = DEFAULT_COLOR

    var positiveCtaBackground: Int = DEFAULT_COLOR
    var negativeCtaBackground: Int = DEFAULT_COLOR
  }

  class Font {
    var titleFont: Int = DEFAULT_FONT
    var messageFont: Int = DEFAULT_FONT
    var ctaFont: Int = DEFAULT_FONT
  }

  class Listener {
    var onPositiveClick: ((dialog: Huddle) -> Unit)? = null
    var onNegativeClick: ((dialog: Huddle) -> Unit)? = null
  }
}