package com.example.asd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.asd.MiniProvider
import com.example.asd.R
//import com.example.asd.apinet.NewsApi
import com.example.asd.apinet.NewsApiService
import com.example.asd.apinet.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var  vmf: ViewModelFactory

    private val vm: NewsViewModel by viewModels { vmf }

    private val disp = CompositeDisposable()

    var dobj : Disposable? = null

    val reqnews by lazy { NewsApiService.buildService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vmf = MiniProvider.provideViewModelFactory(this)

        dobj = reqnews.newsReq()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> onResponse(response) },
                { error -> Log.e("Retrofit: ", "retrofit error", error)
                Toast.makeText(this, "Retro error", Toast.LENGTH_LONG).show()}
            )

    }

    override fun onStart() {
        super.onStart()

        disp.add(vm.newsName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({this.newstext.text = it},
                { error -> Log.e("HIzzzERR", "Error: can't get news", error) }))
    }

    override fun onPause() {
        super.onPause()

        disp.dispose()
    }

    override fun onStop() {
        super.onStop()

        disp.clear()
    }

    private fun onResponse(response: List<Results>) {
        Log.e("Response: ", "$response")
        newstext.text = response.toString()
    }
}