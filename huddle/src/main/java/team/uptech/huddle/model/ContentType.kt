package team.uptech.huddle.model


sealed class ContentType {
  object ScrollView : ContentType()
  object RecyclerView : ContentType()
  object PictureOnly : ContentType()
}