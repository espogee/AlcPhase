package com.ameyaw_ansu_proj.alc4phase_1

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ameyaw_ansu_proj.alc4phase_1.utils.ConnectivityStateUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_about.*
import org.jetbrains.anko.toast


class AboutActivity : AppCompatActivity() {
    private var webView: WebView? = null
    private var progressBar: ProgressBar? = null
    private var snackBar: Snackbar? = null
    private var builder:AlertDialog.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webView = web_view
        progressBar = progressBar
        progressBar?.visibility = View.GONE
        if (ConnectivityStateUtil.isOnline(this)) {
            andelaAboutPage()
        } else {
            noConnectivityMessage()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun andelaAboutPage() {
        if (!ConnectivityStateUtil.isOnline(this)) {
            noConnectivityMessage()
            return
        }
        webView?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                showProgress()
                val url = request?.url.toString()
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                showProgress(false)
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                builder = AlertDialog.Builder(this@AboutActivity)
                builder?.setTitle("Warning")
                builder?.setMessage(getString(R.string.ssl_error))
                builder?.setPositiveButton("proceed"){ dialog, _ ->
                    handler?.proceed()
                    dialog.dismiss()
                }
                builder?.setNegativeButton("cancel"){ dialog, _ ->
                    handler?.cancel()
                    dialog.dismiss()
                }
                builder?.setCancelable(false)
                builder?.show()
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                toast(error.toString())
            }
        }

        val webSettings = webView?.settings
        webSettings?.javaScriptEnabled = true
        webSettings?.useWideViewPort = true
        webView?.loadUrl(getString(R.string.andela_page_url))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun showProgress(show: Boolean = true) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    private fun noConnectivityMessage(msg: String = getString(R.string.internet_error)) {
        snackBar = Snackbar.make(root, msg, Snackbar.LENGTH_LONG)
        snackBar?.setAction("retry") {
            andelaAboutPage()
        }
        snackBar?.show()
    }
}
