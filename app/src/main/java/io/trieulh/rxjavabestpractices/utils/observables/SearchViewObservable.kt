package io.trieulh.rxjavabestpractices.utils.observables

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Trieulh on 25,March,2019
 */

object SearchViewObservable {
    fun fromView(view: EditText): Observable<String> {
        val subject: PublishSubject<String> = PublishSubject.create()

        view.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let { text ->
                    subject.onNext(text.toString())
                    Log.d("SearchView",s.toString())
                }
            }
        })

        return subject
    }
}