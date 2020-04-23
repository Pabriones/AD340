package com.dark_matter.pabri.ad340

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeedittext: EditText = findViewById(R.id.zipcodeedittext)
        val enterButton: Button = findViewById(R.id.submitbutton)

        enterButton.setOnClickListener{
            val zipcode: String =zipcodeedittext.text.toString()

            if(zipcode.length != 5){
                Toast.makeText(this, R.string.zipcode_entry_error, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, zipcode, Toast.LENGTH_SHORT).show()
            }

            //Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
        }
    }
}
