package team.uptech.huddle.builder

import com.google.android.material.shape.ShapeAppearanceModel
import team.uptech.huddle.model.ContentType
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.Constants
import team.uptech.huddle.util.Constants.DEFAULT_DIALOG_SHAPE
import team.uptech.huddle.util.ElementMarker


/**
 * Base DSL Builder for TODO: add docs
 *
 * @property widthPercentage the integer value from `40` to `100` that represents the width of the dialog. Default: 90%
 * @property isCancelableOnTouchOutside if `true` a use allowed dismiss the dialog on touch outside. Default: true
 * @property enableDim the boolean value that represents dim under the dialog. Default: true
 * @property dimValue the float value from `0` to `1` that represents dim amount under the dialog. Default: 0.25f
 * @property ctaMode the mode that determines whether secondary button will appear or not when the dialog is shown. Default: CtaMode.Single
 * @property contentType the type of content holder. It might be `ScrollView` or `RecyclerView` when the dialog is shown. Default: ContentType.ScrollView
 */
@ElementMarker
open class DefaultBuilder {
  val content: ContentBuilder = ContentBuilder()
  val font: FontBuilder = FontBuilder()

  var widthPercentage: Int = 90
  var isCancelableOnTouchOutside: Boolean = false
  var enableDim: Boolean = true
  var dimValue: Float = 0.25f
  var ctaMode: CtaMode = CtaMode.Single
  var contentType: ContentType = ContentType.ScrollView
  var shape: ShapeAppearanceModel? = DEFAULT_DIALOG_SHAPE
  var shapeTint: Int = Constants.DEFAULT_COLOR

  @ElementMarker
  fun content(init: ContentBuilder.() -> Unit) {
    content.apply(init)
  }

  @ElementMarker
  fun font(init: FontBuilder.() -> Unit) {
    font.apply(init)
  }
}