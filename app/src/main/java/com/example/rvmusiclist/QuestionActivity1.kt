package com.example.rvmusiclist


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rvmusiclist.adapter.MusicAdapter
import com.example.rvmusiclist.api.ApiClient.Companion.retrofitBuilder
import com.example.rvmusiclist.model.MusicModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionActivity1 : AppCompatActivity() {

    private lateinit var musicRv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        musicRv = findViewById(R.id.superHero_rv)
        getMyData()
    }

    private fun getMyData() {
        val retrofitData = retrofitBuilder.getData("379283", "All")

        retrofitData.enqueue(object : Callback<MusicModel> {
            override fun onResponse(
                call: Call<MusicModel>,
                response: Response<MusicModel>
            ) {
                val responseBody = response.body()!!
                initSuperHeroRv(responseBody)
            }

            override fun onFailure(call: Call<MusicModel>, t: Throwable) {
                Log.d("Main Activity", "onFailure" + t.message)
            }
        })
    }

    private fun initSuperHeroRv(responseBody: MusicModel) {
        val adapter = MusicAdapter(object : MusicAdapter.OnClickInterface {

            override fun onItemClicked(doc: MusicModel.Data) {
                /* val musicDeFrg = MusicDetailsFragment.newInstance()

                 val arguments = Bundle()
                 arguments.putParcelable("MUSIC_ITEM", doc)


                 musicDeFrg.arguments = arguments*/
                val mIntent = Intent(this@QuestionActivity1, MusicActivity::class.java)
                val mBundle = Bundle()
                mBundle.putParcelable("MUSIC_ITEM", doc)
                mIntent.putExtras(mBundle)
                startActivity(mIntent)
            }


        })
        musicRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        musicRv.adapter = adapter
        adapter.submitList(responseBody.data)
    }
}