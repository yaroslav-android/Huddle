package team.uptech.huddle.core

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import team.uptech.huddle.R
import team.uptech.huddle.core.parameters.Parameters
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.extension.getColorIfNotDefaultWithFallback
import team.uptech.huddle.util.extension.getThemeColor
import team.uptech.huddle.util.extension.setWidthRelativeToParent


abstract class BaseDialog : DialogFragment(), DialogInterface.OnKeyListener {

  protected val parameters = Parameters()

  /**
   * The method server for bind views from layout for setup listeners
   */
  protected abstract fun bindViews(view: View)

  /**
   * The method servers for applying [parameters][Parameters] after [bindViews]
   *
   * @see Parameters
   */
  protected abstract fun applySettings()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NO_FRAME, R.style.BaseDialogStyle)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return super.onCreateDialog(savedInstanceState).apply {
      window?.requestFeature(Window.FEATURE_NO_TITLE)

      val drawable = createDialogShape()
      window?.setBackgroundDrawable(drawable)

      if (parameters.dialog.enableDim) window?.setDimAmount(parameters.dialog.dimValue)
      else window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

      setCanceledOnTouchOutside(parameters.dialog.isCancelableOnTouchOutside || parameters.dialog.ctaMode is CtaMode.None)
      setOnKeyListener(this@BaseDialog)
    }
  }

  /**
   * TODO: add docs
   */
  protected open fun createDialogShape(): MaterialShapeDrawable? {
    val shape = parameters.dialog.shape ?: return null
    return MaterialShapeDrawable(shape.build()).applyTint()
  }

  private fun MaterialShapeDrawable.applyTint(): MaterialShapeDrawable {
    val handleError: (exception: Throwable) -> Unit = { error ->
      Log.e(TAG, "Failed to resolve colorBackground.", error)
    }

    try {
      requireContext().getColorIfNotDefaultWithFallback(
        parameters.colors.shapeTint,
        action = { setTint(it) },
        fallback = { setTint(requireContext().getThemeColor(android.R.attr.colorBackground)) }
      )
    } catch (exception: Resources.NotFoundException) {
      handleError(exception)
    } catch (exception: UnsupportedOperationException) {
      handleError(exception)
    }

    return this
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    bindViews(view)
    applySettings()
  }

  override fun onStart() {
    dialog?.setWidthRelativeToParent(activity, parameters.dialog.widthPercentage)
    super.onStart()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    val bitmap = parameters.image.bitmap
    if (bitmap != null) outState.putParcelable(BITMAP_KEY, bitmap)

    outState.putString(PARAMETERS_KEY, Json.encodeToString(parameters))
    super.onSaveInstanceState(outState)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    if (savedInstanceState != null) {
      if (savedInstanceState.containsKey(BITMAP_KEY)) {
        parameters.image.bitmap = savedInstanceState.getParcelable(BITMAP_KEY)
      }

      savedInstanceState.getString(PARAMETERS_KEY)?.run { parameters.restore(Json.decodeFromString(this)) }
    }
  }

  override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
      return !parameters.dialog.isCancelableOnTouchOutside || parameters.dialog.ctaMode !is CtaMode.None
    }

    return false
  }

  /**
   * The method allows to provide data from DSL to the [parameters][Parameters]
   *
   * @see Parameters
   * @see DialogBuilder
   */
  open fun importSettings(builder: BaseBuilder): BaseDialog = apply {
    arguments = builder.args
    parameters.importFrom(builder)
  }

  companion object {
    private const val TAG = "BaseDialog"
    private const val BITMAP_KEY = "dialog-image-bitmap_key"
    private const val PARAMETERS_KEY = "dialog-parameters_key"
  }
}
