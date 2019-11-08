package com.example.cafe


import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.cafe.Database.SuggestModels
import com.example.cafe.databinding.FragmentDetailBinding


/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private lateinit var showCafe: CafeControllerModels
    private lateinit var saveSuggest: SuggestControllerModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail, container, false
        )
        showCafe = ViewModelProviders.of(this).get(CafeControllerModels::class.java)
        saveSuggest = ViewModelProviders.of(this).get(SuggestControllerModels::class.java)

        setData(binding)
        setImageButtonSuggest(binding)
        binding.btnBackDetail.setOnClickListener {
            view?.findNavController()
                ?.navigate(DetailFragmentDirections.actionDetailFragmentToMainFragment())
        }

        binding.btnSuggestDetail.setOnClickListener {
            dialogAlert(it, binding)
        }
        return binding.root
    }

    fun dialogAlert(view: View?, binding: FragmentDetailBinding) {
        val mAlertDialog = AlertDialog.Builder(activity)
        var nameThai = arguments?.getString("nameThai")
        mAlertDialog.setIcon(R.mipmap.ic_launcher_round)
        mAlertDialog.setTitle("การแนะนำ")
        mAlertDialog.setMessage("คุณจะแนะนำร้านนี้หรือไม่")
        mAlertDialog.setPositiveButton("ใช่") { dialog, id ->
            binding.btnSuggestDetail.setImageResource(android.R.drawable.btn_star_big_on)
            showCafe.allListCafe.observe(this, Observer { cafe ->
                cafe.map {
                    if (it.nameThai == nameThai) {
                        saveSuggest.insert(SuggestModels(nameThai, it.image))
                    }
                }
            })
        }
        mAlertDialog.setNegativeButton("ไม่") { dialog, id ->
            binding.btnSuggestDetail.setImageResource(android.R.drawable.btn_star_big_off)
            saveSuggest.delete(nameThai.toString())
        }
        mAlertDialog.show()
    }

    private fun setImageButtonSuggest(binding: FragmentDetailBinding) {
        var nameThai = arguments?.getString("nameThai")
        saveSuggest.allSuggest.observe(this, Observer { cafe ->
            cafe.map {
                if (it.nameThai == nameThai) {
                    binding.btnSuggestDetail.setImageResource(android.R.drawable.btn_star_big_on)
                }
            }
        })
    }

    private fun setData(binding: FragmentDetailBinding) {
        var nameThai = arguments?.getString("nameThai")
        val mapImage = setImage()
        showCafe.allListCafe.observe(this, Observer { cafe ->
            cafe.map {
                if (it.nameThai == nameThai) {
                    binding.txtTitleDetail.text = it.nameThai
                    binding.txtTitleDetailEng.text = it.nameEng
                    binding.imgDetail.setImageResource(mapImage.get(it.image)!!)
                    binding.txtDetail.text = it.detail
                }
            }
        })
    }

    private fun setImage(): HashMap<String, Int> {
        val map = HashMap<String, Int>()
        map["1"] = R.drawable.cafe12
        map["2"] = R.drawable.cafe22
        map["3"] = R.drawable.cafe32
        map["4"] = R.drawable.cafe42
        return map
    }

}
