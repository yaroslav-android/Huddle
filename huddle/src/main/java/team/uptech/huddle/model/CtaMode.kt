package team.uptech.huddle.model


sealed class CtaMode {
  object Single : CtaMode()
  object Duo : CtaMode()
  object None : CtaMode()
}