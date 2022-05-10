package com.eugene.calculator

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.eugene.calculator.databinding.MainFragmentBinding
import kotlinx.coroutines.flow.*

class MainFragment : Fragment() {
    private val NAME_SHARED_PREFERENCE = "calculator_themes"
    private val APP_THEME = "APP_THEME"
    private val SCHOOL_THEME = 0

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getTheme()
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserve()
        initCalculatorButtons()
        initButtonSettings()
    }

    private fun getTheme() {
        val sharedPreferences =
            activity?.getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE)

        if (sharedPreferences != null) {
            val int = sharedPreferences.getInt(APP_THEME, 0)

            if (int == SCHOOL_THEME) {
                activity?.setTheme(R.style.SchoolTheme)
            } else {
                activity?.setTheme(R.style.SpaceTheme)
            }
        }
    }

    private fun startObserve() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.result
                .onEach { binding.textView2.text = it }
                .collect()
        }
    }

    private fun initCalculatorButtons() {
        with(binding) {
            buttonZero.setOnClickListener { viewModel.addButtonNumber(buttonZero.text.toString()) }
            button1.setOnClickListener { viewModel.addButtonNumber(button1.text.toString()) }
            button2.setOnClickListener { viewModel.addButtonNumber(button2.text.toString()) }
            button3.setOnClickListener { viewModel.addButtonNumber(button3.text.toString()) }
            button4.setOnClickListener { viewModel.addButtonNumber(button4.text.toString()) }
            button5.setOnClickListener { viewModel.addButtonNumber(button5.text.toString()) }
            button6.setOnClickListener { viewModel.addButtonNumber(button6.text.toString()) }
            button7.setOnClickListener { viewModel.addButtonNumber(button7.text.toString()) }
            button8.setOnClickListener { viewModel.addButtonNumber(button8.text.toString()) }
            button9.setOnClickListener { viewModel.addButtonNumber(button9.text.toString()) }
            buttonPoint.setOnClickListener { viewModel.addButtonPoint(buttonPoint.text.toString()) }
            buttonAc.setOnClickListener { viewModel.clearAll() }
            buttonDelete.setOnClickListener { viewModel.deleteChar() }
            buttonDiv.setOnClickListener { viewModel.addButtonSign(buttonDiv.text.toString()) }
            buttonMultiplic.setOnClickListener { viewModel.addButtonSign(buttonMultiplic.text.toString()) }
            buttonDeduction.setOnClickListener { viewModel.addButtonSign(buttonDeduction.text.toString()) }
            buttonSum.setOnClickListener { viewModel.addButtonSign(buttonSum.text.toString()) }
            buttonEqual.setOnClickListener { viewModel.buttonEquals() }
            buttonPercent.setOnClickListener { viewModel.buttonPercent() }
        }
    }

    private fun initButtonSettings() {
        binding.buttonSettings.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.content_fragment, SettingsFragment())
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}