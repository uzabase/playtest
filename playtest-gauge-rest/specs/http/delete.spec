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

## URL を正規表現でアサーションできる
* URL"/test/212952b3-7333-4c0b-9e55-3be465c59a99"にDELETEリクエストを送る
* API"mockApi"の正規表現で全体マッチするURL"/test/([0-9a-f]{8})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{12})"にDELETEリクエストされた
