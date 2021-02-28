package com.deepdweller.agay.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.deepdweller.agay.R
import com.google.android.material.card.MaterialCardView

class ManualFragment (): Fragment() {

//    val bundle = bundleOf(
//        "URL_TECH" to R.string.url_tech,
//        "URL_CULTURE" to R.string.url_culture
//    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_manual, container, false)
        val card_tech: MaterialCardView = view.findViewById(R.id.card_tech)
        val card_culture: MaterialCardView = view.findViewById(R.id.card_culture)

        card_tech.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("key", "URL_TECH")
            bundle.putInt("URL_TECH",  R.string.url_tech)
            view.findNavController().navigate(R.id.action_manualFragment_to_webFragment, bundle)
        }
        card_culture.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("key", "URL_CULTURE")
            bundle.putString("URL_CULTURE", requireContext().resources.getString(R.string.url_culture))
            view.findNavController().navigate(R.id.action_manualFragment_to_webFragment, bundle)
        }

        return view
    }

}

