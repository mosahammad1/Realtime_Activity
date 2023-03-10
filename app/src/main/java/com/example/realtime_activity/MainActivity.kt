package com.example.realtime_activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()
        findViewById<Button>(R.id.save).setOnClickListener {
            val name = findViewById<EditText>(R.id.PersonName).text.toString()
            val id = findViewById<EditText>(R.id.PersonID).text.toString()
            val age = findViewById<EditText>(R.id.PersonAge).text.toString()

            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )
            myRef.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()

        }


        findViewById<Button>(R.id.getData).setOnClickListener {
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue()
                    findViewById<TextView>(R.id.textData).text = value.toString()
                    Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()

                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext,"Faller",Toast.LENGTH_SHORT).show()
                }
            })

        }


    }
}