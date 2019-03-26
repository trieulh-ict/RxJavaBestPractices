package io.trieulh.rxjavabestpractices.samples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.trieulh.rxjavabestpractices.R
import io.trieulh.rxjavabestpractices.utils.observables.SearchViewObservable
import kotlinx.android.synthetic.main.activity_search_view_observable.*
import java.util.concurrent.TimeUnit

class SearchViewObservableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view_observable)

        initView()
    }

    private fun initView() {
        SearchViewObservable.fromView(searchEditText)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .filter { it.trim().isNotEmpty() }
            .distinctUntilChanged()
            .switchMap { text -> Observable.just(searchData(text)).delay(3000, TimeUnit.MILLISECONDS) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                lastSearchText.text = "Last search: $lastSearch"
                countText.text = "Api call: " + searchCount.toString()
            }
    }

    private var lastSearch: String? = null

    private var searchCount: Int = 0

    private fun searchData(query: String): List<String> {
        lastSearch = query
        searchCount += 1
        return data.filter { it.contains(query, true) }
    }

    companion object {
        val data = listOf("a", "ab", "bc", "abcd")
    }
}
