package com.g22solutions.carsapp.base

import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PagingRecyclerListner(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    val PAGE_START = 1

    companion object {
         val PAGE_SIZE = 10
    }

    override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (!isLoading() && !isLastPage()) {

            if (!recyclerView.canScrollVertically(1)) {
                loadMoreItems()
            }

        }
    }

    abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean


}