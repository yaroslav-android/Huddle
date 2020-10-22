package team.uptech.demo.huddle

import android.content.Context
import team.uptech.huddle.Huddle
import team.uptech.huddle.builder.Builder
import team.uptech.huddle.model.ContentType
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.extension.create
import team.uptech.huddle.util.extension.dp


fun Context.buildSimpleDuoCtaDialog() = Huddle().create<Builder> {
  dialog {
    ctaMode = CtaMode.Duo

    content {
      title = getString(R.string.app_name)
      message = "We analyse thousands of companies across many different sectors. " +
        "By cross-referencing data from 3rd party sources, " +
        "we are able to score each company based on how well they treat their employees, " +
        "their commitment to environmental improvements, and their ethical business practice.\nEach dollar you spend is impacting the world."
    }

    font {
      title = R.font.lato_bold
      message = R.font.lato_regular
      cta = R.font.lato_semibold
    }
  }

  positiveCTA {
    text = "OK, got it!"
    backgroundColor = R.color.colorPrimary
  }

  negativeCTA {
    text = "Dismiss"
    rippleColor = R.color.colorPrimary10
  }
}

fun Context.buildSimpleDialog() = Huddle().create<Builder> {
  dialog {
    isCancelableOnTouchOutside = true

    content {
      title = "Something went wrong"
      message = "There is an error occurred on the server.\nPlease try again later."
    }
  }

  positiveCTA {
    text = "OK"
  }
}

fun Context.buildSimpleImageDialog() = Huddle().create<Builder> {
  dialog {
    isCancelableOnTouchOutside = true

    content {
      title = "Something went wrong"
      message = "There is an error occurred on the server.\nPlease try again later."
    }

    image {
      resource = R.drawable.ic_warning
      tint = R.color.blue
      width = 38f.dp.toInt()
      height = 38f.dp.toInt()
    }
  }

  positiveCTA {
    text = "OK"
  }
}

fun Context.buildOnlyImageDialog() = Huddle().create<Builder> {
  dialog {
    isCancelableOnTouchOutside = true
    contentType = ContentType.PictureOnly
    ctaMode = CtaMode.None

    image {
      resource = R.drawable.ic_save_the_planet
      width = 150f.dp.toInt()
      height = 150f.dp.toInt()
    }
  }
}