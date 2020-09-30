package team.uptech.huddle.builder

import team.uptech.huddle.core.BaseBuilder
import team.uptech.huddle.util.ElementMarker
import team.uptech.huddle.util.RootMarker


/**
 * DSL Builder for TODO: add docs
 *
 * Allows to build a dialog with single or dual buttons
 *
 */
@RootMarker
open class Builder : BaseBuilder() {
  val progress: ProgressBuilder = ProgressBuilder()
  val image: ImageBuilder = ImageBuilder()
  val positiveCTA: CtaBuilder = CtaBuilder()
  val negativeCTA: CtaBuilder = CtaBuilder()

  @ElementMarker
  fun progress(init: ProgressBuilder.() -> Unit) {
    progress.apply(init)
  }

  @ElementMarker
  fun image(init: ImageBuilder.() -> Unit) {
    image.apply(init)
  }

  @ElementMarker
  fun positiveCTA(init: CtaBuilder.() -> Unit) {
    positiveCTA.apply(init)
  }

  @ElementMarker
  fun negativeCTA(init: CtaBuilder.() -> Unit) {
    negativeCTA.apply(init)
  }
}