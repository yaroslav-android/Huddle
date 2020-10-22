package team.uptech.huddle.model

import kotlinx.serialization.Serializable

@Serializable
sealed class ContentType {
  @Serializable
  object ScrollView : ContentType()
  @Serializable
  object RecyclerView : ContentType()
  @Serializable
  object PictureOnly : ContentType()
}