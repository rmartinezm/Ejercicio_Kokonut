package io.arkstud.ejerciciokokonut.ui.splash_screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.ui.common.BaseView
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity(), BaseView {

    /* ViewModel associated to this activity */
    private val splashViewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        observeViewModel()
        splashViewModel.validateUserLogged()
    }

    /**
     *
     */
    private fun observeViewModel(){
        splashViewModel.loading.observe(this, loadingObserver(progressBarSplashScreen))
    }

}
