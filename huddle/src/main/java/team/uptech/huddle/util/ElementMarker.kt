package team.uptech.huddle.util

import androidx.annotation.RestrictTo


/**
 * Does not allow to invoke every available implicit receiver inside a lambda same DSL.
 * Root DSL should not be marked with this annotation.
 * @hide
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@DslMarker
annotation class ElementMarker