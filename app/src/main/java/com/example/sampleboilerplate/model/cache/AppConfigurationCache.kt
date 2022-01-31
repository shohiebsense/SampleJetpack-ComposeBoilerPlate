package com.example.sampleboilerplate.model.cache

data class AppConfigurationCache(
    val onBoardingState: Int = 0
){
    companion object {
        val ONBOARDING_STATE_STARTED = 0
        val ONBOARDING_STATE_FINISHED = 1
        val ONBOARDING_STATE_SKIPPED = 2
    }

    fun isAlreadyOnBoarding() : Boolean  = onBoardingState == ONBOARDING_STATE_SKIPPED || onBoardingState == ONBOARDING_STATE_FINISHED
}