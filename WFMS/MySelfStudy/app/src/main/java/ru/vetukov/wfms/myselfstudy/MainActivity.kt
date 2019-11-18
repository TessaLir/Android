package ru.vetukov.wfms.myselfstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.vetukov.wfms.myselfstudy.Utils.Utils

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_btn_enter.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, BaseActivity::class.java)
        intent.putExtra(Utils.EMAIL_FIELD, main_et_email.text)
        intent.putExtra(Utils.PWD_FIELD, main_et_pwd.text)
        startActivity(intent)
    }
}
