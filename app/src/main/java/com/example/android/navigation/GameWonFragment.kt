/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*

import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener{view: View ->
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment2())
        }
        //Use the generated Args class to get the args from the bundle


//        Toast.makeText(context,
//        "NumCorrect: ${args.numCorrect}, NumWuestins: ${args.numQuestions}", Toast.LENGTH_LONG)
//                .show()
        // tell the fragment to use menu
        setHasOptionsMenu(true)
        return binding.root
    }


    //The menu is inflated here
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)

        // check if the activity resolves
        if ( null == getShareIntent().resolveActivity(activity!!.packageManager)){
            // hide share menu item is intent returns null
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }
    // creates the intent and put the extra strings
    private fun getShareIntent(): Intent {
        var args = GameWonFragmentArgs.fromBundle(arguments!!)

        //returning an Intent with everything set
        return ShareCompat.IntentBuilder.from(activity).setText(getString(R.string.share_success_text,
        args.numCorrect, args.numQuestions)).setType("text/plain").intent

//        var shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.setType("text/plain")
//                .putExtra(Intent.EXTRA_TEXT,
//                        getString(R.string.share_success_text,args.numCorrect, args.numQuestions))
//        return shareIntent

    }
    //starts the intent
    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    // Called when a menu item is selected
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
