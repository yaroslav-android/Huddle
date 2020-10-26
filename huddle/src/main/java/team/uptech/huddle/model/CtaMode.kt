package team.uptech.huddle.model

import kotlinx.serialization.Serializable
import team.uptech.huddle.model.CtaMode.*

/**
 * The class [CtaMode] controls buttons visibility.
 *
 * @see Single
 * @see Duo
 * @see None
 */
@Serializable
sealed class CtaMode {

  /** Makes visible only primary button */
  @Serializable
  object Single : CtaMode()

  /** Makes visible both primary and secondary buttons */
  @Serializable
  object Duo : CtaMode()

  /** Removes both primary and secondary buttons */
  @Serializable
  object None : CtaMode()
}