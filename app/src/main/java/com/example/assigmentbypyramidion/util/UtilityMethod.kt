package com.example.assigmentbypyramidion.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.assigmentbypyramidion.R
import com.example.assigmentbypyramidion.api.ScrollDirection
import com.example.assigmentbypyramidion.callback.PaginationCallback

object UtilityMethod {

    fun loadImageIntoView(context: Context, url: String?, resourceId: Int?, targetView: ImageView) {
        try {
            if (resourceId != null) {
                Glide.with(context)
                    .load(resourceId).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_image_load_error)
                    )
                    .into(targetView)
            } else if (url != null) {
                val imageUrl = Uri.parse(url)
                Glide.with(context)
                    .load(imageUrl).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_image_load_error)
                    )
                    .into(targetView)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addPagination(
        recyclerView: RecyclerView,
        direction: ScrollDirection,
        paginationCallback: PaginationCallback
    ) {
        var isLoading: Boolean = true
        var previousTotal = 0
        var totalItemCount = 0
        var lastVisibleItems = 0
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = recyclerView.layoutManager?.itemCount!!
                lastVisibleItems =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val isEndOfList = lastVisibleItems + 1 >= totalItemCount
                when (direction) {
                    ScrollDirection.VERTICAL -> {
                        if (dy > 0) {
                            if (isLoading && totalItemCount > previousTotal) {
                                previousTotal = totalItemCount
                                isLoading = false
                            }
                            if (!isLoading && (totalItemCount > 5 && isEndOfList)) {
                                paginationCallback.loadNext(recyclerView)
                                isLoading = true
                            }
                        }
                    }
                    ScrollDirection.HORIZONTAL -> {
                        if (dx > 0) {
                            if (isLoading && totalItemCount > previousTotal) {
                                previousTotal = totalItemCount
                                isLoading = false
                            }
                            if (!isLoading && (totalItemCount > 5 && isEndOfList)) {
                                paginationCallback.loadNext(recyclerView)
                                isLoading = true
                            }
                        }
                    }
                }
            }
        })
    }

}