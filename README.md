# Pentoryall

 <img src="https://raw.githubusercontent.com/2024-KDT-JNA/Pentoryall/main/src/main/resources/static/images/common/favicon.png" width="150" alt="favicon">

## 프로젝트 소개

<img src="https://github.com/2024-KDT-JNA/Pentoryall/assets/42160693/ccc80f4c-5b6b-4508-8288-c29cde57d261" width="200" alt="logo_pentoryall_w">
<br/><br/>

-   펜토리얼(Pentoryall)은 자신의 이야기를 글로 작성하여 공유하고 수익을 낼 수 있는 중개 플랫폼 입니다.
-   모두가 작가이며 독자가 되는 아이디어 공간으로 신청 없이도 작가로 활동할 수 있습니다.

## 규칙

-   [Git, Github 커밋 가이드](https://github.com/2024-KDT-JNA/Pentoryall/wiki/%EC%BB%A4%EB%B0%8B-%EA%B0%80%EC%9D%B4%EB%93%9C)

## 개발 환경

![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat-square&logo=intellij-idea&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=flat-square&logo=notion&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-5865F2?style=flat-square&logo=discord&logoColor=white)
![Figma](https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=figma&logoColor=white)

![HTML](https://img.shields.io/badge/HTML-E34F26?style=flat-square&logo=html5&logoColor=white)
![CSS](https://img.shields.io/badge/CSS-1572B6?style=flat-square&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black)
![jQuery](https://img.shields.io/badge/jQuery-0769AD?style=flat-square&logo=jquery&logoColor=white)

![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=spring-security&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=thymeleaf&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-4479A1?style=flat-square&logo=mybatis&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white)

![Bootstrap Icons](https://img.shields.io/badge/bootstrap-icona?logo=Bootstrap&logoColor=white&label=bootstrap-icons%401.11.3&labelColor=712cf9&color=%23666)
![Swiper.js](https://img.shields.io/badge/Swiper.js-6332F6?style=flat-square&logo=swiper&logoColor=white)
![Toast UI Editor](https://img.shields.io/badge/Toast_UI_Editor-7952B3?style=flat-square&logo=toast&logoColor=white)
![PortOne API](https://img.shields.io/badge/PortOne_API-f97316?style=flat-square&logo=api&logoColor=white)

## 프로젝트 구조

각 도메인 별로 `controller`, `service`, `mapper`, `dto` 등의 하위 패키지로 구성

<details>
  <summary>java</summary>
 
```
com
└─pentoryall
    ├─admin
    ├─comment
    ├─common
    │  ├─exception
    │  └─page
    ├─config
    ├─email
    ├─genre
    ├─genreOfArt
    ├─membership
    ├─point
    ├─portone
    ├─post
    ├─series
    ├─settlement
    ├─story
    ├─subscribe
    └─user
```

</details>

<details>
  <summary>resources</summary>
 
```
resources
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
```

</details>

## 주요 기능

-   `Spring Security`를 활용한 로그인, 회원가입
-   회원가입 시 메일 인증을 위해 인증 키 전송
-   회원 별 스토리 페이지
-   장르 별 베스트 게시글
-   시리즈 별 포스트 등록
-   포스트 작성 시 `Toast-ui-editor` 연동
-   회원 및 시리즈, 포스트 썸네일 등록
-   포스트 발행 옵션 (공개여부, 유료여부 등)
-   댓글과 답 댓글 작성
-   포스트 및 댓글 신고
-   `PotOne API`를 활용한 포인트 충전 및 내역 조회
-   정산 요청을 위한 계좌 정보 등록 및 검증 (`PotOne API`)
-   포인트 거래를 통해 수익 적립
-   수익 정산 기능
-   회원 구독 기능
-   멤버십 개설 및 가입
-   포스트 좋아요 기능
-   회원, 정산, 신고, 유료 포스트 수정 요청 등에 대한 관리자 페이지 제공

## 개선 목표

-   검색 옵션을 다양하게 만들어 사용자들이 여러 검색 기능을 사용할 수 있도록 하기
-   포인트 거래 시 발생하는 수익, 정산을 스케쥴러와 배치를 이용해 관리
-   멤버십 가입 시 스케쥴러를 통해 매달 가입 연장 처리
-   구독한 회원의 최신 포스트 목록 조회
-   AWS S3 연동하여 파일 저장 구현
-   웹 소켓을 이용하여 알림 기능 구현

## 역할 분담

|  **팀원**  |                                                                                                                                                      | **담당**                                          |
| :--------: | :--------------------------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------ |
| **고동환** |      [<img src="https://avatars.githubusercontent.com/u/111329365?v=4" height=100 width=100> <br/> @eurdream98](https://github.com/eurdream98)       | 시리즈, 포스트, 댓글                              |
| **김아현** |     [<img src="https://avatars.githubusercontent.com/u/42160693?s=96&v=4" height=100 width=100> <br/> @may54ther](https://github.com/may54ther)      | 공통 레이아웃(기본, 스토리, 설정) <br> 결제, 정산 |
| **신승재** | [<img src="https://avatars.githubusercontent.com/u/154950075?s=60&v=4" height=100 width=100> <br/> @seungjaeshina](https://github.com/seungjaeshina) | 멤버십, 구독                                      |
| **오나윤** |     [<img src="https://avatars.githubusercontent.com/u/99164178?s=60&v=4" height=100 width=100> <br/> @yunii2222](https://github.com/yunii2222)      | 회원, 좋아요                                      |
| **정가연** |         [<img src="https://avatars.githubusercontent.com/u/163974510?v=4" height=100 width=100> <br/> @9bfish8](https://github.com/9bfish8)          | 관리자(회원, 장르, 신고, 유료 포스트, 정산)       |

<!--
## 트러블 슈팅
## 8. 프로젝트 후기
-->
