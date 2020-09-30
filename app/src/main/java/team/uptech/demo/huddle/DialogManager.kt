package team.uptech.demo.huddle

import android.content.Context
import team.uptech.huddle.Huddle
import team.uptech.huddle.builder.Builder
import team.uptech.huddle.model.CtaMode
import team.uptech.huddle.util.extension.create


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
  }

  positiveCTA {
    text = "OK, got it!"
  }

  negativeCTA {
    text = "Dismiss"
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