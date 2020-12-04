package com.example.recyclerviewnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog.view.*
import java.nio.file.Files.delete
import android.view.LayoutInflater as LayoutInflater1

class MainActivity : AppCompatActivity(), OnClickListener {
    var adapter =  MyAdapter (this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvadapter.adapter = adapter
        setData()
        rvadapter.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    fun setData() {
        val params: MutableList<Model> = mutableListOf()
        for(i in 1..6) {
            params.add(Model("Name $i", "about $i"))
        }
        adapter.models = params
    }

    override fun click() {
        var intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    override fun setOnLongClick(view: View, index: Int) {
    var popupMenu = PopupMenu (this, view)
        popupMenu.inflate(R.menu.context_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.delete -> adapter.deleteItem(index)
            }
            when(it.itemId) {
                R.id.add ->{
                    val addDialogView = android.view.LayoutInflater.from(this).inflate(R.layout.add_dialog, null)
                    val dialog = AlertDialog.Builder(this).setView((addDialogView)).show()
                    addDialogView.btnok.setOnClickListener {
                        adapter.addItem(index, addDialogView.et1.text.toString(), addDialogView.et2.text.toString())
                        dialog.dismiss()
                    }
                    addDialogView.btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                }
                R.id.edit ->{
                    val editDialogView = android.view.LayoutInflater.from(this).inflate(R.layout.edit_dialog, null)
                    val dialog = AlertDialog.Builder(this).setView((editDialogView)).show()
                    editDialogView.btnok.setOnClickListener {
                        adapter.editItem(index, editDialogView.et1.text.toString(), editDialogView.et2.text.toString())
                        dialog.dismiss()
                    }
                    editDialogView.btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }
                }
            }

            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }
}