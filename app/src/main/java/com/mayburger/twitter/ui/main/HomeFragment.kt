package com.mayburger.twitter.ui.main

import androidx.fragment.app.Fragment
import com.mayburger.twitter.databinding.FragmentHomeBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class HomeFragment : Fragment() {

    val binding by viewBinding(FragmentHomeBinding::bind)

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return FragmentHomeBinding.inflate(inflater).root
//    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}