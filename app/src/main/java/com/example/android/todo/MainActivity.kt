package com.example.android.todo

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.size
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.add
import kotlinx.android.synthetic.main.activity_main.clear
import kotlinx.android.synthetic.main.activity_main.delete
import kotlinx.android.synthetic.main.activity_main.editText
import kotlinx.android.synthetic.main.activity_main.listView
import kotlinx.android.synthetic.main.activity_main.textView1
import kotlinx.android.synthetic.main.content_details.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemlist = arrayListOf<String>()
        var adapter =ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice
                , itemlist)

        var del = arrayListOf<String>()
        var ad = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice
            , del)

        add.setOnClickListener {
            if(editText.text.toString().isEmpty())
                android.widget.Toast.makeText(this, "You need to enter a task", android.widget.Toast.LENGTH_SHORT).show()
            else {
                itemlist.add(editText.text.toString())
                listView.adapter = adapter
                adapter.notifyDataSetChanged()
                editText.text.clear()
            }
        }

        val vis = { v : View -> v.visibility = View.VISIBLE}

        ok.setOnClickListener {
            if(nameText.text.toString().isEmpty())
                android.widget.Toast.makeText(this, "You haven't entered anything", android.widget.Toast.LENGTH_SHORT).show()
            else {
                var name = "Here is what" + " " + nameText.text.toString() + " " + "needs to do:";
                textView1.text = name
                gon(v = nameText)
                gon(v = ok)
                vis(textView2)
                vis(editText)
                vis(linearLayout2)
                vis(add)
                vis(delete)
                vis(clear)
                vis(textView1)
                vis(listView)
                vis(listView1)
            }
        }

        clear.setOnClickListener {
            if(ad.count == 0 && adapter.count == 0 )
                android.widget.Toast.makeText(this, "Your list is already clear", android.widget.Toast.LENGTH_SHORT).show()
            else {
                itemlist.clear()
                adapter.notifyDataSetChanged()
                del.clear()
                ad.notifyDataSetChanged()
                gon(textView5)
            }
        }


        listView.setOnItemClickListener { adapterView, view, i, l ->
            var item = itemlist.get(i)
            android.widget.Toast.makeText(this, "$item done!", android.widget.Toast.LENGTH_SHORT).show()
            del.add(itemlist.get(i))
            adapter.remove(itemlist.get(i))
            listView.adapter =  adapter
            adapter.notifyDataSetChanged()
            listView1.adapter =  ad
            ad.notifyDataSetChanged()
            vis(textView5)
        }

        listView.setOnItemLongClickListener{ adapterView, view, i, l ->
            adapter.remove(itemlist.get(i))
            listView.adapter =  adapter
            adapter.notifyDataSetChanged()
            true
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView1.checkedItemPositions
            if(position.size() == 0)
                android.widget.Toast.makeText(this, "You haven't selected anything", android.widget.Toast.LENGTH_SHORT).show()
            else {
                val count = listView1.count
                var item = count - 1
                while (item >= 0) {
                    if (position.get(item)) {
                        ad.remove(del.get(item))
                    }
                    item--
                }
                position.clear()
                ad.notifyDataSetChanged()
                if (ad.count == 0)
                    gon(textView5)
            }
        }

    }

    private fun gon(v: View) {
        v.visibility=View.GONE
    }

}