package kr.lul.android.ui.navigation.navigator

import android.net.Uri
import androidx.navigation.NavBackStackEntry

/**
 * 어떤 화면([NavBackStackEntry])에서 다른 화면으로 이동할 방법을 제공한다.
 *
 * @see androidx.navigation.NavBackStackEntry
 */
interface Navigator {
    /**
     * 현재 화면의 목적지.
     */
    val destination: Destination

    /**
     * 이전 화면으로 돌아가기.
     */
    fun back()

    /**
     * "앱 정보" 화면으로 이동.
     */
    fun settings()

    /**
     * 브라우저로 페이지를 연다.
     *
     * @param url 웹 페이지 URL.
     */
    fun web(url: String) {
        web(Uri.parse(url))
    }

    /**
     * 브라우저로 페이지를 연다.
     *
     * @param uri 웹 페이지 URI.
     */
    fun web(uri: Uri)

    /**
     * 전화 걸기.
     *
     * @param phoneNumber 전화번호.
     */
    fun call(phoneNumber: String) {
        call(Uri.parse("tel:$phoneNumber"))
    }

    /**
     * 전화 걸기.
     *
     * @param phoneNumber 전화번호 URI.
     */
    fun call(phoneNumber: Uri)
}
