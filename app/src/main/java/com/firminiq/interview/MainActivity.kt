package com.firminiq.interview

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var confirmPassword:EditText
    lateinit var btSubmit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        clickListner()
    }

    private fun clickListner() {
        btSubmit.setOnClickListener { validateForm() }
    }

    private fun validateForm()
    {
        var tvErrorEmail:TextView=findViewById(R.id.tv_error_email)
        var tvErrorPassword:TextView=findViewById(R.id.tv_error_password)
        var tvErrorConfirmPassword:TextView=findViewById(R.id.tv_error_confirm_password)

        if(email.text.length==0)
        {
            tvErrorEmail.text=getString(R.string.emt_email)
            tvErrorEmail.visibility= View.VISIBLE

        }
        else if(!email.text.isValidEmail())
        {
            tvErrorEmail.text=getString(R.string.email_valid)
            tvErrorEmail.visibility= View.VISIBLE
        }
        else if(password.text.length==0)
        {
            tvErrorEmail.visibility= View.GONE
            tvErrorPassword.text=getString(R.string.emt_pasword)
            tvErrorPassword.visibility= View.VISIBLE
        }
        else if(confirmPassword.text.length==0)
        {
            tvErrorPassword.visibility= View.GONE
            tvErrorConfirmPassword.text=getString(R.string.emt_con)
            tvErrorConfirmPassword.visibility= View.VISIBLE
        }
        else if(!password.text.equals(confirmPassword.text))
        {
            tvErrorPassword.visibility= View.GONE
            tvErrorConfirmPassword.text=getString(R.string.confirm_pass)
            tvErrorConfirmPassword.visibility= View.VISIBLE
        }
        else
        {
            tvErrorEmail.visibility=View.GONE
            tvErrorPassword.visibility=View.GONE
            tvErrorConfirmPassword.visibility=View.GONE
            showConfimAlert(email =email.text.toString() )
        }
    }

    private fun initView() {
        email=findViewById(R.id.et_email)
        password=findViewById(R.id.et_password)
        confirmPassword=findViewById(R.id.et_confirm_password)
        btSubmit=findViewById(R.id.bt_submit)
    }


    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


    fun showConfimAlert(email:String)
    {
        AlertDialog.Builder(this)
            .setTitle("Welcome")
            .setMessage(email)
            .setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    // Continue with delete operation
                }) // A null listener allows the button to dismiss the dialog and take no further action.
            //.setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }


}