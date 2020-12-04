package com.example.recyclerviewnew

import android.view.View

interface OnClickListener {
    fun click()
    fun setOnLongClick(view: View, index: Int)
}