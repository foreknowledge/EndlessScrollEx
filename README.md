# EndlessScrollEx

Paging 라이브러리를 이용해 무한 스크롤을 구현한 미니 프로젝트입니다.

### 시작하기

프로젝트 루트 디렉토리에 `local.properties` 파일에 아래의 한 줄을 추가합니다.

해당 프로젝트는 TMDB API를 이용하기 때문에 TMDB API Key가 필요합니다. API Key는 [TMDB Introduction](https://developers.themoviedb.org/3/getting-started/introduction)에서 가이드를 참고해서 발급 받을 수 있습니다.

```bash
API_KEY="발급 받은 API key"
```

### 라이브러리

- AAC
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging)
  - [LifeCycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding)
- [Glide](https://github.com/bumptech/glide)
- [OkHttp](https://square.github.io/okhttp)
- [Retrofit](https://square.github.io/retrofit) 
