package team.uptech.huddle.util.dsl


/**
 * Does not allow to invoke every available implicit receiver inside a lambda same DSL.
 *
 * Root DSL should not be marked with this annotation.
 */
@DslMarker
annotation class DialogMarker