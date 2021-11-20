# HTTP DELETE リクエストを送信するステップのテスト

tags: http-request-test

## DELETEリクエストを送信できる
* URL"/"にDELETEリクエストを送る
* URL"/"にDELETEリクエストが送信された

## DELETEリクエストに対するレスポンスをアサートできる
* URL"/"にDELETEリクエストを送る
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* JSON形式のレスポンスボディが<file:expected/put-response.json>と一致する

## ヘッダーを指定してDELETEリクエストを送信できる
* URL"/"にヘッダー"content-type: application/json"で、DELETEリクエストを送る
* URL"/"にヘッダー"content-type: application/json"で、DELETEリクエストが送信された
