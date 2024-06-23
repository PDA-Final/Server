## S3 File Utils

- File UL
- Multiple File UL
- File delete
- Multiple file delete by URL

### Exceptions

File UL/삭제 중 에러 발생 시 관련 Transaction 처리를 위해 몇몇 메소드는 Exception을 throw 합니다. 각 method에 주석을 간략히 작성 해 두었으니 참고 바랍니다.

### 사용

사용 할 모듈의 `build.gradle`에 아래와 같이 dependency를 추가합니다.

```
project(":applications:board-application") {
    ...
    dependencies {
        ...
        
        implementation project(':utils:s3-utils')
    }
    ...
}
```

`utils`의 `s3-utils` 하위 `src/main/resources`에 `application-keys.properties`를 추가합니다.
```
s3.accessKey: s3 bucket access key
s3.secretKey: s3 bucket secret access key
s3.bucket: s3 bucket 이름
```

사용하고자 하는 코드에 `S3Service`를 추가하여 사용합니다

```java
@RequiredArgsConstructor
public class BoardService {
    private final S3Service s3Service;
}
```

또는 

```java
public class BoardService {
    @Autowired
    private S3Service s3Service;
}
```

`s3Service.upload()` 등 필요한 method를 호출하여 사용합니다.

- `upload()` 시 `dirName`은 S3 bucket에 생성될 디렉토리 이름입니다. `image(img)`, `assets` 등 같은 자원을 나타내는 이름이나 `board`, `user`, `profile` 등 도메인을 나타내는 이름을 사용해주세요 

### 유의 사항

- `upload()`는 `List<MultipartFile>` 을 지원하지 않습니다. `MultipartFile[]` 을 사용해 주세요
- `delete`는 `String[]`을 지원하지 않습니다. `List<String>`을 사용해 주세요
- 각 method 별 `throws` 와 주석을 통해 발생하는 `exception`을 나열하고 호출하는 method에서 exception handling을 수행하도록 작성 하였습니다. 귀찮더라도 `try catch` 를 통해 transaction 관리 부탁드립니다.
- `MultipartFile`을 `File`로 변환하여 업로드를 수행합니다. `AmazonS3`에서 `MultipartFile`을 통한 업로드를 지원하므로 추후 변경을 고려해야 합니다.