package com.example.cafe


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.cafe.databinding.FragmentMainBinding
import com.example.cafe.databinding.FragmentSearchfragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class Searchfragment : Fragment() {

    private lateinit var showCafe: CafeControllerModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchfragmentBinding>(
            inflater,
            R.layout.fragment_searchfragment, container, false
        )
        showCafe = ViewModelProviders.of(this).get(CafeControllerModels::class.java)

        binding.btnSearch.setOnClickListener {
            searchName(binding)
        }
        return binding.root
    }

    private fun searchName(binding: FragmentSearchfragmentBinding) {
        val search = binding.editTxtFind.text.toString() + "%"
        val a = showCafe.find(search)
        a.observe(this, Observer { item ->
            if (item.isNotEmpty()) {
                item.map {
                    view?.findNavController()
                        ?.navigate(SearchfragmentDirections.actionSearchfragmentToDetailFragment2(it.nameThai))
                }
            } else {
                Toast.makeText(
                    getActivity(),
                    "ค้นหาไม่พบ กรุณากรอกชื่อร้านใหม่อีกครั้ง",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
