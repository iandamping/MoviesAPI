package com.ian.app.muviepedia.ui.fragment.profile

import com.ian.app.muviepedia.base.BaseFragmentView
import com.ian.app.muviepedia.data.model.UserProfileData

/**
 *
Created by Ian Damping on 10/06/2019.
Github = https://github.com/iandamping
 */
interface ProfileView : BaseFragmentView {
    fun onSuccessGetProfileData(data: UserProfileData)
}