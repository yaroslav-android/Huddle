package team.uptech.demo.huddle

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import team.uptech.huddle.Huddle
import team.uptech.huddle.util.extension.compose
import team.uptech.huddle.util.extension.isLaunched


class MainActivity : AppCompatActivity() {
  private var dialog: Huddle? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    btnSimpleDuoCtaDialog.setOnClickListener {
      if (dialog.isLaunched()) return@setOnClickListener

      buildSimpleDuoCtaDialog().compose(this).also { dialog = it }

      with(Handler()) {
        postDelayed({ dialog?.showProgress(true) }, 500L)
        postDelayed({ dialog?.showProgress(false) }, 2500L)
      }
    }

    btnSimpleSingleCtaDialog.setOnClickListener {
      if (dialog.isLaunched()) return@setOnClickListener

      buildSimpleDialog().compose(this).also { dialog = it }
    }

    btnSimpleSingleImageCtaDialog.setOnClickListener {
      if (dialog.isLaunched()) return@setOnClickListener

      buildSimpleImageDialog().compose(this).also { dialog = it }
    }

    btnOnlyImageDialog.setOnClickListener {
      if (dialog.isLaunched()) return@setOnClickListener

      buildOnlyImageDialog().compose(this).also { dialog = it }
    }
  }
}