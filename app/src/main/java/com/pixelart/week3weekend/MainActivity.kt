package com.pixelart.week3weekend


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.custom_dialog_layout.view.*
import kotlinx.android.synthetic.main.sendtext_layout.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

   fun onClick(view: View)
   {
       when(view.id)
       {
           R.id.btnPdfView ->{
               startActivity(Intent(this, PDFViewerActivity::class.java))
           }

           R.id.btnDialogFragment ->{

               val fragmentDialog = DialogFragment()
               fragmentDialog.show(supportFragmentManager, "DialogFragment")
           }

           R.id.btnDefaultAlertDialog ->{

               val alertDialog = AlertDialog.Builder(this)
               alertDialog.setTitle("Default Dialog")
                   .setMessage("I am a default alert dialog")
                   .setIcon(R.mipmap.ic_launcher_round)
                   .setPositiveButton("OK"){dialog, which ->
                       Toast.makeText(this, "Clicked OK", Toast.LENGTH_SHORT).show()
                   }
                   .setNegativeButton("Cancel"){dialog, which ->
                       dialog.dismiss()
                   }
                   .show()
           }

           R.id.btnCustomAlertDialg ->{
               val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_layout, null)
               val customDialog = AlertDialog.Builder(this)
               customDialog.setView(dialogView)
                   .setPositiveButton("Login"){dialog, which ->
                       var email = dialogView.etEmail.text.toString()
                       var password = dialogView.etPassword.text.toString()
                       Toast.makeText(this, "Logged in with \nEmail:  $email and \nPassword: $password", Toast.LENGTH_SHORT).show()
                   }
                   .setNegativeButton("Cancel"){dialog, which ->
                       dialog.dismiss()
                   }
                   .show()
           }

           R.id.btnAlertDialogChoices ->{
               val multiSelected = ArrayList<String>()
               val alertDialog = AlertDialog.Builder(this)
               alertDialog.setTitle("Dialog With Choices")
                   .setMultiChoiceItems(R.array.multi_choices, null) {dialog, which, isChecked ->
                       if (isChecked)
                           multiSelected.add(which.toString())
                       else if (multiSelected.contains(which.toString()))
                           multiSelected.remove(which.toString())

                   }
                   .setIcon(R.mipmap.ic_launcher_round)
                   .setPositiveButton("OK"){dialog, which ->

                       Toast.makeText(this, "MultiChoices: $multiSelected", Toast.LENGTH_SHORT).show()
                   }
                   .setNegativeButton("Cancel"){dialog, which ->
                       dialog.dismiss()
                   }
                   .show()
           }

           R.id.btnSendNotification ->{
               var intent = Intent(this, NotificationService::class.java)
               intent.action = "startNotification"
               startService(intent)
           }

           R.id.btnSendText ->{
               val dialogView = LayoutInflater.from(this).inflate(R.layout.sendtext_layout, null)
               val textDialog = AlertDialog.Builder(this)
               textDialog.setView(dialogView)
                   .setPositiveButton("Send"){dialog, which ->
                       var number = dialogView.etPhoneNumber.text.toString()
                       var message = dialogView.etMessage.text.toString()

                       sendText(number, message)
                       Toast.makeText(this, "Logged in with \nNumber:  $number and \nMessage: $message", Toast.LENGTH_SHORT).show()
                   }
                   .setNegativeButton("Cancel"){dialog, which ->
                       dialog.dismiss()
                   }
                   .show()
           }
       }
   }

    fun sendText(number : String, message : String)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        }
        else
        {
            val sms = SmsManager.getDefault()
            sms.sendTextMessage(number, null, message, null , null)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show()
    }
}
