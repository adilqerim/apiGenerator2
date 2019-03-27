package com.kerimov.adee.apigenerator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class PostFragmentKt : Fragment() {

    private var mPostList: List<Post>? = ArrayList()
    private var mWeatherList: List<Weather>? = ArrayList()
    private var mAdapter: RecyclerView.Adapter<*>? = null

    var layoutManager: RecyclerView.LayoutManager? = null
    val arrayOfCities: IntArray = intArrayOf(1528334,1527534,1528512,1527592)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = LayoutInflater.from(context).inflate(R.layout.fragment_post, container, false)

        post_recycler_view.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(view.context)

        val callWeather: Call<List<Weather>> = NetworkService
                .getInstance()
                .jsonApi
                .getWeatherList(arrayOfCities)
        callWeather.enqueue(object : Callback<List<Weather>> {
            override fun onFailure(call: Call<List<Weather>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Weather>>, response: Response<List<Weather>>) {
                mWeatherList = response.body()
                getPosts(view)
            }

        })
        return view
    }

    private fun getPosts(view: View) {
        val callPost: Call<List<Post>> = NetworkService
                .getInstance()
                .jsonApi
                .getPost(getRandomArray(10,100))
        callPost.enqueue(object: Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(view.context,"post " + t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                mPostList = response.body()
                mAdapter = PostAdapter(view.context,mPostList,mWeatherList)
                post_recycler_view.adapter = mAdapter
            }

        })
    }

    private fun getRandomArray(ten: Int, diapason: Int) : IntArray {
        val array = IntArray(ten)
        for (i in 0..9) {
            array[i] = (Math.random() * diapason).toInt()
        }
        return array
    }
}