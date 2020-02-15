package com.example.rickandmorty2
/*
    Ben Jokela
    Asn 2
    2020-02-15
 */
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.rickandmorty2.databinding.FragmentCheatBinding


/**
 * A simple [Fragment] subclass.
 */
class CheatFragment : Fragment() {

    private lateinit var binding: FragmentCheatBinding
    private lateinit var navController: NavController

    lateinit var question: String
    lateinit var answer: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cheat, container, false)

        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        var args = CheatFragmentArgs.fromBundle(arguments!!)

        binding.apply {
            question = args.question
            answer = args.answer
            questionView.setText(question)
            cheatButton.setOnClickListener {
                answerView.setText(answer)
            }
            cancelButton.setOnClickListener {
                activity!!.onBackPressed()
            }
        }
    }


}
