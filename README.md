# Pentoryall

## 프로젝트 소개
- 작가 중개 플랫폼
- **all of pen story**
  - 모두가 작가이며 독자가 되는 아이디어 공간
  - 펜토리얼은 자신의 아이디어를 판매를 합니다.

## 팀원 구성

<div align="center">

| **고동환** |    **김아현**    |  **신승재**  |  **오나윤**   | **정가연** |
|:---------------------------------------------:|:---------------------------------------------:|:---------------------------------------------:|:---------------------------------------------:|:---------------------------------------------:|
| [<img src="https://avatars.githubusercontent.com/u/111329365?v=4" height=150 width=150> <br/> @eurdream98](https://github.com/eurdream98) |[<img src="https://avatars.githubusercontent.com/u/42160693?s=96&v=4" height=150 width=150> <br/> @may54ther](https://github.com/may54ther) | [<img src="https://avatars.githubusercontent.com/u/154950075?s=60&v=4" height=150 width=150> <br/> @seungjaeshina](https://github.com/seungjaeshina) | [<img src="https://avatars.githubusercontent.com/u/99164178?s=60&v=4" height=150 width=150> <br/> @yunii2222](https://github.com/yunii2222) |[<img src="https://avatars.githubusercontent.com/u/163974510?v=4" height=150 width=150> <br/> @#59bfish8](https://github.com/#59bfish8) |

</div>

## 0. 규칙

## 1. 개발 환경
- **FE** HTML, CSS, JavaScript, jQuery
- **BE** Java 17, Spring Boot, Spring Security, Thymeleaf, MyBatis
- **DB** MySQL, Redis
- **결제모듈** PortOne API
- **UI** Swiper.js, Toast UI Editor
- **관리** Git, Github
- **협업** Discord, Notion
- **디자인** Figma

## 2. 프로젝트 구조

 
```
main
├─java
│  └─com
│      └─pentoryall
│          ├─admin
│          ├─comment
│          ├─common
│          │  ├─exception
│          │  └─page
│          ├─config
│          ├─email
│          ├─genre
│          ├─genreOfArt
│          ├─membership
│          ├─point
│          ├─portone
│          ├─post
│          ├─series
│          ├─settlement
│          ├─story
│          ├─subscribe
│          └─user
└─resources
    ├─mappers
    ├─messages
    ├─static
    │  ├─css
    │  ├─images
    │  ├─js
    │  └─libs
    └─templates
        ├─error
        ├─fragments
        │  ├─common
        │  ├─post
        │  ├─settings
        │  └─story
        ├─layout
        └─views
            ├─admin
            ├─common
            ├─email
            ├─membership
            ├─point
            ├─post
            ├─series
            ├─settlement
            ├─story
            ├─subscribe
            └─user
                └─settlement
```
- 각 도메인 별로 하위 패키지 구성
  - `controller`
  - `service` 
  - `mapper`
  - `dto`  
<!-- 
## 3. 역할 분담

### 고동환

### 김아현

### 신승재

### 오나윤

### 정가연

## 4. 역할 분담

## 5. 작업 내역

## 6. 트러블 슈팅

## 7. 개선 목표

## 8. 프로젝트 후기

### 고동환

### 김아현

### 신승재

### 오나윤

### 정가연 -->
