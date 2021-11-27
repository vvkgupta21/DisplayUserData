package com.example.assigmentbypyramidion.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assigmentbypyramidion.databinding.ItemUserLayoutBinding
import com.example.assigmentbypyramidion.model.UserDetail
import com.example.assigmentbypyramidion.util.UtilityMethod
import kotlin.collections.ArrayList

class AdapterUserData(private var getList: ArrayList<UserDetail>, ) : RecyclerView.Adapter<AdapterUserData.Holder>() {

    class Holder(val binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model = getList[position]
        holder.binding.model = model
        UtilityMethod.loadImageIntoView(holder.binding.avatar.context, model.avatar, null, holder.binding.avatar)
    }

    override fun getItemCount(): Int = getList.size

//    fun addMoreLikeList(newLikeList: ArrayList<UserDetail>) {
//        val oldLikeListSize = getList.size
//        if (getList != newLikeList) {
//            getList.addAll(newLikeList)
//            notifyItemRangeInserted(oldLikeListSize, newLikeList.size)
//        }
//    }

    fun initList(initialLikeList: ArrayList<UserDetail>) {
        getList = initialLikeList
        notifyDataSetChanged()
    }
}