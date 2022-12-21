# GETリクエストを送信することができる
tags: http-request-test

## URLのみを指定
* URL"/"にGETリクエストを送る
* API"mockApi"のURL"/"に"1"回GETリクエストされた

## ヘッダーを指定してGETリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、GETリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、GETリクエストされた

## ヘッダーのフィールド名は大文字小文字を区別しない
* URL"/"にヘッダー"content-type: application/json"で、GETリクエストを送る
* API"mockApi"のURL"/"にヘッダー"Content-Type: application/json"で、GETリクエストされた

## ヘッダーを複数指定してGETリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json \n options: 1111,2222"で、GETリクエストを送る
* API"mockApi"のURL"/"にヘッダー"content-type: application/json"で、GETリクエストされた
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222"で、GETリクエストされた
* API"mockApi"のURL"/"にヘッダー"content-type: application/json \n options: 1111,2222"で、GETリクエストされた

## クエリパラメータ付きURLとしてアサーションできる
* URL"/?name=value"にGETリクエストを送る
* API"mockApi"のURL"/?name=value"に"1"回GETリクエストされた

## クエリパラメータを個別でアサーションできる
* URL"/?name=value"にGETリクエストを送る
* API"mockApi"のURLパス"/"にクエリパラメータ"name"が"value"でGETリクエストされた

## 複数のクエリパラメータを個別でアサーションできる (順不同)
* URL"/?name1=value1&name2=value2"にGETリクエストを送る
* API"mockApi"のURLパス"/"にクエリパラメータ"name2"が"value2"でGETリクエストされた
* API"mockApi"のURLパス"/"にクエリパラメータ"name1"が"value1"でGETリクエストされた

## 複数のクエリパラメータを個別でアサーションできる (同一のクエリパラメータ名を指定)
* URL"/?name1=value1-1&name2=value2-1&name1=value1-2&name3=value3-1&name3=value3-2"にGETリクエストを送る
* API"mockApi"のURLパス"/"にクエリパラメータ"name1"が"value1-2"でGETリクエストされた
* API"mockApi"のURLパス"/"にクエリパラメータ"name1"が"value1-1"でGETリクエストされた
* API"mockApi"のURLパス"/"にクエリパラメータ"name2"が"value2-1"でGETリクエストされた
* API"mockApi"のURLパス"/"にクエリパラメータ"name3"が"value3-1"でGETリクエストされた
* API"mockApi"のURLパス"/"にクエリパラメータ"name3"が"value3-2"でGETリクエストされた
