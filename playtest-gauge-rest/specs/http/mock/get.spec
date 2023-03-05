# Mockに対するGETリクエストをアサートできる
tags: http-request-test

## リクエスト
* URL"/"にGETリクエストを送る
* API"mockApi"のURL"/"に"1"回GETリクエストされた

## リクエスト(言い換え)
* URL"/"にGETリクエストを送る
* API"mockApi"のURL"/"にGETリクエストされた

## リクエストされていない
* API"mockApi"のURL"/"にGETリクエストされていない

## ヘッダー
* URL"/"にヘッダー"options: 1111,2222"で、GETリクエストを送る
* API"mockApi"のURL"/"にヘッダー"options: 1111,2222"で、GETリクエストされた

## 1度リクエストされたこと
* URL"/"にGETリクエストを送る
* API"mockApi"のURL"/"に"1"回GETリクエストされた

## 2度リクエストされたこと
* URL"/"にGETリクエストを送る
* URL"/"にGETリクエストを送る
* API"mockApi"のURL"/"に"2"回GETリクエストされた

## 0度リクエストされたこと
* API"mockApi"のURL"/"に"0"回GETリクエストされた