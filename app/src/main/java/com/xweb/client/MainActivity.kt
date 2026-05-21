package com.xweb.client

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.xweb.client.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        applySavedLanguage()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_open_home -> {
                    openInBrowser(HOME_URL, false)
                    true
                }
                R.id.action_open_messages -> {
                    openInBrowser(MESSAGES_URL, false)
                    true
                }
                R.id.action_login_in_browser -> {
                    openInBrowser(LOGIN_URL, true)
                    true
                }
                R.id.action_create_dm_shortcut -> {
                    createMessagesShortcut()
                    true
                }
                R.id.action_language -> {
                    showLanguageDialog()
                    true
                }
                else -> false
            }
        }

        binding.btnLogin.setOnClickListener { openInBrowser(LOGIN_URL, true) }
        binding.btnOpenHome.setOnClickListener { openInBrowser(HOME_URL, false) }
        binding.btnOpenMessages.setOnClickListener { openInBrowser(MESSAGES_URL, false) }

        handleLaunchIntent(intent)
        refreshDynamicShortcuts()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleLaunchIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        refreshDynamicShortcuts()
    }

    private fun handleLaunchIntent(sourceIntent: Intent?) {
        // Check if launched from shortcut or has specific destination
        if (sourceIntent?.getBooleanExtra(EXTRA_OPEN_MESSAGES, false) == true) {
            openInBrowser(MESSAGES_URL, false)
        } else if (sourceIntent?.getBooleanExtra(EXTRA_OPEN_LOGIN, false) == true) {
            openInBrowser(LOGIN_URL, true)
        } else if (sourceIntent?.getBooleanExtra(EXTRA_SKIP_AUTO_OPEN, false) != true) {
            // Auto-open last visited page on normal launch
            openLastVisitedPage()
        }
    }

    private fun openLastVisitedPage() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val lastPage = prefs.getString(KEY_LAST_PAGE, null)
        
        when (lastPage) {
            PAGE_HOME -> openInBrowser(HOME_URL, false)
            PAGE_MESSAGES -> openInBrowser(MESSAGES_URL, false)
            PAGE_LOGIN -> openInBrowser(LOGIN_URL, true)
            // If no last page saved, do nothing (stay on main screen)
        }
    }

    private fun openInBrowser(url: String, isAuthFlow: Boolean) {
        // Save the last visited page
        val pageType = when (url) {
            HOME_URL -> PAGE_HOME
            MESSAGES_URL -> PAGE_MESSAGES
            LOGIN_URL -> PAGE_LOGIN
            else -> null
        }
        pageType?.let {
            getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_LAST_PAGE, it)
                .apply()
        }

        val uri = Uri.parse(url)
        try {
            val customTabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(this, uri)
        } catch (_: Exception) {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        if (isAuthFlow) {
            Toast.makeText(this, getString(R.string.toast_auth_opened_in_browser), Toast.LENGTH_SHORT).show()
        }
    }

    private fun createMessagesShortcut() {
        if (!ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            Toast.makeText(this, getString(R.string.toast_shortcut_not_supported), Toast.LENGTH_SHORT).show()
            return
        }
        val shortcutIntent = Intent(this, MainActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(MESSAGES_URL)
            putExtra(EXTRA_OPEN_MESSAGES, true)
        }
        val shortcut = ShortcutInfoCompat.Builder(this, SHORTCUT_ID_PINNED_MESSAGES)
            .setShortLabel(getString(R.string.shortcut_dm_short_label))
            .setLongLabel(getString(R.string.shortcut_dm_long_label))
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher))
            .setIntent(shortcutIntent)
            .build()
        ShortcutManagerCompat.requestPinShortcut(this, shortcut, null)
        Toast.makeText(this, getString(R.string.toast_shortcut_created), Toast.LENGTH_SHORT).show()
    }

    private fun refreshDynamicShortcuts() {
        val shortcuts = listOf(
            ShortcutInfoCompat.Builder(this, SHORTCUT_ID_DYNAMIC_LOGIN)
                .setShortLabel(getString(R.string.action_login_in_browser))
                .setLongLabel(getString(R.string.action_login_in_browser))
                .setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher))
                .setIntent(Intent(this, MainActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra(EXTRA_OPEN_LOGIN, true)
                    data = Uri.parse(LOGIN_URL)
                })
                .setRank(0)
                .build(),
            ShortcutInfoCompat.Builder(this, SHORTCUT_ID_DYNAMIC_MESSAGES)
                .setShortLabel(getString(R.string.shortcut_dm_short_label))
                .setLongLabel(getString(R.string.shortcut_dm_long_label))
                .setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher))
                .setIntent(Intent(this, MainActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra(EXTRA_OPEN_MESSAGES, true)
                    data = Uri.parse(MESSAGES_URL)
                })
                .setRank(1)
                .build()
        )
        ShortcutManagerCompat.setDynamicShortcuts(this, shortcuts)
    }

    private fun showLanguageDialog() {
        val currentLang = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_LANGUAGE, LANGUAGE_ENGLISH) ?: LANGUAGE_ENGLISH
        
        val items = arrayOf(
            getString(R.string.action_language_english),
            getString(R.string.action_language_chinese)
        )
        val checkedItem = if (currentLang == LANGUAGE_CHINESE) 1 else 0

        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_language_title)
            .setSingleChoiceItems(items, checkedItem) { dialog, which ->
                val newLang = if (which == 0) LANGUAGE_ENGLISH else LANGUAGE_CHINESE
                if (newLang != currentLang) {
                    saveLanguage(newLang)
                    dialog.dismiss()
                    // Recreate activity with new language
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(EXTRA_SKIP_AUTO_OPEN, true)
                    finish()
                    startActivity(intent)
                } else {
                    dialog.dismiss()
                }
            }
            .setNegativeButton(R.string.dialog_negative_cancel, null)
            .show()
    }

    private fun saveLanguage(language: String) {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_LANGUAGE, language)
            .apply()
    }

    private fun applySavedLanguage() {
        val savedLang = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_LANGUAGE, LANGUAGE_ENGLISH) ?: LANGUAGE_ENGLISH
        
        val locale = if (savedLang == LANGUAGE_CHINESE) {
            Locale.CHINESE
        } else {
            Locale.ENGLISH
        }
        
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    companion object {
        private const val EXTRA_OPEN_MESSAGES = "extra_open_messages"
        private const val EXTRA_OPEN_LOGIN = "extra_open_login"
        private const val EXTRA_SKIP_AUTO_OPEN = "extra_skip_auto_open"
        private const val SHORTCUT_ID_PINNED_MESSAGES = "shortcut_messages"
        private const val SHORTCUT_ID_DYNAMIC_LOGIN = "dynamic_login"
        private const val SHORTCUT_ID_DYNAMIC_MESSAGES = "dynamic_messages"
        private const val HOME_URL = "https://x.com/home"
        private const val LOGIN_URL = "https://x.com/i/flow/login"
        private const val MESSAGES_URL = "https://x.com/messages"
        
        // SharedPreferences keys
        private const val PREFS_NAME = "xweb_prefs"
        private const val KEY_LAST_PAGE = "last_page"
        private const val KEY_LANGUAGE = "language"
        
        // Page types
        private const val PAGE_HOME = "home"
        private const val PAGE_MESSAGES = "messages"
        private const val PAGE_LOGIN = "login"
        
        // Language codes
        private const val LANGUAGE_ENGLISH = "en"
        private const val LANGUAGE_CHINESE = "zh"
    }
}
