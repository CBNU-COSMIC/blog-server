## 📚 프로젝트 소개
> 충북대학교 컴퓨터공학과 동아리 COSMIC을 위한 블로그 프로젝트입니다.

24년 2학기 개신프론티어 교과목 프로젝트입니다. <br/>
마일스톤 기반의 협업, FastAPI 학습, 지속적인 데이터 수집에 대해 집중했습니다.

<br>

## 🔖 기능


<p align="center">
  <img width="80%" alt="스크린샷 2024-12-04 오후 2 01 23" src="https://github.com/user-attachments/assets/7cf363a2-8aa6-4c89-98ca-a5fc2b7cb72a">
</p>

<br>

### 동아리 소개 및 커뮤니티
> 사용자는 게시글을 작성할 수 있다.
- 동아리 소개와 게시글을 작성하고 확인할 수 있습니다.
- 게시글은 동아리 인원만 확인할 수 있고 안에는 댓글이 있습니다.

<br>

### 동아리 일정 확인
> 사용자는 동아리 방을 예약할 수 있다.
- 동아리 방 및 일정을 등록할 수 있습니다.
- UI의 한계로 하루 최대 3개의 일정만 등록할 수 있습니다.

<br>

### 학교 내 공지사항 모아보기
- 학교, 학과, SW사업단 공지를 모두 볼 수 있습니다.
- 이를 위해 주기적으로 크롤링을 하여 데이터를 수집합니다.
<br>


## 🏛️ 아키텍처

### 서버 아키텍처
<p align="center">
  <img width="60%" src="https://github.com/user-attachments/assets/05c6caf7-717d-4285-b55a-db956f5487d4">
</p>

<br>

### 기술 스택
**Language** | Python 3.9

**Framework** | FastAPI 0.115.5, SQLAlchemy 2.0.36

**Database** | MySQL 8.0.35

**Deploy** | Docker, Docker Compose

<br><br>

## 💁🏻‍♂️ 협업 전략

### 문화
<p align="center">
<img width="70%" alt="스크린샷 2024-12-04 오후 2 35 24" src="https://github.com/user-attachments/assets/3d5d7569-0523-4327-b882-da5754d3d8e6">
</p>

- 코드 리뷰: Merge 전 팀장의 코드 리뷰와 수정을 거쳐야 합니다.
- 페어 프로그래밍: 지속적으로 팀장과 팀원이 함께 페어 프로그래밍을 진행합니다.
- 스터디: 매주 금요일 16시 ~ 20시는 Python과 FastAPI를 함께 학습합니다. 

<br>

### 마일스톤 및 스토리 기반의 태스크 분할

<p align="center">
  <img width="45%" alt="스크린샷 2024-12-04 오후 2 36 38" src="https://github.com/user-attachments/assets/70c20ebb-d0fa-4323-b1cf-1c6bf0a60295">
  <img width="45%" alt="스크린샷 2024-12-04 오후 2 37 04" src="https://github.com/user-attachments/assets/c993166f-9b0a-4bcc-be3a-be8626b46bb5">
</p>

- **WHY? (적용한 이유)**
  - 함께 협업하는 인원이 4명이여서 스토리와 태스크를 나누었습니다.
  - 이러한 방식을 하다보니 전체적인 달성률을 보기 힘들어 마일스톤을 도입했습니다.
- **HOW? (적용 방법)**
  - 프로젝트 요구사항을 세밀하게 작성하고 태스크로 변경했습니다.
  - 각 태스크가 3개 이상의 메서드에 관여할 경우 나눕니다.

<br><br>

## 🤔 기술적 고민

### 🔐 로그인 인증을 위한 방식

- 

<details>
<summary>자세히보기</summary>

**문제 상황**

**해결 방안**

**아쉬운 점**

</details>

<br>

<br><br>

## 😊 프로젝트 팀원
| 오민석 (팀장) | 송영은 | 신소희 | 오현지 | 정선웅 | 
|:---:| :---: | :---: | :---: | :---: |
|<a href="https://github.com/minseok-oh"><img src="https://avatars.githubusercontent.com/u/68336833?v=4" width="90px" height="90px"/></a> |<a href="https://github.com/songyeongeun"><img src="https://avatars.githubusercontent.com/u/107869024?v=4" width="90px" height="90px"/></a>| <a href="https://github.com/sinsohi"><img src="https://avatars.githubusercontent.com/u/136775478?v=4" width="90px" height="90px"/></a>|<a href="https://github.com/Hyunjiiing"><img src="https://avatars.githubusercontent.com/u/107828202?v=4" width="90px" height="90px"/></a> | <a href="https://github.com/jeongseonwoong"><img src="https://avatars.githubusercontent.com/u/84301208?v=4" width="90px" height="90px"/></a>|
