package com.example.sprint3.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sprint3.R
import com.example.sprint3.models.Product
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class ProductListActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        // Inicialize o Firebase Realtime Database
        database = FirebaseDatabase.getInstance().reference

        val productsListView = findViewById<ListView>(R.id.productsListView)

        database.child("products").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val products = mutableListOf<String>()
                for (snapshot in dataSnapshot.children) {
                    val product = snapshot.getValue(Product::class.java)
                    if (product != null) {
                        products.add(product.name)
                    }
                }
                val adapter = ArrayAdapter(this@ProductListActivity, android.R.layout.simple_list_item_1, products)
                productsListView.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ProductListActivity, "Erro ao carregar produtos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
