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
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import team.uptech.huddle.R
import team.uptech.huddle.core.parameters.Parameters
import team.uptech.huddle.util.extension.dp
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

      setCanceledOnTouchOutside(parameters.dialog.isCancelableOnTouchOutside)
      setOnKeyListener(this@BaseDialog)
    }
  }

  /**
   * TODO: add docs
   */
  protected open fun createDialogShape(): MaterialShapeDrawable {

    // TODO: add MaterialShapeDrawable customisation support
    val shape = ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, 8f.dp)
    val drawable = MaterialShapeDrawable(shape.build())

    val handleError: (exception: Throwable) -> Unit = { error ->
      Log.e(TAG, "Failed to resolve colorBackground.", error)
    }

    try {
      drawable.setTint(requireContext().getThemeColor(android.R.attr.colorBackground))
    } catch (exception: Resources.NotFoundException) {
      handleError(exception)
    } catch (exception: UnsupportedOperationException) {
      handleError(exception)
    }

    return drawable
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    bindViews(view)
    applySettings()
  }

  override fun onStart() {
    super.onStart()

    dialog?.setWidthRelativeToParent(activity, parameters.dialog.widthPercentage)
  }

  override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
      return !parameters.dialog.isCancelableOnTouchOutside
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
  }
}
