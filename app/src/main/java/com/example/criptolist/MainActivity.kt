package com.example.criptolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coinsList: ArrayList<Coin> = ArrayList()
        val fileName = "coins.json"
        val jsonString = application.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        val obj = JSONObject(jsonString)
        val coinsArray = obj.getJSONArray("coins")

        try {
            for (i in 0 until coinsArray.length()) {
                val coin = coinsArray.getJSONObject(i);
                val id = coin.getString("id");
                val symbol = coin.getString("symbol");
                val name = coin.getString("name");
                val image = coin.getString("image");
                val currentPrice = coin.getDouble("current_price");
                val change = coin.getString("change")

                val coinObj = Coin(
                    id,
                    symbol,
                    name,
                    image,
                    currentPrice,
                    change
                )

                // add the details in the list
                coinsList.add(coinObj)
            }
            val myListAdapter = CoinsAdapter(this, coinsList)
            listViewCoins.adapter = myListAdapter
//            listViewCoins.setOnItemClickListener(){adapterView, view, position, id ->
//                val itemAtPos = adapterView.getItemAtPosition(position)
//                val itemIdAtPos = adapterView.getItemIdAtPosition(position)
//                val intent = Intent(this, EditarCliente::class.java)
//                intent.putExtra("id", clientes[position].id)
//                startActivity(intent)
//            }
        } catch (e: JSONException) {

        }


    }
}