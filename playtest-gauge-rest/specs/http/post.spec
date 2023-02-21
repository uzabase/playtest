# POSTリクエストを送信することができる
tags: http-request-test

## URLのみを指定
* URL"/"にPOSTリクエストを送る
* URL"/"にPOSTリクエストが送信された

## ヘッダーを指定
* URL"/"にヘッダー"content-type: application/json"で、POSTリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、POSTリクエストされた

## ヘッダーのフィールド名は大文字小文字を区別しない
* URL"/"にヘッダー"content-type: application/json"で、POSTリクエストを送る
* API"mockApi"のURL"/"にヘッダー"Content-Type: application/json"で、POSTリクエストされた

## ヘッダーを複数指定
* URL"/"にヘッダー"content-type: application/json \n options: 1111,2222"で、POSTリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、POSTリクエストされた
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222"で、POSTリクエストされた
* API"mockApi"のURL"/"にヘッダー"content-type: application/json \n options: 1111,2222"で、POSTリクエストされた

## ボディとヘッダーを指定
* URL"/"にボディ"{\"test\": \"test\"}"、ヘッダー"content-type: application/json"で、POSTリクエストを送る
* API"mockApi"のURL"/"にボディ"/verifications/postWithHeader.json"JSONファイルの内容、ヘッダー"content-type: application/json"で、POSTリクエストされた

## URL を正規表現でアサーションできる
* URL"/test/212952b3-7333-4c0b-9e55-3be465c59a99"にPOSTリクエストを送る
* API"mockApi"の正規表現で全体マッチするURL"/test/([0-9a-f]{8})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{12})"にPOSTリクエストされた
