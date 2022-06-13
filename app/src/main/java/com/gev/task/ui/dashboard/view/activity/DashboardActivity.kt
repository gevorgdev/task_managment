package com.gev.task.ui.dashboard.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gev.task.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}