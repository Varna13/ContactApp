package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.datafaker.Faker
import java.util.UUID

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvContact)

//        val contactList = mutableListOf(
//            Contact("gh","6879"),
//            Contact("uij", "8798"),
//            Contact("uidj", "879778"),
//            Contact("vghj", "79897"))
//        for (i in 1..100){
//            val uuid = UUID.randomUUID()
//            contactList.add(Contact(uuid.toString(), "123456789$i"))
//        }

        val faker: Faker = Faker()

        val contactList: ArrayList<Contact> = ArrayList()
        for (i in 1..100){
            contactList.add(Contact(faker.name().firstName(), faker.phoneNumber().cellPhone().toString()))
        }
        recyclerView.adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}
