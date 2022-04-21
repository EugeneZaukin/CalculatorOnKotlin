package com.eugene.calculator

import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import com.eugene.calculator.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalculatorButtons()
    }

    private fun initCalculatorButtons() {
        with(binding) {
            buttonZero.setOnClickListener {  }
            button1.setOnClickListener {  }
            button2.setOnClickListener {  }
            button3.setOnClickListener {  }
            button4.setOnClickListener {  }
            button5.setOnClickListener {  }
            button6.setOnClickListener {  }
            button7.setOnClickListener {  }
            button8.setOnClickListener {  }
            button9.setOnClickListener {  }
        }
    }
}