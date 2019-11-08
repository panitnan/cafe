package com.example.cafe


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe.Database.CafeModels
import com.example.cafe.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.item_view.view.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var showCafe: CafeControllerModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater,
            R.layout.fragment_main, container, false
        )
        showCafe = ViewModelProviders.of(this).get(CafeControllerModels::class.java)
        initialDatabase(binding)
        binding.btnSearch.setOnClickListener {
            view?.findNavController()
                ?.navigate(MainFragmentDirections.actionMainFragmentToSearchfragment2())
        }
        binding.btnSuggest.setOnClickListener {
            view?.findNavController()
                ?.navigate(MainFragmentDirections.actionMainFragmentToSuggestFragment2())
        }
        return binding.root
    }

    private fun initialDatabase(binding: FragmentMainBinding) {
        showCafe.allListCafe.observe(this, Observer { item ->
            if (item.isNotEmpty()) {
                val adapter = CustomAdpterMain()
                binding.listViewData.adapter = adapter
                showCafe.allListCafe.observe(viewLifecycleOwner, Observer {
                    it?.let {
                        adapter.data = it
                    }
                })
            } else {
                showCafe.insert(
                    CafeModels(
                        "พิตต้าเฮ้าส์",
                        "(Pitta House)",
                        getString(R.string.cafeOneValue),
                        "1"
                    )
                )
                showCafe.insert(
                    CafeModels(
                        "บางพระเรือห้วยท่าไทร",
                        "(Bangprarae Huatasai)",
                        getString(R.string.cafeTwoValue),
                        "2"
                    )
                )
                showCafe.insert(
                    CafeModels(
                        "เมล่อนคาเฟ่",
                        "(Melon JJ Farm)",
                        getString(R.string.cafeThreeValue),
                        "3"
                    )
                )
                showCafe.insert(
                    CafeModels(
                        "คูณคาเฟ่",
                        "(Koons Cafe)",
                        getString(R.string.cafeFourValue),
                        "4"
                    )
                )
                Handler().postDelayed({
                    val adapter = CustomAdpterMain()
                    binding.listViewData.adapter = adapter
                    showCafe.allListCafe.observe(viewLifecycleOwner, Observer {
                        it?.let {
                            adapter.data = it
                        }
                    })
                }, 200)
            }
        })

    }
}
