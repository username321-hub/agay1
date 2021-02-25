package com.deepdweller.agay.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.content.ContentProviderCompat.requireContext
import com.deepdweller.agay.R

class ManualFragment (): Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return WebView(requireContext()).apply { loadUrl("file:///android_asset/manual.html") }
    }
}
/*
enum class URL(var path: String){
    MANUAL("file:///android_asset/manual.html")
}*/
