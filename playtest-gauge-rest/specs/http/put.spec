# PUTリクエストを送信することができる
tags: http-request-test

## URLのみを指定
* URL"/"にPUTリクエストを送る
* URL"/"にPUTリクエストが送信された
* API"mockApi"のURL"/"にPUTリクエストされた

## ボディを指定
* URL"/"にボディ"{\"test\": \"test\"}"で、PUTリクエストを送る
* URL"/"にボディ"{\"test\": \"test\"}"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にボディ"/verifications/put.json"JSONファイルの内容でPUTリクエストされた

## ヘッダーを指定
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、PUTリクエストされた

## ヘッダーのフィールド名は大文字小文字を区別しない
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にヘッダー"Content-Type: application/json"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にヘッダー"Content-Type: application/json"で、PUTリクエストされた

## ヘッダーを複数指定
* URL"/"にヘッダー"content-type: application/json \n options: 1111,2222"で、PUTリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にヘッダー"content-type: application/json "で、PUTリクエストされた
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222 "で、PUTリクエストされた
* API"mockApi"のURL"/"にヘッダー"content-type: application/json \n options: 1111,2222 "で、PUTリクエストされた

## ボディとヘッダーを指定
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、PUTリクエストを送る
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、PUTリクエストが送信された
* API"mockApi"のURL"/"にボディ"/verifications/putWithHeader.json"JSONファイルの内容、ヘッダー"content-type: application/json"で、PUTリクエストされた
