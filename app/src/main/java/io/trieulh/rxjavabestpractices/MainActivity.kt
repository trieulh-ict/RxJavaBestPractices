package io.trieulh.rxjavabestpractices

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.trieulh.rxjavabestpractices.samples.SearchViewObservableActivity
import io.trieulh.rxjavabestpractices.utils.ext.OnItemClickListener
import io.trieulh.rxjavabestpractices.utils.ext.addOnItemClickListener
import io.trieulh.rxjavabestpractices.utils.ext.launchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        sampleListView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = SamplesAdapter(dataSet)
            addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    when (position) {
                        0 -> launchActivity<SearchViewObservableActivity>()
                    }
                }
            })
        }
    }

    companion object {
        val dataSet = mapOf(
            0 to "SearchView Observable Implementation"
        )
    }
}
