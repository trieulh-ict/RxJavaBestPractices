package io.trieulh.rxjavabestpractices

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class SamplesAdapter(val dataSet: Map<Int, String>) : RecyclerView.Adapter<SamplesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sample, parent, false) as TextView
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position]
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}
