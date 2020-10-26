package team.uptech.huddle

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RestrictTo
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.basic_dialog_layout.*
import team.uptech.huddle.builder.Builder
import team.uptech.huddle.core.BaseBuilder
import team.uptech.huddle.core.BaseDialog
import team.uptech.huddle.util.extension.erase
import team.uptech.huddle.util.extension.makeEnabledOrDisabled
import team.uptech.huddle.util.extension.makeVisibleOrGone


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

  protected var helper: HuddleHelper? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.basic_dialog_layout, container, false)
  }

  override fun importSettings(builder: BaseBuilder) = apply {
    super.importSettings(builder)

    if (builder is Builder) {
      parameters.importFrom(builder)
    }

    helper = HuddleHelper(this@Huddle, parameters)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    if (helper == null) helper = HuddleHelper(this@Huddle, parameters)

    helper?.bindActionListeners()
    helper?.setParametersToView()
  }

  override fun bindViews(view: View) {
    helper?.bindActionListeners()
  }

  override fun applySettings() {
    helper?.setParametersToView()
  }

  /**
   * @throws IllegalStateException if [title view][MaterialTextView] isn't form the base layout or `null`.
   */
  open fun updateTitle(title: String) {
    updateTextFor(dialogTitle, title, null)
  }

  /**
   * @throws IllegalStateException if [message view][MaterialTextView] isn't form the base layout or `null`.
   */
  open fun updateMessage(message: String?, messageSpannableString: SpannableString?) {
    updateTextFor(dialogMessage, message, messageSpannableString)
  }

  /** @hide */
  @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
  private fun updateTextFor(view: MaterialTextView?, text: String?, textSpannableString: SpannableString?) {
    val exception = "You are trying to modify the MaterialTextView that is not from the default layout. " +
      "Consider overriding this method."

    val verifiedView = checkNotNull(view) { exception }
    helper?.setupViewText(verifiedView, text, textSpannableString, stringRes = null)
  }

  /**
   * Change the visibility of the positive action progress.
   *
   * @param shouldShow true if the progress should be showed, false otherwise.
   */
  open fun showProgress(shouldShow: Boolean) {
    if (!isResumed) return

    actionPositiveProgress.makeVisibleOrGone { shouldShow }

    with(actionPositive) {
      makeEnabledOrDisabled { !shouldShow }

      if (shouldShow) erase()
      else helper?.setupPositiveCtaContent()
    }
  }
}