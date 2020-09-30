package team.uptech.huddle

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.basic_dialog_layout.*
import team.uptech.huddle.builder.Builder
import team.uptech.huddle.core.BaseBuilder
import team.uptech.huddle.core.BaseDialog
import team.uptech.huddle.model.ContentType
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.Constants.NO_IMAGE_RES
import team.uptech.huddle.util.FixedLinkMovementMethod
import team.uptech.huddle.util.extension.*


/**
 * Basic implementation of the dialog for the app. Allows to show with single or dual buttons.
 * There are no required properties for [Builder].
 *
 * Careful: if you will provide your own click handlers you have to handle dismiss manually.
 *
 * @see Builder
 * @see Parameters
 */
open class Huddle : BaseDialog() {

  override fun importSettings(builder: BaseBuilder) = apply {
    super.importSettings(builder)

    if (builder is Builder) {
      parameters.importFrom(builder)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.basic_dialog_layout, container, false)
  }

  override fun bindViews(view: View) {
    actionPositive.setOnClickListener {
      with(parameters) {
        if (listeners.onPositiveClick == null) dismissAllowingStateLoss()
        listeners.onPositiveClick?.invoke(this@Huddle)
      }
    }

    actionNegative.setOnClickListener {
      with(parameters) {
        if (listeners.onNegativeClick == null) dismissAllowingStateLoss()
        listeners.onNegativeClick?.invoke(this@Huddle)
      }
    }
  }

  open fun showProgress(shouldShow: Boolean) {
    if (!isResumed) return

    actionPositiveProgress.makeVisibleOrGone { shouldShow }

    if (shouldShow) {
      actionPositive.disable()
      actionPositive.erase()
    } else {
      actionPositive.enable()

      if (actionPositive.text.isNotBlank()) return

      actionPositive.text = parameters.texts.positiveCtaText
        .takeIf { it.isNotBlank() } ?: getString(R.string.action_positive)
    }
  }

  open fun updateTitle(title: String) {
    updateTextFor(dialogTitle, title, null)
  }

  open fun updateMessage(message: String?, messageSpannableString: SpannableString?) {
    updateTextFor(dialogMessage, message, messageSpannableString)
  }

  private fun updateTextFor(view: MaterialTextView?, text: String?, textSpannableString: SpannableString?) {
    val exception = "You are trying to modify the MaterialTextView that is not from the default layout. " +
      "Consider overriding this method."

    checkNotNull(view) { exception }
      .text = when {
      !text.isNullOrBlank() -> text
      !textSpannableString.isNullOrBlank() -> textSpannableString
      else -> return
    }
  }

  override fun applySettings() {
    setupDialogAppearance()
    setupProgress()
    setupTitle()
    setupMessage()
    setupContent()
    setupImage()
    setupActionPositive()
    setupActionNegative()
  }

  private fun setupDialogAppearance() {
    with(actionNegative) {
      when (parameters.dialog.ctaMode) {
        CtaMode.Single, CtaMode.None -> makeGone()
        CtaMode.Duo -> makeVisible()
      }
    }
  }

  private fun setupProgress() {
    requireContext().getColorIfNotDefault(parameters.colors.progress) {
      actionPositiveProgress.indeterminateDrawable.colorFilter = PorterDuffColorFilter(it, PorterDuff.Mode.SRC_IN)
    }
  }

  private fun setupTitle() {
    with(parameters) {
      dialogTitle.text = texts.title.takeIf { it.isNotBlank() } ?: ""
      dialogTitle.makeVisibleOrGone { texts.title.isNotBlank() }
    }
  }

  private fun setupMessage() {
    with(parameters) {
      if (texts.messageSpan.isNotBlank()) dialogMessage.movementMethod = FixedLinkMovementMethod()

      dialogMessage.text = texts.message
        .takeIf { it.isNotBlank() } ?: texts.messageSpan.takeIf { it.isNotBlank() } ?: ""
    }
  }

  private fun setupContent() {
    with(parameters) {
      when (dialog.contentType) {
        ContentType.ScrollView -> {
          gRecyclerViewContent.makeGone()
          scrollableContent.makeVisibleOrGone { (texts.message.isNotBlank() || texts.messageSpan.isNotBlank()) }
        }
        ContentType.RecyclerView -> {
          gRecyclerViewContent.makeVisible()
          scrollableContent.makeGone()
        }
        ContentType.PictureOnly -> {
          gRecyclerViewContent.makeGone()
          scrollableContent.makeGone()
        }
      }
    }
  }

  private fun setupImage() {
    with(parameters) {
      val isResourceValid = image.resource != NO_IMAGE_RES
      val isBitmapValid = image.bitmap != null

      with(dialogImage) {
        if (isResourceValid) setImageResource(image.resource)
        else if (isBitmapValid) setImageBitmap(image.bitmap)

        if (isResourceValid || isBitmapValid) {
          scaleType = image.scaleType

          if (image.width != 0) setWidth(image.width)
          if (image.height != 0) setHeight(image.height)

          requestLayout()
          makeVisible()
        }
      }
    }
  }

  private fun setupActionPositive() {
    with(parameters) {
      if (dialog.ctaMode is CtaMode.None) {
        actionPositive.makeGone()
        return@with
      }

      actionPositive.apply {
        text = texts.positiveCtaText.takeIf { it.isNotBlank() } ?: getString(R.string.action_positive)
        requireContext().getColorIfNotDefault(colors.positiveCtaText) { setTextColor(it) }
        requireContext().getColorIfNotDefault(colors.positiveCtaBackground) { setBackgroundColor(it) }
      }
    }
  }

  private fun setupActionNegative() {
    with(parameters) {
      if (dialog.ctaMode is CtaMode.None || dialog.ctaMode is CtaMode.Single) {
        actionNegative.makeGone()
        return@with
      }

      actionNegative.apply {
        text = texts.negativeCtaText.takeIf { it.isNotBlank() } ?: getString(R.string.action_negative)
        requireContext().getColorIfNotDefault(colors.negativeCtaText) { setTextColor(it) }
        requireContext().getColorIfNotDefault(colors.negativeCtaBackground) { strokeColor = ColorStateList.valueOf(it) }
      }
    }
  }
}