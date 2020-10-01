package team.uptech.huddle.widget

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.RestrictTo
import androidx.recyclerview.widget.RecyclerView
import team.uptech.huddle.R


/** @hide */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class RestrictedHeightRecyclerView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

  private var maxHeight = 0

  init {
    if (!isInEditMode) {
      if (attrs != null) {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.RestrictedHeightRecyclerView)
        maxHeight = styledAttrs.getDimensionPixelSize(
          R.styleable.RestrictedHeightRecyclerView_restrictedHeight,
          resources.getDimensionPixelSize(R.dimen.dialog_default_content_height)
        )

        styledAttrs.recycle()
      }
    }
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST))
  }
}