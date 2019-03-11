package io.arkstud.ejerciciokokonut.ui.home.profile

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.model.configuration.GlideApp
import io.arkstud.ejerciciokokonut.ui.common.BaseView
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(), BaseView {

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.loadProfile()
        initializeViews()
        observeViewModel()
    }

    /**
     *
     */
    private fun initializeViews() {
        tvLogOut.setOnClickListener { profileViewModel.logOut() }
    }

    /**
     *
     */
    private fun observeViewModel(){
        profileViewModel.loading.observe(this, loadingObserver(progressbarProfile))
        profileViewModel.message.observe(this, messageObserver(context!!))
        profileViewModel.user.observe(this, Observer {
            it!!
            etName.setText(it.name)
            etLastName.setText(it.last_name!!)
            etEmail.setText(it.email!!)
            GlideApp.with(context)
                .load(it.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.no_user_photo)
                .error(R.drawable.no_user_photo)
                .centerCrop()
                .into(civImage)
        })
    }


}
