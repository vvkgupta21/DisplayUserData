package com.example.assigmentbypyramidion.callback

import androidx.recyclerview.widget.RecyclerView

interface PaginationCallback {
    fun loadNext(recyclerView: RecyclerView?)
}