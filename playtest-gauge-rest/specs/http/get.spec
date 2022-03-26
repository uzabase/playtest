# GETリクエストを送信することができる
tags: http-request-test

## URLのみを指定
* URL"/"にGETリクエストを送る
* API"mockApi"のURL"/"にGETリクエストされた

## ヘッダーを指定してGETリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、GETリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、GETリクエストされた

## ヘッダーを複数指定してGETリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、GETリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、GETリクエストされた
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222"で、GETリクエストされた
* API"mockApi"のURL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222"で、GETリクエストされた
// 不要なヘッダーを付け加えたときには失敗する
API"mockApi"のURL"/"にヘッダー"content-type: application/json \r\n options: 1111,2222 \r\n x-unnecessary: hoge"で、GETリクエストされた