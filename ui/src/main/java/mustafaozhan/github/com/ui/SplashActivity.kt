package mustafaozhan.github.com.ui

import android.content.Intent
import android.os.Bundle
import com.github.mustafaozhan.basemob.activity.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
