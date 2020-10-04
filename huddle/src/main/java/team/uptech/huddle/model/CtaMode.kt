package team.uptech.huddle.model

/**
 * The class [CtaMode] controls buttons visibility.
 *
 * @see Single
 * @see Duo
 * @see None
 */
sealed class CtaMode {

  /** Makes visible only primary button */
  object Single : CtaMode()

  /** Makes visible both primary and secondary buttons */
  object Duo : CtaMode()

  /** Removes both primary and secondary buttons */
  object None : CtaMode()
}