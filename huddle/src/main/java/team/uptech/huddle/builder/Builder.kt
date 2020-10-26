package team.uptech.huddle.builder

import team.uptech.huddle.Huddle
import team.uptech.huddle.core.BaseBuilder
import team.uptech.huddle.util.dsl.ElementMarker


/**
 * The DSL Builder for [Huddle]
 *
 * This class is responsible for [Huddle] dialog setup.
 *
 * @see CtaBuilder
 * @see ImageBuilder
 * @see ProgressBuilder
 */
@ElementMarker
open class Builder : BaseBuilder() {

  /**
   * Progress DSL builder for the primary button.
   */
  val progress: ProgressBuilder = ProgressBuilder()

  /**
   * Progress DSL builder for the primary button.
   */
  val image: ImageBuilder = ImageBuilder()

  /**
   * CTA DSL builder for the primary button.
   */
  val positiveCTA: CtaBuilder = CtaBuilder()

  /**
   * CTA DSL builder for the secondary button.
   */
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