# DELETEリクエストを送信することができる
tags: http-request-test

## URLのみを指定
* URL"/"にDELETEリクエストを送る
* API"mockApi"のURL"/"にDELETEリクエストされた

## ヘッダーを指定
* URL"/"にヘッダー"content-type: application/json"で、DELETEリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、DELETEリクエストされた

## ヘッダー名は大文字小文字を区別しない
* URL"/"にヘッダー"content-type: application/json"で、DELETEリクエストを送る
* API"mockApi"のURL"/"にヘッダー"Content-Type: application/json"で、DELETEリクエストされた

## ヘッダーを複数指定
* URL"/"にヘッダー"content-type: application/json \n options: 1111,2222"で、DELETEリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、DELETEリクエストされた
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222"で、DELETEリクエストされた
* API"mockApi"のURL"/"にヘッダー"content-type: application/json \n options: 1111,2222"で、DELETEリクエストされた
