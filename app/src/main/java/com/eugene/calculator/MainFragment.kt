package com.eugene.calculator

import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.eugene.calculator.databinding.MainFragmentBinding
import kotlinx.coroutines.flow.*

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
        startObserve()
        initCalculatorButtons()
    }

    private fun startObserve() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.result
                .onEach {
                    binding.textView2.text = it
                }
                .collect()
        }
    }

    private fun initCalculatorButtons() {
        with(binding) {
            buttonZero.setOnClickListener { viewModel.addButtonClick(buttonZero.text.toString()) }
            button1.setOnClickListener { viewModel.addButtonClick(button1.text.toString()) }
            button2.setOnClickListener { viewModel.addButtonClick(button2.text.toString()) }
            button3.setOnClickListener { viewModel.addButtonClick(button3.text.toString()) }
            button4.setOnClickListener { viewModel.addButtonClick(button4.text.toString()) }
            button5.setOnClickListener { viewModel.addButtonClick(button5.text.toString()) }
            button6.setOnClickListener { viewModel.addButtonClick(button6.text.toString()) }
            button7.setOnClickListener { viewModel.addButtonClick(button7.text.toString()) }
            button8.setOnClickListener { viewModel.addButtonClick(button8.text.toString()) }
            button9.setOnClickListener { viewModel.addButtonClick(button9.text.toString()) }
        }
    }
}