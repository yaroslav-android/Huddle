package team.uptech.huddle.util

import android.text.Layout
import android.text.Selection
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.widget.TextView
import androidx.annotation.RestrictTo


/**
 *
 * The onTouchEvent() implementation of the LinkMovementMethod requests all ClickableSpan instances
 * from the SpannableStringBuilder with getSpans() that filters its spans basically with `instanceof`.
 *
 * But problem is if you use CharacterStyle.wrap() your span is no longer a ClickableSpan instance,
 * but a CharacterStyle.Passthrough instance instead.
 *
 * Therefore getSpans() does not return it anymore and it's no longer clickable.
 *
 * https://stackoverflow.com/a/34698757/7750432
 *
 * @author Floern
 * @see LinkMovementMethod
 * @hide
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class FixedLinkMovementMethod : LinkMovementMethod() {
  override fun onTouchEvent(widget: TextView, buffer: Spannable, event: MotionEvent): Boolean {
    val action: Int = event.action

    if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
      val layout: Layout = widget.layout

      var x = event.x
      var y = event.y.toInt()

      x -= widget.totalPaddingLeft
      y -= widget.totalPaddingTop
      x += widget.scrollX
      y += widget.scrollY

      val line: Int = layout.getLineForVertical(y)
      val off: Int = layout.getOffsetForHorizontal(line, x)

      val candidates: Array<CharacterStyle> = buffer.getSpans(off, off, CharacterStyle::class.java)
      var clickableSpan: ClickableSpan? = null

      for (characterStyle in candidates) {
        if (characterStyle.underlying is ClickableSpan) {
          clickableSpan = characterStyle.underlying as ClickableSpan
          break
        }
      }

      if (clickableSpan != null) {
        if (action == MotionEvent.ACTION_UP) {
          clickableSpan.onClick(widget)
        } else if (action == MotionEvent.ACTION_DOWN) {
          Selection.setSelection(
            buffer,
            buffer.getSpanStart(clickableSpan),
            buffer.getSpanEnd(clickableSpan)
          )
        }
        return true
      } else {
        Selection.removeSelection(buffer)
      }
    }

    return super.onTouchEvent(widget, buffer, event)
  }
}