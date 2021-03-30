package com.dankai.recyclerview.recycler.base

import android.view.View

/**
 * @author dankai
 * @date 3/30/21 10:41 PM
 */

interface TypeInterface {

    /**
     * 获取该类型布局的ID
     */
    fun getLayoutId(): Int

    /**
     * 获得该类型布局的ViewHolder
     */
    fun onCreateViewHolder(itemView: View): BaseViewHolder<*>

}