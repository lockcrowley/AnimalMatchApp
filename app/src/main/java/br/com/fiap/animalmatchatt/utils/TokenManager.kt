package br.com.fiap.animalmatchatt.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import org.json.JSONObject
import java.util.Date

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun saveAccessToken(token: String?, user: String?) {
        val editor = prefs.edit()
        editor.putString("access_token", token)
        editor.putString("user", user)
        editor.apply()
    }

    fun getAccessToken(): String? {
        val token = prefs.getString("access_token", null)
        if (token != null) {
            if (isTokenExpired(token)) {
                clearUser()
                clearTokens()
                return null
            }
        }
        return token
    }

    fun getUser(): String? {
        return prefs.getString("user", null)
    }

    fun clearUser() {
        val editor = prefs.edit()
        editor.remove("user")
        editor.apply()
    }

    fun clearTokens() {
        val editor = prefs.edit()
        editor.remove("access_token")
        editor.remove("user")
        editor.apply()
    }

    fun isTokenExpired(token: String): Boolean {
        return try {
            val parts = token.split(".")
            if (parts.size != 3) {
                true
            } else {
                val payload = String(Base64.decode(parts[1], Base64.DEFAULT))
                val jsonObject = JSONObject(payload)
                val exp = jsonObject.getLong("exp")
                val now = Date().time / 1000
                exp < now
            }
        } catch (e: Exception) {
            true
        }
    }
}
