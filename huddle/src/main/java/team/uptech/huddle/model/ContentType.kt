package team.uptech.huddle.model

/**
 * The class [ContentType] determines how a dialog will display a content.
 *
 * @see ScrollView
 * @see RecyclerView
 * @see PictureOnly
 */
sealed class ContentType {

  /** Makes the dialog's message scrollable using a `ScrollView` */
  object ScrollView : ContentType()

  /** Makes the dialog's message scrollable using a `RecyclerView` */
  object RecyclerView : ContentType()

  /** Hides all views except the `ImageView` */
  object PictureOnly : ContentType()
}