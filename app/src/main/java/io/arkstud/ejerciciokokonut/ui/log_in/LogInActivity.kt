package io.arkstud.ejerciciokokonut.ui.log_in

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.gimlet.edittextform.EditTextForm
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.ui.common.BaseView
import kotlinx.android.synthetic.main.activity_log_in.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class LogInActivity : AppCompatActivity(), BaseView {

    /* ViewModel associated to this activity */
    private val logInViewModel: LogInViewModel by viewModel()
    /* */
    private var enabledButton: Boolean by Delegates.observable(false) { _, _, value ->
        btnLogIn.apply {
            background = ContextCompat.getDrawable(this@LogInActivity, if(value) R.drawable.enabled_button else R.drawable.disabled_button)
            setTextColor(ContextCompat.getColor(this@LogInActivity, if(value) android.R.color.white else android.R.color.darker_gray))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        initializeViews()
        observeViewModel()
    }

    /**
     *
     */
    private fun initializeViews() {
        etEmail.setType(EditTextForm.EMAIL, R.string.email_error)
        etEmail.onFocusChangeError = true
        etPassword.setType(EditTextForm.TEXT, R.string.empty_error)
        etPassword.onFocusChangeError = true
        etPassword.addValidation({ it.length > 5 }, R.string.password_length_error)
        etPassword.addValidation({ !it.contains(" ") }, R.string.password_space_error)
        EditTextForm.checkAll(listOf(etEmail, etPassword), { enabledButton = true }, { enabledButton = false })
    }

    /**
     *
     */
    private fun observeViewModel(){
        logInViewModel.loading.observe(this, loadingObserver(progressBarLogIn))
        logInViewModel.message.observe(this, messageObserver(this))
    }

    /**
     *
     * @param view
     */
    fun onLogInButtonClicked(view: View){
        if(enabledButton)
            logInViewModel.logIn(etEmail.text.toString().trim(), etPassword.text.toString())
    }
}
