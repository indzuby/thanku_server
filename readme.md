# Thanks Server

## Packages

### controller, service, repository
말그대로

### form
컨트롤러가 데이터를 받는 실제 데이터의 구조체

### model
릴레이션에 매핑될 객체 정보

### configure
설정을 위한 정보


## API
### 사용자
#### 사용자 회원가입
/api/user(사용자), /api/rider(배달원)
header:
```
Content-type  application/json; charset=utf-8
Authorization Basic dGhhbmtzQ2xpZW50SWQ6dGhhbmtzU2VjcmV0
```

param :
``` json
{
    "email": "ksmail13@gmail.com",
    "name": "김민규",
    "phone": "010-6416-2037",
    "password": "12345"
}
```

response
```json
{
    "id": 1,
    "createTime": null,
    "updatedTime": null,
    "phone": "010-6416-2037",
    "password": "$2a$10$WE55l3YiOwRCVvJi.FsPLeM05NO6zgtT/73xrqjN30/28LmMuLmTa",
    "name": "김민규",
    "email": "ksmail13@gmail.com",
    "socialId": null,
    "socialAccessToken": null,
    "profilePath": "/profile/default.png",
    "type": "USER",
    "signUpType": "EMAIL",
    "quickList": []
}
```

#### social signup
```
/api/user/social
```
header:
```
Content-type  application/json; charset=utf-8
Authorization Basic dGhhbmtzQ2xpZW50SWQ6dGhhbmtzU2VjcmV0
```

param :
```json
{
    "token": "tokenstring",
    "name": "김민규",  // optional
    "type": "facebook",
    "email": "ksmail12@gmail.com" // optional
}
```

response :

```json
{
  "id": 3,
  "createTime": null,
  "updatedTime": null,
  "phone": null,
  "password": null,
  "name": "김민규",
  "email": "ksmail12@gmail.com",
  "socialId": null,
  "socialAccessToken": null,
  "profilePath": "/profile/default.png",
  "type": null,
  "signUpType": "FACEBOOK",
  "quickList": []
}
```

#### login (get a AccessToken)
/auth/token
header :
```
Content-Type application/x-www-form-urlencoded
Authorization Basic dGhhbmtzQ2xpZW50SWQ6dGhhbmtzU2VjcmV0
```

param :
```
scope=read write delete&client_secret=thanksSecret&username=ksmail13@gmail.com&client_id=thanksClientId&password=12345&grant_type=password
```

response :
```
{"access_token":"b422a7d7-07db-40ad-b24b-5faf9495e079","token_type":"bearer","refresh_token":"5ebeae0c-5220-4782-b50b-119b9a3a54a4","scope":"delete read write"}
```
