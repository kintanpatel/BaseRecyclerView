package uffizio.trakzee.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.singleClick(view: (View) -> Unit) {
    setOnClickListener(view)
}

fun FragmentManager.add(containerId: Int, fragment: Fragment) {
    beginTransaction().add(containerId, fragment).commit()
}

fun FragmentManager.replace(containerId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
    if (addToBackStack)
        beginTransaction().replace(containerId, fragment).addToBackStack("").commit()
    else beginTransaction().replace(containerId, fragment).commit()
}



