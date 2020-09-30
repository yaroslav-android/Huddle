package team.uptech.huddle.core

import android.os.Bundle
import team.uptech.huddle.builder.DefaultBuilder
import team.uptech.huddle.util.ElementMarker
import team.uptech.huddle.util.RootMarker


/**
 * Base DSL Builder for TODO: add docs
 *
 * @property args the Bundle arguments for the dialog
 * @property dialog the base parameters for the dialog
 */
@RootMarker
abstract class BaseBuilder {
  var args = Bundle()
  val dialog: DefaultBuilder = DefaultBuilder()

  @ElementMarker
  fun args(init: Bundle.() -> Unit = {}) {
    if (!args.isEmpty) args.clear()
    args = args.apply(init)
  }

  @ElementMarker
  fun dialog(init: DefaultBuilder.() -> Unit) {
    dialog.apply(init)
  }
}