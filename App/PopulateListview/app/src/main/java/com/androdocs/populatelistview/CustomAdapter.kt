package com.androdocs.populatelistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso


class CustomAdapter(private val context: Context,
                    private val dataList: ArrayList<HashMap<String, String>>) : BaseAdapter() {

    private val inflater: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int { return dataList.size }
    override fun getItem(position: Int): Int { return position }
    override fun getItemId(position: Int): Long { return position.toLong() }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var dataitem = dataList[position]

        val rowView = inflater.inflate(R.layout.list_row, parent, false)
        rowView.findViewById<TextView>(R.id.row_name).text = dataitem.get("name")
        rowView.findViewById<TextView>(R.id.row_age).text = dataitem.get("age")
        rowView.findViewById<TextView>(R.id.row_city).text = dataitem.get("city")

        Picasso.get()
            .load(dataitem.get("image"))
            .resize(50, 50)
            .centerCrop()
            .into(rowView.findViewById<ImageView>(R.id.row_image))

        val delete_button =  rowView.findViewById<Button>(R.id.delete)

        delete_button.setOnClickListener{
            Toast.makeText(rowView.getContext(), "Cibo Rimosso", Toast.LENGTH_LONG).show()
            dataList.remove(dataList[position])
            notifyDataSetChanged()
        }


        val to_shoplist =  rowView.findViewById<Button>(R.id.to_shoplist)

        to_shoplist.setOnClickListener{
            Toast.makeText(rowView.getContext(), "Cibo Aggiunto Al Frigo", Toast.LENGTH_LONG).show()
            dataList.remove(dataList[position])
            notifyDataSetChanged()
        }



        rowView.tag = position

        return rowView
    }
}