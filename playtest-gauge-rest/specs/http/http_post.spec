# HTTP POST リクエストを送信するステップのテスト

tags: http-request-test

## POSTリクエストを送信できる
* URL"/"にPOSTリクエストを送る
* URL"/"にPOSTリクエストが送信された

## POSTリクエストに対するレスポンスをアサートできる
* URL"/"にPOSTリクエストを送る
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* JSON形式のレスポンスボディが<file:expected/post-response.json>と一致する

## POSTにBodyを指定してリクエストを送信できる
* URL"/"に、Body"{\"test\": \"post\"}"でPOSTリクエストを送る
* URL"/"に、Body"{\"test\": \"post\"}"でPOSTリクエストが送信された