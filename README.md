# Pentoryall
<img src="https://raw.githubusercontent.com/2024-KDT-JNA/Pentoryall/main/src/main/resources/static/images/common/favicon.png" width="150" alt="Symbol">

## 프로젝트 소개
 <img src="https://raw.githubusercontent.com/2024-KDT-JNA/Pentoryall/main/src/main/resources/static/images/common/logo_pentoryall.png" alt="logo">  

- 펜토리얼(Pentoryall)은 자신의 이야기를 글로 작성하여 공유하고 수익을 낼 수 있는 중개 플랫폼 입니다.
- 모두가 작가이며 독자가 되는 아이디어 공간으로 신청 없이도 작가로 활동할 수 있습니다.
 
## 0. 규칙
- [Git, Github 커밋 가이드](https://github.com/2024-KDT-JNA/Pentoryall/wiki/%EC%BB%A4%EB%B0%8B-%EA%B0%80%EC%9D%B4%EB%93%9C)

## 1. 개발 환경

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

![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=spring-security&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=thymeleaf&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-4479A1?style=flat-square&logo=mybatis&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white)

![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=flat-square&logo=bootstrap&logoColor=white)
![Swiper.js](https://img.shields.io/badge/Swiper.js-6332F6?style=flat-square&logo=swiper&logoColor=white)
![Toast UI Editor](https://img.shields.io/badge/Toast_UI_Editor-7952B3?style=flat-square&logo=toast&logoColor=white)
![PortOne API](https://img.shields.io/badge/PortOne_API-4A154B?style=flat-square&logo=api&logoColor=white)

## 2. 프로젝트 구조

- 각 도메인 별로 하위 패키지 구성
  - `controller`
  - `service` 
  - `mapper`
  - `dto`  

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

<center>
 
## 3. 역할 분담

| **팀원** | | **담당** |
|:---------:|:-------------:|:--------------------------------------------------------|
| **고동환** | [<img src="https://avatars.githubusercontent.com/u/111329365?v=4" height=100 width=100> <br/> @eurdream98](https://github.com/eurdream98) | 시리즈, 포스트, 댓글 |
| **김아현** | [<img src="https://avatars.githubusercontent.com/u/42160693?s=96&v=4" height=100 width=100> <br/> @may54ther](https://github.com/may54ther) | 공통 레이아웃 <br> 결제, 정산  |
| **신승재** | [<img src="https://avatars.githubusercontent.com/u/154950075?s=60&v=4" height=100 width=100>  <br/> @seungjaeshina](https://github.com/seungjaeshina) | 멤버십, 구독 |   
| **오나윤** | [<img src="https://avatars.githubusercontent.com/u/99164178?s=60&v=4" height=100 width=100> <br/> @yunii2222](https://github.com/yunii2222) | 회원, 좋아요 |
| **정가연** | [<img src="https://avatars.githubusercontent.com/u/163974510?v=4" height=100 width=100>  <br/> @9bfish8](https://github.com/9bfish8) | 관리자(회원, 장르, 신고, 유료 포스트, 정산) |

</center>



<!--
## 5. 작업 내역

## 6. 트러블 슈팅

## 7. 개선 목표

## 8. 프로젝트 후기
-->
