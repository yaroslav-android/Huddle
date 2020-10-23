package team.uptech.huddle.core.parameters

import androidx.annotation.RestrictTo
import kotlinx.serialization.Serializable
import team.uptech.huddle.builder.Builder
import team.uptech.huddle.core.BaseBuilder


/** @hide */
@Serializable
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
    colors.positiveCtaText = builder.positiveCTA.textColor
    colors.negativeCtaText = builder.negativeCTA.textColor
    colors.positiveCtaBackground = builder.positiveCTA.backgroundColor
    colors.negativeCtaBackground = builder.negativeCTA.backgroundColor
    colors.positiveCtaRipple = builder.positiveCTA.rippleColor
    colors.negativeCtaRipple = builder.negativeCTA.rippleColor

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

    fonts.titleFont = builder.dialog.font.title
    fonts.messageFont = builder.dialog.font.message
    fonts.ctaFont = builder.dialog.font.cta
  }

  fun restore(restoredParameters: Parameters) {
    image.resource = restoredParameters.image.resource
    image.bitmap = restoredParameters.image.bitmap
    image.width = restoredParameters.image.width
    image.height = restoredParameters.image.height
    image.scaleType = restoredParameters.image.scaleType

    colors.imageTint = restoredParameters.colors.imageTint
    colors.progress = restoredParameters.colors.progress
    colors.title = restoredParameters.colors.title
    colors.message = restoredParameters.colors.message
    colors.positiveCtaText = restoredParameters.colors.positiveCtaText
    colors.negativeCtaText = restoredParameters.colors.negativeCtaText
    colors.positiveCtaBackground = restoredParameters.colors.positiveCtaBackground
    colors.negativeCtaBackground = restoredParameters.colors.negativeCtaBackground
    colors.positiveCtaRipple = restoredParameters.colors.positiveCtaRipple
    colors.negativeCtaRipple = restoredParameters.colors.negativeCtaRipple

    texts.positiveCtaText = restoredParameters.texts.positiveCtaText
    texts.negativeCtaText = restoredParameters.texts.negativeCtaText

    listeners.onPositiveClick = restoredParameters.listeners.onPositiveClick
    listeners.onNegativeClick = restoredParameters.listeners.onNegativeClick

    dialog.widthPercentage = restoredParameters.dialog.widthPercentage
    dialog.isCancelableOnTouchOutside = restoredParameters.dialog.isCancelableOnTouchOutside
    dialog.enableDim = restoredParameters.dialog.enableDim
    dialog.dimValue = restoredParameters.dialog.dimValue
    dialog.ctaMode = restoredParameters.dialog.ctaMode
    dialog.contentType = restoredParameters.dialog.contentType
    dialog.shape = restoredParameters.dialog.shape

    colors.shapeTint = restoredParameters.colors.shapeTint

    texts.title = restoredParameters.texts.title
    texts.message = restoredParameters.texts.message
    texts.messageSpan = restoredParameters.texts.messageSpan

    fonts.titleFont = restoredParameters.fonts.titleFont
    fonts.messageFont = restoredParameters.fonts.messageFont
    fonts.ctaFont = restoredParameters.fonts.ctaFont
  }
}