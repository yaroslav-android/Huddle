package team.uptech.huddle.core

import android.os.Bundle
import android.view.Window
import android.widget.ScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import team.uptech.huddle.Huddle
import team.uptech.huddle.builder.ColorBuilder
import team.uptech.huddle.builder.ContentBuilder
import team.uptech.huddle.builder.FontBuilder
import team.uptech.huddle.model.ContentType
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.Constants
import team.uptech.huddle.util.dsl.DialogMarker
import team.uptech.huddle.util.dsl.ElementMarker


/**
 * Base DSL Builder for [BaseDialog].
 *
 * This class is responsible for primary dialog parameters such as width, dim, shape, cancellation on touch outside, etc.
 *
 * @see ColorBuilder
 * @see ContentBuilder
 * @see FontBuilder
 */
@DialogMarker
abstract class BaseBuilder {

  /**
   * The dialog's [Bundle] arguments.
   * Designed for [BaseDialog] or [Huddle] derived dialog classes.
   */
  var args = Bundle()

  /**
   * Content DSL builder for title, message and buttons.
   * @see ContentBuilder
   * @see ColorBuilder
   */
  val content: ContentBuilder = ContentBuilder()

  /**
   * Font DSL builder for title, message and buttons.
   * @see FontBuilder
   */
  val font: FontBuilder = FontBuilder()

  /**
   * The width of the dialog measured in percentage. It can consume values in range from 40% to 100%
   *
   * By default it takes 90% of the screen width.
   */
  var widthPercentage: Int = 90

  /**
   * If true than a user can dismiss the dialog by touch outside action, false otherwise.
   *
   * By default the dialog is not cancellable on touch outside.
   */
  var isCancelableOnTouchOutside: Boolean = false

  /**
   * If true than under the dialog will be displayed the dim, false otherwise.
   *
   * By default the dialog is showing dim under the background.
   */
  var enableDim: Boolean = true

  /**
   * The value that controls the dim amount. It can consume values in range from 0.0f to 1.0f.
   *
   * By default the dialog is showing dim under the background.
   */
  var dimValue: Float = 0.25f

  /**
   * The value that controls the dialog's mode.
   * Possible dialog setups:
   * 1. One button
   * 2. Two buttons
   * 3. Buttons hidden
   *
   * By default the dialog is showing only single button.
   */
  var ctaMode: CtaMode = CtaMode.Single

  /**
   * The value that controls the dialog's content scroll type.
   * Possible dialog setups:
   * 1. [ScrollView] as content scroll handler
   * 2. [RecyclerView] as content scroll handler
   * 3. Picture only as content
   *
   * By default the dialog is using [ScrollView].
   */
  var contentType: ContentType = ContentType.ScrollView

  /**
   * The shape of the dialog. Supports [MaterialShapeDrawable].
   * Setting the background to null will make the window be opaque.
   *
   * @see ShapeAppearanceModel.Builder
   * @see Window.setBackgroundDrawable
   */
  var shape: ShapeAppearanceModel.Builder? = Constants.DEFAULT_DIALOG_SHAPE

  /**
   * The color resource for the dialog's [shape].
   *
   * By default the dialog is using system [android.R.attr.colorBackground].
   */
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