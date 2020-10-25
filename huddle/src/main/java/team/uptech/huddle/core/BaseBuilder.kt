package team.uptech.huddle.core

import android.os.Bundle
import com.google.android.material.shape.ShapeAppearanceModel
import team.uptech.huddle.builder.ContentBuilder
import team.uptech.huddle.builder.FontBuilder
import team.uptech.huddle.model.ContentType
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.Constants
import team.uptech.huddle.util.dsl.DialogMarker
import team.uptech.huddle.util.dsl.ElementMarker
import team.uptech.huddle.util.dsl.RootMarker


/**
 * Base DSL Builder for TODO: add docs
 *
 * @property args the Bundle arguments for the dialog
 * @property dialog the base parameters for the dialog
 */
@DialogMarker
abstract class BaseBuilder {
  var args = Bundle()

  val content: ContentBuilder = ContentBuilder()
  val font: FontBuilder = FontBuilder()

  var widthPercentage: Int = 90
  var isCancelableOnTouchOutside: Boolean = false
  var enableDim: Boolean = true
  var dimValue: Float = 0.25f
  var ctaMode: CtaMode = CtaMode.Single
  var contentType: ContentType = ContentType.ScrollView
  var shape: ShapeAppearanceModel.Builder? = Constants.DEFAULT_DIALOG_SHAPE
  var shapeTint: Int = Constants.DEFAULT_COLOR

  @ElementMarker
  fun content(init: ContentBuilder.() -> Unit) {
    content.apply(init)
  }

  @ElementMarker
  fun font(init: FontBuilder.() -> Unit) {
    font.apply(init)
  }

  @ElementMarker
  fun args(init: Bundle.() -> Unit = {}) {
    if (!args.isEmpty) args.clear()
    args = args.apply(init)
  }
}