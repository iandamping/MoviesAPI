package com.ian.app.muviepedia.ui.fragment.profile_fragment

import android.content.Context
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.ian.app.helper.util.startActivity
import com.ian.app.muviepedia.MoviesApp.Companion.gson
import com.ian.app.muviepedia.MoviesApp.Companion.mFirebaseAuth
import com.ian.app.muviepedia.MoviesApp.Companion.prefHelper
import com.ian.app.muviepedia.base.BaseFragmentPresenter
import com.ian.app.muviepedia.data.model.UserProfileData
import com.ian.app.muviepedia.ui.activity.MainActivity
import com.ian.app.muviepedia.util.MovieConstant.saveUserProfile

/**
 *
Created by Ian Damping on 10/06/2019.
Github = https://github.com/iandamping
 */
class ProfilePresenter : BaseFragmentPresenter<ProfileView>() {
    private lateinit var userData: UserProfileData
    private var ctx: Context? = null
    private var listener: FirebaseAuth.AuthStateListener? = null

    override fun onAttach() {
        ctx = getLifeCycleOwner().context
    }

    override fun onCreateView(view: View) {
        view()?.initView(view)
        getFirebaseUserData()

    }

    private fun getFirebaseUserData() {
        listener = FirebaseAuth.AuthStateListener { auth ->
            if (mFirebaseAuth.currentUser != null) {
                if (!prefHelper.getStringInSharedPreference(saveUserProfile).isNullOrBlank()) {
                    this.userData = gson.fromJson(prefHelper.getStringInSharedPreference(saveUserProfile), UserProfileData::class.java)
                    view()?.onSuccessGetProfileData(userData)
                } else if (prefHelper.getStringInSharedPreference(saveUserProfile).isNullOrBlank()) {
                    this.userData = UserProfileData(mFirebaseAuth.currentUser?.uid,
                            mFirebaseAuth.currentUser?.photoUrl.toString(),
                            mFirebaseAuth.currentUser?.displayName,
                            mFirebaseAuth.currentUser?.email)
                    prefHelper.saveStringInSharedPreference(saveUserProfile, gson.toJson(userData))
                    ctx?.startActivity<MainActivity>()
                }
            } else view()?.onFailedGetData("not logged in")
        }
    }

    fun initOnResume() {
        listener?.let { mFirebaseAuth.addAuthStateListener(it) }
    }

    fun initOnPause() {
        listener?.let { mFirebaseAuth.removeAuthStateListener(it) }
    }

    fun logOut() {
        ctx?.let { AuthUI.getInstance().signOut(it) }
        prefHelper.deleteSharedPreference()
        ctx?.startActivity<MainActivity>()
    }
}