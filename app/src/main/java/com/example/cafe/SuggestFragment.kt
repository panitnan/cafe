package com.example.cafe


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.cafe.databinding.FragmentMainBinding
import com.example.cafe.databinding.FragmentSuggestBinding

/**
 * A simple [Fragment] subclass.
 */
class SuggestFragment : Fragment() {

    private lateinit var suggestCafe: SuggestControllerModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSuggestBinding>(
            inflater,
            R.layout.fragment_suggest, container, false
        )
        suggestCafe =  ViewModelProviders.of(this).get(SuggestControllerModels::class.java)

        setView(binding)
        binding.btnBackSuggest.setOnClickListener {
            view?.findNavController()
                ?.navigate(SuggestFragmentDirections.actionSuggestFragmentToMainFragment())
        }
        binding.btnSearchSuggest.setOnClickListener {
            view?.findNavController()
                ?.navigate(SuggestFragmentDirections.actionSuggestFragmentToSearchfragment())
        }
        return binding.root
    }

    private fun setView(binding:FragmentSuggestBinding){
        val adapter = CustomAdpterSuggest()
        binding.listViewSuggest.adapter = adapter
        Handler().postDelayed({
            suggestCafe.allSuggest.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.data = it
                }
            })
        },200)
    }

}
