package com.example.criptolist

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CoinsAdapter(private val context: Activity, private val coins: ArrayList<Coin>): ArrayAdapter<Coin>(context, R.layout.list_item_coin, coins)  {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_item_coin, null, true)


        val txtName = rowView.findViewById(R.id.txtName) as TextView
        val txtCurrentPrice = rowView.findViewById(R.id.txtCurrentPrice) as TextView
        val txtSymbol = rowView.findViewById(R.id.txtSymbol) as TextView
        val txtChange = rowView.findViewById(R.id.txtChange) as TextView
        val image = rowView.findViewById(R.id.imgCoin) as ImageView

        if(coins[position].change.startsWith('-')) {
            txtChange.setTextColor(Color.parseColor("#FD474B"))
        } else {
            txtChange.setTextColor(Color.parseColor("#82C280"))
        }

        txtName.text = coins[position].name
        txtCurrentPrice.text = "${"$"}${NumberFormat.getNumberInstance(Locale.US).format(coins[position].currentPrice)}"
        txtSymbol.text = coins[position].symbol.uppercase()
        txtChange.text = coins[position].change

        Picasso.get().load(coins[position].image).into(image)

        return rowView
    }
}