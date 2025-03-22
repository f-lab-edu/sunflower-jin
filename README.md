# 🌻 Jetpack Compose Sunflower Clone

> **[Sunflower](https://github.com/android/sunflower)** 프로젝트를 Jetpack Compose 기반으로 재구현한 안드로이드 앱입니다.  
MVVM 패턴과 클린 아키텍처를 적용하고, 이미지 로딩에는 **Coil** 라이브러리를 사용합니다.  

---

## ✨ 주요 기능

- 식물 목록 조회
- 식물 상세 정보 보기
- 식물 추가
- Compose 기반의 UI 전면 구성
- Coil을 이용한 이미지 로딩
- MVVM + Clean Architecture 패턴 적용

---

## 🧱 기술 스택

| 계층 | 기술 구성 |
|------|----------|
| UI   | Jetpack Compose, Material 3 |
| 아키텍처 | MVVM, 클린 아키텍처
| 이미지 | Coil |
| 언어 | Kotlin |


---

## 🗂 프로젝트 구조
📦com.jin.sunflower  
- Data
  - Repository  // Domain Repository 인터페이스 구현
  - Local       // LocalDataSource
  - Remote      // API 연동 DataSource
- Domain
  - Entity      // 핵심 비즈니스 Entity 정의
  - Repository  // Repository 인터페이스
  - UseCase     // 비즈니스 로직 단위
- UI
  - Screen      // Jetpack Compose UI
  - ViewModel   // 각 화면에 대한 ViewModel
