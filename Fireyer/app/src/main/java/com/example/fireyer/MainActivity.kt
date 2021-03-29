package com.example.fireyer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btclear.setOnClickListener {
            ename.setText("")
            surname.setText("")
        }

        btsend.setOnClickListener {
            val fname = ename.text.toString()
            val sur = surname.text.toString()

            val fireb = FirebaseDatabase.getInstance()
            val ref = fireb.getReference("Employee")
            val id: String? = ref.push().key

            val Employee = Employee(id.toString(), fname, sur)

            ref.child(id.toString()).setValue(Employee).addOnCompleteListener {
                Toast.makeText(applicationContext, "Complete", Toast.LENGTH_LONG).show()

                ename.setText("")
                surname.setText("")

            }
        }
    }
}