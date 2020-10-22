package team.uptech.huddle.model

import kotlinx.serialization.Serializable

@Serializable
sealed class CtaMode {
  @Serializable
  object Single : CtaMode()
  @Serializable
  object Duo : CtaMode()
  @Serializable
  object None : CtaMode()
}