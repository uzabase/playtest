# Mockに対するDELETEリクエストをアサートできる
tags: http-request-test

## リクエスト
* URL"/"にDELETEリクエストを送る
* API"mockApi"のURL"/"に"1"回DELETEリクエストされた

## リクエストされた(言い換え)
* URL"/"にDELETEリクエストを送る
* API"mockApi"のURL"/"にDELETEリクエストされた

## リクエストされていない
* API"mockApi"のURL"/"にDELETEリクエストされていない

## ヘッダー
* URL"/"にヘッダー"options: 1111,2222"で、DELETEリクエストを送る
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222"で、DELETEリクエストされた
