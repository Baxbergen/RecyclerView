package com.example.recyclerviewnew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private  var listener: OnClickListener):RecyclerView.Adapter<MyViewHolder> () {

    var models: MutableList<Model> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    fun deleteItem(pos: Int) {
        models.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(0, models.size)
    }

    fun addItem (pos: Int, title: String, descrip: String) {
        models.add(pos+1, Model("$title", "$descrip"))
        notifyDataSetChanged()
    }
     fun editItem(index: Int, title: String, descrip: String) {
         models.set(index, Model("$title","$descrip"))
         notifyDataSetChanged()
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item,parent, false)
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.populate(models[position], listener, position)
    }

    override fun getItemCount(): Int {
        return models.size
    }
}