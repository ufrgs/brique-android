package br.ufrgs.brique.presentation.extensions

import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.ufrgs.brique.App


val AppCompatActivity.app: App get() = application as App

fun AppCompatActivity.shouldAskPermission(permission: String): Boolean {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
        return false
    }
    return ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
}

fun AppCompatActivity.askPermission(permissions: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment, tag: String, animate: Boolean) {
    val transaction = supportFragmentManager.beginTransaction()

    if (animate) {
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    transaction.replace(containerId, fragment, tag)
    transaction.addToBackStack(tag)
    transaction.commit()

}

fun AppCompatActivity.setToolbar(toolbar: Toolbar, showHome: Boolean = true) {
    this.setSupportActionBar(toolbar)
    if (showHome)
        if (showHome) {
            supportActionBar?.displayOptions = ActionBar.DISPLAY_HOME_AS_UP or
                    ActionBar.DISPLAY_SHOW_HOME or ActionBar.DISPLAY_SHOW_TITLE

            toolbar.setNavigationOnClickListener { onBackPressed() }
        }

}

fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}


