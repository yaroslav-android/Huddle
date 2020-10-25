package team.uptech.huddle.model

import kotlinx.serialization.Serializable
import team.uptech.huddle.model.ContentType.*

/**
 * The class [ContentType] determines how a dialog will display a content.
 *
 * @see ScrollView
 * @see RecyclerView
 * @see PictureOnly
 */
@Serializable
sealed class ContentType {

  /** Makes the dialog's message scrollable using a `ScrollView` */
  @Serializable
  object ScrollView : ContentType()

  /** Makes the dialog's message scrollable using a `RecyclerView` */
  @Serializable
  object RecyclerView : ContentType()

  /** Hides all views except the `ImageView` */
  @Serializable
  object PictureOnly : ContentType()
}