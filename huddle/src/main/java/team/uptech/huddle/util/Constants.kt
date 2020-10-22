package team.uptech.huddle.util

import androidx.annotation.RestrictTo
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import team.uptech.huddle.util.extension.dp


@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
object Constants {

  /**
   * TODO: add docs
   */
  const val DEFAULT_COLOR = -1

  /**
   * TODO: add docs
   */
  const val NO_IMAGE_RES = -1

  /**
   * TODO: add docs
   */
  const val NO_SIZE = -1

  /**
   * TODO: add docs
   */
  val DEFAULT_DIALOG_SHAPE = ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, 8f.dp)

  /**
   * TODO: add docs
   */
  const val DEFAULT_FONT = -1

  /**
   * Use in conjunction with `@Deprecated` annotation
   * for all methods/objects/classes/etc. informs that in further PRs it will be removed.
   *
   * @hide
   */
  const val MARK_REMOVE = "deprecate-mark_to-be-removed"

  /**
   * Use in conjunction with `@Deprecated` annotation
   * for all methods/objects/classes/etc. informs that there are no usages across the project.
   * Should be removed in further PRs.
   *
   * @hide
   */
  const val NO_USAGE = "deprecate-mark_no-usage"

  /**
   * Use in conjunction with `@Deprecated` annotation
   * for all methods/objects/classes/etc. informs that in further PRs will be replaced with a new API
   * or you should not use it anywhere in the project.
   *
   * The new API should be described in `replaceWith` variable.
   * Example: `@Deprecated(REPLACE, replaceWith = ReplaceWith("TheNewApiClass.method()"))`
   *
   * @hide
   */
  const val REPLACE = "deprecate-mark_replace-api"

  /**
   * Use in conjunction with `@Deprecated` annotation
   * for all methods/objects/classes/etc. informs that in further PRs will be replaced after some changes applied.
   * Should be removed in further PRs.
   *
   * @hide
   */
  const val DELAYED_REFACTORING = "deprecate-mark_delayed-refactoring"
}