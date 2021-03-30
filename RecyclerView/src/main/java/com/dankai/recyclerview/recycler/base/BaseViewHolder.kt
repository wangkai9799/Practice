package com.dankai.recyclerview.recycler.base

import android.content.Context
import android.util.SparseArray
import android.view.View
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView

/**
 * @author dankai
 * @date 3/30/21 10:44 PM
 */

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val views: SparseArray<View> = SparseArray()

    fun getViewById(id: Int): View {
        var view = views[id]
        if (null == view) {
            view = itemView.findViewById(id)
            views[id] = view
        }
        return view
    }

    fun begin(data: T, context: Context) {
        findView()
        setData(data, context)
    }

    /**
     * 在这个方法里面获取每个Item中的控件
     * 在BaseViewHolder的子类中定义控件，然后在这个方法里面进行初始化
     */
    abstract fun findView()

    /**
     * 在这个方法里面根据T中的数据，给每个Item设置数据
     */
    abstract fun setData(data: T, context: Context)

}