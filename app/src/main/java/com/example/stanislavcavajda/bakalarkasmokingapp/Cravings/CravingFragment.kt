package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.app.Fragment
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.stanislavcavajda.bakalarkasmokingapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CravingFragment : Fragment() {

    var cravingBtnAnimation: AnimationDrawable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_craving, container, false)

        var animation = AnimationUtils.loadAnimation(activity,R.anim.add_craving_animation)
        var cigaretteAnim = AnimationUtils.loadAnimation(activity,R.anim.alpha_anim)

        var imageView = view.findViewById<ImageView>(R.id.inside_oval)
        var cigarette = view.findViewById<ImageView>(R.id.cigarette)
        imageView.startAnimation(animation)
        cigarette.startAnimation(cigaretteAnim)

        imageView.setOnClickListener(View.OnClickListener {
            val addCraving = Intent(activity, AddCraving::class.java)
            startActivity(addCraving)
        })

        return view
    }
}
