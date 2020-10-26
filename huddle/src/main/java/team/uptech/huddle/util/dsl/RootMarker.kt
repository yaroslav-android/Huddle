package team.uptech.huddle.util.dsl


/**
 * Does not allow to invoke every available implicit receiver inside a lambda same DSL.
 */
@DslMarker
annotation class RootMarker