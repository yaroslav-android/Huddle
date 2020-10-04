package team.uptech.huddle.core.parameters

import androidx.annotation.RestrictTo
import team.uptech.huddle.builder.Builder
import team.uptech.huddle.core.BaseBuilder


/**
 * Properties from DSL Builder for TODO: add docs
 * @see DialogBuilder
 * @hide
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class Parameters {
  val dialog: Dialog = Dialog()
  val image: Dialog.Image = Dialog.Image()
  val texts: Dialog.Text = Dialog.Text()
  val colors: Dialog.Color = Dialog.Color()
  val fonts: Dialog.Font = Dialog.Font()
  val listeners: Dialog.Listener = Dialog.Listener()

  fun importFrom(builder: Builder) {
    image.resource = builder.image.resource
    image.bitmap = builder.image.bitmap
    image.width = builder.image.width
    image.height = builder.image.height
    image.scaleType = builder.image.scaleType

    colors.imageTint = builder.image.tint
    colors.progress = builder.progress.progressColorRes
    colors.title = builder.dialog.content.color.titleRes
    colors.message = builder.dialog.content.color.messageRes
    colors.positiveCtaText = builder.positiveCTA.textColorRes
    colors.negativeCtaText = builder.negativeCTA.textColorRes
    colors.positiveCtaBackground = builder.positiveCTA.backgroundColorRes
    colors.negativeCtaBackground = builder.negativeCTA.backgroundColorRes

    texts.positiveCtaText = builder.positiveCTA.text
    texts.negativeCtaText = builder.negativeCTA.text

    listeners.onPositiveClick = builder.positiveCTA.onClick
    listeners.onNegativeClick = builder.negativeCTA.onClick
  }

  fun importFrom(builder: BaseBuilder) {
    dialog.widthPercentage = builder.dialog.widthPercentage
    dialog.isCancelableOnTouchOutside = builder.dialog.isCancelableOnTouchOutside
    dialog.enableDim = builder.dialog.enableDim
    dialog.dimValue = builder.dialog.dimValue
    dialog.ctaMode = builder.dialog.ctaMode
    dialog.contentType = builder.dialog.contentType
    dialog.shape = builder.dialog.shape

    colors.shapeTint = builder.dialog.shapeTint

    texts.title = builder.dialog.content.title
    texts.message = builder.dialog.content.message
    texts.messageSpan = builder.dialog.content.messageSpan

    fonts.titleFont = builder.dialog.font.titleFont
    fonts.messageFont = builder.dialog.font.messageFont
    fonts.ctaFont = builder.dialog.font.ctaFont
  }
}