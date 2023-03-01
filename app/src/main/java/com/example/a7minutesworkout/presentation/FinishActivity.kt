package com.example.a7minutesworkout.presentation

import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.database.HistoryDao
import com.example.a7minutesworkout.database.HistoryEntity
import com.example.a7minutesworkout.app.WorkoutApp
import com.example.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            finish()
        }


        val dao = (application as WorkoutApp).db.getInstance()
        addDateToDatabase(dao)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun addDateToDatabase(historyDao: HistoryDao){
        val myCalendar = Calendar.getInstance()
        val dateTime = myCalendar.time
        Log.e("Date: ","" + dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}