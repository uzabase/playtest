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

## URL を正規表現でアサーションできる
* URL"/test/212952b3-7333-4c0b-9e55-3be465c59a99"にPUTリクエストを送る
* API"mockApi"の正規表現で全体マッチするURL"/test/([0-9a-f]{8})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{12})"にPUTリクエストされた
