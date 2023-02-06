package com.android.uservitals.coreui

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

class FlowObserver<T>(
    lifecycleOwner: LifecycleOwner,
    private val flow: Flow<T>
) {


    private var job: Job? = null


    init {
        lifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when(event) {

                Lifecycle.Event.ON_CREATE -> {
                    job = flow.launchIn(source.lifecycleScope)
                }

                Lifecycle.Event.ON_DESTROY -> {
                    job?.cancel()
                    job = null
                }

            }
        })
    }


}


inline fun <reified T> Flow<T>.observeIn(lifecycleOwner: LifecycleOwner): FlowObserver<T> {
    return FlowObserver(lifecycleOwner, this)
}


class FragmentObserver<T>(
    fragment: Fragment,
    private val flow: Flow<T>
) {


    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            FlowObserver(viewLifecycleOwner, flow)
        }
    }


}


inline fun <reified T> Flow<T>.observeIn(fragment: Fragment): FragmentObserver<T> {
    return FragmentObserver(fragment, this)
}