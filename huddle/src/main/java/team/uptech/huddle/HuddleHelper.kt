package team.uptech.huddle

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.SpannableString
import android.view.View
import android.widget.TextView
import androidx.annotation.RestrictTo
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.basic_dialog_layout.*
import team.uptech.huddle.core.parameters.Parameters
import team.uptech.huddle.model.ContentType
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.Constants.DEFAULT_FONT
import team.uptech.huddle.util.Constants.NO_IMAGE_RES
import team.uptech.huddle.util.Constants.NO_SIZE
import team.uptech.huddle.util.FixedLinkMovementMethod
import team.uptech.huddle.util.extension.*


/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class HuddleHelper(private val view: Huddle, private val parameters: Parameters) {

  internal fun bindActionListeners() {
    with(parameters) {
      setupActionPositiveListener()
      setupActionNegativeListener()
    }
  }

  private fun Parameters.setupActionPositiveListener() {
    setupOnClickListener(view.actionPositive, listeners.onPositiveClick)
  }

  private fun Parameters.setupActionNegativeListener() {
    setupOnClickListener(view.actionNegative, listeners.onNegativeClick)
  }

  private fun setupOnClickListener(target: View, listener: ((dialog: Huddle) -> Unit)?) {
    target.setOnClickListener {
      if (listener == null) view.dismissAllowingStateLoss()
      listener?.invoke(view)
    }
  }

  internal fun setParametersToView() {
    setupGuideLines()
    setupDialogAppearance()
    setupDialogTheme()
    setupDialogContent()
  }

  private fun setupGuideLines() {
    with(parameters) {
      setupBottomGuideLineMargin()
    }
  }

  private fun Parameters.setupBottomGuideLineMargin() {
    view.glBottomBarrier.setGuidelineEnd(
      when (dialog.ctaMode) {
        CtaMode.Single, CtaMode.None -> 24f.dp
        CtaMode.Duo -> 16f.dp
      }.toInt()
    )
  }

  private fun setupDialogAppearance() {
    with(parameters) {
      setupImageVisibility()
      setupActionPositiveVisibility()
      setupActionNegativeVisibility()
      setupDialogTitleVisibility()
      setupContentVisibility()
    }
  }

  private fun Parameters.setupImageVisibility() {
    val isResourceValid = image.resource != NO_IMAGE_RES
    val isBitmapValid = image.bitmap != null

    view.dialogImage.makeVisibleOrGone { isResourceValid || isBitmapValid }
  }

  private fun Parameters.setupActionPositiveVisibility() {
    view.actionPositive.makeVisibleOrGone { dialog.ctaMode is CtaMode.Duo || dialog.ctaMode is CtaMode.Single }
  }

  private fun Parameters.setupActionNegativeVisibility() {
    with(view.actionNegative) {
      when (dialog.ctaMode) {
        CtaMode.Single, CtaMode.None -> makeGone()
        CtaMode.Duo -> makeVisible()
      }
    }
  }

  private fun Parameters.setupDialogTitleVisibility() {
    view.dialogTitle.makeVisibleOrGone { texts.title.isNotBlank() }
  }

  private fun Parameters.setupContentVisibility() {
    when (dialog.contentType) {
      ContentType.ScrollView -> {
        view.gRecyclerViewContent.makeGone()
        view.scrollableContent.makeVisibleOrGone { (texts.message.isNotBlank() || texts.messageSpan.isNotBlank()) }
      }
      ContentType.RecyclerView -> {
        view.gRecyclerViewContent.makeVisible()
        view.scrollableContent.makeGone()
      }
      ContentType.PictureOnly -> {
        view.gRecyclerViewContent.makeGone()
        view.scrollableContent.makeGone()
      }
    }
  }

  private fun setupDialogTheme() {
    with(parameters) {
      setupImageColor()
      setupProgressColor()
      setupTitleColor()
      setupMessageColor()
      setupPositiveCtaColor()
      setupNegativeCtaColor()
      setupPositiveCtaRippleColor()
      setupNegativeCtaRippleColor()
      setupFonts()
    }
  }

  private fun Parameters.setupImageColor() {
    view.requireContext().getColorIfNotDefault(colors.imageTint) {
      view.dialogImage.setColorFilter(it)
    }
  }

  private fun Parameters.setupProgressColor() {
    view.requireContext().getColorIfNotDefault(colors.progress) {
      view.actionPositiveProgress.indeterminateDrawable.colorFilter = PorterDuffColorFilter(it, PorterDuff.Mode.SRC_IN)
    }
  }

  private fun Parameters.setupTitleColor() {
    setupTextColor(view.dialogTitle, colors.title)
  }

  private fun Parameters.setupMessageColor() {
    setupTextColor(view.dialogMessage, colors.message)
  }

  private fun Parameters.setupPositiveCtaColor() {
    if (dialog.ctaMode is CtaMode.None) return

    setupTextColor(view.actionPositive, colors.positiveCtaText)
    setupCtaBackground(view.actionPositive, colors.positiveCtaBackground, isSecondaryCTA = false)
  }

  private fun Parameters.setupNegativeCtaColor() {
    if (dialog.ctaMode is CtaMode.None || dialog.ctaMode is CtaMode.Single) return

    setupTextColor(view.actionNegative, colors.negativeCtaText)
    setupCtaBackground(view.actionNegative, colors.negativeCtaBackground, isSecondaryCTA = true)
  }

  private fun Parameters.setupPositiveCtaRippleColor() {
    if (dialog.ctaMode is CtaMode.None) return

    setupCtaRippleColor(view.actionPositive, colors.positiveCtaRipple)
  }

  private fun Parameters.setupNegativeCtaRippleColor() {
    if (dialog.ctaMode is CtaMode.None || dialog.ctaMode is CtaMode.Single) return

    setupCtaRippleColor(view.actionNegative, colors.negativeCtaRipple)
  }

  private fun setupCtaRippleColor(view: MaterialButton, colorRes: Int) {
    view.context.getColorIfNotDefault(colorRes) { view.rippleColor = ColorStateList.valueOf(it) }
  }

  private fun setupTextColor(view: TextView, colorRes: Int) {
    view.context.getColorIfNotDefault(colorRes) { view.setTextColor(it) }
  }

  private fun Parameters.setupFonts() {
    setupFont(view.dialogTitle, fonts.title)
    setupFont(view.dialogMessage, fonts.message)
    setupFont(view.actionPositive, fonts.cta)
    setupFont(view.actionNegative, fonts.cta)
  }

  private fun setupFont(view: TextView, fontRes: Int) {
    if (fontRes != DEFAULT_FONT) view.typeface = view.context.getFont(fontRes)
  }

  private fun setupCtaBackground(view: MaterialButton, colorRes: Int, isSecondaryCTA: Boolean) {
    with(view) {
      context.getColorIfNotDefault(colorRes) {
        val preparedColor = ColorStateList.valueOf(it)

        if (isSecondaryCTA) strokeColor = preparedColor
        else backgroundTintList = preparedColor
      }
    }
  }

  private fun setupDialogContent() {
    with(parameters) {
      setupImageContent()
      setupTitleContent()
      setupMessageContent()
      setupActionPositiveContent()
      setupActionNegativeContent()
    }
  }

  private fun Parameters.setupImageContent() {
    val isResourceValid = image.resource != NO_IMAGE_RES
    val isBitmapValid = image.bitmap != null

    if (!isResourceValid && !isBitmapValid) return

    with(view.dialogImage) {
      scaleType = image.scaleType

      if (isResourceValid) setImageResource(image.resource)
      else if (isBitmapValid) setImageBitmap(image.bitmap)

      if (image.width != NO_SIZE) setWidth(image.width)
      if (image.height != NO_SIZE) setHeight(image.height)

      requestLayout()
    }
  }

  private fun Parameters.setupTitleContent() {
    setupViewText(view.dialogTitle, texts.title, spanString = null, stringRes = null)
  }

  private fun Parameters.setupMessageContent() {
    setupViewText(view.dialogMessage, texts.message, texts.messageSpan, stringRes = null)
  }

  internal fun setupPositiveCtaContent() {
    parameters.setupActionPositiveContent()
  }

  private fun Parameters.setupActionPositiveContent() {
    if (dialog.ctaMode is CtaMode.None) return

    setupViewText(view.actionPositive, texts.positiveCtaText, spanString = null, stringRes = R.string.action_positive)
  }

  private fun Parameters.setupActionNegativeContent() {
    if (dialog.ctaMode is CtaMode.None || dialog.ctaMode is CtaMode.Single) return

    setupViewText(view.actionNegative, texts.negativeCtaText, spanString = null, stringRes = R.string.action_negative)
  }

  internal fun setupViewText(view: TextView, text: String?, spanString: SpannableString?, stringRes: Int?) {
    if (!spanString.isNullOrBlank()) view.movementMethod = FixedLinkMovementMethod()

    view.text = when {
      !text.isNullOrBlank() -> text
      !spanString.isNullOrBlank() -> spanString
      stringRes != null -> view.context.getString(stringRes)
      else -> return
    }
  }
}