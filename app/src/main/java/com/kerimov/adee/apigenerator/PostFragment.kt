package com.kerimov.adee.apigenerator


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class PostFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var mPostList: List<Post>? = ArrayList()
    private val mWeather: Weather? = null
    private var mWeatherList: List<Weather>? = ArrayList()
    private val BishkekWeatherId = 1528334
    private val OshWeahterId = 1527534
    private val CholponAtaWeahterId = 1528512
    private val NarynWeatherId = 1527592
    private val IdArray = intArrayOf(BishkekWeatherId, OshWeahterId, CholponAtaWeahterId, NarynWeatherId)


    internal val randomArray = getRandomArray(10, 100)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        recyclerView = view.findViewById(R.id.post_recycler_view)
        val textView = view.findViewById<TextView>(R.id.tv_random)


        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(view.context)
        recyclerView!!.layoutManager = layoutManager


        val callWeather = NetworkServiceWeather
                .getInstance()
                .jsonApiWeather
                .getWeatherList(IdArray)
        callWeather.enqueue(object : Callback<List<Weather>> {
            override fun onResponse(call: Call<List<Weather>>, response: Response<List<Weather>>) {
                mWeatherList = response.body()
                getPosts(view)
            }

            override fun onFailure(call: Call<List<Weather>>, t: Throwable) {
                Toast.makeText(view.context, "weather" + t.message, Toast.LENGTH_LONG).show()
                textView.text = t.message
            }
        })
        return view
    }

    private fun getPosts(view: View) {
        val call = NetworkService
                .getInstance()
                .jsonApi
                .getPost(randomArray)
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                mPostList = response.body()
                mAdapter = PostAdapter(view.context, mPostList, mWeatherList)
                recyclerView!!.adapter = mAdapter
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(view.context, "post " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun getRandomArray(ten: Int, diapason: Int): IntArray {
        val array = IntArray(ten)
        for (i in 0..9) {
            array[i] = (Math.random() * diapason).toInt()
        }
        return array
    }

    companion object {

        private val TAG = "MainActivity"
    }


}// Required empty public constructor
