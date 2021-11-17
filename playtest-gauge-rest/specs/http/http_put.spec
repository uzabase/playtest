# HTTP PUT リクエストを送信するステップのテスト

tags: http-request-test

## PUTリクエストを送信できる
* URL"/"にPUTリクエストを送る
* URL"/"にPUTリクエストが送信された

## PUTリクエストに対するレスポンスをアサートできる
* URL"/"にPUTリクエストを送る
* HTTPレスポンスステータスコードが"200"である
* レスポンスヘッダーに"x-example-header"が存在し、その値が"example1"である
* JSON形式のレスポンスボディが<file:expected/put-response.json>と一致する

## PUTにBodyを指定してリクエストを送信できる
* URL"/"に、Body"{\"test\": \"test\"}"でPUTリクエストを送る
* URL"/"に、Body"{\"test\": \"test\"}"でPUTリクエストが送信された