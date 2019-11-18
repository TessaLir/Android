package ru.vetukov.wfms.myselfstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_base.*
import ru.vetukov.wfms.myselfstudy.Utils.Utils

class BaseActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        if (intent != null) {
            val email = intent.extras?.get(Utils.EMAIL_FIELD)
            val pwd = intent.extras?.get(Utils.PWD_FIELD)

            main_tv_result_text.text = if(email.toString() == "admin@admin.ru"
                                       && pwd.toString() == "123") resources.getText(R.string.main_tv_result_true)
                                       else resources.getText(R.string.main_tv_result_false)
        }
        main_btn_back.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        onBackPressed()
    }

}
